package com.hernanbosqued.olx;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hernanbosqued.olx.domain.model.EntitiesModel;
import com.hernanbosqued.olx.domain.model.StatusModel;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class ItemViewHolder extends BaseViewHolder<StatusModel> implements ItemContract.View {
    private final TextView headerTextView;
    private View progressbar;
    private ItemPresenter presenter;
    private TextView statusTextView;
    private ImageView attachedImageView;
    private ImageView avatarImageView;
    private ITEM_TYPE itemType;

    ItemViewHolder(View view, ITEM_TYPE itemType) {
        super(view);
        presenter = new ItemPresenter();

        this.itemType = itemType;
        this.headerTextView = (TextView) view.findViewById(R.id.header);
        this.statusTextView = (TextView) view.findViewById(R.id.status);
        this.avatarImageView = (ImageView) view.findViewById(R.id.avatar);
        this.attachedImageView = (ImageView) view.findViewById(R.id.attached);
    }

    @Override
    public void bind(final StatusModel item) {
        presenter.bind(this, item);
    }

    @Override
    public void initViews() {
        progressbar = itemView.findViewById(R.id.progress);
        attachedImageView.setVisibility(GONE);
        progressbar.setVisibility(GONE);
    }

    @Override
    public void showStatus(String status, int startIndex, int finishIndex, EntitiesModel.EntityModel[]... entities) {
        final List<EmojiParser.UnicodeCandidate> emojis = new ArrayList<>();
        String convertedStatus = EmojiParser.parseFromUnicode(status, new EmojiParser.EmojiTransformer() {
            @Override
            public String transform(EmojiParser.UnicodeCandidate unicodeCandidate) {
                emojis.add(unicodeCandidate);
                return String.valueOf(new char[StringUtils.countMatches(unicodeCandidate.getEmoji().getHtmlHexadecimal(),"&#") + (unicodeCandidate.hasFitzpatrick() ? 1 : 0)]);
            }
        });

        Spannable spannableStatus = new SpannableString(convertedStatus);

        for (EntitiesModel.EntityModel[] item : entities) {
            spannableStatus = spanEntity(spannableStatus, item);
        }

        SpannableStringBuilder sb = new SpannableStringBuilder();
        int index = startIndex, offset = 0;
        for (EmojiParser.UnicodeCandidate emoji : emojis) {
            sb.append(spannableStatus.subSequence(index - offset, emoji.getEmojiStartIndex() - offset));
            sb.append(emoji.getEmoji().getUnicode());

            if (emoji.getEmoji().getUnicode().length() > 1) {
                offset += emoji.getEmoji().getUnicode().length() + (emoji.hasFitzpatrick() ? 1 : 0) - 1;
            }
            index = emoji.getEmojiEndIndex();
        }

        sb.append(spannableStatus.subSequence(index - offset, finishIndex));

        this.statusTextView.setText(sb);
    }


    private Spannable spanEntity(Spannable spannableStatus, EntitiesModel.EntityModel[] entity) {
        for (EntitiesModel.EntityModel item : entity) {
            try {
                spannableStatus.setSpan(new ForegroundColorSpan(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimaryLight)), item.indices[0], item.indices[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStatus.setSpan(new StyleSpan(Typeface.BOLD), item.indices[0], item.indices[1], 0);
            } catch (Exception err) {
                //some indices are set beyond de status length
            }
        }
        return spannableStatus;
    }

    @Override
    public void setAvatar(String url) {
        Glide.with(itemView.getContext())
                .load(url)
                .fitCenter()
                .into(avatarImageView);
    }

    @Override
    public void setAttachedImage(String mediaUrl) {
        progressbar.setVisibility(View.VISIBLE);
        attachedImageView.setVisibility(View.VISIBLE);
        Glide.with(itemView.getContext())
                .load(mediaUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressbar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressbar.setVisibility(GONE);
                        return false;
                    }
                })
                .centerCrop()
                .into(attachedImageView);
    }

    @Override
    public void showHeader(String header) {
        this.headerTextView.setText(header);
    }

    @Override
    public void setBackground() {
        int color;
        switch (itemType) {
            case ROW_ODD:
                color = ContextCompat.getColor(itemView.getContext(), R.color.light_gray);
                break;
            default:
            case ROW_EVEN:
                color = ContextCompat.getColor(itemView.getContext(), R.color.gray);
                break;
        }
        this.itemView.setBackgroundColor(color);
    }
}

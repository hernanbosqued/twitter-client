package com.hernanbosqued.olx;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
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

import java.security.spec.ECField;

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
    public void showStatus(String status, EntitiesModel.EntityModel[]... entities) {
        Spannable spannableEntity = new SpannableString( status);
        for (EntitiesModel.EntityModel[] item : entities) {
            spannableEntity = spanEntity(spannableEntity, item);
        }
        this.statusTextView.setText(spannableEntity);
    }

    private Spannable spanEntity(CharSequence textToSpan, EntitiesModel.EntityModel[] entity) {
        Spannable spannable = new SpannableString(textToSpan);
        for (EntitiesModel.EntityModel entityModel : entity) {
            try {
                spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimaryLight)), entityModel.indices[0], entityModel.indices[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(new StyleSpan(Typeface.BOLD), entityModel.indices[0], entityModel.indices[1], 0);
            }catch(Exception err){
                //some indices are set beyond de status length
            }
        }
        return spannable;
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

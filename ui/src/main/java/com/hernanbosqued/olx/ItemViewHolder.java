package com.hernanbosqued.olx;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
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
import com.hernanbosqued.olx.domain.TwitterModel;

import java.util.List;

import static android.view.View.GONE;

public class ItemViewHolder extends BaseViewHolder<TwitterModel.StatusModel> {
    private final View progressbar;
    private final ItemPresenter presenter;
    private TextView textView;
    private ImageView attachedImageView;
    private ImageView avatarImageView;

    ItemViewHolder(View view, ItemsAdapter.ITEM_TYPE itemType) {
        super(view);
        int color;
        presenter = new ItemPresenter();

        this.textView = (TextView) view.findViewById(R.id.text);
        this.avatarImageView = (ImageView) view.findViewById(R.id.avatar);
        this.attachedImageView = (ImageView) view.findViewById(R.id.attached);


        progressbar = view.findViewById(R.id.progress);
        attachedImageView.setVisibility(GONE);
        progressbar.setVisibility(GONE);

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


    @Override
    public void bind(final TwitterModel.StatusModel item) {
        Spannable spannableScreenName = spanScreenName("@" + item.user.screenName + ": ");
        Spannable spannableEntity = spanEntity(item.text, item.entities.hashtags);
        spannableEntity = spanEntity(spannableEntity, item.entities.urls);
        spannableEntity = spanEntity(spannableEntity, item.entities.userMentions);
        this.textView.setText(TextUtils.concat(spannableScreenName, spannableEntity.subSequence(item.displayTextRange[0], item.displayTextRange[1])));

        setAvatar(item.user.profileImageUrl);
        setAttached(item.extendedEntities);
    }

    private void setAttached(TwitterModel.StatusModel.ExtendedEntitiesModel mediaEntityModel) {
        if( mediaEntityModel != null && mediaEntityModel.media != null )
        {
            for(TwitterModel.StatusModel.ExtendedEntitiesModel.MediaEntityModel item : mediaEntityModel.media)
            {
                if( item.type.contentEquals("photo") )
                {
                    progressbar.setVisibility(View.VISIBLE);
                    attachedImageView.setVisibility(View.VISIBLE);
                    Glide.with(itemView.getContext())
                            .load(item.mediaUrl)
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
                    return;
                }
            }
        }
        attachedImageView.setVisibility(View.GONE);
    }

    private void setAvatar(String url) {
        Glide.with(itemView.getContext())
                .load(url)
                .fitCenter()
                .into(avatarImageView);
    }

    private Spannable spanScreenName(CharSequence textToSpan) {
        Spannable spannable = new SpannableString(textToSpan);
        spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent)), 0, textToSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD), 0, textToSpan.length(), 0);
        return spannable;
    }

    private Spannable spanEntity(CharSequence textToSpan, List<TwitterModel.StatusModel.EntitiesModel.EntityModel> entity) {
        Spannable spannable = new SpannableString(textToSpan);
        for (TwitterModel.StatusModel.EntitiesModel.EntityModel entityModel : entity) {
            spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimaryLight)), entityModel.indices[0], entityModel.indices[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new StyleSpan(Typeface.BOLD), entityModel.indices[0], entityModel.indices[1], 0);
        }
        return spannable;

    }

    private class ItemPresenter extends BasePresenter<TwitterModel.StatusModel, ItemContract.View>{

        @Override
        public void bindView(@NonNull ItemContract.View view) {
            super.bindView(view);
        }

        @Override
        protected void updateView() {

        }
    }

}

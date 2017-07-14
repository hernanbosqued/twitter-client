package com.hernanbosqued.olx;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.hernanbosqued.olx.domain.TwitterModel;

import java.util.List;

public class ItemViewHolder extends BaseViewHolder<TwitterModel.StatusModel> {
    private TextView textView;
    private TwitterModel.StatusModel item;

    ItemViewHolder(View view, ItemsAdapter.ITEM_TYPE itemType) {
        super(view);
        int color;
        this.textView = (TextView) view.findViewById(R.id.title);
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
        this.item = item;
        Spannable text1 = spanScreenName("@" + this.item.user.screenName + ": ");
        Spannable text2 = spanEntity(this.item.text, this.item.entities.hashtags);
                  text2 = spanEntity(text2, this.item.entities.urls);
                  text2 = spanEntity(text2, this.item.entities.userMentions);
        this.textView.setText(TextUtils.concat(text1, text2.subSequence(item.displayTextRange[0],item.displayTextRange[1])));
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
}

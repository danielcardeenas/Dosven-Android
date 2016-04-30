package io.leind.dosven.Profile.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.Home.activity.BaseDrawerActivity;
import io.leind.dosven.R;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

public class ProfileActivity extends BaseDrawerActivity {
    @Bind(R.id.profile_image) ImageView profileImage;
    @Bind(R.id.appbar)
    AppBarLayout appBar;
    @Bind(R.id.profile_properties1) LinearLayout profileProperties1;
    @Bind(R.id.profile_properties2) LinearLayout profileProperties2;
    @Bind(R.id.profile_progress) ProgressBar progressBar;
    @Bind(R.id.profile_eff) TextView efficiency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setTitle("");

        float heightDp = getResources().getDisplayMetrics().heightPixels / 3;
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)appBar.getLayoutParams();
        layoutParams.height = (int)heightDp * 2;

        LinearLayout.LayoutParams frameParams1 = (LinearLayout.LayoutParams)profileProperties1.getLayoutParams();
        frameParams1.height = (int)heightDp / 2;

        LinearLayout.LayoutParams frameParams2 = (LinearLayout.LayoutParams)profileProperties2.getLayoutParams();
        frameParams2.height = (int)heightDp / 2;

        Spannable wordtoSpan = new SpannableString("92/100");
        wordtoSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorAccentDark)), 2, 6,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        efficiency.setText(wordtoSpan);

        progressBar.getProgressDrawable().setColorFilter(
                Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);

        setUpProfileItems();
    }

    private void setUpProfileItems() {
        Glide.with(this)
                .load("https://scontent-mia1-1.xx.fbcdn.net/hphotos-xfa1/v/t1.0-9/1546343_10202027354337682_1784165829_n.jpg?_nc_eui=ARhd6UmVXfcZFVIO7CjU7UzWay2m1vH7HDPhzl3e_i42Cix2oU14_woeGOMO&oh=cf1160bb99b1be9a96863c4cd1b0f2fb&oe=57859003")
                .centerCrop()
                .bitmapTransform(new ColorFilterTransformation(this, Color.argb(100, 0, 0, 0)))
                .into(profileImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }
}

package io.leind.dosven.Feed.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;

import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;
import io.leind.dosven.UIViews.Transforms.ResizeAnimation;

public class FeedActivity extends AppCompatActivity {
    @Bind(R.id.appbar)
    AppBarLayout appBar;
    @Bind(R.id.profile_progress)
    ProgressBar succesBar;
    @Bind(R.id.feed_edit_filters)
    Button editFilters;

    private int baseAppBarHeight;
    private int editAppBarHeight;
    private boolean isEditMode = false;

    /**
     * Font icons wrapper
     * */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
        setTitle("");

        succesBar.getProgressDrawable().setColorFilter(
                Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);

        appBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                appBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                appBar.getHeight(); //height is ready

                // Save params
                baseAppBarHeight = appBar.getHeight();
                editAppBarHeight = Utils.convertDpToPx(FeedActivity.this, 560);
                initEditMode();
            }
        });
    }

    private void initEditMode() {
        editFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditMode) {
                    ValueAnimator anim = ValueAnimator.ofInt(editAppBarHeight, baseAppBarHeight);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = appBar.getLayoutParams();
                            layoutParams.height = val;
                            appBar.setLayoutParams(layoutParams);
                        }
                    });
                    anim.setDuration(400);
                    anim.start();
                    anim.setInterpolator(new LinearOutSlowInInterpolator());
                    /*ViewPropertyObjectAnimator
                            .animate(appBar)
                                    //.setInterpolator(new BounceInterpolator())
                            .height(baseAppBarHeight).start();*/
                    isEditMode = false;
                }
                else {
                    ValueAnimator anim = ValueAnimator.ofInt(baseAppBarHeight, editAppBarHeight);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = appBar.getLayoutParams();
                            layoutParams.height = val;
                            appBar.setLayoutParams(layoutParams);
                        }
                    });
                    anim.setDuration(400);
                    anim.setInterpolator(new LinearOutSlowInInterpolator());
                    anim.start();
                    /*ViewPropertyObjectAnimator
                            .animate(appBar)
                                    //.setInterpolator(new BounceInterpolator())
                            .height(editAppBarHeight).start();*/

                    isEditMode = true;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }
}

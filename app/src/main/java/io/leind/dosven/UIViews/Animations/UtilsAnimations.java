package io.leind.dosven.UIViews.Animations;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by leind on 06/05/16.
 */
public class UtilsAnimations {

    /**
     * Simple view resize animation
     * Uses LinearOutSlowInInterpolator
     * */
    public static void resizeViewHeight(final View view, int from, int to) {
        ValueAnimator anim = ValueAnimator.ofInt(from, to);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = val;
                view.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(400);
        anim.start();
        anim.setInterpolator(new LinearOutSlowInInterpolator());
    }

    public static int getDominantColor(Bitmap bitmap) {
        bitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
        int color = bitmap.getPixel(0, 0);
        return color;
    }
}

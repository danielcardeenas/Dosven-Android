package io.leind.dosven.UI.Feed.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ProgressBar;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.utils.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;
import io.leind.dosven.UIViews.Animations.UtilsAnimations;

public class FeedActivity extends AppCompatActivity {
    @Bind(R.id.appbar)
    AppBarLayout appBar;

    @Bind(R.id.profile_progress)
    ProgressBar succesBar;

    @Bind(R.id.feed_edit_filters)
    Button editFilters;

    @Bind(R.id.feed_recycler_view)
    RecyclerView recyclerView;

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

        // When appbar gets drawed
        appBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                appBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // Height is ready
                appBar.getHeight();

                // Save params
                baseAppBarHeight = appBar.getHeight();
                editAppBarHeight = Utils.convertDpToPx(FeedActivity.this, 600);
                initEditMode();
            }
        });
    }

    private void initEditMode() {
        editFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditMode) {
                    UtilsAnimations.resizeViewHeight(appBar, editAppBarHeight, baseAppBarHeight);
                    isEditMode = false;
                }
                else {
                    UtilsAnimations.resizeViewHeight(appBar, baseAppBarHeight, editAppBarHeight);
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

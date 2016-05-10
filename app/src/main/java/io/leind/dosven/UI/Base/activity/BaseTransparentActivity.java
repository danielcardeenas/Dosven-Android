package io.leind.dosven.UI.Base.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;

/**
 * Created by leind on 11/20/15.
 */
public class BaseTransparentActivity extends AppCompatActivity {
    public static final int ANIM_DURATION_TOOLBAR = 300;

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private MenuItem searchMenuItem;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        injectViews();
    }

    protected void injectViews() {
        ButterKnife.bind(this);
        setupToolbar();
    }

    protected void injectViews(boolean setMenu) {
        ButterKnife.bind(this);
        setupToolbar(setMenu);
    }

    public void setContentViewWithoutInject(int layoutResId) {
        super.setContentView(layoutResId);
    }

    protected void setupToolbar() {
        if (toolbar != null) {
            toolbar.setBackgroundColor(Color.TRANSPARENT);
            setSupportActionBar(toolbar);
        }
    }

    protected void setupToolbar(boolean setMenu) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            if (setMenu)
                toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchMenuItem.setActionView(R.layout.search_item_view);
        startIntroAnimation();
        return true;
    }

    /**
     * Calculate ActionBar height
     * */
    public int getToolbarSize() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarSize, tv, true)) {
            return  TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        else
            return -1;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public MenuItem getSearchMenuItem() {
        return searchMenuItem;
    }

    /**
     * Toolbar items animation
     * */
    public void startIntroAnimation() {
        int actionbarSize = getToolbarSize();
        getToolbar().setTranslationY(-actionbarSize);
        getSearchMenuItem().getActionView().setTranslationY(-actionbarSize);

        getToolbar().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);

        getSearchMenuItem().getActionView().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                })
                .start();
    }
}
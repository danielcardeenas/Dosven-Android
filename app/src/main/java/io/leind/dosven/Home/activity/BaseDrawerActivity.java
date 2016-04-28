package io.leind.dosven.Home.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import io.leind.dosven.R;
import io.leind.dosven.UIViews.Transforms.CircleTransform;

/**
 * Created by leind on 11/20/15.
 */
public class BaseDrawerActivity extends BaseActivity {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    NavigationView navigationView;
    ImageView menuUserProfilePhoto;

    private int avatarSize;
    private String profilePhoto;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentViewWithoutInject(R.layout.activity_drawer);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.content_root);
        LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        menuUserProfilePhoto = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.drawer_menu_user_photo);

        injectViews(true);
        setupHeader();
    }

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        if (getToolbar() != null) {
            getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            });
        }
    }

    private void setupHeader() {
        this.avatarSize = getResources().getDimensionPixelSize(R.dimen.user_profile_drawer_avatar_size);
        this.profilePhoto = getResources().getString(R.string.user_profile_photo);
        Glide.with(this)
                .load(profilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .override(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransform(this))
                .into(menuUserProfilePhoto);
    }

}
/**
 * Created by leind on 10/05/16.
 */

package io.leind.dosven.UI.Login.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.login_background)
    ImageView loginBackground;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setBackgroundImage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setBackgroundImage() {
        Glide.with(this)
                .load("https://snap-photos.s3.amazonaws.com/img-thumbs/960w/MRJ2ESOQWX.jpg")
                .bitmapTransform(new ColorFilterTransformation(this, Color.argb(150, 0, 0, 0)))
                .into(loginBackground);
    }
}

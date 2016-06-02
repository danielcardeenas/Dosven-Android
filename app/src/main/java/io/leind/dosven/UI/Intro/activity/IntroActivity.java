/**
 * Created by leind on 10/05/16.
 */

package io.leind.dosven.UI.Intro.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;
import io.leind.dosven.UI.Login.activity.LoginActivity;
import io.leind.dosven.UI.SingUp.activity.SingUpActivity;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

public class IntroActivity extends AppCompatActivity {
    // UI
    @Bind(R.id.login_background) ImageView loginBackground;
    @Bind(R.id.singup_button) AppCompatButton singupButton;
    @Bind(R.id.login_button) AppCompatButton loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        setSingupButton();
        setLoginButton();
        //setBackgroundImage();
    }

    private void setSingupButton() {
        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSingupActivity();
            }
        });
    }

    private void setLoginButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginActivity();
            }
        });
    }

    private void startSingupActivity() {
        Intent myIntent = new Intent(IntroActivity.this, SingUpActivity.class);
        IntroActivity.this.startActivity(myIntent);
    }

    private void startLoginActivity() {
        Intent myIntent = new Intent(IntroActivity.this, LoginActivity.class);
        IntroActivity.this.startActivity(myIntent);
    }

    private void setBackgroundImage() {
        Glide.with(this)
                .load("https://snap-photos.s3.amazonaws.com/img-thumbs/960w/MRJ2ESOQWX.jpg")
                .bitmapTransform(new ColorFilterTransformation(this, Color.argb(150, 0, 0, 0)))
                .into(loginBackground);
    }
}

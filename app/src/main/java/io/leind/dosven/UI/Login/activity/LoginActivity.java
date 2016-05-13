/**
 * Created by leind on 10/05/16.
 */

package io.leind.dosven.UI.Login.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;
import io.leind.dosven.UI.SingUp.activity.SingUpActivity;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

public class LoginActivity extends AppCompatActivity {
    // UI
    @Bind(R.id.login_background) ImageView loginBackground;
    @Bind(R.id.singup_button) AppCompatButton singupButton;
    @Bind(R.id.login_facbeook_button) AppCompatButton facebookButton;

    // Facebook
    LoginManager loginManager;
    CallbackManager callbackManager;
    ProfileTracker profileTracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setSingupButton();
        setBackgroundImage();

        setUpFacebook();

    }

    private void setUpFacebook() {
        // Init
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();

        //loginManager.logOut();

        // Callback registration
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("Facebook", "Success");
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        // Button
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Profile.getCurrentProfile() == null) {
                    loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
                }
                else {

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setSingupButton() {
        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, SingUpActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                LoginActivity.this.startActivity(myIntent);
            }
        });
    }

    private void setBackgroundImage() {
        Glide.with(this)
                .load("https://snap-photos.s3.amazonaws.com/img-thumbs/960w/MRJ2ESOQWX.jpg")
                .bitmapTransform(new ColorFilterTransformation(this, Color.argb(150, 0, 0, 0)))
                .into(loginBackground);
    }
}

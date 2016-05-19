package io.leind.dosven.UI.Splash;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.Profile;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;
import io.leind.dosven.UI.Login.activity.LoginActivity;

/**
 * Created by leind on 13/05/16.
 */
public class SplashActivity extends AppCompatActivity {
    @Bind(R.id.splash_logo)
    TextView splashLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setTitle("");

        if (Profile.getCurrentProfile() == null) {
            startLoginActivity();
        } else {
            startLoginActivity();
        }
    }

    private void startLoginActivity() {
        Intent i = new Intent(SplashActivity.this, LoginActivity.class);

        TextView sharedView = splashLogo;
        String transitionName = getString(R.string.dosven_name);

        ActivityOptions transitionActivityOptions = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, sharedView, transitionName);
        }
        startActivity(i, transitionActivityOptions.toBundle());
    }
}

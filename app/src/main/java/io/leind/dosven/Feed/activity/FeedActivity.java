package io.leind.dosven.Feed.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsContextWrapper;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;
import io.leind.dosven.Home.activity.BaseActivity;
import io.leind.dosven.R;

public class FeedActivity extends BaseActivity {
    @Bind(R.id.appbar)
    AppBarLayout appBar;

    //@Bind(R.id.tag_container)
    //TagContainerLayout tagContainer;

    List<String> tags = Arrays.asList("sup1", "sup2", "sup3");

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

        //tagContainer.setTags(tags);

    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }
}

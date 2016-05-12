package io.leind.dosven.UI.SingUp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;
import io.leind.dosven.UI.SingUp.fragment.Email.EmailFragment;
import io.leind.dosven.UI.SingUp.fragment.Email.EmailFragmentListener;
import io.leind.dosven.UI.SingUp.fragment.Password.PasswordFragment;
import io.leind.dosven.UI.SingUp.fragment.Password.PasswordFragmentListener;
import io.leind.dosven.UI.SingUp.fragment.UserType.UserTypeFragment;
import io.leind.dosven.UI.SingUp.fragment.UserType.UserTypeFragmentListener;

/**
 * Created by leind on 11/05/16.
 */
public class SingUpActivity extends AppCompatActivity {
    @Bind(R.id.singup_pager) ViewPager viewPager;

    // Listeners
    EmailFragmentListener emailFragmentListener = new EmailFragmentListener() {
        @Override
        public void onCanAdvance() {
            // Go next page
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < 3) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        }
    };

    PasswordFragmentListener passwordFragmentListener = new PasswordFragmentListener() {
        @Override
        public void onCanAdvance() {
            // Go next page
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < 3) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        }
    };

    UserTypeFragmentListener userTypeFragmentListener = new UserTypeFragmentListener() {
        @Override
        public void onCanAdvance() {
            // Go next page
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < 3) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Iconics
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        ButterKnife.bind(this);
        setTitle("");

        setupViewPager();
    }

    /**
     * Set up view pager fragments
     * */
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        EmailFragment emailFragment = EmailFragment.newInstance(0);
        emailFragment.setEmailFragmentListener(emailFragmentListener);

        PasswordFragment passwordFragment = PasswordFragment.newInstance(0);
        passwordFragment.setPasswordFragmentListener(passwordFragmentListener);

        UserTypeFragment userTypeFragment = UserTypeFragment.newInstance(0);
        userTypeFragment.setUserTypeFragmentListener(userTypeFragmentListener);

        adapter.addFrag(userTypeFragment);
        adapter.addFrag(emailFragment);
        adapter.addFrag(passwordFragment);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() > 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
        }
        else {
            super.onBackPressed();
        }
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }
}

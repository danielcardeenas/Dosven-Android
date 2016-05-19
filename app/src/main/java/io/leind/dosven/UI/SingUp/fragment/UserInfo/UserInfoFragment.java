package io.leind.dosven.UI.SingUp.fragment.UserInfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;

/**
 * Created by leind on 11/05/16.
 */
public class UserInfoFragment extends Fragment {
    @Bind(R.id.login_button)
    RelativeLayout continueButton;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    int BLUE_HEX = 0xFF1B81F1; // 0xAARRGGBB
    int RED_HEX = 0xFFD62A27; // 0xAARRGGBB
    int GREEN_HEX = 0xFF4AD1B3; // 0xAARRGGBB

    // Event
    UserInfoFragmentListener userInfoFragmentListener;

    public static UserInfoFragment newInstance(int index) {
        UserInfoFragment fragment = new UserInfoFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    public void setUserInfoFragmentListener(UserInfoFragmentListener userInfoFragmentListener) {
        this.userInfoFragmentListener = userInfoFragmentListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.singup_page_4, container, false);
        ButterKnife.bind(this, view);

        setContinueButton();
        return view;
    }

    private void setContinueButton() {
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressBar.setVisibility(View.VISIBLE);
                continuePressed();
            }
        });
    }

    private void continuePressed() {
        userInfoFragmentListener.onCanAdvance();
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void changeButtonColor(int hex) {
        continueButton.setBackgroundColor(hex);
    }
}

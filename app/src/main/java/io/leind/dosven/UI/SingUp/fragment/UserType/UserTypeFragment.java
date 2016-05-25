package io.leind.dosven.UI.SingUp.fragment.UserType;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;

/**
 * Created by leind on 11/05/16.
 */
public class UserTypeFragment extends Fragment {
    @Bind(R.id.login_button)
    RelativeLayout continueButton;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.usertype_freelancer_radio)
    RadioButton freelancerRadio;

    @Bind(R.id.usertype_user_radio)
    RadioButton userRadio;

    int BLUE_HEX = 0xFF1B81F1; // 0xAARRGGBB
    int RED_HEX = 0xFFD62A27; // 0xAARRGGBB
    int GREEN_HEX = 0xFF4AD1B3; // 0xAARRGGBB

    // Event
    UserTypeFragmentListener userTypeFragmentListener;

    public static UserTypeFragment newInstance(int index) {
        UserTypeFragment fragment = new UserTypeFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    public void setUserTypeFragmentListener(UserTypeFragmentListener userTypeFragmentListener) {
        this.userTypeFragmentListener = userTypeFragmentListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.singup_page_3, container, false);
        ButterKnife.bind(this, view);

        setContinueButton();
        setRadioButtons();
        return view;
    }

    private void setRadioButtons() {
        userRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userRadio.isChecked()) {
                    changeButtonColor(GREEN_HEX);
                }
            }
        });

        freelancerRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (freelancerRadio.isChecked()) {
                    changeButtonColor(GREEN_HEX);
                }
            }
        });
    }

    private void setContinueButton() {
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressBar.setVisibility(View.VISIBLE);
                if (userRadio.isChecked() || freelancerRadio.isChecked()) {
                    continuePressed();
                }
                else {
                    changeButtonColor(RED_HEX);
                }
            }
        });
    }

    private void continuePressed() {
        userTypeFragmentListener.onCanAdvance();
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

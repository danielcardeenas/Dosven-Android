package io.leind.dosven.UI.SingUp.fragment.Email;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.leind.dosven.R;
import io.leind.dosven.Utils.METValidators.EmailValidator;
import io.leind.dosven.Utils.Utils;

/**
 * Created by leind on 11/05/16.
 */
public class EmailFragment extends Fragment {
    @Bind(R.id.login_button)
    AppCompatButton continueButton;

    @Bind(R.id.email_edit_text)
    MaterialEditText editText;

    int BLUE_HEX = 0xFF1B81F1; // 0xAARRGGBB
    int RED_HEX = 0xFFD62A27; // 0xAARRGGBB
    int GREEN_HEX = 0xFF4AD1B3; // 0xAARRGGBB

    // Event
    EmailFragmentListener emailFragmentListener;

    public static EmailFragment newInstance(int index) {
        EmailFragment fragment = new EmailFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    public void setEmailFragmentListener(EmailFragmentListener emailFragmentListener) {
        this.emailFragmentListener = emailFragmentListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.singup_page_1, container, false);
        ButterKnife.bind(this, view);

        setEditText();
        setContinueButton();

        return view;
    }

    private void setEditText() {
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    continuePressed();
                    return true;
                }

                // If it's not enter key
                else { changeButtonColor(GREEN_HEX); }

                return false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 changeButtonColor(GREEN_HEX);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setContinueButton() {
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuePressed();
            }
        });
    }

    private void continuePressed() {
        // Validate email text
        editText.validateWith(new EmailValidator("Please use a valid email"));

        // Email is not valid so change the color of the button
        // This performs the same validation from above (EmailValidator)
        if (!Utils.isValidEmail(editText.getText().toString())) {
            changeButtonColor(RED_HEX);
        }

        else {
            emailFragmentListener.onCanAdvance();
        }
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void changeButtonColor(int hex) {
        ColorStateList colorStateList = new ColorStateList(new int[][] {{0}}, new int[] {hex}); // 0xAARRGGBB
        continueButton.setSupportBackgroundTintList(colorStateList);
    }
}

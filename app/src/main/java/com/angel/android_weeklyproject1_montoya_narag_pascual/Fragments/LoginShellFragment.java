package com.angel.android_weeklyproject1_montoya_narag_pascual.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angel.android_weeklyproject1_montoya_narag_pascual.R;

public class LoginShellFragment extends Fragment {
    public LoginShellFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_shell, container, false);
    }

    public void onClickLoginShell(View view) {
        switch (view.getId()) {
            case R.id.btLoginShellAdmin:
                loginForAdmin();
                break;
            case R.id.btLoginShellStudent:
                loginForStudent();
                break;
            case R.id.btBackToMain:
                backToMain();
                break;
        }
    }

    private void backToMain() {

    }

    private void loginForStudent() {

    }

    private void loginForAdmin() {

    }

}

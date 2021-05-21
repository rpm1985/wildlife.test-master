package com.example.animalapp;


import android.content.Intent;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private LoginButton loginButton;
    private TextView info;
    private CallbackManager callbackManager;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_home);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button home_button_login = (Button)view.findViewById(R.id.home_button_login);

        home_button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new LoginFragment<Fragment>(LoginFragment.class));
                fr.commit();

            }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        info = view.findViewById(R.id.info);
        loginButton = view.findViewById(R.id.login_button);
        loginButton.setFragment(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile"));
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText("Login attempt successful");
                Log.d("Fb Attempt","Success");
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
                Log.d("Fb Attempt","Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                info.setText("Login attempt failed.");
                Log.d("Fb Attempt","Fail");
            }
        });


//        callbackManager = CallbackManager.Factory.create();
//
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        info.setText(
//                                "User ID: "
//                                        + loginResult.getAccessToken().getUserId()
//                                        + "\n" +
//                                        "Auth Token: "
//                                        + loginResult.getAccessToken().getToken()
//                        );
//                        Log.d("LMFb Attempt","Success");
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        info.setText("Login attempt canceled.");
//                        Log.d("LMFb Attempt","cancel");
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        info.setText("Login attempt failed.");
//                        Log.d("LMFb Attempt","Fail");
//                    }
//                });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

    }

}

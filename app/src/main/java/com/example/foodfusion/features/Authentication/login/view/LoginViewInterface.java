package com.example.foodfusion.features.Authentication.login.view;

import com.google.firebase.auth.FirebaseUser;

public interface LoginViewInterface {
    void onSuccess(FirebaseUser user);

    void onFailure(String message);

    void onSuccessSignInGoogle();

    void OnFailureSignInGoogle(String message);
}

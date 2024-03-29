package com.example.foodfusion.features.Authentication.signup.view;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpViewInterface {
    void onSignUpSuccess(FirebaseUser user);
    void OnSignUpFailure(String message);

    void onSuccessSignUpGoogle();
    void OnFailureSignUpGoogle(String message);
}

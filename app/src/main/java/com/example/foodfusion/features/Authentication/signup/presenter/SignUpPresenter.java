package com.example.foodfusion.features.Authentication.signup.presenter;

import com.example.foodfusion.features.Authentication.signup.view.SignUpViewInterface;
import com.example.foodfusion.model.repositories.authentication_repository.AuthenticationFireBaseRepo;
import com.example.foodfusion.model.repositories.authentication_repository.AuthenticationRepository;
import com.example.foodfusion.remoteDataSource.remoteFireBaes.auth.SignUpNetworkCallBack;
import com.example.foodfusion.remoteDataSource.remoteFireBaes.auth.SignUpWithGoogleNetworkCallBack;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPresenter implements SignUpPresenterInterface, SignUpNetworkCallBack, SignUpWithGoogleNetworkCallBack {

    private SignUpViewInterface signUpViewInterface;
    private AuthenticationRepository authenticationRepository;

    private static SignUpPresenter signUpPresenter = null;

    private SignUpPresenter(SignUpViewInterface signUpViewInterface) {
        this.signUpViewInterface = signUpViewInterface;
        authenticationRepository = AuthenticationFireBaseRepo.getInstance();

    }

    public static SignUpPresenter getInstance(SignUpViewInterface signUpViewInterface) {
        if (signUpPresenter == null) {
            signUpPresenter = new SignUpPresenter(signUpViewInterface);
        }
        return signUpPresenter;
    }

    @Override
    public void signUp(String email, String password) {
        authenticationRepository.signUp(email, password, this);
    }

    @Override
    public void signUpWithGoogle(AuthCredential authCredential) {
        authenticationRepository.signUpWithGoogle(authCredential, this);
    }

    @Override
    public void onSuccessSignUp(FirebaseUser user) {
        signUpViewInterface.onSignUpSuccess(user);
    }

    @Override
    public void onFailureSignUp(String message) {
        signUpViewInterface.OnSignUpFailure(message);
    }

    @Override
    public void onSuccessGoogle() {
        signUpViewInterface.onSuccessSignUpGoogle();
    }

    @Override
    public void onFailureGoogle(String message) {
        signUpViewInterface.OnFailureSignUpGoogle(message);
    }
}

package com.example.tigo.SignIn;

public interface SignInView {
    void showErrorMessage(String message);
    void showSuccessMessage(String message);
    void navigateToMainMenu();
    void navigateToLogIn();
}


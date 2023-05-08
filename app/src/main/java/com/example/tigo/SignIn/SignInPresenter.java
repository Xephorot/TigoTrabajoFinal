package com.example.tigo.SignIn;

import com.example.tigo.DataBase.UsersDataBase;

public class SignInPresenter {
    private final SignInView view;
    private final UsersDataBase database;

    public SignInPresenter(SignInView view, UsersDataBase database) {
        this.view = view;
        this.database = database;
    }

    public void onCreateAccountClicked(String fullName, String email, String password) {
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            view.showErrorMessage("Por favor complete todos los campos");
        } else {
            boolean userExists = database.checkUser(email);
            if (!userExists) {
                long result = database.addUser(fullName, email, password);
                if (result > 0) {
                    view.showSuccessMessage("Cuenta creada exitosamente");
                    view.navigateToMainMenu();
                } else {
                    view.showErrorMessage("Cuenta creada exitosamente");
                }
            } else {
                view.showErrorMessage("Este email ya existe, por favor ingrese otro");
            }
        }
    }

    public void onSignInClicked() {
        view.navigateToLogIn();
    }
}

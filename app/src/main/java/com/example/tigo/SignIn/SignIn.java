package com.example.tigo.SignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tigo.DataBase.UsersDataBase;
import com.example.tigo.LogIn.LogIn;
import com.example.tigo.MainMenu;
import com.example.tigo.R;

//Este Codigo utiliza el Patron de Diseño MVC (Modelo-Vista-Controlador)
public class SignIn extends AppCompatActivity implements SignInView {
    private EditText fullNameEditText, emailEditText, passwordEditText;
    private Button createAccountButton, signInButton;
    private UsersDataBase database;
    private SignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        fullNameEditText = findViewById(R.id.NombreCompletoEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        createAccountButton = findViewById(R.id.loginButton);
        signInButton = findViewById(R.id.registerButton);

        database = new UsersDataBase(this);
        presenter = new SignInPresenter(this, database);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = fullNameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                presenter.onCreateAccountClicked(fullName, email, password);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignInClicked();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cerrar la conexión a la base de datos al destruir la actividad
        database.close();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMainMenu() {
        Intent intent = new Intent(SignIn.this, MainMenu.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToLogIn() {
        Intent intent = new Intent(SignIn.this, LogIn.class);
        startActivity(intent);
        finish();
    }
}
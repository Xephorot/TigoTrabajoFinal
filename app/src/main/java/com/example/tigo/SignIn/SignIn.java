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
public class SignIn extends AppCompatActivity {
    private EditText nombreCompletoEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button crearCuentaButton;
    private Button iniciarSesionButton;

    private UsersDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        nombreCompletoEditText = findViewById(R.id.NombreCompletoEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        crearCuentaButton = findViewById(R.id.loginButton);
        iniciarSesionButton = findViewById(R.id.registerButton);

        database = new UsersDataBase(this);

        crearCuentaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreCompleto = nombreCompletoEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (nombreCompleto.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignIn.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    boolean usuarioExistente = database.checkUser(email);
                    if (!usuarioExistente) {
                        long resultado = database.addUser(nombreCompleto, email, password);
                        if (resultado > 0) {
                            Toast.makeText(SignIn.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignIn.this, MainMenu.class));
                            finish();
                        } else {
                            Toast.makeText(SignIn.this, "Error al crear cuenta", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignIn.this, "Este email ya existe, por favor ingrese otro", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        iniciarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, LogIn.class));
                finish();
            }
        });
    }
}
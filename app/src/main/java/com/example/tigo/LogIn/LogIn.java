package com.example.tigo.LogIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tigo.DataBase.UsersDataBase;
import com.example.tigo.MainMenu;
import com.example.tigo.R;
import com.example.tigo.SignIn.SignIn;

//Este Codigo utiliza el Patron de Diseño MVC (Modelo-Vista-Controlador)

public class LogIn extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private UsersDataBase usersDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Verificar si el usuario ya ha iniciado sesión
        SharedPreferences sharedPreferences = getSharedPreferences("mis_preferencias", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email_usuario", null);
        if (userEmail != null) {
            // Si el usuario ya ha iniciado sesión, redirigir a MainActivity
            Intent mainIntent = new Intent(LogIn.this, MainMenu.class);
            startActivity(mainIntent);
            finish();}

        // Obtener referencias de vistas
        TextView textView = findViewById(R.id.textView);
        ImageView imageView = findViewById(R.id.imageView);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);
        Button forgottenButton = findViewById(R.id.ForgottenButton);

        // Crear instancia de la clase UsersDataBase
        usersDataBase = new UsersDataBase(this);

        // Configurar evento clic para botón "Iniciar Sesión"
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valores de los campos de texto
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Verificar si los campos están vacíos
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LogIn.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificar si el usuario existe en la base de datos
                if (!usersDataBase.checkUser(email)) {
                    Toast.makeText(LogIn.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificar si la contraseña coincide con la almacenada en la base de datos
                if (!usersDataBase.checkPassword(email, password)) {
                    Toast.makeText(LogIn.this, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Guardar información del usuario en SharedPreferences
                String userName = usersDataBase.getUserNameByEmail(email);
                SharedPreferences sharedPreferences = getSharedPreferences("mis_preferencias", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nombre_usuario", userName);
                editor.putString("email_usuario", email);
                editor.apply();

                // Iniciar sesión
                Intent intent = new Intent(LogIn.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        // Configurar evento clic para botón "Registrarse"
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a la pantalla de registro
                Intent intent = new Intent(LogIn.this, SignIn.class);
                startActivity(intent);
            }
        });

        // Configurar evento clic para botón "Olvidé la Contraseña"
        forgottenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar mensaje de que esta función estará disponible próximamente
                Toast.makeText(LogIn.this, "Esta función estará disponible próximamente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cerrar la conexión a la base de datos al destruir la actividad
        usersDataBase.close();
    }
}






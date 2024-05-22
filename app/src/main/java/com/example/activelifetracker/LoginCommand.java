package com.example.activelifetracker;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

//Uso: Para encapsular una solicitud como un objeto, permitiendo parametrizar a los clientes con diferentes solicitudes, encolar solicitudes o registrar las solicitudes.
//
//Implementación: Crear comandos para las acciones de inicio de sesión y registro.

public class LoginCommand implements Command {
    private final FirebaseAuth mAuth;
    private final String email;
    private final String password;
    private final Context context;

    public LoginCommand(FirebaseAuth mAuth, String email, String password, Context context) {
        this.mAuth = mAuth;
        this.email = email;
        this.password = password;
        this.context = context;
    }

    @Override
    public void execute() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                context.startActivity(IntentFactory.createIntent(context, home.class));
                Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(context, "Error Al Inicializar", Toast.LENGTH_SHORT).show());
    }
}

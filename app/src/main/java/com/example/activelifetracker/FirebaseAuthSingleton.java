package com.example.activelifetracker;

import com.google.firebase.auth.FirebaseAuth;


//Uso: Para garantizar que solo haya una instancia de FirebaseAuth en toda la aplicación.
//
//Implementación: Podrías crear una clase Singleton que maneje la instancia de FirebaseAuth.
public class FirebaseAuthSingleton {
    private static FirebaseAuth mAuth;

    private FirebaseAuthSingleton() {
    }

    public static FirebaseAuth getInstance() {
        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }
}

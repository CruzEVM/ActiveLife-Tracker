package com.example.activelifetracker;

import android.content.Context;
import android.content.Intent;

//Uso: Para crear instancias de actividades o fragmentos. En este caso, podrías usar una fábrica para manejar la creación de Intent para diferentes actividades.
//
//Implementación: Crear una fábrica de intents.
public class IntentFactory {
    public static Intent createIntent(Context context, Class<?> cls) {
        return new Intent(context, cls);
    }
}

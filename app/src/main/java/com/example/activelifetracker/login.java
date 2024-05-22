package com.example.activelifetracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText login_email, login_password;
    boolean passwordvisibe;
    Button btn_login, btn_newacount;
    FirebaseAuth mAuth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Instancear variables
        mAuth = FirebaseAuthSingleton.getInstance();
        login_email = findViewById(R.id.et_loginemail);
        login_password = findViewById(R.id.et_loginpasw);
        btn_login = findViewById(R.id.btn_login);

        // Animacion del corazon
        ImageView imageView = findViewById(R.id.iv_iconlogin);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.heart_beat_animation);
        imageView.startAnimation(animation);

        // Boton Login
        btn_login.setOnClickListener(v -> {
            String emailUser = login_email.getText().toString().trim();
            String passUser = login_password.getText().toString().trim();

            if (emailUser.isEmpty() && passUser.isEmpty()) {
                Toast.makeText(login.this, "Ingresar Los Datos", Toast.LENGTH_SHORT).show();
            } else {
                Command loginCommand = new LoginCommand(mAuth, emailUser, passUser, login.this);
                loginCommand.execute();
            }
        });

        // Animacion visualizar contraseña
        login_password.setOnTouchListener((v, event) -> {
            final int Right = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= login_password.getRight() - login_password.getCompoundDrawables()[Right].getBounds().width()) {
                    int selection = login_email.getSelectionEnd();
                    if (passwordvisibe) {
                        login_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0);
                        login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    } else {
                        login_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_24, 0);
                        login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    passwordvisibe = false;
                    login_password.setSelection(selection);
                    return true;
                }
            }
            return false;
        });

        // Inicializar Formulario de registro
        btn_newacount = findViewById(R.id.bt_newacount);
        btn_newacount.setOnClickListener(v -> {
            register_user registerUser = new register_user();
            registerUser.show(getSupportFragmentManager(), "Navegar a Fragment");
        });
    }
}

package com.example.activelifetracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText email, password;
    boolean passwordvisibe;

    Button btn_newacount;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        //animacion del corazon
        ImageView imageView = findViewById(R.id.iv_iconlogin);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.heart_beat_animation);
        imageView.startAnimation(animation);

        //animacion visualizar contraseña
        email=findViewById(R.id.ed_loginemail);
        password=findViewById(R.id.et_loginpasw);

        password.setOnTouchListener((v, event) -> {
            final int Right=2;
            if (event.getAction()==MotionEvent.ACTION_UP){
                if (event.getRawX() >= password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()) {
                    int selection=password.getSelectionEnd();
                    if (passwordvisibe){
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.baseline_visibility_off_24,0);
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }else {
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.baseline_visibility_24,0);
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    passwordvisibe=false;
                    password.setSelection(selection);
                    return true;
                }
            }

            return false;
        });
        //Inicializar Formulario de registro
        btn_newacount= findViewById(R.id.bt_newacount);
        btn_newacount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_user registerUser = new register_user();
                registerUser.show(getSupportFragmentManager(), "Navegar a Fragment");
            }
        });
    }
}
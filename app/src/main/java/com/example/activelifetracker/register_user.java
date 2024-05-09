package com.example.activelifetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class register_user extends DialogFragment {

    Button adduser;
    EditText nameuser, lastnameuser, email, pass;
    private FirebaseFirestore bd;
    FirebaseAuth bdAuth;


    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register_user, container, false);
        bd = FirebaseFirestore.getInstance();
        bdAuth = FirebaseAuth.getInstance();

        adduser = v.findViewById(R.id.bt_create);
        nameuser = v.findViewById(R.id.ed_name);
        lastnameuser = v.findViewById(R.id.ed_lastname);
        email = v.findViewById(R.id.ed_loginemail);
        pass = v.findViewById(R.id.ed_loginpasw);

        adduser.setOnClickListener(v1 -> {
            String name = nameuser.getText().toString().trim();
            String lastname = lastnameuser.getText().toString().trim();
            String useremail = email.getText().toString().trim();
            String userpass = pass.getText().toString().trim();

            if (name.isEmpty() && lastname.isEmpty() && useremail.isEmpty() && userpass.isEmpty()){
                Toast.makeText(getContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
            }else {
                resgisterUser(name,lastname,useremail,userpass);

            }
        });
        return v;
    }

    //Patron de diseño Factory
    public static class IntentFactory {

        public static void createLoginIntent(Context context) {
            new Intent(context, login.class);
        }
    }

    private void resgisterUser(String name, String lastname, String useremail, String userpass) {
        bdAuth.createUserWithEmailAndPassword(useremail,userpass).addOnCompleteListener(task -> {
            String id = Objects.requireNonNull(bdAuth.getCurrentUser()).getUid();
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("name", name);
            map.put("lastname", lastname);
            map.put("email", useremail);
            map.put("password", userpass);

            bd.collection("user").document(id).set(map).addOnSuccessListener(unused -> {
                Toast.makeText(getContext(), "Error al registrar", Toast.LENGTH_SHORT).show();
                Objects.requireNonNull(getDialog()).dismiss();
                IntentFactory.createLoginIntent(getActivity());

            }).addOnFailureListener(e -> {
                Toast.makeText(getContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
                Objects.requireNonNull(getDialog()).dismiss();
                IntentFactory.createLoginIntent(getActivity());
            });
        }).addOnFailureListener(e -> {
        Toast.makeText(getContext(), "Usuario Registrado", Toast.LENGTH_SHORT).show();
            Objects.requireNonNull(getDialog()).dismiss();
            IntentFactory.createLoginIntent(getActivity());
        });

    }
}
package com.example.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SignUp extends AppCompatActivity {
    int num =4;
    private FirebaseAuth mAuth;
    //public static ArrayList<User> users= new ArrayList<User>() ;
    private String s_name ,
            s_email,
            s_phone,
            s_pass;
    EditText t_name,
            t_emil ,
            t_phone ,
            t_pass ,
            t_cpass ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        t_name= findViewById(R.id.name_input);
        t_emil = findViewById(R.id.email_upup);
        t_phone = findViewById(R.id.phone_input);
        t_pass = findViewById(R.id.pass_up);
        t_cpass = findViewById(R.id.c_pass_up);

        Button btn_show1 = findViewById(R.id.show1_up),
                btn_show2= findViewById(R.id.show2_up),
                btn_signup = findViewById(R.id.signup_btn);

        btn_show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPassword(t_pass);
            }
        });
        btn_show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPassword(t_cpass);
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = t_name.getText().toString();
                String email = t_emil.getText().toString().trim();
                String phone = t_phone.getText().toString();
                String pass = t_pass.getText().toString().trim();
                String s_cpass = t_cpass.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                }

                                // ...
                            }
                        });

            }
        });


    }

//    private boolean signup() {
//
//
//
//        if (pass.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || s_cpass.isEmpty()) {
//            Toast.makeText(this, "You must fill all filds ... try again ", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (!pass.equals(s_cpass)) {
//            Toast.makeText(this, "your confirmation password is incorrect", Toast.LENGTH_LONG).show();
//            return false;
//        }
//        else {
//
//
//            return true;
//        }
//
//    }

    public static boolean isShow = true;
    public static void showPassword (EditText pass){

        if (isShow){
            pass.setTransformationMethod(null);
            isShow = false;
        }
        else
        {
            pass.setTransformationMethod(new PasswordTransformationMethod());
            isShow = true;
        }
    }



}
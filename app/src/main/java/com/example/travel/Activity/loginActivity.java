package com.example.travel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.travel.R;
import com.example.travel.databinding.ActivityLoginBinding;

public class loginActivity extends AppCompatActivity {


    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper=new DatabaseHelper(this);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.loginEmail.getText().toString();
                String password=binding.loginPassword.getText().toString();


                if (email.equals("")||password.equals(""))
                    Toast.makeText(loginActivity.this, "ALl fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkcredentials=databaseHelper.checkpassword(email,password);

                    if (checkcredentials==true){
                        Toast.makeText(loginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), introActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(loginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.signupredirecttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginActivity.this,signupActivity.class);
                startActivity(intent);
            }
        });

    }
}
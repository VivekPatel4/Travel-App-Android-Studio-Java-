package com.example.travel.Activity;

import android.app.Activity;
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
import com.example.travel.databinding.ActivitySignupBinding;

public class signupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper=new DatabaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.signupEmail.getText().toString();
                String password=binding.signupPassword.getText().toString();
                String confirmpassword=binding.signupConfirm.getText().toString();

                if (email.equals("") || password.equals("") || confirmpassword.equals(""))
                    Toast.makeText(signupActivity.this,"All filed are mandatory",Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirmpassword)){
                        Boolean checkuseremail=databaseHelper.checkemail(email);

                        if (checkuseremail==false){
                            Boolean insert=databaseHelper.insertdata(email,password);

                            if (insert==true){
                                Toast.makeText(signupActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), loginActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(signupActivity.this,"signup Failed",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(signupActivity.this,"User Alredy exists,please login",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(signupActivity.this,"Invalid password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.loginredirecttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),loginActivity.class);
                startActivity(intent);
            }
        });

    }
}
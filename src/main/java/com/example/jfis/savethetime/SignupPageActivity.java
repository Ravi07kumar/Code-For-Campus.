package com.example.jfis.savethetime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupPageActivity extends AppCompatActivity {
    private EditText email , password ,keyword ;
    private Button register ;
    private RadioGroup radioGroup ;
    private RadioButton rst ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        email=findViewById(R.id.emailSignupText);
        password=findViewById(R.id.passwordSignupText);
        keyword=findViewById(R.id.keywordSignupText);
        register=findViewById(R.id.registerSignupButton);
        mAuth =FirebaseAuth.getInstance();

        radioGroup=findViewById(R.id.statusSignupRadioGroup);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = email.getText().toString();
                String passwordString = password.getText().toString();
                String keywordString =  keyword.getText().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                rst = findViewById(selectedId);
                if( selectedId == -1 ||  emailId.trim().isEmpty()  || passwordString.trim().isEmpty() || keywordString.trim().isEmpty()){
                    Toast.makeText(SignupPageActivity.this, "  Please Enter Proper Details ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(rst.getText().toString().equals("ADMIN")){
                    if(! keywordString.equals("ROHIT")){
                        Toast.makeText(SignupPageActivity.this, "Keyword is wrong for admin ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if(rst.getText().toString().equals("STUDENT")){
                    if(! keywordString.equals("123")){
                        Toast.makeText(SignupPageActivity.this, "Keyword is wrong for student  ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                createUser(emailId,passwordString,selectedId);

            }
        });

    }

    private void createUser(String emailId, String passwordString, int selectedId) {

        rst = findViewById(selectedId);
        mAuth.createUserWithEmailAndPassword(emailId,passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupPageActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                            if (rst.getText().toString().equals("ADMIN")) {
                                Intent intent = new Intent(SignupPageActivity.this, AdminActivity.class);
                                startActivity(intent);
                            }else if (rst.getText().toString().equals("STUDENT")){
                                Intent intent = new Intent(SignupPageActivity.this, UserActivity.class);
                                startActivity(intent);

                            }

                        }else{
                            Toast.makeText(SignupPageActivity.this, "Authentication failed Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

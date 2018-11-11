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

public class LoginPageActivity extends AppCompatActivity {
    private EditText email , password ,keyword ;
    private Button login ;
    private RadioGroup radioGroup ;
    private RadioButton rst ;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email=findViewById(R.id.emailLoginText);
        password=findViewById(R.id.passwordLoginText);
        keyword=findViewById(R.id.keywordLoginText);
        login=findViewById(R.id.registerLoginButton);
        mAuth =FirebaseAuth.getInstance();

        radioGroup=findViewById(R.id.statusLoginRadioGroup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = email.getText().toString();
                String passwordString = password.getText().toString();
                String keywordString =  keyword.getText().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                rst = findViewById(selectedId);
                if( selectedId == -1 ||  emailId.trim().isEmpty()  || passwordString.trim().isEmpty() || keywordString.trim().isEmpty()){
                    Toast.makeText(LoginPageActivity.this, "  Please Enter Proper Details ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(rst.getText().toString().equals("ADMIN")){
                    if(! keywordString.equals("ROHIT")){
                        Toast.makeText(LoginPageActivity.this, "Keyword is wrong for admin ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if(rst.getText().toString().equals("STUDENT")){
                    if(! keywordString.equals("123")){
                        Toast.makeText(LoginPageActivity.this, "Keyword is wrong for student  ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                allReadyUserMethod  ( emailId , passwordString,selectedId);

            }
        });
    }

    private void allReadyUserMethod(String emailId, String passwordString, int selectedId) {
        mAuth.signInWithEmailAndPassword(emailId,passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginPageActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                            if (rst.getText().toString().equals("ADMIN")) {
                                Intent intent = new Intent(LoginPageActivity.this, AdminActivity.class);
                                startActivity(intent);
                            }else if (rst.getText().toString().equals("STUDENT")) {
                                Intent intent = new Intent(LoginPageActivity.this, UserActivity.class);
                                startActivity(intent);
                            }

                        }else{
                            Toast.makeText(LoginPageActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

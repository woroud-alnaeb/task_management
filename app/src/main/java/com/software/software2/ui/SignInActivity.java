package com.software.software2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.software.software2.R;
import com.software.software2.data.LoginDataSource;
import com.software.software2.data.LoginRepository;
import com.software.software2.data.Result;
import com.software.software2.data.model.UserModel;


public class SignInActivity extends AppCompatActivity {
    private LoginRepository loginRepository= new LoginRepository(new LoginDataSource());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        final EditText usernameEditText =  findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login_button);
        final ProgressBar loadingProgressBar = findViewById(R.id.progress_bar);


        loginButton.setOnClickListener(v -> {
            loadingProgressBar.setVisibility(View.VISIBLE);

            Result<UserModel> result = loginRepository.login(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());


            loadingProgressBar.setVisibility(View.GONE);

            if (result instanceof Result.Success) {
                Toast.makeText(getApplicationContext(), getString(R.string.welcome), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.login_failed), Toast.LENGTH_LONG).show();
            }


            //Complete and destroy login activity once successful
            finish();
        });
    }
}
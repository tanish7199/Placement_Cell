package com.gmail.placement_cell;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;


public class LoginActivity extends AppCompatActivity {

    public static int APP_REQUEST_CODE = 1;
    public int flag = 1;
    Button StudentLogin;
    Button FacultyLogin;
    Button AdminLogin;
    TextView Choose;
    Button Email;
    Button Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.withEmail);
        Phone = findViewById(R.id.withPhone);
        StudentLogin = findViewById(R.id.Student);
        FacultyLogin = findViewById(R.id.Faculty);
        AdminLogin = findViewById(R.id.Admin);
        Choose = findViewById(R.id.Choose);


        StudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email.setVisibility(View.VISIBLE);
                Phone.setVisibility(View.VISIBLE);
                FacultyLogin.setVisibility(View.INVISIBLE);
                AdminLogin.setVisibility(View.INVISIBLE);
                Choose.setVisibility(View.VISIBLE);
                flag = 2;
                AccessToken accessToken = AccountKit.getCurrentAccessToken();
                if (accessToken != null) {
                    // if previously logged in, proceed to the account activity
                    launchStudentAccountActivity();
                }
            }
        });

        FacultyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email.setVisibility(View.VISIBLE);
                Phone.setVisibility(View.VISIBLE);
                StudentLogin.setVisibility(View.INVISIBLE);
                AdminLogin.setVisibility(View.INVISIBLE);
                Choose.setVisibility(View.VISIBLE);
                flag = 3;
                AccessToken accessToken = AccountKit.getCurrentAccessToken();
                if (accessToken != null) {
                    // if previously logged in, proceed to the account activity
                    launchFacultyAccountActivity();
                }
            }
        });

        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email.setVisibility(View.VISIBLE);
                Phone.setVisibility(View.VISIBLE);
                FacultyLogin.setVisibility(View.INVISIBLE);
                StudentLogin.setVisibility(View.INVISIBLE);
                Choose.setVisibility(View.VISIBLE);
                flag = 4;
                AccessToken accessToken = AccountKit.getCurrentAccessToken();
                if (accessToken != null) {
                    // if previously logged in, proceed to the account activity
                    launchAdminAccountActivity();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // confirm that this response matches your request
        if (requestCode == APP_REQUEST_CODE) {
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (loginResult.getError() != null) {
                // display login error
                String toastMessage = loginResult.getError().getErrorType().getMessage();
                Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
            } else if (loginResult.getAccessToken() != null) {
                if (flag == 2)
                    launchStudentAccountActivity();
                else if (flag == 3)
                    launchFacultyAccountActivity();
                else if (flag == 4)
                    launchAdminAccountActivity();
            }
        }
    }

    private void onLogin(final LoginType loginType) {
        final Intent intent = new Intent(LoginActivity.this, AccountKitActivity.class);

        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        loginType,
                        AccountKitActivity.ResponseType.TOKEN
                );
        final AccountKitConfiguration configuration = configurationBuilder.build();

        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configuration);
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    public void onPhoneLogin(View view) {
        onLogin(LoginType.PHONE);
    }

    public void onEmailLogin(View view) {
        onLogin(LoginType.EMAIL);
    }

    private void launchStudentAccountActivity() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
        finish();
    }

    private void launchFacultyAccountActivity() {
        Intent intent = new Intent(this, facultyMenuPage.class);
        startActivity(intent);
        finish();
    }

    private void launchAdminAccountActivity() {
        Intent intent = new Intent(this, adminHomePage.class);
        startActivity(intent);
        finish();
    }
}
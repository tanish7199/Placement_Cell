package com.gmail.placement_cell;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.gmail.placement_cell.student_database.StudentContract;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Locale;

public class HomePage extends AppCompatActivity {

    public int count = 0;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;
    TextView info;
    EditText CGPA;
    EditText Resume;
    EditText Name;
    EditText Roll;
    String CGPA1;
    String Resume1;
    String Name1;
    String Roll1;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        count = 0;

        info = findViewById(R.id.info);
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {


                PhoneNumber phoneNumber = account.getPhoneNumber();
                if (account.getPhoneNumber() != null) {
                    // if the phone number is available, display it
                    String formattedPhoneNumber = formatPhoneNumber(phoneNumber.toString());
                    info.setText(formattedPhoneNumber);

                } else {
                    // if the email address is available, display it
                    String emailString = account.getEmail();
                    info.setText(emailString);
                }

            }

            @Override
            public void onError(final AccountKitError error) {
                // display error
                String toastMessage = error.getErrorType().getMessage();
                Toast.makeText(HomePage.this, toastMessage, Toast.LENGTH_LONG).show();
            }
        });
        mButton1 = findViewById(R.id.Home);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 != 0) {
                    mButton1.setBackgroundColor(Color.MAGENTA);
                    count++;
                } else
                    mButton1.setBackgroundColor(Color.rgb(145, 145, 253));
            }
        });
        mButton2 = findViewById(R.id.History);
        count = 0;
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, History_HomePage.class);
                startActivity(intent);
                if (count % 2 != 0) {
                    mButton2.setBackgroundColor(Color.MAGENTA);
                    count++;
                } else
                    mButton2.setBackgroundColor(Color.rgb(145, 145, 253));
            }
        });
        mButton3 = findViewById(R.id.Contact_Us);
        count = 0;
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this,ContactUs.class);
                startActivity(i);
                if (count % 2 != 0) {
                    mButton3.setBackgroundColor(Color.MAGENTA);
                    count++;
                } else
                    mButton3.setBackgroundColor(Color.rgb(145, 145, 253));
            }
        });
        mButton4 = findViewById(R.id.About_Us);
        count = 0;
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this,ContactUs.class);
                startActivity(i);
                if (count % 2 != 0) {
                    mButton4.setBackgroundColor(Color.MAGENTA);
                    count++;
                } else
                    mButton3.setBackgroundColor(Color.rgb(145, 145, 253));
            }
        });
        Submit = findViewById(R.id.SubmitStudent);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = findViewById(R.id.Name);
                Name1 = Name.getText().toString().trim();
                if (TextUtils.isEmpty(Name1)) {
                    Name.setError("Enter your name");
                }

                CGPA = findViewById(R.id.CGPA);
                CGPA1 = CGPA.getText().toString().trim();
                if ((!(TextUtils.isDigitsOnly(CGPA1)))||TextUtils.isEmpty(CGPA1)) {
                    CGPA.setError("Enter valid CGPA");
                }
                Roll = findViewById(R.id.RollNumber);
                Roll1 = Roll.getText().toString().trim();
                if (!(TextUtils.isDigitsOnly(Roll1))||TextUtils.isEmpty(Roll1)) {
                    Roll.setError("Enter valid roll number");
                }

                Resume = findViewById(R.id.Resume);
                Resume1 = Resume.getText().toString().trim();
                if (TextUtils.isEmpty(Resume1)) {
                    Resume.setError("Enter resume link");
                }

                database();
            }
        });

    }

    public void onLogout(View view) {
        // logout of Account Kit
        AccountKit.logOut();
        launchLoginActivity();
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private String formatPhoneNumber(String phoneNumber) {
        // helper method to format the phone number for display
        try {
            PhoneNumberUtil pnu = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber pn = pnu.parse(phoneNumber, Locale.getDefault().getCountry());
            phoneNumber = pnu.format(pn, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        return phoneNumber;
    }


    private void database() {
        Name = findViewById(R.id.Name);
        Name1 = Name.getText().toString().trim();

        CGPA = findViewById(R.id.CGPA);
        CGPA1 = CGPA.getText().toString().trim();

        Roll = findViewById(R.id.RollNumber);
        Roll1 = Roll.getText().toString().trim();

        Resume = findViewById(R.id.Resume);
        Resume1 = Resume.getText().toString().trim();

        ContentValues Values = new ContentValues();
        Values.put(StudentContract.StudentEntry.STUDENT_NAME, Name1);
        Values.put(StudentContract.StudentEntry.CGPA, CGPA1);
        Values.put(StudentContract.StudentEntry.ROLL_NUMBER, Roll1);
        Values.put(StudentContract.StudentEntry.RESUME, Resume1);

        Uri newUri = getContentResolver().insert(StudentContract.StudentEntry.CONTENT_URI, Values);

        if (newUri == null) {
            Toast.makeText(this, "Error saving", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }


}
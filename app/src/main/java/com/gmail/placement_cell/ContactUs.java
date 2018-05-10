package com.gmail.placement_cell;

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
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Locale;

public class ContactUs extends AppCompatActivity {
    public int count = 0;
    TextView info;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        mButton1 = findViewById(R.id.Home);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContactUs.this, HomePage.class);
                startActivity(i);
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
                Intent intent = new Intent(ContactUs.this, History_HomePage.class);
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
                Intent i = new Intent(ContactUs.this, studentAboutUs.class);
                startActivity(i);
                if (count % 2 != 0) {
                    mButton4.setBackgroundColor(Color.MAGENTA);
                    count++;
                } else
                    mButton3.setBackgroundColor(Color.rgb(145, 145, 253));
            }
        });
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
                Toast.makeText(ContactUs.this, toastMessage, Toast.LENGTH_LONG).show();
            }
        });

        Button contactSubmit = findViewById(R.id.contactSubmitForm);


        contactSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                EditText contactName = findViewById(R.id.contactName);
                final String contactName1 = contactName.getText().toString();

                EditText contactEmail = findViewById(R.id.contactEmail);
                final String contactEmail1 = contactEmail.getText().toString();

                EditText contactMessage = findViewById(R.id.contactMessage);
                final String contactMessage1 = contactMessage.getText().toString();

                EditText contactPhone = findViewById(R.id.contactPhone);
                final String contactPhone1 = contactPhone.getText().toString();

                if (TextUtils.isEmpty(contactEmail1)) {
                    contactEmail.setError("Enter correct email");
                    flag = 1;
                }

                if (TextUtils.isEmpty(contactMessage1)) {
                    contactMessage.setError("Enter valid message");
                    flag = 1;
                }

                if (TextUtils.isEmpty(contactName1)) {
                    contactName.setError("Enter valid name");
                    flag = 1;
                }
                if (TextUtils.isEmpty(contactPhone1)) {
                    contactPhone.setError("Enter valid phone");
                    flag = 1;
                }

                String Message = contactName1 + " " + contactEmail1 + " " + contactPhone1 + " " + contactMessage1;
                if (flag == 0) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Query regarding ongoing placements.");
                    intent.putExtra(Intent.EXTRA_TEXT, Message);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
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

}
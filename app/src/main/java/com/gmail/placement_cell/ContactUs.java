package com.gmail.placement_cell;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    TextView info;
    public int count = 0;
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
                Intent i = new Intent(ContactUs.this,HomePage.class);
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
                Intent i = new Intent(ContactUs.this,studentAboutUs.class);
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
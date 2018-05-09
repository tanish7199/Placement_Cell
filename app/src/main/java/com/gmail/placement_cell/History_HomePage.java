package com.gmail.placement_cell;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class History_HomePage extends AppCompatActivity {

    public int count = 0;
    List<CompaniesList> companiesLists;

    TextView info;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__home_page);
        int position = 10;
        Integer[] Images = {
                R.drawable.ab, R.drawable.cd,
                R.drawable.ef, R.drawable.gh,
                R.drawable.ij, R.drawable.kl,
                R.drawable.mn, R.drawable.op,
                R.drawable.qr, R.drawable.st
        };
        String[] companyName = {"BYJUs", "Hitachi", "Infosys",
                "Amazon", "Verizon", "Unisys", "Capgemini", "Deepak-Nitrite", "OT-Morpho", "Birlasoft"};
        companiesLists = new ArrayList<>();
        for (int i = 0; i < position; i++) {
            companiesLists.add(new CompaniesList(Images[i], companyName[i]));
        }


        GridView listView = findViewById(R.id.GridView1);

        CompaniesAdapter adapter = new CompaniesAdapter(this, companiesLists);
        listView.setAdapter(adapter);
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
                Toast.makeText(History_HomePage.this, toastMessage, Toast.LENGTH_LONG).show();
            }
        });
        mButton1 = findViewById(R.id.Home);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(History_HomePage.this, HomePage.class);
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
                /*Intent intent = new Intent(History_HomePage.this, History_HomePage.class);
                startActivity(intent);
                */
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
                Intent i = new Intent(History_HomePage.this, ContactUs.class);
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
                Intent i = new Intent(History_HomePage.this, studentAboutUs.class);
                startActivity(i);
                if (count % 2 != 0) {
                    mButton4.setBackgroundColor(Color.MAGENTA);
                    count++;
                } else
                    mButton3.setBackgroundColor(Color.rgb(145, 145, 253));
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
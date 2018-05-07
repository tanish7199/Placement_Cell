package com.gmail.placement_cell;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    List<CompaniesList> companiesLists;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__home_page);
        int position = 9;
        Integer[] Images = {
                R.drawable.ab, R.drawable.cd,
                R.drawable.ef, R.drawable.gh,
                R.drawable.ij, R.drawable.kl,
                R.drawable.mn, R.drawable.op,
                R.drawable.qr, R.drawable.st
        };
        String[] companyName = {"BYJUs", "Hitachi", "Infosys",
                "Amazon", "Verizon", "Unisys",
                "Capgemini", "Deepak-Nitrite", "OT-Morpho", "Birlasoft"};
        companiesLists = new ArrayList<>();
        for (int i = 0; i <= position; i++) {
            companiesLists.add(new CompaniesList(Images[i], companyName[i]));
        }

        GridView listView = findViewById(R.id.GridView);
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
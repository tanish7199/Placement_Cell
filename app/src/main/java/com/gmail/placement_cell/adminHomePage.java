package com.gmail.placement_cell;

import android.content.Intent;
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

public class adminHomePage extends AppCompatActivity {


    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

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
                    //  if the email address is available, display it
                    String emailString = account.getEmail();
                    info.setText(emailString);
                }

            }

            @Override
            public void onError(final AccountKitError error) {
                // display error
                String toastMessage = error.getErrorType().getMessage();
                Toast.makeText(adminHomePage.this, toastMessage, Toast.LENGTH_LONG).show();
            }
        });

        Button Submit = findViewById(R.id.submitView);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //displayDatabaseInfo();
                Intent i = new Intent(adminHomePage.this, showTimeTable.class);
                startActivity(i);
            }
        });
        Button SubmitStudDetails = findViewById(R.id.submitStudentDetails);
        SubmitStudDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(adminHomePage.this,showStudentDetails.class);
                startActivity(i);
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
        finish();
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

   /* public void displayDatabaseInfo() {
        String[] projection = {
                timetableContract.timetableEntry.TEACHER_NAME,
                timetableContract.timetableEntry.MONDAY,
                timetableContract.timetableEntry.TUESDAY,
                timetableContract.timetableEntry.WEDNESDAY,
                timetableContract.timetableEntry.THURSDAY,
                timetableContract.timetableEntry.FRIDAY
        };
        //displayView = findViewById(R.id.tables);

        try (Cursor cursor = getContentResolver().query(
                timetableContract.timetableEntry.CONTENT_URI,   // The content URI of the words table
                projection,             // The columns to return for each row
                null,                   // Selection criteria
                null,                   // Selection criteria
                null)) {
            if (cursor != null) {

                displayView.append(timetableContract.timetableEntry._ID + " - " +
                        timetableContract.timetableEntry.TEACHER_NAME + " - " +
                        timetableContract.timetableEntry.MONDAY + " - " +
                        timetableContract.timetableEntry.TUESDAY + " - " +
                        timetableContract.timetableEntry.WEDNESDAY + " - " +
                        timetableContract.timetableEntry.THURSDAY + " - " +
                        timetableContract.timetableEntry.FRIDAY + "\n");

                // Figure out the index of each column
                int idColumnIndex = cursor.getColumnIndex(timetableContract.timetableEntry._ID);
                int teacherName = cursor.getColumnIndex(timetableContract.timetableEntry.TEACHER_NAME);
                int Monday = cursor.getColumnIndex(timetableContract.timetableEntry.MONDAY);
                int Tuesday = cursor.getColumnIndex(timetableContract.timetableEntry.TUESDAY);
                int Wednesday = cursor.getColumnIndex(timetableContract.timetableEntry.WEDNESDAY);
                int Thursday = cursor.getColumnIndex(timetableContract.timetableEntry.THURSDAY);
                int Friday = cursor.getColumnIndex(timetableContract.timetableEntry.FRIDAY);

                // Iterate through all the returned rows in the cursor
                while (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    for (int i = 0; i < cursor.getCount(); i++) {
                        int currentID = cursor.getInt(idColumnIndex);
                        String currentName = cursor.getString(teacherName);
                        String Monday1 = cursor.getString(Monday);
                        String Tuesday1 = cursor.getString(Tuesday);
                        String Wednesday1 = cursor.getString(Wednesday);
                        String Thursday1 = cursor.getString(Thursday);
                        String Friday1 = cursor.getString(Friday);

                        // Display the values from each column of the current row in the cursor in the TextView
                        displayView.append(("\n" + currentID + " - " +
                                currentName + " - " +
                                Monday1 + " - " +
                                Tuesday1 + " - " +
                                Wednesday1 + " - " +
                                Thursday1 + " - " +
                                Friday1));
                    }
                    cursor.moveToNext();
                }
            }
        }
    }
*/



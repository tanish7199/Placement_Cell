package com.gmail.placement_cell;

import android.content.ContentValues;
import android.content.Intent;
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
import com.gmail.placement_cell.database.timetableContract;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Locale;

public class facultyHomePage extends AppCompatActivity {

    TextView info;
    TextView text;
    EditText Monday1;
    EditText Tuesday1;
    EditText Wednesday1;
    EditText Thursday1;
    EditText Friday1;
    EditText Teacher1;
    String Monday;
    String Tuesday;
    String Wednesday;
    String Thursday;
    String Friday;
    String Teacher;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home_page);

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
                Toast.makeText(facultyHomePage.this, toastMessage, Toast.LENGTH_LONG).show();
            }
        });


        Submit = findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher1 = findViewById(R.id.Teacher);
                Teacher = Teacher1.getText().toString();
                if (TextUtils.isEmpty(Teacher)) {
                    Teacher1.setError("Enter your name");
                }
                database();
            }
        });
        //displayDatabaseInfo();
    }

    //DATABASE
    private void database() {

        Teacher1 = findViewById(R.id.Teacher);
        Monday1 = findViewById(R.id.Monday);
        Tuesday1 = findViewById(R.id.Tuesday);
        Wednesday1 = findViewById(R.id.Wednesday);
        Thursday1 = findViewById(R.id.Thursday);
        Friday1 = findViewById(R.id.Friday);

        Teacher = Teacher1.getText().toString().trim();
        Monday = Monday1.getText().toString().trim();
        Tuesday = Tuesday1.getText().toString().trim();
        Wednesday = Wednesday1.getText().toString().trim();
        Thursday = Thursday1.getText().toString().trim();
        Friday = Friday1.getText().toString().trim();

        ContentValues Values = new ContentValues();
        Values.put(timetableContract.timetableEntry.TEACHER_NAME, Teacher);
        Values.put(timetableContract.timetableEntry.MONDAY, Monday);
        Values.put(timetableContract.timetableEntry.TUESDAY, Tuesday);
        Values.put(timetableContract.timetableEntry.WEDNESDAY, Wednesday);
        Values.put(timetableContract.timetableEntry.THURSDAY, Thursday);
        Values.put(timetableContract.timetableEntry.FRIDAY, Friday);

        Uri newUri = getContentResolver().insert(timetableContract.timetableEntry.CONTENT_URI, Values);

        if (newUri == null) {
            Toast.makeText(this, "Error saving", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }



    /*public void displayDatabaseInfo() {
        timetableDbHelper mDbHelper = new timetableDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + timetableContract.timetableEntry.TABLE_NAME, null);
        try {
            text = findViewById(R.id.tables);
//            text.setText(cursor.getCount());
            text.setText("Number of rows in pets database table: " + cursor.getCount());
        } finally {
            cursor.close();
        }
    }
*/

    //ACCOUNT KIT LOGOUT AND LOGIN FUNCTIONALITY

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

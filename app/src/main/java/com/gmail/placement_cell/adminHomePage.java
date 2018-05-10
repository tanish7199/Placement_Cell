package com.gmail.placement_cell;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
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
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class adminHomePage extends AppCompatActivity {


    private static int currentPage = 0;
    private static ViewPager mPager;
    private static int NUM_PAGES = 0;
    TextView info;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{
            R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f,
            R.drawable.g, R.drawable.h
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        info = findViewById(R.id.info);
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();
        init();
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
                Intent i = new Intent(adminHomePage.this, showStudentDetails.class);
                startActivity(i);
            }
        });
        Button assign = findViewById(R.id.assignDuties);
        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(adminHomePage.this,"Development in progress",Toast.LENGTH_SHORT).show();
            }
        });
        Button add = findViewById(R.id.addCompany);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(adminHomePage.this,"Development in progress",Toast.LENGTH_SHORT).show();
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

    private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new slideAdapter(adminHomePage.this, imageModelArrayList));

        CirclePageIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
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



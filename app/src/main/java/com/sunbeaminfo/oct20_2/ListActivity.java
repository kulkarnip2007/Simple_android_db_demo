package com.sunbeaminfo.oct20_2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sunbeaminfo.oct20_2.db.DBHelper;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        readFromDatabase();
    }

    private void readFromDatabase() {
        // select PersonId, PersonName, Email, Age from Person where <selection>;
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String columns[] = {"PersonName", "Age", "Email", "PersonId"};
        Cursor cursor = db.query("Person", columns, null, null, null, null, null);

        // check if cursor is NOT empty
        if (!cursor.isAfterLast()) {

            // start pointing to the first record
            cursor.moveToFirst();

            while(!cursor.isAfterLast()) {
                int personId = cursor.getInt(cursor.getColumnIndex("PersonId"));
                String name = cursor.getString(cursor.getColumnIndex("PersonName"));
                String email = cursor.getString(cursor.getColumnIndex("Email"));
                int age = cursor.getInt(cursor.getColumnIndex("Age"));

                Log.e(TAG, "Person Id: " + personId);
                Log.e(TAG, "Name: " + name);
                Log.e(TAG, "Email: " + email);
                Log.e(TAG, "Age: " + age);

                cursor.moveToNext();
            }

        }

        cursor.close();
        db.close();
    }
}

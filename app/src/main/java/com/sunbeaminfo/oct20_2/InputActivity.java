package com.sunbeaminfo.oct20_2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sunbeaminfo.oct20_2.db.DBHelper;

public class InputActivity extends AppCompatActivity {

    EditText editName, editEmail, editAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editAge = (EditText) findViewById(R.id.editAge);
    }

    public void save(View v) {
        if (editName.getText().toString().length() == 0) {
            Toast.makeText(this, "Please Enter name", Toast.LENGTH_SHORT).show();
        } else if (editEmail.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        } else if (editAge.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter age", Toast.LENGTH_SHORT).show();
        } else {
            // insert

            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            // insert into Person (PersonName, Email, Age) values ('person1', 'person1@test.com', 45 );

            ContentValues values = new ContentValues();
            values.put("PersonName", editName.getText().toString());
            values.put("Email", editEmail.getText().toString());
            values.put("Age", Integer.parseInt(editAge.getText().toString()));
            db.insert("Person", null, values);


            db.close();
        }
    }
}

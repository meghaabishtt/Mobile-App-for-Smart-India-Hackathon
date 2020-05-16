package com.prince.women_security;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    EditText mEditText1, mEditText2, mEditText3, mEditText4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        mEditText1=findViewById(R.id.editTextPhoneNo1);
        mEditText2=findViewById(R.id.editTextPhoneNo2);
        mEditText3=findViewById(R.id.editTextPhoneNo3);
        mEditText4=findViewById(R.id.editTextSMS);

        mEditText1.setText(sharedPreferences.getString("string_et1",""));
        mEditText2.setText(sharedPreferences.getString("string_et2",""));
        mEditText3.setText(sharedPreferences.getString("string_et3",""));
        mEditText4.setText(sharedPreferences.getString("string_et4",""));

        Button save=findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this, "Thanks ,Your Data is Saved !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public void saveData(){
        savePreferences("string_et1", mEditText1.getText().toString());
        savePreferences("string_et2", mEditText2.getText().toString());
        savePreferences("string_et3", mEditText3.getText().toString());
        savePreferences("string_et4", mEditText4.getText().toString());

    }
    @Override
    public void onBackPressed(){
        saveData();
        super.onBackPressed();
    }
}
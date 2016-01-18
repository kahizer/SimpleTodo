package com.example.jvillanueva.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    private final int RESULT_OK = 10;
    int position;
    String currentText;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.currentText = this.getIntent().getStringExtra("text");
        this.position = getIntent().getIntExtra("position", 0);

        editText = (EditText)findViewById(R.id.editText);
        editText.setText(currentText);

        int cursorPosition = currentText.length();
        Editable etext = editText.getText();
        Selection.setSelection(etext, cursorPosition);
    }

    public void OnSaveText(View view) {
        editText = (EditText)findViewById(R.id.editText);
        String updatedText = editText.getText().toString();
        Intent data = new Intent();
        data.putExtra("newText", updatedText);
        data.putExtra("position", this.position);
        setResult(0, data);
        finish();
    }

    public void OnCancel(View view) {
        Intent data = new Intent();
        setResult(-1, data);
        finish();
    }
}

package org.sun.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity {

    private EditText edit_startX;
    private EditText edit_startY;
    private EditText edit_referX;
    private EditText edit_referY;
    private EditText edit_endX;
    private EditText edit_endY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        initViews();
    }

    private void initViews() {
        edit_startX = (EditText) findViewById(R.id.edit_startX);
        edit_startY = (EditText) findViewById(R.id.edit_startY);
        edit_referX = (EditText) findViewById(R.id.edit_referX);
        edit_referY = (EditText) findViewById(R.id.edit_referY);
        edit_endX = (EditText) findViewById(R.id.edit_endX);
        edit_endY = (EditText) findViewById(R.id.edit_endY);
    }

    public void optionOk(View v) {
        try {
            Intent intent = new Intent();
            intent.putExtra("StartX", Float.parseFloat(edit_startX.getText().toString()));
            intent.putExtra("StartY", Float.parseFloat(edit_startY.getText().toString()));
            intent.putExtra("ReferX", Float.parseFloat(edit_referX.getText().toString()));
            intent.putExtra("ReferY", Float.parseFloat(edit_referY.getText().toString()));
            intent.putExtra("EndX", Float.parseFloat(edit_endX.getText().toString()));
            intent.putExtra("EndY", Float.parseFloat(edit_endY.getText().toString()));
            setResult(RESULT_OK, intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "不能有参数为空", 1).show();
        }
    }
}

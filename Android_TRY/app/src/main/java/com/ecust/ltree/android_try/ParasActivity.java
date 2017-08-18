package com.ecust.ltree.android_try;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by LTREE on 2016-05-28.
 */
public class ParasActivity extends AppCompatActivity {

    private Button btnOK;
    private EditText editTextMin, editTextMax;
    private int rangeMin, rangeMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_paras);

        btnOK = (Button)findViewById(R.id.btnOk);
        editTextMin = (EditText)findViewById(R.id.editTextMin);
        editTextMax = (EditText)findViewById(R.id.editTextMax);
        btnListener btnOkListener = new btnListener();
        btnOK.setOnClickListener(btnOkListener);
    }

    class btnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            try {
                rangeMin = Integer.parseInt(editTextMin.getText().toString());
                rangeMax = Integer.parseInt(editTextMax.getText().toString());
            }
            catch (Exception ex){
                rangeMin = 1;
                rangeMax = 100;
            }

            Intent intent = new Intent(ParasActivity.this , MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("rMin", rangeMin);
            bundle.putInt("rMax", rangeMax);
            intent.putExtras(bundle);
            startActivity(intent);

            finish();
        }
    }
}

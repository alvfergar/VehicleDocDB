package com.app.vehicledocdb.vehicledocdb;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.vehicledocdb.vehicledocdb.greendaomodel.DaoSession;
import com.app.vehicledocdb.vehicledocdb.greendaomodel.IncidentDao;
import com.app.vehicledocdb.vehicledocdb.model.Incident;
import com.app.vehicledocdb.vehicledocdb.util.BuildDates;
import com.app.vehicledocdb.vehicledocdb.util.DbConnection;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class IncidentActivity extends AppCompatActivity {

    private DaoSession daoSession;
    private IncidentDao incidentDao;

    private Button mButtonCreate;
    private EditText  inputIncidentDescription,
            inputIncidentDate, inputIncidentPrice;
    private TextInputLayout  inputLayoutIncidentDescription,
            inputLayoutIncidentDate, inputLayoutIncidentPrice;

    private DatePickerDialog alarmDatePickerDialog;
    private SimpleDateFormat dateFormatter;


    private RadioGroup radioGroupIncidentName;
    private String mIncidentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident);


        radioGroupIncidentName = (RadioGroup) findViewById(R.id.radioGroupIncidentName);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        
        inputLayoutIncidentDescription = (TextInputLayout)
                findViewById(R.id.input_layout_incident_description);
        inputLayoutIncidentDate = (TextInputLayout)
                findViewById(R.id.input_layout_incident_date);
        inputLayoutIncidentPrice = (TextInputLayout)
                findViewById(R.id.input_layout_incident_price);


        inputIncidentDescription = (EditText) findViewById(R.id.input_incident_description);
        inputIncidentDate = (EditText) findViewById(R.id.input_incident_date);
        inputIncidentPrice = (EditText) findViewById(R.id.input_incident_price);


        // Alarm date picker will be created here, and we will set value to our edittext
        Calendar newCalendar = Calendar.getInstance();
        alarmDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                inputIncidentDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        inputIncidentDescription.addTextChangedListener(new IncidentTextWatcher(inputIncidentDescription));
        inputIncidentDate.addTextChangedListener(new IncidentTextWatcher(inputIncidentDate));
        inputIncidentDate.setOnTouchListener(new RequirementAlarmDateOnTouchListener(inputIncidentDate));
        inputIncidentPrice.addTextChangedListener(new IncidentTextWatcher(inputIncidentPrice));
        
        daoSession = DbConnection.getDaoSession(this);

        incidentDao = daoSession.getIncidentDao();

        mButtonCreate = (Button) findViewById(R.id.button_incident_create);

        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitForm();
            }
        });

    }

    private void submitForm() {

        String dateTextToPersist;

        if (!isValidateRadioName()){
            return;
        }

        if (!isValidateAddress()) {
            return;
        }

        if (!isValidateDate()) {
            return;
        }

        if (!isValidatePrice()) {
            return;
        }

        //We need to convert date from UI format to DB format
        dateTextToPersist = BuildDates.convertDayMonthYearToYearMonthDay(inputIncidentDate.getText().toString());


        Incident incidentToPersist = new Incident();
        //incidentToPersist.setName(inputIncidentName.getText().toString());
        incidentToPersist.setName(mIncidentName.toString());
        incidentToPersist.setDescription(inputIncidentDescription.getText().toString());
        incidentToPersist.setDate(dateTextToPersist);
        incidentToPersist.setPrice(Double.valueOf(inputIncidentPrice.getText().toString()));

        incidentDao.insert(incidentToPersist);
        DbConnection.closeDb();
        Toast.makeText(this, "New Incident Created", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //We need to close this activity because it's a create method and we don't want
        //to navigate here across other activities with back button
        finish();
    }


    private boolean isValidateRadioName(){
        int selectedRadioButtonID = radioGroupIncidentName.getCheckedRadioButtonId();
        boolean result = true;

        if (selectedRadioButtonID != -1){
//          we need to get name of field by tag, because text field is translated.
            RadioButton radioButtonNameSelected = (RadioButton) findViewById(selectedRadioButtonID);
            mIncidentName = radioButtonNameSelected.getTag().toString();
        }else{
            result = false;
        }

        return result;
    }

    private boolean isValidateAddress() {
        String address = inputIncidentDescription.getText().toString().trim();
        boolean result = true;
        if (address.isEmpty()) {
            inputLayoutIncidentDescription.setError(getString(R.string.incident_error));
            requestFocus(inputIncidentDescription);
            result = false;
        } else {
            inputLayoutIncidentDescription.setErrorEnabled(false);
        }
        return result;
    }

    private boolean isValidateDate() {
        String date = inputIncidentDate.getText().toString().trim();
        boolean result = true;
        if (date.isEmpty()) {
            inputLayoutIncidentDate.setError(getString(R.string.incident_error));
            requestFocus(inputIncidentDate);
            result = false;
        } else {
            inputLayoutIncidentDate.setErrorEnabled(false);
        }
        return result;
    }

    private boolean isValidatePrice() {
        String price = inputIncidentPrice.getText().toString();
        boolean result = true;
        if (price.isEmpty()) {
            inputLayoutIncidentPrice.setError(getString(R.string.incident_error));
            requestFocus(inputIncidentPrice);
            result = false;
        } else {
            inputLayoutIncidentPrice.setErrorEnabled(false);
        }
        return result;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class IncidentTextWatcher implements TextWatcher{

        private View view;

        public IncidentTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //we need to filter which is changed
            switch (view.getId()) {
                case R.id.input_incident_description:
                    isValidateAddress();
                    break;
                case R.id.input_incident_date:
                    isValidateDate();
                    break;
                case R.id.input_incident_price:
                    isValidatePrice();
                    break;
            }
        }
    }

    private class RequirementAlarmDateOnTouchListener implements View.OnTouchListener {

        private View view;

        public RequirementAlarmDateOnTouchListener(View view) {
            this.view = view;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v == inputIncidentDate) {
                alarmDatePickerDialog.show();
            }
            return false;
        }
    }
}

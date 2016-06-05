package com.app.vehicledocdb.vehicledocdb;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IncidentActivity extends AppCompatActivity {

    private Button mButtonCreate;
    private EditText inputIncidentName, inputIncidentAddress,
            inputIncidentDate, inputIncidentPrice;
    private TextInputLayout inputLayoutIncidentName, inputLayoutIncidentAddress,
            inputLayoutIncidentDate, inputLayoutIncidentPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident);

        inputLayoutIncidentName = (TextInputLayout)
                findViewById(R.id.input_layout_incident_name);
        inputLayoutIncidentAddress = (TextInputLayout)
                findViewById(R.id.input_layout_incident_address);
        inputLayoutIncidentDate = (TextInputLayout)
                findViewById(R.id.input_layout_incident_date);
        inputLayoutIncidentPrice = (TextInputLayout)
                findViewById(R.id.input_layout_incident_price);

        inputIncidentName = (EditText) findViewById(R.id.input_incident_name);
        inputIncidentAddress = (EditText) findViewById(R.id.input_incident_address);
        inputIncidentDate = (EditText) findViewById(R.id.input_incident_date);
        inputIncidentPrice = (EditText) findViewById(R.id.input_incident_price);

        inputIncidentName.addTextChangedListener(new IncidentTextWatcher(inputIncidentName));
        inputIncidentAddress.addTextChangedListener(new IncidentTextWatcher(inputIncidentAddress));
        inputIncidentDate.addTextChangedListener(new IncidentTextWatcher(inputIncidentDate));
        inputIncidentPrice.addTextChangedListener(new IncidentTextWatcher(inputIncidentPrice));

        mButtonCreate = (Button) findViewById(R.id.button_incident_create);

        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

    }

    private void submitForm() {
        if (!isValidateName()) {
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

        Toast.makeText(getApplicationContext(), "Created!", Toast.LENGTH_SHORT).show();
    }

    private boolean isValidateName() {
        String name = inputIncidentName.getText().toString().trim();
        boolean result = true;
        if (name.isEmpty()) {
            inputLayoutIncidentName.setError(getString(R.string.incident_error));
            requestFocus(inputIncidentName);
            result = false;
        } else {
            inputLayoutIncidentName.setErrorEnabled(false);
        }
        return result;
    }

    private boolean isValidateAddress() {
        String address = inputIncidentAddress.getText().toString().trim();
        boolean result = true;
        if (address.isEmpty()) {
            inputLayoutIncidentAddress.setError(getString(R.string.incident_error));
            requestFocus(inputIncidentAddress);
            result = false;
        } else {
            inputLayoutIncidentAddress.setErrorEnabled(false);
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
                case R.id.input_incident_name:
                    isValidateName();
                    break;
                case R.id.input_incident_address:
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


}

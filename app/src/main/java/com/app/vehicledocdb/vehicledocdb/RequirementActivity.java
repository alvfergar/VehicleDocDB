package com.app.vehicledocdb.vehicledocdb;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import com.app.vehicledocdb.vehicledocdb.greendaomodel.DaoMaster;
import com.app.vehicledocdb.vehicledocdb.greendaomodel.DaoSession;
import com.app.vehicledocdb.vehicledocdb.greendaomodel.RequirementDao;
import com.app.vehicledocdb.vehicledocdb.model.Requirement;

public class RequirementActivity extends AppCompatActivity {


    public DaoSession daoSession;
    public DaoMaster daoMaster;
    private SQLiteDatabase db;
    private RequirementDao requirementDao;

    private Button mButtonCreate;
    private EditText inputRequirementName, inputRequirementDate;
    private TextInputLayout inputLayoutRequirementName, inputLayoutRequirementDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement);

        inputLayoutRequirementName = (TextInputLayout)
                findViewById(R.id.input_layout_requirement_name);
        inputLayoutRequirementDate = (TextInputLayout)
                findViewById(R.id.input_layout_requirement_date);


        inputRequirementName = (EditText) findViewById(R.id.input_requirement_name);
        inputRequirementDate = (EditText) findViewById(R.id.input_requirement_date);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "vehicle-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        requirementDao = daoSession.getRequirementDao();

        inputRequirementName.addTextChangedListener(new RequirementTextWatcher(inputRequirementName));
        inputRequirementDate.addTextChangedListener(new RequirementTextWatcher(inputRequirementDate));

        mButtonCreate = (Button) findViewById(R.id.button_requirement_create);

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

        if (!isValidateDate()) {
            return;
        }

        Requirement requirementToPersist = new Requirement();
        requirementToPersist.setName(inputRequirementName.getText().toString());
        requirementToPersist.setEndDate(inputRequirementDate.getText().toString());

        requirementDao.insert(requirementToPersist);
        Toast.makeText(this, "New Requirement Created", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean isValidateName() {
        String name = inputRequirementName.getText().toString().trim();
        boolean result = true;
        if (name.isEmpty()) {
            inputLayoutRequirementName.setError(getString(R.string.incident_error));
            requestFocus(inputRequirementName);
            result = false;
        } else {
            inputLayoutRequirementName.setErrorEnabled(false);
        }
        return result;
    }


    private boolean isValidateDate() {
        String date = inputRequirementDate.getText().toString().trim();
        boolean result = true;
        if (date.isEmpty()) {
            inputLayoutRequirementDate.setError(getString(R.string.incident_error));
            requestFocus(inputRequirementDate);
            result = false;
        } else {
            inputLayoutRequirementDate.setErrorEnabled(false);
        }
        return result;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class RequirementTextWatcher implements TextWatcher {

        private View view;

        public RequirementTextWatcher(View view) {
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
                case R.id.input_requirement_name:
                    isValidateName();
                    break;
                case R.id.input_requirement_date:
                    isValidateDate();
                    break;
            }
        }

    }
}

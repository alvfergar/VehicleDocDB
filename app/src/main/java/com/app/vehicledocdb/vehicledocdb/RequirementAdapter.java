package com.app.vehicledocdb.vehicledocdb;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.app.vehicledocdb.vehicledocdb.model.Requirement;
import com.app.vehicledocdb.vehicledocdb.util.BuildDates;

import java.util.List;

/**
 * Created by Alvaro on 05/06/2016.
 */
public class RequirementAdapter extends ArrayAdapter<Requirement> {

    private List<Requirement> requirementList;
    private Activity context;
    private int layout_item_requirement;

    public RequirementAdapter(Activity context, int layout_item_requirement, List<Requirement> requirementList) {
        super(context, layout_item_requirement, requirementList);

        this.context = context;
        this.layout_item_requirement = layout_item_requirement;
        this.requirementList = requirementList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layout_item_requirement, null);

        String dateToView;

        final Requirement requirementInstance = requirementList.get(position);

        TextView textRequirementName = (TextView) convertView.findViewById(R.id.textViewRequirementName);
        TextView textRequirementDate = (TextView) convertView.findViewById(R.id.textViewRequirementDate);
        final Switch switchRequirementAlarm = (Switch) convertView.findViewById(R.id.switchAlarmRequirement);


        switchRequirementAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchRequirementAlarm.isChecked()) {
                    Log.wtf("ALARM", "switch activado para alarma " + requirementInstance.getName());
                }else{
                    Log.wtf("ALARM", "switch desactivado para alarma " + requirementInstance.getName());
                }
            }
        });

        //Date format conversion between DataBase and UI
        dateToView = BuildDates.convertYearMonthDayToDayMonthYear(requirementInstance.getEndDate());

        textRequirementName.setText(requirementInstance.getName());
        textRequirementDate.setText(dateToView);
        switchRequirementAlarm.setChecked(requirementInstance.getEnabled());

        return convertView;
    }
}

package com.app.vehicledocdb.vehicledocdb;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.vehicledocdb.vehicledocdb.model.Requirement;

import java.util.List;

/**
 * Created by Alvaro on 05/06/2016.
 */
public class RequirementAdapter extends ArrayAdapter<Requirement>{

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

        Requirement requirementInstance = requirementList.get(position);

        TextView textRequirementName = (TextView) convertView.findViewById(R.id.textViewRequirementName);
        TextView textRequirementDate = (TextView) convertView.findViewById(R.id.textViewRequirementDate);

        textRequirementName.setText(requirementInstance.getName());
        textRequirementDate.setText(requirementInstance.getEndDate());

        return convertView;
    }
}

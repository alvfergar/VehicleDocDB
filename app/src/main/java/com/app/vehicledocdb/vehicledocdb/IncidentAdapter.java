package com.app.vehicledocdb.vehicledocdb;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.vehicledocdb.vehicledocdb.model.Incident;

import java.util.List;

/**
 * Created by Alvaro on 13/06/2016.
 */
public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder> {


    private List<Incident> incidentList;

    public class IncidentViewHolder extends RecyclerView.ViewHolder{
        public TextView descriptionTextView, dateTextView, priceTextView;

        public IncidentViewHolder(View view) {
            super(view);
            dateTextView = (TextView) view.findViewById(R.id.dateIncidentText);
            priceTextView = (TextView) view.findViewById(R.id.priceIncidentText);
            descriptionTextView = (TextView) view.findViewById(R.id.descriptionIncidentText);
        }
    }

    public IncidentAdapter (List<Incident> incidentList){
        this.incidentList = incidentList;
    }

    @Override
    public IncidentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_incident, parent, false);
        return new IncidentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IncidentAdapter.IncidentViewHolder holder, int position) {
        Incident incident = incidentList.get(position);
        holder.dateTextView.setText(incident.getDate());
        holder.priceTextView.setText(incident.getPrice().toString() + " €");
        holder.descriptionTextView.setText(incident.getDescription());

    }

    @Override
    public int getItemCount() {
        return incidentList.size();
    }
}

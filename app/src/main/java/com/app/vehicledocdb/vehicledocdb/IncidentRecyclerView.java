package com.app.vehicledocdb.vehicledocdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;
import android.widget.Toast;

import com.app.vehicledocdb.vehicledocdb.greendaomodel.IncidentDao;
import com.app.vehicledocdb.vehicledocdb.model.Incident;
import com.app.vehicledocdb.vehicledocdb.util.DbConnection;

import java.util.ArrayList;
import java.util.List;

public class IncidentRecyclerView extends AppCompatActivity {

    private List<Incident> incidentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private IncidentAdapter mAdapter;
    private TextView incidentTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_recycler_view);

        Intent intent = getIntent();
        String incidentChartExtra = intent.getStringExtra("incidentName");
        String[] incidentSplitted = incidentChartExtra.split(":");
        String incidentName = incidentSplitted[0];


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        incidentTitleTextView = (TextView) findViewById(R.id.incidentTitle);

        incidentTitleTextView.setText(incidentName.toString());

        incidentList = DbConnection.getDaoSession(getApplicationContext())
                .getIncidentDao()
                .queryBuilder()
                .where(IncidentDao.Properties.Name.eq(incidentName))
                .orderAsc(IncidentDao.Properties.Date)
                .list();

        DbConnection.closeDb();
        mAdapter = new IncidentAdapter(incidentList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                Incident incidentToDelete = incidentList.get(viewHolder.getAdapterPosition());
                DbConnection.getDaoSession(getApplicationContext()).getIncidentDao().delete(incidentToDelete);
                DbConnection.closeDb();
                Toast.makeText(IncidentRecyclerView.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        mAdapter.notifyDataSetChanged();
    }

}

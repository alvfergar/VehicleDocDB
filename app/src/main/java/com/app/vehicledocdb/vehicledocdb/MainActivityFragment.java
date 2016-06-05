package com.app.vehicledocdb.vehicledocdb;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.app.vehicledocdb.vehicledocdb.greendaomodel.DaoMaster;
import com.app.vehicledocdb.vehicledocdb.greendaomodel.DaoSession;
import com.app.vehicledocdb.vehicledocdb.greendaomodel.RequirementDao;
import com.app.vehicledocdb.vehicledocdb.model.Requirement;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RequirementDao requirementDao;
    private List<Requirement> requirementList;
    private Requirement requirement;
    private RequirementAdapter requirementAdapter;

    public DaoSession daoSession;
    public DaoMaster daoMaster;
    private SQLiteDatabase db;

    private ListView mListView;
    private Button mRequerimentButton;
    private Button mIncidentButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listView);
        mRequerimentButton = (Button) rootView.findViewById(R.id.requirementButton);
        mIncidentButton = (Button) rootView.findViewById(R.id.incidentButton);

        mRequerimentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RequirementActivity.class);
                startActivity(intent);
            }
        });

        mIncidentButton.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               Intent intent = new Intent(getActivity(), IncidentActivity.class);
               startActivity(intent);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Requirement selectedRequirement = (Requirement) requirementList.get(position);
                //Intent
            }
        });

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "vehicle-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        requirementDao = daoSession.getRequirementDao();
        requirementList = requirementDao.loadAll();
        requirementAdapter = new RequirementAdapter(getActivity(), R.layout.list_item_requirement, requirementList);

        mListView.setAdapter(requirementAdapter);

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            requirementAdapter.clear();
            requirementAdapter.addAll(requirementDao.loadAll());
            requirementAdapter.notifyDataSetChanged();
        }
    }
}

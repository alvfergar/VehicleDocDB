package com.app.vehicledocdb.vehicledocdb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.app.vehicledocdb.vehicledocdb.data.DataDummy;
import com.app.vehicledocdb.vehicledocdb.model.Requirement;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ListView mListView;
    private Button mRequerimentButton;
    private Button mIncidentButton;
    private ArrayAdapter mListAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listView);
        mRequerimentButton = (Button) rootView.findViewById(R.id.requirementButton);
        mIncidentButton = (Button) rootView.findViewById(R.id.incidentButton);

        mListAdapter = new ArrayAdapter<Requirement>(getContext(), android.R.layout.simple_list_item_1, DataDummy.getInstance().getListRequirements());
        mListView.setAdapter(mListAdapter);

        return rootView;
    }
}

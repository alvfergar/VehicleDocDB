package com.app.vehicledocdb.vehicledocdb.data;

import com.app.vehicledocdb.vehicledocdb.model.Requirement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvaro on 04/06/2016.
 */
public class DataDummy {


    private static final DataDummy INSTANCE = new DataDummy();

    private List<Requirement> listRequirements;
    private DataDummy() {

        listRequirements = new ArrayList<Requirement>();

        listRequirements.add(new Requirement("Seguro", "2016/08/12"));
        listRequirements.add(new Requirement("Itv", "2017/02/06"));
    }

    public static DataDummy getInstance() {
        return INSTANCE;
    }

    public List<Requirement> getListRequirements(){

        return listRequirements;
    }

}

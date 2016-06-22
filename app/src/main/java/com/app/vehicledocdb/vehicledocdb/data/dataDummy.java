package com.app.vehicledocdb.vehicledocdb.data;

import com.app.vehicledocdb.vehicledocdb.model.Incident;
import com.app.vehicledocdb.vehicledocdb.model.Requirement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvaro on 04/06/2016.
 */
public class DataDummy {


    private static final DataDummy INSTANCE = new DataDummy();

    private List<Requirement> listRequirements;
    private List<Incident> listIncidents;

    private DataDummy() {

        listRequirements = new ArrayList<Requirement>();
        listIncidents = new ArrayList<Incident>();

        listRequirements.add(new Requirement(null, "Seguro", "2016/08/12", Boolean.FALSE));
        listRequirements.add(new Requirement(null, "Itv", "2017/02/06", Boolean.TRUE));
        listRequirements.add(new Requirement(null, "Itv", "2017/04/15", Boolean.FALSE));

        listIncidents.add(new Incident(null, "Repair", "Calle Aire", "2016/06/05", 59.6));
        listIncidents.add(new Incident(null, "Inspection", "Av. Socorro", "2016/05/03", 120.60));
        listIncidents.add(new Incident(null, "Gas", "Calle Becas", "2016/05/03", 20.0));
        listIncidents.add(new Incident(null, "Gas", "Calle Almansa", "2016/04/03", 50.0));
        listIncidents.add(new Incident(null, "Gas", "Calle Trujillo", "2016/04/18", 63.5));

    }

    public static DataDummy getInstance() {
        return INSTANCE;
    }

    public List<Requirement> getListRequirements(){

        return listRequirements;
    }

    public List<Incident> getListIncidents(){

        return listIncidents;
    }

}

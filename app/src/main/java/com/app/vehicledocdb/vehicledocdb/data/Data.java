package com.app.vehicledocdb.vehicledocdb.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.app.vehicledocdb.vehicledocdb.greendaomodel.IncidentDao;
import com.app.vehicledocdb.vehicledocdb.greendaomodel.RequirementDao;
import com.app.vehicledocdb.vehicledocdb.model.Incident;
import com.app.vehicledocdb.vehicledocdb.model.Requirement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvaro on 05/06/2016.
 */
public class Data {

    public static SQLiteDatabase db;

    public static void generateDataTest(SQLiteDatabase db){
        Data.db = db;
        List<ContentValues> contentValuesRequirementList = new ArrayList<>();
        List<ContentValues> contentValuesIncidentList = new ArrayList<>();
        ContentValues contentValues;
        
        List<Requirement> requirementList = DataDummy.getInstance().getListRequirements();
        List<Incident> incidentList = DataDummy.getInstance().getListIncidents();

        for(Requirement req: requirementList){
            contentValues = new ContentValues();
            contentValues.put(RequirementDao.Properties.Name.columnName, req.getName());
            contentValues.put(RequirementDao.Properties.EndDate.columnName, req.getEndDate());
            contentValuesRequirementList.add(contentValues);
            db.insert(RequirementDao.TABLENAME, "", contentValues);
        }

        for(Incident req: incidentList){
            contentValues = new ContentValues();
            contentValues.put(IncidentDao.Properties.Name.columnName, req.getName());
            contentValues.put(IncidentDao.Properties.Description.columnName, req.getDescription());
            contentValues.put(IncidentDao.Properties.Date.columnName, req.getDate());
            contentValues.put(IncidentDao.Properties.Price.columnName, req.getPrice());
            contentValuesIncidentList.add(contentValues);
            db.insert(IncidentDao.TABLENAME, "", contentValues);
        }

    }
}

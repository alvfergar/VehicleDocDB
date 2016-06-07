package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


/**
 * Para ejecutar esto y que genere los datos tenemos que darle a gradle projects entramos en
 * greendaogen-->task-->application--> run y ya generará en la ruta los archivos
 */
public class GeneratorMain {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.app.vehicledocdb.vehicledocdb.greendaomodel");

        Entity requirement = createRequirementTable(schema);
        Entity incident = createIncidentTable(schema);

//        Property personId = lease.addLongProperty("personId").getProperty();
//        lease.addToOne(person, personId);
//
//        ToMany personToLease = person.addToMany(lease, personId);
//        personToLease.setName("leases");

        new DaoGenerator().generateAll(schema, "../app/src/main/java");
    }

    private static Entity createIncidentTable(Schema schema) {
        Entity incident = schema.addEntity("Incident");
        incident.addIdProperty();
        incident.addStringProperty("name");
        incident.addStringProperty("description");
        incident.addStringProperty("date");
        incident.addDoubleProperty("price");
        return incident;
    }

    private static Entity createRequirementTable(Schema schema) {
        Entity requirement = schema.addEntity("Requirement");
        requirement.addIdProperty();
        requirement.addStringProperty("name");
        requirement.addStringProperty("endDate");
        return requirement;
    }
}

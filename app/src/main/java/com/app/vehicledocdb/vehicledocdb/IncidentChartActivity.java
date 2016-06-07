package com.app.vehicledocdb.vehicledocdb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.vehicledocdb.vehicledocdb.greendaomodel.DaoSession;
import com.app.vehicledocdb.vehicledocdb.greendaomodel.IncidentDao;
import com.app.vehicledocdb.vehicledocdb.model.Incident;
import com.app.vehicledocdb.vehicledocdb.util.DbConnection;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentChartActivity extends AppCompatActivity {

    private PieChart mChart;
    //private float[] yData = {25, 20, 35};
    //private String[] xData = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_chart);

        // in this example, a LineChart is initialized from xml
        mChart = (PieChart) findViewById(R.id.chart);

        // configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("Incidents");

        // enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleRadius(25);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        // add data
        addData();

        // customize legends
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        List<Incident> incidentList = new ArrayList<>();
        Map<String, Double> sumPriceByIncident = new HashMap<>();
        int index = 0;

        incidentList = getIncidentsInDB();
        sumPriceByIncident = sumIncidentPriceWithSameName(incidentList);

        for (Map.Entry<String, Double>  element: sumPriceByIncident.entrySet() ){
            yVals1.add(new Entry(element.getValue().floatValue(), index));
            xVals.add(element.getKey()+": "+element.getValue().toString()+" €");
        }
//        for (int i = 0; i < yData.length; i++)
//            yVals1.add(new Entry(yData[i], i));
//
//
//
//        for (int i = 0; i < xData.length; i++)
//            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Vehicle Incidents");
        dataSet.setSliceSpace(5);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }

    private List<Incident> getIncidentsInDB() {
        List<Incident> incidentList = new ArrayList<>();

        DaoSession daoSession = DbConnection.getDaoSession(getApplicationContext());
        IncidentDao incidentDao = daoSession.getIncidentDao();
        incidentList = incidentDao.loadAll();

        return incidentList;
    }

    private Map<String, Double> sumIncidentPriceWithSameName(List<Incident> incidentList) {
        Map<String, List<Double>> incidentMap = new HashMap<>();

        for (Incident incident : incidentList) {
            if( incidentMap.containsKey(incident.getIncidentName())){
                incidentMap.get(incident.getIncidentName()).add(incident.getPrice());
            }else{
                incidentMap.put(incident.getIncidentName(), Lists.newArrayList(incident.getPrice()));
            }

        }

        return Maps.transformValues(incidentMap, new Function<List<Double>, Double>() {
            @Override
            public Double apply(List<Double> input) {
                Double sum = 0.0;
                for(Double element: input){
                    sum += element;
                }
                return sum;
            }
        });
    }



}


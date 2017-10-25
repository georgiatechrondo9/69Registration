package com.apatel428.a69registration.model;

/**
 * Created by HelloWorld on 10/25/2017.
 */

public abstract class AbstractReport {

    public abstract String getUniqueKey();
    public abstract String getCreatedDate();
    public abstract String getLocationType();
    public abstract String getIncidentZip();
    public abstract String getIncidentAddress();
    public abstract String getCity();
    public abstract String getBorough();
    public abstract String getLatitude();
    public abstract String getLongitude();
}
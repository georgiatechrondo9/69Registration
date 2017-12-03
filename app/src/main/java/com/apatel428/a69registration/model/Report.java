package com.apatel428.a69registration.model;

/**
 * Created by 14bpo on 10/24/2017.
 */

public class Report {
    private Long uniquekey;
    private String createddate;
    private String locationtype;
    private String incidentzip;
    private String incidentaddress;
    private String city;
    private String borough;
    private Long latitude;
    private Long longitude;

    public Report() {
    }
    public Report( Long muniquekey, String mcreateddate, String mlocationtype, String mincidentzip, String mincidentaddress, String mcity, String mborough, Long mlatitude, Long mlongitude) {
        Long uniquekey = muniquekey;
        String createddate = mcreateddate;
        String locationtype = mlocationtype;
        String incidentzip = mincidentzip ;
        String incidentaddress = mincidentaddress;
        String city = mcity;
        String borough = mborough;
        Long latitude = mlatitude;
        Long longitude = mlongitude;
    }


    @Override
    public String toString() {
        return "DataItem{" +
                "Unique_Key=" + uniquekey +
                ", Created_Date='" + createddate + '\'' +
                ", Location_Type='" + locationtype + '\'' +
                ", Incident_Zip=" + incidentzip +
                ", Incident_Address='" + incidentaddress + '\'' +
                ", City='" + city + '\'' +
                ", Borough='" + borough + '\'' +
                ", Latitude=" + latitude +
                ", Longitude=" + longitude +
                '}';
    }

    public Long getUniqueKey() {
        return uniquekey;
    }
    public String getCreatedDate() {
        return createddate;
    }
    public String getLocationType() {
        return locationtype;
    }
    public String getIncidentZip() {
        return incidentzip;
    }
    public String getIncidentAddress() {
        return incidentaddress;
    }
    public String getCity() {
        return city;
    }
    public String getBorough() {
        return borough;
    }
    public Long getLatitude() {
        return latitude;
    }
    public Long getLongitude() {
        return longitude;
    }

    public void setUniqueKey(Long unique_Key) {
        uniquekey = unique_Key;
    }
    public void setCreatedDate(String created_Date) {
        createddate = created_Date;
    }
    public void setLocationType(String location_Type) {
        locationtype = location_Type;
    }
    public void setIncidentZip(String incident_Zip) {
        incidentzip = incident_Zip;
    }
    public void setIncidentAddress(String incident_Address) {
        incidentaddress = incident_Address;
    }
    public void setCity(String City) {
        city = City;
    }
    public void setBorough(String Borough) {
        borough = Borough;
    }
    public void setLatitude(Long Latitude) {
        latitude = Latitude;
    }
    public void setLongitude(Long Longitude) {
        longitude = Longitude;
    }
}
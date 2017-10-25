package com.apatel428.a69registration.model;

/**
 * Created by 14bpo on 10/24/2017.
 */

public class Report extends AbstractReport{
    private String uniquekey;
    private String createddate;
    private String locationtype;
    private String incidentzip;
    private String incidentaddress;
    private String city;
    private String borough;
    private String latitude;
    private String longitude;

    public Report() {
    }
    public Report( String muniquekey, String mcreateddate, String mlocationtype, String mincidentzip, String mincidentaddress, String mcity, String mborough, String mlatitude, String mlongitude) {
        String uniquekey = muniquekey;
        String createddate = mcreateddate;
        String locationtype = mlocationtype;
        String incidentzip = mincidentzip ;
        String incidentaddress = mincidentaddress;
        String city = mcity;
        String borough = mborough;
        String latitude = mlatitude;
        String longitude = mlongitude;
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

    public String getUniqueKey() {
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
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }

    public void setUniqueKey(String unique_Key) {
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
    public void setLatitude(String Latitude) {
        latitude = Latitude;
    }
    public void setLongitude(String Longitude) {
        longitude = Longitude;
    }
}
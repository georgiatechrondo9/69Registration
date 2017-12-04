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
    private Double latitude;
    private Double longitude;

    public Report() {
    }
    public Report( String muniquekey, String mcreateddate, String mlocationtype, String mincidentzip, String mincidentaddress, String mcity, String mborough, Double mlatitude, Double mlongitude) {
        String uniquekey = muniquekey;
        String createddate = mcreateddate;
        String locationtype = mlocationtype;
        String incidentzip = mincidentzip ;
        String incidentaddress = mincidentaddress;
        String city = mcity;
        String borough = mborough;
        Double latitude = mlatitude;
        Double longitude = mlongitude;
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

    @Override
    public String getUniqueKey() {
        return uniquekey;
    }
    @Override
    public String getCreatedDate() {
        return createddate;
    }
    @Override
    public String getLocationType() {
        return locationtype;
    }
    @Override
    public String getIncidentZip() {
        return incidentzip;
    }
    @Override
    public String getIncidentAddress() {
        return incidentaddress;
    }
    @Override
    public String getCity() {
        return city;
    }
    @Override
    public String getBorough() {
        return borough;
    }
    @Override
    public Double getLatitude() {
        return latitude;
    }
    @Override
    public Double getLongitude() {
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
    public void setLatitude(Double Latitude) {
        latitude = Latitude;
    }
    public void setLongitude(Double Longitude) {
        longitude = Longitude;
    }
}
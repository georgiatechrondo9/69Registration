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

    /**
     * Null constructor
     */
    public Report() {
    }

    /**
     * Constructor
     *
     * @param muniquekey key
     * @param mcreateddate created date
     * @param mlocationtype location type
     * @param mincidentzip zip code
     * @param mincidentaddress address
     * @param mcity city
     * @param mborough burough
     * @param mlatitude latitude
     * @param mlongitude longitude
     */
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

    /**
     * Returns a string representation of this report.
     *
     * @return String of report
     */
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

    /**
     * Gets unique key
     *
     * @return unique key
     */
    public String getUniqueKey() {
        return uniquekey;
    }

    /**
     * Gets created date
     *
     * @return created date
     */
    public String getCreatedDate() {
        return createddate;
    }

    /**
     * Gets location type
     *
     * @return Location type
     */
    public String getLocationType() {
        return locationtype;
    }

    /**
     * Gets incident zip code
     * @return zip
     */
    public String getIncidentZip() {
        return incidentzip;
    }

    /**
     * Gets incident address
     *
     * @return address
     */
    public String getIncidentAddress() {
        return incidentaddress;
    }

    /**
     * Gets city
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets borough
     * @return burough
     */
    public String getBorough() {
        return borough;
    }

    /**
     * Gets latitude
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Gets longitude
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets unique key
     * @param unique_Key key to set
     */
    public void setUniqueKey(String unique_Key) {
        uniquekey = unique_Key;
    }

    /**
     * Sets created date
     * @param created_Date date to set
     */
    public void setCreatedDate(String created_Date) {
        createddate = created_Date;
    }

    /**
     * Sets location type
     * @param location_Type type to set
     */
    public void setLocationType(String location_Type) {
        locationtype = location_Type;
    }

    /**
     * Sets incident zip
     * @param incident_Zip zip to set
     */
    public void setIncidentZip(String incident_Zip) {
        incidentzip = incident_Zip;
    }

    /**
     * Sets incident address
     * @param incident_Address address to set
     */
    public void setIncidentAddress(String incident_Address) {
        incidentaddress = incident_Address;
    }

    /**
     * Sets city
     * @param City city to set
     */
    public void setCity(String City) {
        city = City;
    }

    /**
     * Sets burough
     * @param Borough burough to set
     */
    public void setBorough(String Borough) {
        borough = Borough;
    }

    /**
     * Sets lattitude
     * @param Latitude latitude to set
     */
    public void setLatitude(String Latitude) {
        latitude = Latitude;
    }

    /**
     * Sets longitude
     * @param Longitude longitude to set
     */
    public void setLongitude(String Longitude) {
        longitude = Longitude;
    }
}
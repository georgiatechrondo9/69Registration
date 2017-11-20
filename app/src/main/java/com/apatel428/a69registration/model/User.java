package com.apatel428.a69registration.model;

/**
 * Created by georg on 10/01/2017.
 */

public class User {

    private int id;
    private String name;
    private String email;
    private String password;

    /**
     * Gets id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email address
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email address
     * @param email email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

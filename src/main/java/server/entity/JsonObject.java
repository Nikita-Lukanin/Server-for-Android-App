package server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class JsonObject {

    private long id;
    private String uuid;
    private String key;
    private long id_connection;
    private String firstName;
    private String lastName;
    private String permission;
    private double latitude;
    private double longitude;

    // Getters & Setters

    public JsonObject() {
    }

    public JsonObject(long id) {
        this.id = id;
    }

    public JsonObject(long id, String key) {
        this.id = id;
        this.key = key;
    }

    public JsonObject(long id_connection, String firstName, String lastName) {
        this.id_connection = id_connection;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public JsonObject(long id_connection, String firstName, String lastName, double latitude, double longitude) {
        this.id_connection = id_connection;
        this.firstName = firstName;
        this.lastName = lastName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId_connection() {
        return id_connection;
    }

    public void setId_connection(long id_connection) {
        this.id_connection = id_connection;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

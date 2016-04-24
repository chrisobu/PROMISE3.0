package com.example.faars.promise30.SQL;

/**
 * Created by faars on 24-Apr-16.
 */
public class Profile {

    private int _id;
    private String _username;
    private String _password;

    public Profile(String username, String password){
        this._username = username;
        this._password = password;
    }

    public Profile(){

    }

    // Setters:
    public void set_id(int _id) {
        this._id = _id;
    }
    public void set_username(String username) {
        this._username = username;
    }
    public void set_password(String password) {
        this._password = password;
    }

    // Getters:
    public int get_id() {
        return _id;
    }
    public String get_username() { return _username; }
    public String get_password() { return _password; }
}

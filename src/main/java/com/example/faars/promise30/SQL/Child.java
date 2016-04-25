package com.example.faars.promise30.SQL;

/**
 * Created by faars on 24-Apr-16.
 */
public class Child {

    private static int _id;
    private static String _childID;
    private static String _hospitalID;
    private static String _countryID;
    private static String _termDate;
    private static String _nickName;
    private static String _profileName;

    public Child(String childID, String hospitalID, String countryID, String termDate, String nickName, String profileName ){
        this._childID = childID;
        this._hospitalID = hospitalID;
        this._countryID = countryID;
        this._termDate = termDate;
        this._nickName = nickName;
        this._profileName = profileName;
    }

    public Child(){

    }

    // Getters:
    public int get_id() {
        return _id;
    }
    public static String get_childID() {
        return _childID;
    }
    public static String get_hospitalID() {
        return _hospitalID;
    }
    public static String get_countryID() {
        return _countryID;
    }
    public static String get_termDate() {
        return _termDate;
    }
    public static String get_nickName() {
        return _nickName;
    }
    public static String get_profileName() {
        return _profileName;
    }


    // Setters:
    public void set_id(int _id) {
        this._id = _id;
    }
    public void set_childID(String _childID) {
        this._childID = _childID;
    }
    public void set_hospitalID(String _hospitalID) {
        this._hospitalID = _hospitalID;
    }
    public void set_countryID(String _countryID) {
        this._countryID = _countryID;
    }
    public void set_termDate(String _termDate) {
        this._termDate = _termDate;
    }
    public void set_nickName(String _nickName) {
        this._nickName = _nickName;
    }
    public void set_profileName(String _profileName) {
        this._profileName = _profileName;
    }
}

package com.example.faars.promise30.SQL;


public class Child {

    private static String _childID;
    private static String _hospitalID;
    private static String _countryID;
    private static String _termDate;
    private static String _nickName;
    private static String _profileName;
    private static String _videoSent;

    public Child(String childID, String hospitalID, String countryID, String termDate, String nickName, String profileName, String videoSent){
        this._childID = childID;
        this._hospitalID = hospitalID;
        this._countryID = countryID;
        this._termDate = termDate;
        this._nickName = nickName;
        this._profileName = profileName;
        this._videoSent = videoSent;
    }

    public Child(){

    }

    // Getters:
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
    public static String get_videoSent() {
        return _videoSent;
    }

    // Setters:
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
    public void set_videoSent(String _videoSent){this._videoSent = _videoSent; }
}
package com.example.faars.promise30.SQL;

/**
 * Created by faars on 03-May-16.
 */
public class Video {

    private String _filename;
    private String _sentStatus;
    private String _profileName;
    private String _childName;

    public Video(String filename, String sentStatus, String profileName, String childName) {
        this._filename = filename;
        this._sentStatus = sentStatus;
        this._profileName = profileName;
        this._childName = childName;
    }

    public Video(){

    }

    // Setters:
    public void set_filename(String _filename) {
        this._filename = _filename;
    }
    public void set_sentStatus(String _sentStatus) {
        this._sentStatus = _sentStatus;
    }
    public void set_profileName(String _profileName) {
        this._profileName = _profileName;
    }
    public void set_childName(String _childName) {
        this._childName = _childName;
    }

    // Getters:
    public String get_filename() {
        return _filename;
    }
    public String get_sentStatus() {
        return _sentStatus;
    }
    public String get_profileName() {
        return _profileName;
    }
    public String get_childName() {
        return _childName;
    }
}

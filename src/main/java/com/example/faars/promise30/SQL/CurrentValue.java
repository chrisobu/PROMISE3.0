package com.example.faars.promise30.SQL;


public class CurrentValue {

    private int _id;
    private String _name;
    private String _value;

    public CurrentValue(){

    }

    // Getters:
    public String get_name() {
        return _name;
    }
    public String get_value() {
        return _value;
    }

    // Setters:
    public void set_name(String _name) {
        this._name = _name;
    }
    public void set_value(String _value) {
        this._value = _value;
    }
}
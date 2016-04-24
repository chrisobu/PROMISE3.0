package com.example.faars.promise30.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by faars on 24-Apr-16.
 */
public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db"; // Name of the database-file
    private static MyDBHandler sInstance;

    // PROFILE TABLE:
    public static final String PROFILE_TABLE= "profiles"; // Name of the table
    public static final String COLUMN_PROFILE_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // CHILD TABLE:
    public static final String CHILD_TABLE = "children"; // Name of the table
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CHILD_ID = "childID";
    public static final String COLUMN_HOSPITAL_ID = "hospitalID";
    public static final String COLUMN_COUNTRY_ID = "countryID";
    public static final String COLUMN_TERM_DATE = "termDate";
    public static final String COLUMN_NICKNAME = "nickName";
    public static final String COLUMN_API_KEY = "apiKey";
    public static final String COLUMN_PROFILE_NAME = "profileName";

    // CURRENT VALUES TABLE:
    public static final String CURRENT_VALUES_TABLE= "currentValues"; // Name of the table
    public static final String COLUMN__VALUE_ID = "_id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_VALUE = "value";


    public static synchronized MyDBHandler getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new MyDBHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    private MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create a new profile table
        String profileQuery = "CREATE TABLE " + PROFILE_TABLE + " (" + COLUMN_PROFILE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USERNAME +
                " TEXT," + COLUMN_PASSWORD +
                " TEXT" + " )";
        db.execSQL(profileQuery);

        // create a new child table
        String childQuery = "CREATE TABLE " + CHILD_TABLE + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CHILD_ID + " TEXT, "
                + COLUMN_HOSPITAL_ID + " TEXT, " + COLUMN_COUNTRY_ID + " TEXT, "
                + COLUMN_TERM_DATE + " TEXT, " + COLUMN_NICKNAME + " TEXT, "
                + COLUMN_API_KEY + " TEXT, " + COLUMN_PROFILE_NAME + " TEXT" + " )";
        db.execSQL(childQuery);

        // create a new current values table
        String currentValuesQuery = "CREATE TABLE " + CURRENT_VALUES_TABLE + " (" + COLUMN__VALUE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME +
                " TEXT," + COLUMN_VALUE +
                " TEXT" + " )";
        db.execSQL(currentValuesQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // delete the old database
        db.execSQL("DROP TABLE IF EXISTS" + PROFILE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + CHILD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + CURRENT_VALUES_TABLE);
        // create a new database
        onCreate(db);
    }


    /** CURRENT VALUES TABLE **/
    public void createCurrentValues(){
        SQLiteDatabase db = getWritableDatabase();

        // Add a current child row:
        ContentValues loggedIn = new ContentValues();
        loggedIn.put(COLUMN_NAME, "loggedIn");
        loggedIn.put(COLUMN_VALUE, "");
        db.insert(CURRENT_VALUES_TABLE, null, loggedIn);

        // Add a current profile row:
        ContentValues profile = new ContentValues();
        profile.put(COLUMN_NAME, "profile");
        profile.put(COLUMN_VALUE, "");
        db.insert(CURRENT_VALUES_TABLE, null, profile);

        // Add a current child row:
        ContentValues child = new ContentValues();
        child.put(COLUMN_NAME, "child");
        child.put(COLUMN_VALUE, "");
        db.insert(CURRENT_VALUES_TABLE, null, child);

        // Add a current video row:
        ContentValues video = new ContentValues();
        video.put(COLUMN_NAME, "video");
        video.put(COLUMN_VALUE, "");
        db.insert(CURRENT_VALUES_TABLE, null, video);
    }

    // Update current values
    public void updateLoggedIn(String newValue){ // true if a user is logged in, else false (in string value to make it easy)
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateLoggedIn = new ContentValues();
        updateLoggedIn.put(COLUMN_NAME, "loggedIn");
        updateLoggedIn.put(COLUMN_VALUE, newValue);
        db.update(PROFILE_TABLE, updateLoggedIn, COLUMN_NAME + " = ?", new String[]{"loggedIn"});
    }
    public void updateCurrentProfile(String newValue){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateCurrentProfile = new ContentValues();
        updateCurrentProfile.put(COLUMN_NAME, "profile");
        updateCurrentProfile.put(COLUMN_VALUE, newValue);
        db.update(PROFILE_TABLE, updateCurrentProfile, COLUMN_NAME + " = ?", new String[]{"profile"});
    }
    public void updateCurrentChild(String newValue){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateCurrentChild = new ContentValues();
        updateCurrentChild.put(COLUMN_NAME, "child");
        updateCurrentChild.put(COLUMN_VALUE, newValue);
        db.update(PROFILE_TABLE, updateCurrentChild, COLUMN_NAME + " = ?", new String[]{"child"});
    }
    public void updateCurrentVideo(String newValue){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateCurrentVideo = new ContentValues();
        updateCurrentVideo.put(COLUMN_NAME, "video");
        updateCurrentVideo.put(COLUMN_VALUE, newValue);
        db.update(PROFILE_TABLE, updateCurrentVideo, COLUMN_NAME + " = ?", new String[]{"video"});
    }


    /** CHILD TABLE **/
    // Add a new row to the table
    public void addChild(Child child){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHILD_ID, child.get_childID());
        values.put(COLUMN_HOSPITAL_ID, child.get_hospitalID());
        values.put(COLUMN_COUNTRY_ID, child.get_countryID());
        values.put(COLUMN_TERM_DATE, child.get_termDate());
        values.put(COLUMN_NICKNAME, child.get_nickName());
        values.put(COLUMN_API_KEY, child.get_apiKey());
        values.put(COLUMN_PROFILE_NAME, child.get_profileName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(PROFILE_TABLE, null, values);
    }

    // Delete a row in the table
    public void deleteChild(String childID){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + CHILD_TABLE + " WHERE " + COLUMN_CHILD_ID + "=\"" + childID + "\";");
    }

    // Update a row in the table
    public void updateChild(Child child){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateChild = new ContentValues();
        updateChild.put(COLUMN_CHILD_ID, child.get_childID());
        updateChild.put(COLUMN_HOSPITAL_ID, child.get_hospitalID());
        updateChild.put(COLUMN_COUNTRY_ID, child.get_countryID());
        updateChild.put(COLUMN_TERM_DATE, child.get_termDate());
        updateChild.put(COLUMN_NICKNAME, child.get_nickName());
        updateChild.put(COLUMN_API_KEY, child.get_apiKey());
        updateChild.put(COLUMN_PROFILE_NAME, child.get_profileName());
        db.update(PROFILE_TABLE, updateChild, COLUMN_CHILD_ID + " = ?", new String[]{child.get_childID()});
    }


    /** PROFILE TABLE **/
    // Add a new row to the table
    public void addProfile(Profile profile){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, profile.get_username());
        values.put(COLUMN_PASSWORD, profile.get_password());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(PROFILE_TABLE, null, values);
    }
    // Delete a row in the table (TODO: not relevant, remove)
    public void deleteProfile(String username){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + PROFILE_TABLE + " WHERE " + COLUMN_USERNAME + "=\"" + username + "\";");
    }
    // Update a row in the table (TODO: not relevant, remove)
    public void updateProfile(Profile profile){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateProfile = new ContentValues();
        updateProfile.put(COLUMN_USERNAME, profile.get_username());
        updateProfile.put(COLUMN_PASSWORD, profile.get_password());
        db.update(PROFILE_TABLE, updateProfile, COLUMN_USERNAME +" = ?" , new String[] {profile.get_username()});
    }

    /** PRINT OUT A TABLE AS A STRING **/
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        //Cursor point to a location in you results
        Cursor c = db.query(PROFILE_TABLE, null, null, null, null, null, null);
        //move to the first row in your results
        c.moveToFirst();

        while (c.moveToNext()){
            if (c.getString(c.getColumnIndex("username")) != null){
                dbString += c.getString(c.getColumnIndex(COLUMN_USERNAME));
                dbString += "   ";
                dbString += c.getString(c.getColumnIndex(COLUMN_PASSWORD));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }
}

package com.example.faars.promise30.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


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
    public static final String COLUMN_PROFILE_NAME = "profileName";
    public static final String COLUMN_VIDEO_SENT = "videoSent";

    // VIDEO TABLE:
    public static final String VIDEO_TABLE= "videos"; // Name of the table
    public static final String COLUMN_VIDEO_ID = "_id";
    public static final String COLUMN_FILENAME = "filename";
    public static final String COLUMN_SENT_STATUS = "sent";
    public static final String COLUMN_PROFILE = "profileName";
    public static final String COLUMN_CHILD = "childName";

    // CURRENT VALUES TABLE:
    public static final String CURRENT_VALUES_TABLE= "currentValues"; // Name of the table
    public static final String COLUMN_VALUE_ID = "_id";
    public static final String COLUMN_NAME = "name";
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
                + COLUMN_PROFILE_NAME + " TEXT, "
                + COLUMN_VIDEO_SENT + " TEXT" + " )";
        db.execSQL(childQuery);

        // create a new video table
        String videoQuery = "CREATE TABLE " + VIDEO_TABLE + " (" + COLUMN_VIDEO_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FILENAME + " TEXT, "
                + COLUMN_SENT_STATUS + " TEXT, " + COLUMN_PROFILE + " TEXT, "
                + COLUMN_CHILD + " TEXT" + " )";
        db.execSQL(videoQuery);

        // create a new current values table
        String currentValuesQuery = "CREATE TABLE " + CURRENT_VALUES_TABLE + " (" + COLUMN_VALUE_ID +
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
        db.execSQL("DROP TABLE IF EXISTS" + VIDEO_TABLE);
        // create a new database
        onCreate(db);
    }


    /**------------------------ CURRENT VALUES TABLE ------------------------**/
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

        // Add a current apiKey row:
        ContentValues apiKey = new ContentValues();
        apiKey.put(COLUMN_NAME, "apiKey");
        apiKey.put(COLUMN_VALUE, "");
        db.insert(CURRENT_VALUES_TABLE, null, apiKey);
    }

    // Update current values
    public void updateLoggedIn(String newValue){ // true if a user is logged in, else false (in string value to make it easy)
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateLoggedIn = new ContentValues();
        updateLoggedIn.put(COLUMN_NAME, "loggedIn");
        updateLoggedIn.put(COLUMN_VALUE, newValue);
        db.update(CURRENT_VALUES_TABLE, updateLoggedIn, COLUMN_NAME + " = ?", new String[]{"loggedIn"});
    }
    public void updateCurrentProfile(String newValue){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateCurrentProfile = new ContentValues();
        updateCurrentProfile.put(COLUMN_NAME, "profile");
        updateCurrentProfile.put(COLUMN_VALUE, newValue);
        db.update(CURRENT_VALUES_TABLE, updateCurrentProfile, COLUMN_NAME + " = ?", new String[]{"profile"});
    }
    public void updateCurrentChild(String newValue){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateCurrentChild = new ContentValues();
        updateCurrentChild.put(COLUMN_NAME, "child");
        updateCurrentChild.put(COLUMN_VALUE, newValue);
        db.update(CURRENT_VALUES_TABLE, updateCurrentChild, COLUMN_NAME + " = ?", new String[]{"child"});
    }
    public void updateCurrentVideo(String newValue){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateCurrentVideo = new ContentValues();
        updateCurrentVideo.put(COLUMN_NAME, "video");
        updateCurrentVideo.put(COLUMN_VALUE, newValue);
        db.update(CURRENT_VALUES_TABLE, updateCurrentVideo, COLUMN_NAME + " = ?", new String[]{"video"});
    }
    public void updateCurrentAPIkey(String newValue){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateCurrentAPIkey = new ContentValues();
        updateCurrentAPIkey.put(COLUMN_NAME, "apiKey");
        updateCurrentAPIkey.put(COLUMN_VALUE, newValue);
        db.update(CURRENT_VALUES_TABLE, updateCurrentAPIkey, COLUMN_NAME + " = ?", new String[]{"apiKey"});
    }
    public String getCurrentChild(){
        SQLiteDatabase db = getReadableDatabase();
        String currentChild;

        Cursor c = db.query(CURRENT_VALUES_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ){
            if(c.getString(c.getColumnIndex(COLUMN_NAME)).equals("child")){
                currentChild = c.getString(c.getColumnIndex(COLUMN_VALUE));
                return currentChild;
            }
        }
        return null;
    }
    public String getCurrentProfile(){
        SQLiteDatabase db = getReadableDatabase();
        String currentProfile;

        Cursor c = db.query(CURRENT_VALUES_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ){
            if(c.getString(c.getColumnIndex(COLUMN_NAME)).equals("profile")){
                currentProfile = c.getString(c.getColumnIndex(COLUMN_VALUE));
                return currentProfile;
            }
        }
        return null;
    }
    public String getCurrentVideo(){
        SQLiteDatabase db = getReadableDatabase();
        String currentVideo;

        Cursor c = db.query(CURRENT_VALUES_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ){
            if(c.getString(c.getColumnIndex(COLUMN_NAME)).equals("video")){
                currentVideo = c.getString(c.getColumnIndex(COLUMN_VALUE));
                return currentVideo;
            }
        }
        return null;
    }
    public String getCurrentAPIkey(){
        SQLiteDatabase db = getReadableDatabase();
        String currentAPI;

        Cursor c = db.query(CURRENT_VALUES_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ){
            if(c.getString(c.getColumnIndex(COLUMN_NAME)).equals("apiKey")){
                currentAPI = c.getString(c.getColumnIndex(COLUMN_VALUE));
                return currentAPI;
            }
        }
        return null;
    }
    public Boolean isLoggedIn(){
        SQLiteDatabase db = getReadableDatabase();
        Boolean loggedIn;

        Cursor c = db.query(CURRENT_VALUES_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ){
            if(c.getString(c.getColumnIndex(COLUMN_NAME)).equals("loggedIn")) {
                loggedIn = Boolean.valueOf(c.getString(c.getColumnIndex(COLUMN_VALUE)));
                return loggedIn;
            }
        }
        return null;
    }


    /**------------------------ VIDEO TABLE ------------------------**/
    // Add a new row to the table
    public void addVideo(Video video){
        ContentValues values = new ContentValues();
        values.put(COLUMN_FILENAME, video.get_filename());
        values.put(COLUMN_SENT_STATUS, video.get_sentStatus());
        values.put(COLUMN_PROFILE, video.get_profileName());
        values.put(COLUMN_CHILD, video.get_childName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(VIDEO_TABLE, null, values);
    }
    // Delete a row in the table
    public void deleteVideo(String filename){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + VIDEO_TABLE + " WHERE " + COLUMN_FILENAME + "=\"" + filename + "\";");
    }
    // Delete all videos in the table of chosen child
    public void deleteAllVideosOfChild(String childName){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(VIDEO_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ) {
            if (c.getString(c.getColumnIndex(COLUMN_PROFILE)).equals(getCurrentProfile())) {
                int i = c.getInt(c.getColumnIndex(COLUMN_VIDEO_ID));
                db.execSQL("DELETE FROM " + VIDEO_TABLE + " WHERE " + COLUMN_VIDEO_ID + "=\"" + i + "\";");
            }
        }
    }
    // Update a row in the table
    public void updateVideo(Video video){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateVideo = new ContentValues();
        updateVideo.put(COLUMN_FILENAME, video.get_filename());
        updateVideo.put(COLUMN_SENT_STATUS, video.get_sentStatus());
        updateVideo.put(COLUMN_PROFILE, video.get_profileName());
        updateVideo.put(COLUMN_CHILD, video.get_childName());
        db.update(VIDEO_TABLE, updateVideo, COLUMN_FILENAME + " = ?", new String[]{video.get_filename()});
    }
    // Return Video class of current video
    public Video getVideoData(String filename){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(VIDEO_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ){
            if(c.getString(c.getColumnIndex(COLUMN_FILENAME)).equals(filename)){
                Video videoData = new Video (filename,
                        c.getString(c.getColumnIndex(COLUMN_SENT_STATUS)),
                        c.getString(c.getColumnIndex(COLUMN_PROFILE)),
                        c.getString(c.getColumnIndex(COLUMN_CHILD)) );
                return videoData;
            }
        }
        return null;
    }
    // Return all videos taken by current profile of current child
    public ArrayList<String> getAllCurrentVideos(String profile, String child){
        ArrayList<String> ListVideos = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(VIDEO_TABLE, null, null, null, null, null, null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            if(c.getString(c.getColumnIndex(COLUMN_PROFILE)).equals(profile) &&
                    c.getString(c.getColumnIndex(COLUMN_CHILD)).equals(child)){
                File directory = new File("/storage/sdcard0/Pictures/PROMISE/" +
                        c.getString(c.getColumnIndex(COLUMN_FILENAME)));
                if(directory.exists()) {
                    ListVideos.add(c.getString(c.getColumnIndex(COLUMN_FILENAME)));
                }else{
                    deleteVideo(c.getString(c.getColumnIndex(COLUMN_FILENAME)));
                }
            }
        }
        Collections.reverse(ListVideos);
        return ListVideos;
    }
    // Return all SENT videos taken by current profile of current child
    public ArrayList<String> getAllCurrentSentVideos(){
        ArrayList<String> ListVideos = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(VIDEO_TABLE, null, null, null, null, null, null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            if(c.getString(c.getColumnIndex(COLUMN_PROFILE)).equals(getCurrentProfile()) &&
                    c.getString(c.getColumnIndex(COLUMN_CHILD)).equals(getCurrentChild()) &&
                    c.getString(c.getColumnIndex(COLUMN_SENT_STATUS)).equals("true")){
                ListVideos.add(c.getString(c.getColumnIndex(COLUMN_FILENAME)));
            }
        }
        return ListVideos;
    }
    //TODO: use this method to check if a notification should be given
    // Return true if any video of the child is sent
    public Boolean checkForSentVideos(String profile, String child){
        ArrayList<String> ListVideos = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(VIDEO_TABLE, null, null, null, null, null, null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            if(c.getString(c.getColumnIndex(COLUMN_PROFILE)).equals(profile) &&
                    c.getString(c.getColumnIndex(COLUMN_CHILD)).equals(child) &&
                    c.getString(c.getColumnIndex(COLUMN_SENT_STATUS)).equals("true")){
                return true;
            }
        }
        return false;
    }
    // Returns true if currentVideo is sent
    public Boolean videoIsSent(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(VIDEO_TABLE, null, null, null, null, null, null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            if(c.getString(c.getColumnIndex(COLUMN_FILENAME)).equals(getCurrentVideo()) &&
                    c.getString(c.getColumnIndex(COLUMN_SENT_STATUS)).equals("true")){
                return true;
            }
        }
        return false;
    }



    /**------------------------ CHILD TABLE ------------------------**/
    // Add a new row to the table
    public void addChild(Child child){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHILD_ID, child.get_childID());
        values.put(COLUMN_HOSPITAL_ID, child.get_hospitalID());
        values.put(COLUMN_COUNTRY_ID, child.get_countryID());
        values.put(COLUMN_TERM_DATE, child.get_termDate());
        values.put(COLUMN_NICKNAME, child.get_nickName());
        values.put(COLUMN_PROFILE_NAME, child.get_profileName());
        values.put(COLUMN_VIDEO_SENT, child.get_videoSent());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(CHILD_TABLE, null, values);
    }
    // Delete a row in the table
    public void deleteChild(String nickname){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(CHILD_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ) {
            if (c.getString(c.getColumnIndex(COLUMN_PROFILE_NAME)).equals(getCurrentProfile()) &&
                    c.getString(c.getColumnIndex(COLUMN_NICKNAME)).equals(nickname)) {
                int i = c.getInt(c.getColumnIndex(COLUMN_ID));
                db.execSQL("DELETE FROM " + CHILD_TABLE + " WHERE " + COLUMN_ID + "=\"" + i + "\";");
            }
        }
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
        updateChild.put(COLUMN_PROFILE_NAME, child.get_profileName());
        updateChild.put(COLUMN_VIDEO_SENT, child.get_videoSent());
        db.update(CHILD_TABLE, updateChild, COLUMN_CHILD_ID + " = ?", new String[]{child.get_childID()});
    }
    // return Child class of current child
    public Child getChildData(String nickName){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(CHILD_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ){
            if(c.getString(c.getColumnIndex(COLUMN_PROFILE_NAME)).equals(getCurrentProfile()) &&
                    c.getString(c.getColumnIndex(COLUMN_NICKNAME)).equals(nickName)){
                Child childData = new Child (c.getString(c.getColumnIndex(COLUMN_CHILD_ID)),
                        c.getString(c.getColumnIndex(COLUMN_HOSPITAL_ID)),
                        c.getString(c.getColumnIndex(COLUMN_COUNTRY_ID)),
                        c.getString(c.getColumnIndex(COLUMN_TERM_DATE)),
                        c.getString(c.getColumnIndex(COLUMN_NICKNAME)),
                        c.getString(c.getColumnIndex(COLUMN_PROFILE_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_VIDEO_SENT)));
                return childData;
            }
        }
        return null;
    }

    public String getCurrentTermDate(){
        return getChildData(getCurrentChild()).get_termDate();
    }

    // Update child table if video is sent of child
    public void updateVideoRecordedOfChild(String childName){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateVideoSent = new ContentValues();
        updateVideoSent.put(COLUMN_NICKNAME, childName);
        updateVideoSent.put(COLUMN_VIDEO_SENT, "true");
        db.update(CHILD_TABLE, updateVideoSent, COLUMN_NICKNAME + " = ?", new String[]{childName});

    }

    public List<String> getChildrenWithinTimeZoneButNoVideosSent(){
        SQLiteDatabase db = getReadableDatabase();
        List<String> children = new ArrayList<>();

        Cursor c = db.query(CHILD_TABLE, null, null, null, null, null, null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            //Log.e("SIRI", "BA: " + c.getString(c.getColumnIndex(COLUMN_NICKNAME)));
            //Log.e("SIRI", "AA: " + c.getString(c.getColumnIndex(COLUMN_VIDEO_SENT)));
            if(c.getString(c.getColumnIndex(COLUMN_VIDEO_SENT)).equals("false")){
                String childTermDate = c.getString(c.getColumnIndex(COLUMN_TERM_DATE));

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Calendar calendar = Calendar.getInstance(); // Get Calendar Instance
                try {
                    calendar.setTime(sdf.parse(childTermDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // Time for next video:
                calendar.add(Calendar.DATE, 71);  // add 10 weeks to term date
                Date endDate = new Date(calendar.getTimeInMillis());
                // Today's date:
                Date currentDate = new Date(System.currentTimeMillis());
                // Countdown:
                int difference;
                difference = (int) (endDate.getTime()/ (24*60*60*1000)
                        -(int) (currentDate.getTime()/ (24*60*60*1000)));

                if (difference <= 0 && difference > -21) {
                    children.add(c.getString(c.getColumnIndex(COLUMN_NICKNAME)));
                }
            }
        }
        return children;
    }

    // Return all children with the same profile name
    public ArrayList<String> getAllProfileChildren(String currentProfile){
        ArrayList<String> ListNames = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(CHILD_TABLE, null, null, null, null, null, null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            if(c.getString(c.getColumnIndex(COLUMN_PROFILE_NAME)).equals(currentProfile)){
                ListNames.add(c.getString(c.getColumnIndex(COLUMN_NICKNAME)));
            }
        }
        return ListNames;
    }
    // Check new child nickname: if used before: true, if not in use: false.
    public Boolean nicknameInUse(String nickname){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(CHILD_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ) {
            if (c.getString(c.getColumnIndex(COLUMN_PROFILE_NAME)).equals(getCurrentProfile()) &&
                    c.getString(c.getColumnIndex(COLUMN_NICKNAME)).equals(nickname)) {
                return true;
            }
        }
        return false;
    }
    // Check new child ID-number: if used before: true, if not in use: false.
    public Boolean childIdInUse(String id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(CHILD_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ){
            if(c.getString(c.getColumnIndex(COLUMN_CHILD_ID)).equals(id)){
                return true;
            }
        }
        return false;
    }


    /**------------------------ PROFILE TABLE ------------------------**/
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
        db.update(PROFILE_TABLE, updateProfile, COLUMN_USERNAME + " = ?", new String[]{profile.get_username()});
    }

    // Check new username: if used before: true, if not in use: false.
    public Boolean usernameInUse(String username){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(PROFILE_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ){
            if(c.getString(c.getColumnIndex(COLUMN_USERNAME)).equals(username)){
                return true;
            }
        }
        return false;
    }
    // Return profile password
    public String getProfilePassword(String profileUsername){
        SQLiteDatabase db = getReadableDatabase();
        String profilePassword;

        Cursor c = db.query(PROFILE_TABLE, null, null, null, null, null, null);
        for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext() ){
            if(c.getString(c.getColumnIndex(COLUMN_USERNAME)).equals(profileUsername)){
                profilePassword = c.getString(c.getColumnIndex(COLUMN_PASSWORD));
                return profilePassword;
            }
        }
        return null;
    }
    public Boolean checkIfAnyRegisteredProfiles(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT count(*) FROM " + PROFILE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int number = cursor.getInt(0);
        if(number>0) {
            return true;
        }else{
            return false;
        }
    }


    /**------------------------ PRINT OUT A TABLE AS A STRING ------------------------**/
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        //Cursor point to a location in you results
        Cursor c = db.query(PROFILE_TABLE, null, null, null, null, null, null);
        //move to the first row in your results
        c.moveToFirst();
        dbString += c.getString(c.getColumnIndex(COLUMN_USERNAME));
        dbString += "   ";
        dbString += c.getString(c.getColumnIndex(COLUMN_PASSWORD));
        dbString += "\n";

        while (c.moveToNext()){
            if (c.getString(c.getColumnIndex("username")) != null){
                dbString += c.getString(c.getColumnIndex(COLUMN_USERNAME));
                dbString += "   ";
                dbString += c.getString(c.getColumnIndex(COLUMN_PASSWORD));
                dbString += "\n";
            }
        }
        return dbString;
    }
}
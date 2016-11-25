package ryhma1.hyteprojekti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Iiro on 17.11.2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    //all static variables
    //database version
    public static final int DATABASE_VERSION = 1;
    //database name
    public static final String DATABASE_NAME = "Hyteprojekti1.db";

    //User table name
    public static final String USER_TABLE = "User_table";

    //user table column names
    public static final String USERID = "ID";
    public static final String FIRSTNAME = "Firstname";
    public static final String LASTNAME = "Lastname";
    public static final String HEIGHT = "Height";
    public static final String WEIGHT = "Weight";
    public static final String USERS_POINTS = "Points";
    public static final String USER_LEVEL = "Level";

    //challenges table name
    public static final String CHALLENGE_TABLE = "Challenge_table";

    //challenges table column names
    public static final String CHALLENGE_ID = "ID";
    public static final String CHALLENGE_NAME = "Name";
    public static final String CHALLENGE_ACTIVITY = "Activity";
    public static final String CHALLENGE_BOUND_1 = "Bound1";
    public static final String CHALLENGE_BOUND_2 = "Bound2";
    public static final String CHALLENGE_POINTS = "Points";
    public static final String CHALLENGE_LEVEL = "LevelID";

    //data table name
    public static final String DATA_TABLE = "Data_table";

    //data table columns
    public static final String DATA_USERID = "UserID";
    public static final String DATA_BG ="Blood Glucose";
    public static final String TIME = "Time";

    //level table name
    public static final String LEVEL_TABLE ="Level_table";

    //level table column names
    public static final String LEVEL_ID = "LevelID";
    public static final String LEVEL_POINT_LIMIT = "Point_Limit";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = getWritableDatabase();
    }

    //Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsers = "CREATE TABLE "+ USER_TABLE +"(" +USERID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ FIRSTNAME +" TEXT,"+ LASTNAME +" TEXT," +
                ""+ HEIGHT +" INTEGER,"+ WEIGHT +" INTEGER,"+ USERS_POINTS +"INTEGER, "+USER_LEVEL+" INTEGER," +
                "FOREIGN KEY ("+USER_LEVEL+") REFERENCES "+LEVEL_TABLE+"("+LEVEL_ID+") )";

        String createChallenges = "CREATE TABLE "+ CHALLENGE_TABLE +"("+ CHALLENGE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ CHALLENGE_NAME +" TEXT,"+ CHALLENGE_ACTIVITY +" INTEGER,"+ CHALLENGE_BOUND_1 +" FLOAT," +
                ""+ CHALLENGE_BOUND_2 +" FLOAT,"+ CHALLENGE_POINTS +" INTEGER, "+ CHALLENGE_LEVEL +" INTEGER, " +
                "FOREIGN KEY ("+ CHALLENGE_LEVEL +") REFERENCES "+LEVEL_TABLE+"("+ LEVEL_ID +") )";

        String createDataTable = "CREATE TABLE "+ DATA_TABLE +" (" +DATA_BG+ " FLOAT PRIMARY KEY, "+TIME+" TIMESTAMP, "+DATA_USERID+" INTEGER, " +
                "FOREIGN KEY ("+DATA_USERID+") REFERENCES "+USER_TABLE+"("+USERID+") )";

        String createLevelTable =" CREATE TABLE "+ LEVEL_TABLE +"("+ LEVEL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ LEVEL_POINT_LIMIT +", INTEGER)";

        db.execSQL(createLevelTable);
        db.execSQL(createUsers);
        db.execSQL(createChallenges);
        db.execSQL(createDataTable);

        int j = 1000;
        for (int i = 1; i < 11; i++){
            int id = i;
            int limit = j*i;
            ContentValues values = new ContentValues();
            values.put(LEVEL_ID, id);
            values.put(LEVEL_POINT_LIMIT, limit);
            db.insert(LEVEL_TABLE, null, values);
            j = j+500;
        }

        ContentValues values = new ContentValues();
        values.put(CHALLENGE_NAME, "TestiHaaste");
        values.put(CHALLENGE_ACTIVITY, 4);
        values.put(CHALLENGE_BOUND_1, 6 );
        values.put(CHALLENGE_BOUND_2, 7.5);
        values.put(CHALLENGE_POINTS, 400);
        values.put(CHALLENGE_LEVEL, 1);
        db.insert(CHALLENGE_TABLE, null, values);

    }


    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int curVer = oldVersion;
        while ( curVer < newVersion ) {
            curVer++;
            switch ( curVer ) {
                case 2: {
                    // Upgrade from V1 to V2
                    db.execSQL("DROP TABLE IF EXISTS"+ LEVEL_TABLE + USER_TABLE +CHALLENGE_TABLE +DATA_TABLE);
                    //creates tables again
                    onCreate(db);
                    break;
                }
                case 3: {
                    // Upgrade from V2 to V3
                    db.execSQL("DROP TABLE IF EXISTS"+ LEVEL_TABLE + USER_TABLE +CHALLENGE_TABLE +DATA_TABLE);
                    //creates tables again
                    onCreate(db);
                    break;
                }
                case 4: {
                    // Upgrade from V3 to V4
                    db.execSQL("DROP TABLE IF EXISTS"+ LEVEL_TABLE + USER_TABLE +CHALLENGE_TABLE +DATA_TABLE);
                    //creates tables again
                    onCreate(db);
                    break;
                }
            }
        }
    }



    //adding new user
    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIRSTNAME, user.getFirstName()); //User name
        values.put(LASTNAME, user.getLastName()); //user lastname
        values.put(HEIGHT, user.getHeight()); //user height
        values.put(WEIGHT, user.getWeight()); //user weight
        values.put(USERS_POINTS, user.getUserPoints()); //user point

        db.insert(USER_TABLE, null, values);
        db.close(); //closing database connection
    }
    //getting single user
    User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USER_TABLE, new String[] { USERID, FIRSTNAME, LASTNAME, HEIGHT, WEIGHT,
                USERS_POINTS }, USERID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)));
        //return userr
        return user;
        }

    //adding new level

    void addLevel(Level level) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LEVEL_ID, level.getLevelID()); //User name
        values.put(LEVEL_POINT_LIMIT, level.getLevelLimit()); //user lastname


        db.insert(LEVEL_TABLE, null, values);
        db.close(); //closing database connection
    }

    //adding new Challenge
    void addChallenge(Challenge challenge){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CHALLENGE_ID, challenge.getChallengeID());
        values.put(CHALLENGE_NAME, challenge.getChallengename());
        values.put(CHALLENGE_ACTIVITY, challenge.getActivity());
        values.put(CHALLENGE_BOUND_1, challenge.getBound1());
        values.put(CHALLENGE_BOUND_2, challenge.getBound2());
        values.put(CHALLENGE_POINTS, challenge.getChallengePoints());
        values.put(CHALLENGE_LEVEL, challenge.getChallengePoints());

        db.insert(CHALLENGE_TABLE, null, values);
        db.close();
    }


    //getting single challenge
    Challenge getChallenge(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CHALLENGE_TABLE, new String[] {CHALLENGE_ID, CHALLENGE_NAME, CHALLENGE_ACTIVITY,
                        CHALLENGE_BOUND_1, CHALLENGE_BOUND_2, CHALLENGE_POINTS, CHALLENGE_LEVEL}, CHALLENGE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Challenge challenge = new Challenge(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));

        return challenge;

    }

    //getting all challenges
    public List<Challenge> getAllChallenges(){
        List<Challenge> challengeList = new ArrayList<Challenge>();

        String selectQuery = " SELECT * FROM " +CHALLENGE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()) {
            do {
                Challenge challenge = new Challenge();
                challenge.setChallengeID(Integer.parseInt(cursor.getString(0)));
                challenge.setChallengeName(cursor.getString(1));
                challenge.setActivity(Integer.parseInt(cursor.getString(2)));
                challenge.setBound1(Integer.parseInt(cursor.getString(3)));
                challenge.setBound2(Integer.parseInt(cursor.getString(4)));
                challenge.setChallengePoints(Integer.parseInt(cursor.getString(5)));
                challenge.setChallengeLevel(Integer.parseInt(cursor.getString(6)));

                challengeList.add(challenge);
            } while (cursor.moveToNext());
        }

        return  challengeList;
    }

    //getting


}

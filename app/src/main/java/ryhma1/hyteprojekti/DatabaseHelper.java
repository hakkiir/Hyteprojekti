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
    public static final String USER_ID = "ID";
    public static final String USER_FIRSTNAME = "Firstname";
    public static final String USER_LASTNAME = "Lastname";
    public static final String USER_HEIGHT = "Height";
    public static final String USER_WEIGHT = "Weight";
    public static final String USER_POINTS = "Points";
    public static final String USER_LEVEL = "Level";
    public static final String USER_BOUND_1 = "Bound1";
    public static final String USER_BOUND_2 = "Bound2";



    //normal values table
    public static final String NORM_TABLE = "Normal_Table";
    public static final String NORM_1 = "Normal_value1";
    public static final String NORM_2 = "Normal_value2";

    //challenges table name
    public static final String CHALLENGE_TABLE = "Challenge_table";

    //challenges table column names
    public static final String CHALLENGE_ID = "ID";
    public static final String CHALLENGE_NAME = "Name";
    public static final String CHALLENGE_ACTIVITY = "Activity";
    public static final String CHALLENGE_POINTS = "Points";
    public static final String CHALLENGE_LEVEL = "LevelID";
    public static final String CHALLENGE_DURATION = "Duration";
    public static final String CHALLENGE_CLOCK  = "Clock";

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
        /*SQLiteDatabase db = getWritableDatabase();*/
    }

    //Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsers = "CREATE TABLE "+ USER_TABLE +"(" +USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER_FIRSTNAME +" TEXT,"+ USER_LASTNAME +" TEXT," +
                ""+ USER_HEIGHT +" INTEGER,"+ USER_WEIGHT +" INTEGER,"+ USER_POINTS +" INTEGER, "+USER_LEVEL+" INTEGER, " +USER_BOUND_1 +" DOUBLE," +
                ""+ USER_BOUND_2 +" DOUBLE, FOREIGN KEY ("+USER_LEVEL+") REFERENCES "+LEVEL_TABLE+"("+LEVEL_ID+") )";

        String createChallenges = "CREATE TABLE "+ CHALLENGE_TABLE +"("+ CHALLENGE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ CHALLENGE_NAME +" TEXT,"+ CHALLENGE_ACTIVITY +" INTEGER,"+ CHALLENGE_POINTS +" INTEGER, "+ CHALLENGE_LEVEL +" INTEGER, " +
                ""+ CHALLENGE_DURATION + " INTEGER, "+ CHALLENGE_CLOCK +" TEXT, FOREIGN KEY ("+ CHALLENGE_LEVEL +") REFERENCES "+LEVEL_TABLE+"("+ LEVEL_ID +"))";

        String createDataTable = "CREATE TABLE "+ DATA_TABLE +" (" +DATA_BG+ " FOAT PRIMARY KEY, "+TIME+" TIMESTAMP, "+DATA_USERID+" INTEGER, " +
                "FOREIGN KEY ("+DATA_USERID+") REFERENCES "+USER_TABLE+"("+USER_ID+") )";

        String createLevelTable =" CREATE TABLE "+ LEVEL_TABLE +"("+ LEVEL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ LEVEL_POINT_LIMIT +", INTEGER)";

        String createNormalValuesTable = "CREATE TABLE "+NORM_TABLE+" ("+NORM_1+" DOUBLE, "+NORM_2+" DOUBLE)";

        db.execSQL(createLevelTable);
        db.execSQL(createUsers);
        db.execSQL(createChallenges);
        db.execSQL(createDataTable);
        db.execSQL(createNormalValuesTable);

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


        /*
        ContentValues values = new ContentValues();
        values.put(USER_ID, 1);
        values.put(USER_FIRSTNAME, "Testi");
        values.put(USER_LASTNAME, "Henkilö");
        values.put(USER_HEIGHT, 180);
        values.put(USER_WEIGHT, 80);
        values.put(USER_POINTS, 0);
        values.put(USER_LEVEL, 1);
        values.put(USER_BOUND_1, 7);
        values.put(USER_BOUND_2, 12);
        db.insert(USER_TABLE, null, values);

        values.put(NORM_1, 5);
        values.put(NORM_2, 7);
        db.insert(NORM_TABLE, null, values);

        values.put(CHALLENGE_ID, 1);
        values.put(CHALLENGE_NAME, "Out of bed"); //Mittaa heti herättyäsi
        values.put(CHALLENGE_ACTIVITY, 1);
        values.put(CHALLENGE_POINTS, 100);
        values.put(CHALLENGE_LEVEL, 1);
        values.put(CHALLENGE_DURATION, 0);
        values.put(CHALLENGE_CLOCK, 9); //Kellon aika maksimissaan->Mittaus ennen aamu yhdeksää
        db.insert(CHALLENGE_TABLE, null, values);

        values.put(CHALLENGE_ID, 2);
        values.put(CHALLENGE_NAME, "Rocksteady");//Pidä mittaukset raja-arvojen sisällä
        values.put(CHALLENGE_ACTIVITY, 4);
        values.put(CHALLENGE_POINTS, 1000);
        values.put(CHALLENGE_LEVEL, 1);
        values.put(CHALLENGE_DURATION, 1440); //Haasteen kesto minuuteissa->24 tuntia
        db.insert(CHALLENGE_TABLE, null, values);

        values.put(CHALLENGE_ID, 3);
        values.put(CHALLENGE_NAME, "Time for bed"); //Mittaa nukkumaan mennessä
        values.put(CHALLENGE_ACTIVITY, 1);
        values.put(CHALLENGE_POINTS, 100);
        values.put(CHALLENGE_LEVEL, 1);
        values.put(CHALLENGE_CLOCK, 22);//Mittaus nukkumaan mennessä->ennen kello 22
        db.insert(CHALLENGE_TABLE, null, values);

        values.put(CHALLENGE_ID, 4);
        values.put(CHALLENGE_NAME, "Delicious"); //Mittaa ennen ruokailua ja kahden tunnin päästä
        values.put(CHALLENGE_ACTIVITY, 2);
        values.put(CHALLENGE_POINTS, 200);
        values.put(CHALLENGE_LEVEL, 1);
        values.put(CHALLENGE_DURATION, 120); //Kesto lähtee ensimmäisestä mittauksesta ja kestää 2 tuntia
        db.insert(CHALLENGE_TABLE, null, values);

        values.put(CHALLENGE_ID, 5);
        values.put(CHALLENGE_NAME, "Like a clock"); //Mittaa kahden tunnin välein
        values.put(CHALLENGE_ACTIVITY, 4);
        values.put(CHALLENGE_POINTS, 500);
        values.put(CHALLENGE_LEVEL, 1);
        values.put(CHALLENGE_DURATION, 960); //Hereillä olo aika 16 tuntia
        db.insert(CHALLENGE_TABLE, null, values);
        */
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
        values.put(USER_ID, user.getUserID());
        values.put(USER_FIRSTNAME, user.getFirstName()); //User name
        values.put(USER_LASTNAME, user.getLastName()); //user lastname
        values.put(USER_HEIGHT, user.getHeight()); //user height
        values.put(USER_WEIGHT, user.getWeight()); //user weight
        values.put(USER_POINTS, user.getUserPoints()); //user point
        values.put(USER_LEVEL, user.getLevel()); //user point
        values.put(USER_BOUND_1, user.getBound1()); //user point
        values.put(USER_BOUND_2, user.getBound2()); //user point

        db.insert(USER_TABLE, null, values);
        db.close(); //closing database connection
    }
    //getting single user
    User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USER_TABLE, new String[] { USER_ID, USER_FIRSTNAME, USER_LASTNAME, USER_HEIGHT, USER_WEIGHT,
                        USER_POINTS, USER_LEVEL, USER_BOUND_1, USER_BOUND_2}, USER_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),Double.parseDouble(cursor.getString(7)), Double.parseDouble(cursor.getString(8)));
        //return user
        return user;
    }

    //getting all users
    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<User>();

        String selectQuery = " SELECT * FROM " +USER_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserID(Integer.parseInt(cursor.getString(0)));
                user.setFirstName(cursor.getString(1));
                user.setLastName(cursor.getString(2));
                user.setWeight(Integer.parseInt(cursor.getString(3)));
                user.setHeight(Integer.parseInt(cursor.getString(4)));
                user.setUserPoints(Integer.parseInt(cursor.getString(5)));
                user.setUserLevel(Integer.parseInt(cursor.getString(6)));
                user.setBound1(Integer.parseInt(cursor.getString(7)));
                user.setBound2(Integer.parseInt(cursor.getString(8)));

                userList.add(user);
            } while (cursor.moveToNext());
        }

        return  userList;
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
        values.put(CHALLENGE_POINTS, challenge.getChallengePoints());
        values.put(CHALLENGE_LEVEL, challenge.getChallengePoints());
        values.put(CHALLENGE_DURATION, challenge.getChallengePoints());
        values.put(CHALLENGE_CLOCK, challenge.getChallengePoints());
        db.insert(CHALLENGE_TABLE, null, values);
        db.close();
    }


    //getting single challenge
    Challenge getChallenge(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CHALLENGE_TABLE, new String[] {CHALLENGE_ID, CHALLENGE_NAME, CHALLENGE_ACTIVITY,
                        CHALLENGE_POINTS, CHALLENGE_LEVEL, CHALLENGE_DURATION, CHALLENGE_CLOCK}, CHALLENGE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Challenge challenge = new Challenge(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)), cursor.getString(6));

        return challenge;

    }

    //getting all challenges
    public List<Challenge> getAllChallenges(){
        List<Challenge> challengeList = new ArrayList<Challenge>();

        String selectQuery = " SELECT * FROM " +CHALLENGE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()) do {
            Challenge challenge = new Challenge();
            challenge.setChallengeID(Integer.parseInt(cursor.getString(0)));
            challenge.setChallengeName(cursor.getString(1));
            challenge.setActivity(Integer.parseInt(cursor.getString(2)));
            challenge.setChallengePoints(Integer.parseInt(cursor.getString(3)));
            challenge.setChallengeLevel(Integer.parseInt(cursor.getString(4)));
            challenge.setDuration(Integer.parseInt(cursor.getString(5)));
            challenge.setClock(cursor.getString(6));

            challengeList.add(challenge);
        } while (cursor.moveToNext());

        return  challengeList;
    }


    //getting

}
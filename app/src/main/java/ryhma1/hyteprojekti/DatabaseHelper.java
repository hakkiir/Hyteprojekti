package ryhma1.hyteprojekti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

    //challenges table name
    public static final String CHALLENGE_TABLE = "Challenge_table";

    //challenges table column names
    public static final String ChallengesID = "ID";
    public static final String ChallengesName = "Name";
    public static final String ChallengesActivity = "Activity";
    public static final String ChallengesBound1 = "Bound1";
    public static final String ChallengesBound2 = "Bound2";
    public static final String ChallengesPoints = "Points";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    //Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsers = "CREATE TABLE "+ USER_TABLE +"(" +USERID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ FIRSTNAME +" TEXT,"+ LASTNAME +" TEXT," +
                ""+ HEIGHT +" INTEGER,"+ WEIGHT +" INTEGER,"+ USERS_POINTS +"INTEGER)";

        String createChallenges = "CREATE TABLE "+ CHALLENGE_TABLE +"("+ChallengesID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ChallengesName+" TEXT,"+ChallengesActivity+" INTEGER,"+ChallengesBound1+" FLOAT," +
                ""+ChallengesBound2+" FLOAT,"+ChallengesPoints+" INTEGER)";
        db.execSQL(createUsers);
        db.execSQL(createChallenges);
    }


    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS"+ USER_TABLE);
            //creates tables again
            onCreate(db);
    }

    /**'''''''
     *  ALL CRUD(Create, Read, Update, Delete) Operations
     */

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

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)));
        //return user
        return user;
        }


}

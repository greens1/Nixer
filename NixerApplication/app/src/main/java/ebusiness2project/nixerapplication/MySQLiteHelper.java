package ebusiness2project.nixerapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Date;


import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static int ACTIVE_USER_ID = 0;

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "NixerDatabaseLocal1";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create user table
        String CREATE_USER_TABLE = "CREATE TABLE User ( " +
                "userID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstName TEXT, " + "email TEXT, " + "password TEXT, " +
                "dob STRING" +
                ")";

        // create user table
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_TASK_TABLE = "CREATE TABLE Task ( " +
                "taskID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " + "price FLOAT, " + "description TEXT, " +
                "location TEXT, " + "category TEXT" +
                ")";

        db.execSQL(CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS User");

        db.execSQL("DROP TABLE IF EXISTS Task");
        this.onCreate(db);
    }

    private static final String TABLE_USER = "User";
    private static final String TABLE_TASK = "Task";

    private static final String USER_ID = "userID";
    private static final String FIRST_NAME = "firstName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String DOB = "dob";

    private static final String TASK_ID = "taskID";
    private static final String TITLE = "title";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String LOCATION = "location";
    private static final String CATEGORY = "category";

    private static final String[] COLUMNS_USERS = {USER_ID, FIRST_NAME,
             EMAIL, PASSWORD, DOB};
    private static final String [] COLUMNS_TASK = {TASK_ID, TITLE, PRICE, DESCRIPTION, LOCATION, CATEGORY};

    public boolean addUser(User user){
        Log.d("add User", user.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER +
                " WHERE " + EMAIL + " = '" + user.getEmail() + "'" , null);

        // record does not exist
        if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
            //cursor is empty
            values.put(FIRST_NAME, user.getFirstName());
            values.put(EMAIL, user.getEmail());
            values.put(PASSWORD, user.getPassword());
            values.put(DOB, user.getDob());

            db.insert(TABLE_USER,
                    null,
                    values);
            db.close();
            return true;
        }

        return false;
    } // end addUser

    public void addTask(Task project){
        Log.d("add User", project.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        
        values.put(TITLE, project.getTitle());
        values.put(PRICE, project.getPrice());
        values.put(DESCRIPTION, project.getDescription());
        values.put(LOCATION, project.getLocation());
        values.put(CATEGORY, project.getCategory());

        db.insert(TABLE_TASK,
                null,
                values);
        db.close();
    } // end addUser

    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_USER, // a. table
                        COLUMNS_USERS, // b. column names
                        "userID = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args     // data injection
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User();
        user.setUserID(cursor.getInt(0));                          // store record
        user.setFirstName(cursor.getString(1));
        user.setEmail(cursor.getString(2));
        user.setPassword(cursor.getString(3));
        user.setDob(cursor.getString(4));
        Log.d("getUser(" + id + ")", user.toString());
        return user;
    }

    public boolean userLogin(String inputEmail, String inputPassword){
        //want to make sure the user does not already exist and if not, added
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER +
                " WHERE " + EMAIL + " = '" + inputEmail + "'" , null);

        // record does not exist
        if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
            return false;
        }

        if (cursor != null)         // check a record has been returned
            cursor.moveToFirst();   // move to first record returned
        String temp = cursor.getString(3);

        if(inputPassword.equals(cursor.getString(3))) {
            ACTIVE_USER_ID = cursor.getInt(0);
            return true;
        }

        return false;

    }

    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_TASK, // a. table
                        COLUMNS_TASK, // b. column names
                        "taskID = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args     // data injection
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)         // check a record has been returned
            cursor.moveToFirst();   // move to first record returned

        Task task = new Task();
        task.setTaskID(cursor.getInt(0));                          // store record
        task.setTitle(cursor.getString(1));
        task.setPrice(cursor.getFloat(2));
        task.setDescription(cursor.getString(3));
        task.setLocation(cursor.getString(4));
        task.setCategory(cursor.getString(5));
        Log.d("getTask(" + id + ")", task.toString());
        return task;


    }

    public List<User> getAllUsers() {
        //returns all users
        List<User> users = new LinkedList<User>();
        String query = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build session and add it to list
        User s = null;
        if (cursor.moveToFirst()) { // returns false if no records returned
            do {
                s = new User();
                s.setUserID(cursor.getInt(0));
                s.setFirstName(cursor.getString(1));
                s.setEmail(cursor.getString(2));
                s.setPassword(cursor.getString(3));
                s.setDob(cursor.getString(4));

                users.add(s);
            } while (cursor.moveToNext());
        }

        Log.d("getAllUsers()", s.toString());

        return users;
    }
    public List<Task> getAllTasks() {
        //returns all tasks
        List<Task> tasks = new LinkedList<Task>();
        String query = "SELECT  * FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build session and add it to list
        Task s = null;

        if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
            return null;
        }

        if (cursor.moveToFirst()) { // returns false if no records returned
            do {
                s = new Task();
                s.setTaskID(cursor.getInt(0));        // read column 0 (recordID)
                s.setTitle(cursor.getString(1));
                s.setPrice(cursor.getFloat(2));
                s.setDescription(cursor.getString(3));
                s.setLocation(cursor.getString(4));
                s.setCategory(cursor.getString(5));
                tasks.add(s);
            } while (cursor.moveToNext());
            Log.d("getAllTasks()", s.toString());
            // return list of tasks
        }
        // return null if fails
        return tasks;
    }

    public List<Task> getListOfTasksByCategory(String category){
        //this function will return the tasks relating to a specific category chosen by the user
        List<Task> tasks = new LinkedList<Task>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_TASK +
                " WHERE " + CATEGORY + " = '" + category + "'", null);
        Task b = null;

        if(c.moveToFirst()){
            do{
                b = new Task();
                b.setTaskID(c.getInt(0));        // read column 0 (recordID)
                b.setTitle(c.getString(1));
                b.setPrice(c.getFloat(2));
                b.setDescription(c.getString(3));
                b.setLocation(c.getString(4));
                b.setCategory(c.getString(5));
                tasks.add(b);
            } while (c.moveToNext());
        }
        Log.d("getTasksByCateogry", category);
        return tasks;
    }



    public void deleteUser(int id) {
        //this function will delete a user from the database

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USER,
                USER_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();

        Log.d("deleteUser", String.valueOf(id));
    }
    public void deleteTask(int id) {
        //this function will delete a task from the database

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TASK,
                TASK_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();

        Log.d("deleteTask", String.valueOf(id));

    }

    public int returnUserID(){
        return ACTIVE_USER_ID;
        //returns which user is currently logged in

    }

}

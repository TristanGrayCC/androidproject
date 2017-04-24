package example.codeclan.com.mytasklistapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import example.codeclan.com.mytasklistapp.Task;

/**
 * Created by user on 21/04/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "taskListApp";
    // Contacts table name
    private static final String TASK_LIST = "taskList";
    // Tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TASK_LIST + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PRIORITY + " INTEGER," + KEY_NAME + " TEXT,"
        + KEY_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TASK_LIST);
        // Creating tables again
        onCreate(db);
    }

    // Adding new task
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRIORITY, task.getPriority());
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DESCRIPTION, task.getDescription());
    // Inserting Row
        db.insert(TASK_LIST, null, values);
        db.close(); // Closing database connection
    }

    // Getting one task
    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TASK_LIST, new String[] { KEY_ID, KEY_PRIORITY,
                        KEY_NAME, KEY_DESCRIPTION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Task task = new Task(Integer.parseInt(cursor.getString(0)),
                cursor.getInt(1), cursor.getString(2), cursor.getString(3));
    // return task
        return task;
    }

    // Getting All Tasks
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<Task>();
    // Select All Query
        String selectQuery = "SELECT * FROM " + TASK_LIST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
    // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setID(Integer.parseInt(cursor.getString(0)));
                task.setPriority(Integer.parseInt(cursor.getString(1)));
                task.setName(cursor.getString(2));
                task.setDescription(cursor.getString(3));
    // Adding task to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }
    // return task list
        return taskList;
    }

    // Getting tasks Count
    public int getTasksCount() {
        String countQuery = "SELECT * FROM " + TASK_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
    // return count
        return cursor.getCount();
    }

    // Updating a task
    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRIORITY, task.getPriority());
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DESCRIPTION, task.getDescription());
    // updating row
        return db.update(TASK_LIST, values, KEY_ID + " = ?",
                new String[]{String.valueOf(task.getID())});
    }

    // Deleting a task
    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TASK_LIST, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getID()) });
        db.close();
    }
}
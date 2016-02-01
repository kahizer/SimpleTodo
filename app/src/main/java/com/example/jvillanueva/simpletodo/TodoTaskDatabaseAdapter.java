package com.example.jvillanueva.simpletodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kahizer on 1/31/2016.
 */
public class TodoTaskDatabaseAdapter {

    TodoTaskHelper helper;

    public TodoTaskDatabaseAdapter(Context context){
        helper = new TodoTaskHelper(context);
    }

    public long insertData(TaskViewModel task){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoTaskHelper.KEY_TITTLE, task.tittle);
        contentValues.put(TodoTaskHelper.KEY_DESCRIPTION, task.description);
        contentValues.put(TodoTaskHelper.KEY_PRIORITY, task.priority);
        contentValues.put(TodoTaskHelper.KEY_STATUS, task.status);
        contentValues.put(TodoTaskHelper.KEY_DUEDATE, task.dueDate.toString());
        contentValues.put(TodoTaskHelper.KEY_CREATEDDATE, task.createdDate.toString());

        long id = db.insert(helper.TABLE_NAME, null, contentValues);
        return id;

    }

     public class TodoTaskHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "Tasks.db";
        private static final int DATABASE_VERSION = 2;
        private static final String TABLE_NAME = "TaskViewModels";

        //TaskViewModel columns
        private static final String KEY_TASK_ID = "id";
        private static final String KEY_TITTLE = "Tittle";
        private static final String KEY_DESCRIPTION = "Description";
        private static final String KEY_PRIORITY = "Priority";
        private static final String KEY_STATUS = "Status";
        private static final String KEY_DUEDATE = "DueDate";
        private static final String KEY_CREATEDDATE = "CreatedDate";

        private Context context;

        public TodoTaskHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;

            Message.message(context, "Constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // SQL for creating the tables
            String CREATE_TASKVIEWMODEL_TABLE = "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    KEY_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // Define a primary key
                    KEY_TITTLE + " TEXT," +
                    KEY_DESCRIPTION + " TEXT," +
                    KEY_PRIORITY + " TEXT," +
                    KEY_STATUS + " TEXT," +
                    KEY_DUEDATE + " DATETIME," +
                    KEY_CREATEDDATE + " DATETIME" +
                    ")";

            Message.message(context, "onCreate Called");

            try{
                db.execSQL(CREATE_TASKVIEWMODEL_TABLE);
            }catch (Exception ex){
                Message.message(context, ""+ex);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // SQL for upgrading the tables
            try{
                Message.message(this.context, "onUpgrade was called");
                if(oldVersion != newVersion){
                    db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
                    onCreate(db);
                }
            }catch (Exception ex){
                Message.message(this.context, ex.getMessage());
            }

        }
    }
}

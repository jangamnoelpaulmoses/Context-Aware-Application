package com.example.mcfinal;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String dName = "symptoms_db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, dName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your database table
        String CREATE_TABLE = "CREATE TABLE user_data ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "symptom TEXT,"
                + "rating REAL)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If you need to upgrade your database, do it here
        db.execSQL("DROP TABLE IF EXISTS user_data");
        onCreate(db);
    }
}

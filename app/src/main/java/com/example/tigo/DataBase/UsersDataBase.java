package com.example.tigo.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users_db.db";
    private static final String TABLE_NAME = "users_table";

    // Columnas de la tabla
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";

    // Sentencia SQL para crear la tabla
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_NAME + " TEXT,"
                    + COL_EMAIL + " TEXT,"
                    + COL_PASSWORD + " TEXT"
                    + ")";

    public UsersDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Se ejecuta cuando se crea la base de datos por primera vez
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se ejecuta cuando se actualiza la base de datos a una versión más reciente
        // Aquí podrías realizar alguna acción como migrar los datos a una nueva estructura de tabla
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean checkUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }

    public long addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_EMAIL, email);
        values.put(COL_PASSWORD, password);

        db.insert(TABLE_NAME, null, values);

        db.close();
        return 0;
    }
    public boolean checkPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        boolean exists = cursor.moveToFirst();
        if (exists) {
            @SuppressLint("Range") String storedPassword = cursor.getString(cursor.getColumnIndex(COL_PASSWORD));
            exists = password.equals(storedPassword);
        }

        cursor.close();
        db.close();

        return exists;
    }

    public String getUserNameByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        String userName = null;

        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndexOrThrow(COL_NAME);
            userName = cursor.getString(nameColumnIndex);
        }

        cursor.close();
        db.close();

        return userName;
    }
}

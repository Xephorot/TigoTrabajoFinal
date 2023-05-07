package com.example.tigo.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBplan extends SQLiteOpenHelper {
    private static final String DB_NAME = "mis_planes.db";
    private static final int DB_VERSION = 1;

    public static final String PLAN_TABLE_NAME = "plann";
    public static final String PLAN_COL_ID = "_id";
    public static final String PLAN_COL_NOMBRE = "nombre";
    public static final String PLAN_COL_DESCRIPCION = "descripcion";
    public static final String PLAN_COL_PRECIO = "precio";

    public static final String PAQUETE_TABLE_NAME = "paquete";
    public static final String PAQUETE_COL_ID = "_id";
    public static final String PAQUETE_COL_NOMBRE = "nombre";
    public static final String PAQUETE_COL_DESCRIPCION = "descripcion";
    public static final String PAQUETE_COL_PRECIO = "precio";

    public DBplan(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PLAN_TABLE_NAME + " (" +
                PLAN_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PLAN_COL_NOMBRE + " TEXT," +
                PLAN_COL_DESCRIPCION + " TEXT," +
                PLAN_COL_PRECIO + " REAL" +
                ")");
        db.execSQL("CREATE TABLE " + PAQUETE_TABLE_NAME + " (" +
                PAQUETE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PAQUETE_COL_NOMBRE + " TEXT," +
                PAQUETE_COL_DESCRIPCION + " TEXT," +
                PAQUETE_COL_PRECIO + " REAL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PLAN_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PAQUETE_TABLE_NAME);
        onCreate(db);
    }

}
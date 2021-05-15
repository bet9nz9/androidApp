package com.example.kursach.SQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    public SQLHelper(@Nullable Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE VACANCY_TABLE(" +
                "    ID INTEGER NOT NULL," +
                "    COAST REAL NOT NULL," +
                "    SHORT_DESC VARCHAR(250)," +
                "    FULL_DESC VARCHAR(10000)," +
                "    IS_SELECTED INTEGER NOT NULL," +
                "    EXECUTOR_ID INTEGER ," +
                "    EMPLOYEE_ID INTEGER NOT NULL," +
                "    PRIMARY KEY (ID)," +
                "    FOREIGN KEY (EXECUTOR_ID) REFERENCES USER_TABLE(ID)," +
                "    FOREIGN KEY (EMPLOYEE_ID) REFERENCES USER_TABLE(ID)" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE USER_TABLE (" +
                "    ID INTEGER NOT NULL," +
                "    LOGIN VARCHAR(150) NOT NULL," +
                "    PASS VARCHAR(150) NOT NULL," +
                "    USERNAME VARCHAR(150) NOT NULL," +
                "    USER_VACANCIES INTEGER," +
                "    ASSIGNED_VACANCIES INTEGER," +
                "    PRIMARY KEY (ID)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

package com.example.kursach.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.kursach.exception.Messages;
import com.example.kursach.model.VacancyDataModel;
import com.example.kursach.model.VacancyFields;
import com.example.kursach.validator.VacancyValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VacancySqlHelper extends SQLHelper {

    public static final String VACANCY_TABLE = "VACANCY_TABLE";

    public VacancySqlHelper(@Nullable Context context) {
        super(context);
    }

    public void createVacancy(Context context, VacancyDataModel vacancy) {
        VacancyValidator.isValidVacancyToCreate(vacancy);

        vacancy.setId(UUID.randomUUID().getMostSignificantBits());
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(VacancyFields.ID_FIELD, vacancy.getId());
        cv.put(VacancyFields.COAST_FIELD, vacancy.getCoast().toString());
        cv.put(VacancyFields.SHORT_DESC_FIELD, vacancy.getShortDesc());
        cv.put(VacancyFields.FULL_DESC_FIELD, vacancy.getFullDesc());
        if (vacancy.getSelected()) {
            cv.put(VacancyFields.IS_SELECTED_FIELD, 1);
        } else {
            cv.put(VacancyFields.IS_SELECTED_FIELD, 0);
        }
        cv.put(VacancyFields.EMPLOYEE_ID_FIELD, vacancy.getEmployeeId());

        long insert = database.insert(VACANCY_TABLE, null, cv);
        if (insert == -1) {
            Toast.makeText(context, Messages.ERROR_CREATE_VACANCY, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, Messages.VACANCY_IS_CREATED, Toast.LENGTH_LONG).show();
        }
    }

    public VacancyDataModel getVacancyByParameter(String parameter, String fieldName) {
        String query = String.format(Queries.SELECT_ONE, VACANCY_TABLE, fieldName, parameter);

        VacancyDataModel vacancy = null;

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            vacancy = new VacancyDataModel();
            do {
                vacancy.setId(cursor.getLong(0));
                vacancy.setCoast(new BigDecimal(cursor.getString(1)));
                vacancy.setShortDesc(cursor.getString(2));
                vacancy.setFullDesc(cursor.getString(3));

                if (cursor.getInt(4) == 1) {
                    vacancy.setSelected(true);
                } else {
                    vacancy.setSelected(false);
                }
                vacancy.setExecutorId(cursor.getLong(5));
                vacancy.setEmployeeId(cursor.getLong(6));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return vacancy;
    }

    public List<VacancyDataModel> getAllVacancies(Context context) {
        List<VacancyDataModel> resultList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(String.format(Queries.SELECT_ALL, VACANCY_TABLE), null);
        if (cursor.moveToFirst()) {
            do {
                VacancyDataModel vacancy = new VacancyDataModel();
                vacancy.setId(cursor.getLong(0));
                vacancy.setCoast(new BigDecimal(cursor.getString(1)));
                vacancy.setShortDesc(cursor.getString(2));
                vacancy.setFullDesc(cursor.getString(3));

                if (cursor.getInt(4) == 1) {
                    vacancy.setSelected(true);
                } else {
                    vacancy.setSelected(false);
                }
                vacancy.setExecutorId(cursor.getLong(5));
                vacancy.setEmployeeId(cursor.getLong(6));
                resultList.add(vacancy);
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(context, Messages.NOTHING_FOUND, Toast.LENGTH_LONG).show();
        }

        cursor.close();
        database.close();

        return resultList;
    }

    public List<VacancyDataModel> getAllVacanciesByParameter(String parameter, String fieldName, Context context) {
        List<VacancyDataModel> resultList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(String.format(Queries.SELECT_ALL_WHERE, VACANCY_TABLE, fieldName, parameter), null);
        if (cursor.moveToFirst()) {
            do {
                VacancyDataModel vacancy = new VacancyDataModel();
                vacancy.setId(cursor.getLong(0));
                vacancy.setCoast(new BigDecimal(cursor.getString(1)));
                vacancy.setShortDesc(cursor.getString(2));
                vacancy.setFullDesc(cursor.getString(3));

                if (cursor.getInt(4) == 1) {
                    vacancy.setSelected(true);
                } else {
                    vacancy.setSelected(false);
                }
                vacancy.setExecutorId(cursor.getLong(5));
                vacancy.setEmployeeId(cursor.getLong(6));
                resultList.add(vacancy);
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(context, Messages.NOTHING_FOUND, Toast.LENGTH_LONG).show();
        }

        cursor.close();
        database.close();

        return resultList;
    }

    public void updateVacancy(Context context, VacancyDataModel vacancyDataModel) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(VacancyFields.EXECUTOR_ID_FIELD, vacancyDataModel.getExecutorId());

        long insert = database.update(VACANCY_TABLE, cv, "ID=?", new String[]{vacancyDataModel.getId().toString()});
        if (insert == -1) {
            Toast.makeText(context, Messages.ERROR_UPDATE_VACANCY, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, Messages.VACANCY_IS_UPDATED, Toast.LENGTH_LONG).show();
        }
    }
}

package com.example.kursach.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.kursach.exception.Messages;
import com.example.kursach.exception.ValidationException;
import com.example.kursach.model.UserDataModel;
import com.example.kursach.model.UserFields;
import com.example.kursach.validator.UserValidator;

import java.util.UUID;

public class UserSqlHelper extends SQLHelper {

    private static final String USER_TABLE = "USER_TABLE";

    public UserSqlHelper(@Nullable Context context) {
        super(context);
    }

    public boolean createUser(Context context, UserDataModel user) {
        UserValidator.isValidUserToCreate(user);

        if (isUserNotExists(user)) {
            user.setId(UUID.randomUUID().getMostSignificantBits());
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(UserFields.ID_FIELD, user.getId());
            cv.put(UserFields.LOGIN_FIELD, user.getLogin());
            cv.put(UserFields.PASSWORD_FIELD, user.getPassword());
            cv.put(UserFields.USERNAME_FIELD, user.getUsername());
            cv.put(UserFields.USER_VACANCIES, user.getUserVacancies());
            cv.put(UserFields.ASSIGNED_VACANCIES, user.getAssignedVacancies());

            long insert = database.insert(USER_TABLE, null, cv);
            if (insert == -1) {
                Toast.makeText(context, Messages.ERROR_CREATE_USER, Toast.LENGTH_LONG).show();
                return false;
            } else {
                Toast.makeText(context, Messages.USER_IS_CREATED, Toast.LENGTH_LONG).show();
                return true;
            }
        } else {
            Toast.makeText(context, Messages.USER_IS_ALREADY_EXISTS, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public UserDataModel getUserByParameter(String parameter, String fieldName) {
        String query = String.format(Queries.SELECT_ONE, USER_TABLE, fieldName, parameter);

        UserDataModel user = null;

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            user = new UserDataModel();
            do {
                user.setId(cursor.getLong(0));
                user.setLogin(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setUsername(cursor.getString(3));
                user.setUserVacancies(cursor.getLong(4));
                user.setAssignedVacancies(cursor.getLong(5));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return user;
    }

    public void updateUser(Context context, UserDataModel newUserParameters) {
        UserValidator.isValidUserToCreate(newUserParameters);

        if (!isUserNotExists(newUserParameters)) {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(UserFields.PASSWORD_FIELD, newUserParameters.getPassword());
            cv.put(UserFields.ASSIGNED_VACANCIES, newUserParameters.getAssignedVacancies());
            cv.put(UserFields.USER_VACANCIES, newUserParameters.getUserVacancies());

            long insert = database.update(USER_TABLE, cv, "ID=?", new String[]{newUserParameters.getId().toString()});
            if (insert == -1) {
                Toast.makeText(context, Messages.ERROR_UPDATE_USER, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, Messages.USER_IS_UPDATED, Toast.LENGTH_LONG).show();
            }
        } else {
            throw new ValidationException(Messages.USER_IS_NOT_EXISTS);
        }
    }

    private boolean isUserNotExists(UserDataModel user) {

        return getUserByParameter(user.getUsername(), UserFields.USERNAME_FIELD) == null && getUserByParameter(user.getLogin(), UserFields.LOGIN_FIELD) == null;
    }
}

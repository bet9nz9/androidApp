package com.example.kursach.validator;

import com.example.kursach.exception.Messages;
import com.example.kursach.exception.ValidationException;
import com.example.kursach.model.UserDataModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public static boolean isValidUserToCreate(UserDataModel userDataModel) throws RuntimeException {

        isValidField(userDataModel.getLogin(), "login");
        isValidField(userDataModel.getPassword(), "password");
        isValidField(userDataModel.getUsername(), "username");

        return true;
    }

    private static void isValidField(String field, String fieldName) {

        if (!checkLength(field, fieldName)) {
            throw new ValidationException(String.format(Messages.MIN_LENGTH_SIX_SYMBOLS, fieldName));
        }

        isContainSpecialSymbols(field, fieldName);

    }

    private static boolean checkLength(String value, String fieldName) {
        if (value == null || value.equals("")) {
            throw new ValidationException(String.format(Messages.FIElD_IS_EMPTY, fieldName));
        } else {
            return value.length() >= 6;
        }
    }

    private static void isContainSpecialSymbols(String value, String filedName) {
        Pattern pattern = Pattern.compile("((\\w+)?(\\d+)?(\\w+)?)");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            String foundedValue = value.substring(matcher.start(), matcher.end());
            if (foundedValue.length() != value.length()) {
                throw new ValidationException(String.format(Messages.CONTAINS_UNRESOLVED_SYMBOLS, filedName));
            }
        } else {
            throw new ValidationException(String.format(Messages.NOT_VALID_FIELD, filedName));
        }
    }

}

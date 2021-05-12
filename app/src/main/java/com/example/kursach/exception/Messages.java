package com.example.kursach.exception;

public interface Messages {

    String USER_NOT_FOUND = "User not found!";
    String USER_IS_ALREADY_EXISTS = "User is already exists!";
    String USER_IS_NOT_EXISTS = "User is not exists!";
    String ERROR_CREATE_USER = "Error while creating user!";
    String WRONG_LOGIN_OR_PASS = "Wrong login or password!";
    String PASS_IS_NOT_CONFIRMED = "Password is not confirmed!";
    String MIN_LENGTH_SIX_SYMBOLS = "Min length of %s 6 symbols.";
    String FIElD_IS_EMPTY = "Field %s cannot be empty!";
    String CONTAINS_UNRESOLVED_SYMBOLS = "Field %s contains unresolved symbols.";
    String NOT_VALID_FIELD = "Field %s is not valid.";
    String USER_IS_CREATED = "User has been successfully created!";

    String COAST_LESS_THAN_ZERO = "Coast cannot be less thant zero!";
    String COAST_MORE_THAN_5000 = "Coast cannot be more than 5000!";
    String COAST_EQUALS_ZERO = "Coast cannot be equal zero!";
    String YOU_ARE_NOT_LOGGINED = "You are not loggined!";
    String COAST_IS_NOT_NUMERIC = "Field price is not numeric!";
    String VACANCY_IS_CREATED = "Vacancy has been successfully created!";
    String ERROR_CREATE_VACANCY = "Error while creating vacancy!";


}

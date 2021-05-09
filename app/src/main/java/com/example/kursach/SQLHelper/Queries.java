package com.example.kursach.SQLHelper;

public interface Queries {

    String SELECT_ONE = "SELECT * FROM %s WHERE %s='%s'";
    String SELECT_ALL = "SELECT * FROM %1";

    String INSERT_USER = "INSET INTO USER_TABLE(ID, LOGIN, PASS, USERNAME) VALUES (%1, %2, %3, %4)";
    String INSERT_VACANCY = "INSET INTO VACANCY_TABLE(ID, COAST, SHORT_DESC, FULL_DESC, IS_SELECTED, EXECUTOR_ID, EMPLOYEE_ID) VALUES (%1, %2, %3, %4, %5, %6, %7)";
    String CHANGE_OBJECT = "UPDATE TABLE %1 SET %2 = %3";
}

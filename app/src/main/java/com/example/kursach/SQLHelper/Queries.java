package com.example.kursach.SQLHelper;

public interface Queries {

    String SELECT_ONE = "SELECT * FROM %s WHERE %s='%s'";
    String SELECT_ALL = "SELECT * FROM %1";

    String SELECT_ALL_WHERE = "SELECT * FROM %s where %s=%s";
}

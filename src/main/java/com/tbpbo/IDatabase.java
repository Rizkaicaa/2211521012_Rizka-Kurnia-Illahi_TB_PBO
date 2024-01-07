package com.tbpbo;

import java.sql.SQLException;

//Interface IDatabase
public interface IDatabase {
    public void insert() throws SQLException;

    public void display() throws SQLException;

    public void update() throws SQLException;

    public void search() throws SQLException;

    public void delete() throws SQLException;
}

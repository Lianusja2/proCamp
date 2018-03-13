package com.flowergarden.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by inna.pshenychna on 3/12/2018.
 */
public class DaoFactoryImpl implements DaoFactory {
    private File file = new File("flowergarden.db");
    private String url;

    public DaoFactoryImpl() {
        try {
            url = "jdbc:sqlite:" + file.getCanonicalFile().toURI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    @Override
    public BouquetDao getBouquetDao(Connection connection) {
        return new BouquetDaoImpl(connection);
    }

    @Override
    public FlowerDao getFlowerDao(Connection connection) {
        return new FlowerDaoImpl(connection);
    }
}

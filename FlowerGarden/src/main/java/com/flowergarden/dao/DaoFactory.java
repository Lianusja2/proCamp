package com.flowergarden.dao;

/**
 * Created by inna.pshenychna on 3/12/2018.
 */

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Factory for work with DB
 */
public interface DaoFactory {

    /**
     * @return Connection to DB
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * @param connection to DB
     * @return BouquetDao for controlling persistence state of Bouquet object
     */
    BouquetDao getBouquetDao(Connection connection);

    /**
     * @param connection to DB
     * @return FlowerDao for controlling persistence state of Flower object
     */
    FlowerDao getFlowerDao(Connection connection);
}
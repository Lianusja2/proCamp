package com.flowergarden.dao;

import java.sql.SQLException;
import java.util.List;

import com.flowergarden.flowers.Flower;


/**
 * Flower Data Access Object interface.
 */
public interface FlowerDao {

    /**
     * Get the  Flower object.
     *
     * @param id of Bouquet
     * @return Flower
     * @throws SQLException
     */
    Flower read(int id) throws SQLException;


    /**
     * Get the list of flowers.
     *
     * @return list of  flowers
     * @throws SQLException
     */
    List<Flower> getAll() throws SQLException;


    /**
     * Get the list of flowers in bouquet
     *
     * @param bouquetId id of a bouquet
     * @return list of  flowers that belongs to the bouquet
     * @throws SQLException
     */
    List<Flower> getAllFlowersInBouquet(int bouquetId) throws SQLException;


}

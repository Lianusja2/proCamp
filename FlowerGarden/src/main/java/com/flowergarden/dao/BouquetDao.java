package com.flowergarden.dao;

import java.sql.SQLException;
import java.util.List;

import com.flowergarden.bouquet.Bouquet;

/**
 * Bouquet Data Access Object interface.
 * Provides CRUD operations with Bouquet.
 */
public interface BouquetDao {
    /**
     * Get the Bouquet object.
     *
     * @param id of Bouquet
     * @return Bouquet
     */
    Bouquet read(int id) throws SQLException;

    /**
     * Get the list of bouquets.
     *
     * @return list of  bouquets
     * @throws SQLException
     */
    List<Bouquet> getAll() throws SQLException;

}

package com.flowergarden.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.Flower;


public class BouquetDaoImpl implements BouquetDao {
    private final BasicDataSource basicDataSource;
    private FlowerDao flowerDao;

    BouquetDaoImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;

    }

    public void setFlowerDao(FlowerDao flowerDao) {
        this.flowerDao = flowerDao;
    }

    @Override
    public Bouquet read(int id) throws SQLException {
        String sql = "SELECT * FROM bouquet WHERE id = ?;";
        Bouquet bouquet = null;
        try (Connection connection = basicDataSource.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            bouquet = parseBouquet(rs);
            List<Flower> flowers = flowerDao.getAllFlowersInBouquet(bouquet.getId());
            flowers.forEach(bouquet::addFlower);
        }
        return bouquet;
    }

    private Bouquet parseBouquet(ResultSet rs) throws SQLException {
        Bouquet bouquet = null;
        if (rs.getString("name").equals("married")) {
            bouquet = new MarriedBouquet();
            bouquet.setId(rs.getInt("id"));
            bouquet.setAssembledPrice(rs.getFloat("assemble_price"));
        }
        return bouquet;
    }

    @Override
    public List<Bouquet> getAll() throws SQLException {
        String sql = "SELECT * FROM bouquet;";
        List<Bouquet> bouquets = new ArrayList<>();
        try (Connection connection = basicDataSource.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                bouquets.add(parseBouquet(rs));
            }
            return bouquets;
        }
    }
}

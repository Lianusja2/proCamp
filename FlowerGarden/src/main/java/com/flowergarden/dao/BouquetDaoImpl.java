package com.flowergarden.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.Flower;

public class BouquetDaoImpl implements BouquetDao {
    private final Connection connection;

    BouquetDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Bouquet read(int id) throws SQLException {
        String sql = "SELECT * FROM bouquet WHERE id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        rs.next();
        Bouquet bouquet = parseBouquet(rs);
        List<Flower> flowers = new FlowerDaoImpl(connection).getAllFlowersInBouquet(bouquet.getId());
        flowers.forEach(flower -> bouquet.addFlower(flower));
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
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            bouquets.add(parseBouquet(rs));
        }
        return bouquets;
    }
}

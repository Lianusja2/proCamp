package com.flowergarden.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.dbcp.BasicDataSource;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;


public class FlowerDaoImpl implements FlowerDao {
    private final BasicDataSource basicDataSource;

    FlowerDaoImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public Flower read(int id) throws SQLException {
        String sql = "SELECT * FROM flower WHERE id = ?;";
        ResultSet rs = null;
        try (Connection connection = basicDataSource.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            rs.next();
        }
        return Objects.isNull(rs) ? null : parseFlower(rs);
    }

    private Flower parseFlower(ResultSet rs) throws SQLException {
        Flower flower = null;
        boolean spikes = rs.getInt("spike") == 1;
        int petals = rs.getInt("petals");
        switch (rs.getString("name")) {
            case "rose":
                flower = new Rose(spikes, rs.getInt("lenght"), rs.getFloat("price"), new FreshnessInteger(rs.getInt("freshness")));
                flower.setId(rs.getInt("id"));
                break;

            case "chamomile":
                flower = new Chamomile(petals, rs.getInt("lenght"), rs.getFloat("price"), new FreshnessInteger(rs.getInt("freshness")));
                flower.setId(rs.getInt("id"));
                break;

            default:
                break;

        }
        return flower;
    }

    @Override
    public List<Flower> getAll() throws SQLException {
        String sql = "SELECT * FROM flower;";
        List<Flower> flowers = new ArrayList<>();
        try (Connection connection = basicDataSource.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flower flower = parseFlower(rs);
                flowers.add(flower);
            }
            return flowers;
        }
    }

    @Override
    public List<Flower> getAllFlowersInBouquet(int bouquetId) throws SQLException {
        String sql = "SELECT * FROM flower WHERE bouquet_id = ?;";
        List<Flower> flowers = new ArrayList<>();
        try (Connection connection = basicDataSource.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bouquetId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flower flower = parseFlower(rs);
                flowers.add(flower);
            }
            return flowers;
        }
    }

}

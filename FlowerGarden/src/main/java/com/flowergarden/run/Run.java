package com.flowergarden.run;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.DaoFactoryImpl;

public class Run {

    public static void main(String[] args) throws IOException {
        DaoFactoryImpl daoFactory = new DaoFactoryImpl();
        try (Connection con = daoFactory.getConnection()) {
            Bouquet bouquet = daoFactory.getBouquetDao(con).read(1);
            System.out.println(bouquet.getPrice());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

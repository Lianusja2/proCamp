package com.flowergarden.run;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.BouquetDaoImpl;
import com.flowergarden.dao.BouquetDoaJson;

public class Run {

    public static void main(String[] args) {

        String fileName = "Bouqeuts.txt";
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        BouquetDao bouquetDao = context.getBean("bouquetDao", BouquetDaoImpl.class);

        List<Bouquet> bouquets = new ArrayList<>();
        try {
            bouquets.add(bouquetDao.read(1));
            bouquets.add(bouquetDao.read(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(bouquets);

        BouquetDoaJson bouquetJson = new BouquetDoaJson();
        List<Bouquet> newBouquets = new ArrayList<>();
        try {
            bouquetJson.save(bouquets, fileName);
            newBouquets  = bouquetJson.read(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newBouquets);
    }

}

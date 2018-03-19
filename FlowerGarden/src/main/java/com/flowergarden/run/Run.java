package com.flowergarden.run;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.BouquetDaoImpl;

public class Run {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        BouquetDao bouquetDao = context.getBean("bouquetDao", BouquetDaoImpl.class);

        Bouquet bouquet = null;
        try {
            bouquet = bouquetDao.read(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(bouquet.getPrice());
    }

}

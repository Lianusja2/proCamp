package com.flowergarden.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.google.gson.Gson;

/**
 * Created by inna.pshenychna on 3/26/2018.
 */
public class BouquetDoaJson {
    private Gson gson = new Gson();


    public void save(List<Bouquet> bouquets, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            saveBouquets(bouquets, writer);
        }
    }

    private void saveBouquets(List<Bouquet> bouquets, PrintWriter writer) {
        for (Bouquet bouquet : bouquets) {
            saveBouquet(bouquet, writer);
        }
    }

    private void saveBouquet(Bouquet bouquet, PrintWriter writer) {
        writer.println(gson.toJson(bouquet));
    }


    public List<Bouquet> read(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return readBouquets(reader);
        }
    }

    private List<Bouquet> readBouquets(BufferedReader reader) throws IOException {
        List<Bouquet> bouquets = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            MarriedBouquet bouquet = gson.fromJson(line, MarriedBouquet.class);
            if (bouquet != null) {
                bouquets.add(bouquet);
            }
        }
        return bouquets;
    }

}

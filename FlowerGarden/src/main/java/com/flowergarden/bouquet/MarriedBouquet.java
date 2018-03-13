package com.flowergarden.bouquet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.flowergarden.exception.IlligalParameterException;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.properties.FreshnessInteger;

public class MarriedBouquet implements Bouquet<GeneralFlower> {

    private float assemblePrice = 120;
    private List<GeneralFlower> flowerList = new ArrayList<>();
    private int id;
    private String name;


    void setFlowerListForMock(List<GeneralFlower> list) {
        flowerList = list;
    }

    @Override
    public float getPrice() {
        float price = assemblePrice;
        for (GeneralFlower flower : flowerList) {
            price += flower.getPrice();
        }
        return price;
    }

    @Override
    public void addFlower(GeneralFlower flower) {
        if (flower.getPrice() < 0) {
            throw new IlligalParameterException("Can not add flower to bouquet with negative price");
        }
        if (flower.getLenght() < 0) {
            throw new IlligalParameterException("Can not add flower to bouquet with negative length");
        }
        if (flower.getFreshness().compareTo(new FreshnessInteger(0)) == -1) {
            throw new IlligalParameterException("Can not add flower to bouquet with negative freshness");
        }
        flowerList.add(flower);
    }

    @Override
    public Collection<GeneralFlower> searchFlowersByLenght(int start, int end) {
        if (start > end) {
            throw new IlligalParameterException(String.format("Min length '%s', can not be greater then max length '%s'", start, end));
        }
        if (start < 0) {
            throw new IlligalParameterException(String.format("Min length '%s', can not be negative", start));
        }
        List<GeneralFlower> searchResult = new ArrayList<GeneralFlower>();
        for (GeneralFlower flower : flowerList) {
            if (flower.getLenght() >= start && flower.getLenght() <= end) {
                searchResult.add(flower);
            }
        }
        return searchResult;
    }

    @Override
    public void sortByFreshness() {
        Collections.sort(flowerList);
    }

    @Override
    public Collection<GeneralFlower> getFlowers() {
        return flowerList;
    }

    public void setAssembledPrice(float price) {
        assemblePrice = price;
    }

    public float getAssemblePrice() {
        return assemblePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     @Override
    public String toString() {
        return "MarriedBouquet{" +
                "assemblePrice=" + assemblePrice +
                ", flowerList=" + flowerList +
                ", id=" + id +
                '}';
    }
}

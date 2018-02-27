package com.flowergarden.bouquet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;

/**
 * Created by inna.pshenychna on 2/26/2018.
 */
public class MarriedBouquetTest {

    private MarriedBouquet marriedBouquet;
    private Rose rose;

    @Before
    public void init() {
        Chamomile chamomile = mock(Chamomile.class);
        when(chamomile.getPrice()).thenReturn((float) 25.23);
        when(chamomile.getLenght()).thenReturn(20);

        rose = mock(Rose.class);
        when(rose.getPrice()).thenReturn((float) 30.30);
        when(rose.getLenght()).thenReturn(25);

        marriedBouquet = new MarriedBouquet();
        marriedBouquet.addFlower(rose);
        marriedBouquet.addFlower(chamomile);
    }

    @Test
    public void getPricePositiveTest() {
        Assert.assertEquals(175.53, marriedBouquet.getPrice(), 0.01);
    }

    @Test
    public void searchFlowersByLenghtPositiveTest() {
        Assert.assertEquals(Collections.singletonList(rose), marriedBouquet.searchFlowersByLenght(23, 27));
    }

    @Test
    public void searchFlowersByLenghtWhenMinGraterThanMaxTest() {
        Assert.assertEquals(new ArrayList<GeneralFlower>(),marriedBouquet.searchFlowersByLenght(27, 20));
    }

    @Test
    public void searchFlowersByLenghtWithNegativeMinMaxValuesTest() {
        Assert.assertEquals(new ArrayList<GeneralFlower>(),marriedBouquet.searchFlowersByLenght(-10, -1));
    }
}

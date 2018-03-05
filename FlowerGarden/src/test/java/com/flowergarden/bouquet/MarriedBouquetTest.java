package com.flowergarden.bouquet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.flowergarden.exception.IlligalParameterException;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;

/**
 * Created by inna.pshenychna on 2/26/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class MarriedBouquetTest {

    private MarriedBouquet marriedBouquet;
    @Mock
    private Rose rose;
    @Mock
    private Chamomile chamomile;

    @Before
    public void init() {
        when(chamomile.getPrice()).thenReturn((float) 25.23);
        when(chamomile.getLenght()).thenReturn(20);
        when(chamomile.getFreshness()).thenReturn(new FreshnessInteger(1));

        when(rose.getPrice()).thenReturn((float) 30.30);
        when(rose.getLenght()).thenReturn(25);
        when(rose.getFreshness()).thenReturn(new FreshnessInteger(1));

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

    @Test(expected = IlligalParameterException.class)
    public void searchFlowersByLenghtWhenMinGraterThanMaxTest() {
        marriedBouquet.searchFlowersByLenght(27, 20);
    }

    @Test(expected = IlligalParameterException.class)
    public void searchFlowersByLenghtWithNegativeMinMaxValuesTest() {
        marriedBouquet.searchFlowersByLenght(-1, 4);
    }

    @Test
    public void flowerListTest() {
        List list = mock(List.class);
        MarriedBouquet marriedBouquet = new MarriedBouquet();
        marriedBouquet.setFlowerListForMock(list);

        marriedBouquet.addFlower(chamomile);

        verify(list).add(chamomile);
    }


    @Test(expected = IlligalParameterException.class)
    public void addFlowerWithNegativePriceTest() {
        Tulip tulip = mock(Tulip.class);
        when(tulip.getPrice()).thenReturn((float) -1);

        marriedBouquet.addFlower(tulip);
    }

    @Test(expected = IlligalParameterException.class)
    public void addFlowerWithNegativeLengthTest() {
        Tulip tulip = mock(Tulip.class);
        when(tulip.getLenght()).thenReturn(-1);

        marriedBouquet.addFlower(tulip);
    }


    @Test(expected = IlligalParameterException.class)
    public void addFlowerWithNegativeFreshnessTest() {
        Tulip tulip = mock(Tulip.class);
        when(tulip.getFreshness()).thenReturn(new FreshnessInteger(-1));

        marriedBouquet.addFlower(tulip);
    }

    @Test
    public void setAssebledPriceTest(){
        MarriedBouquet marriedBouquet = new MarriedBouquet();
        marriedBouquet.setAssembledPrice(100);

        Assert.assertEquals(100, marriedBouquet.getPrice(), 0.01);
    }


}

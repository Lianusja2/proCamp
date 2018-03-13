package com.flowergarden.run;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by inna.pshenychna on 3/5/2018.
 * Tests created in during training class as examples of mockito capabilities
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoExampleTests {

    @Mock
    List mockList;

    @Test
    public void test1() {
        //mock creation List
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test(expected = RuntimeException.class)
    public void testStub() {

        List mockedList = mock(List.class);


        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());


        Assert.assertEquals("first", mockedList.get(0));
        mockedList.get(1);
    }

    @Test
    public void testMatcher() {
        List mockedList = mock(List.class);

        when(mockedList.get(anyInt())).thenReturn("first");

        Assert.assertEquals("first", mockedList.get(anyInt()));
        verify(mockedList).get(anyInt());

    }


    @Test
    public void testAnnotationMock() {
        when(mockList.get(anyInt())).thenReturn("first");

        Assert.assertEquals("first", mockList.get(anyInt()));
        verify(mockList).get(anyInt());

    }

    @Test
    public void testNumberOfCalls() {
        List mockedList = mock(List.class);

        when(mockedList.get(anyInt())).thenReturn("first");
        verify(mockedList, never()).get(anyInt());

        mockedList.get(0);
        mockedList.get(1);
        mockedList.get(999);

        verify(mockedList, times(3)).get(anyInt());
        verify(mockedList, atLeast(1)).get(anyInt());
        verify(mockedList, atMost(4)).get(anyInt());

        InOrder inOrder = Mockito.inOrder(mockedList);
        inOrder.verify(mockedList).get(0);
        inOrder.verify(mockedList).get(1);

    }


    @Test(expected = RuntimeException.class)
    public void testSequenceOfMethodeCalls() {
        List mockedList = mock(List.class);

        when(mockedList.get(anyInt())).thenReturn("first", "second").thenThrow(new RuntimeException());

        Assert.assertEquals("first", mockedList.get(anyInt()));
        Assert.assertEquals("second", mockedList.get(anyInt()));
        mockedList.get(anyInt());
    }


}

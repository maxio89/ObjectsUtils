package com.github.maxio89.utils.objects.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    private class Drop implements Summable<Drop> {

        private final int size;

        public Drop(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        @Nonnull
        @Override
        public Drop add(@Nullable Drop drop) {
            return null == drop ? this : new Drop(size + drop.getSize());
        }
    }

    private Calculator<Drop> calculator = new Calculator<>();


    @Test
    public void sum_withListOfDrops_returnsOneBigDrop() {

        //given
        final List<Drop> drops = Arrays.asList(new Drop(1), new Drop(10));

        //when
        final Drop sum = calculator.sum(drops);

        //then
        assertNotNull(sum);
        assertNotNull(sum.getSize());
        assertEquals(11, sum.getSize());
    }

    @Test
    public void sum_withSingleDrop_returnsOneBigDrop() {

        //given
        final List<Drop> drops = Collections.singletonList(new Drop(1));

        //when
        final Drop sum = calculator.sum(drops);

        //then
        assertNotNull(sum);
        assertNotNull(sum.getSize());
        assertEquals(1, sum.getSize());
    }

    @Test
    public void sum_withEmptyList_returnsNull() {

        //given
        final List<Drop> drops = Collections.emptyList();

        //when
        final Drop sum = calculator.sum(drops);

        //then
        assertNull(sum);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sum_withNullAsAnArgument_throwsIllegalArgumentException() {

        //given

        //when
        calculator.sum(null);

        //then
    }

    //Play with Mockito...
    @Test
    public void sum_withListOfMocks_returnsSum() {

        //given
        Drop drop1 = mock(Drop.class);
        Drop drop2 = mock(Drop.class);
        Drop sumMock = mock(Drop.class);
        final List<Drop> drops = new LinkedList<>(Arrays.asList(drop1, drop2));
        when(drop1.add(any(Drop.class))).thenReturn(sumMock);
        when(drop2.add(any(Drop.class))).thenReturn(sumMock);
        //when
        final Drop sum = calculator.sum(drops);

        //then
        verify(drop1).add(eq(drop2));
        verify(drop2, never()).add(any(Drop.class));
        assertNotNull(sum);
        assertNotNull(sum.getSize());
        assertEquals(sumMock, sum);
    }

}
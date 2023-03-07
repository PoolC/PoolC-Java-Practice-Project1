package com.poolc.javapractice.lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Step1Test {

    @Test
    public void makeLottoTest(){
        Step1 step1 = new Step1();
        ArrayList<ArrayList<Integer>> lotto = step1.makeLotto(100);
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < lotto.get(i).size(); j++){
                Assertions.assertTrue(lotto.get(i).get(j) >= 1 && lotto.get(i).get(j) <= 45);
            }
        }
    }

    @Test
    public void winNumbersTest(){
        Step1 step1 = new Step1();
        ArrayList<Integer> actualList = step1.winNumbers("1, 2,3, 4,  5,6");
        ArrayList<Integer> expectedList = new ArrayList<>();
        for (int i = 1; i < 7; i++){
            expectedList.add(i);
        }
        Assertions.assertEquals(actualList, expectedList);
    }

    @Test
    public void countResultsTest(){
        Step1 step1 = new Step1();
        ArrayList<Integer> winListTest = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ArrayList<ArrayList<Integer>> lottoTest = new ArrayList<>(2);
        lottoTest.add(new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8)));
        lottoTest.add(new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8, 9)));
        ArrayList<Integer> actualList = step1.countResults(winListTest, lottoTest, 2);

        ArrayList<Integer> expectedList = new ArrayList<>();
        expectedList.add(4);
        expectedList.add(3);
        Assertions.assertEquals(actualList, expectedList);
    }

    @Test
    public void resultTest(){
        Step1 step1 = new Step1();
        ArrayList<Integer> lottoResultsTest = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 3, 4, 4));
        ArrayList<Integer> actual = step1.result(lottoResultsTest);

        ArrayList<Integer> expected = new ArrayList<>(4);
        expected.add(3);
        expected.add(2);
        expected.add(0);
        expected.add(0);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void earningRateTest(){
        Step1 step1 = new Step1();
        ArrayList<Integer> lottoResultsTest = new ArrayList<>();
        lottoResultsTest.add(3);
        double actual = step1.earningRate(lottoResultsTest,14000);

        double expected = -64.28;

        Assertions.assertEquals(actual, expected);
    }
}

package com.poolc.javapractice.lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Step1Test {

    @Test
    public void makeLottoTest(){
        Step1 step1 = new Step1();
        ArrayList<Integer>[] lotto = step1.makeLotto(100);
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < lotto[i].size(); j++){
                Assertions.assertTrue(lotto[i].get(j) >= 1 && lotto[i].get(j) <= 45);
            }
        }
    }

    @Test
    public void winNumbersTest(){
        Step1 step1 = new Step1();
        ArrayList<Integer> expectedList = step1.winNumbers("1, 2,3, 4, 5,6");
        ArrayList<Integer> actualList = new ArrayList<>();
        for (int i = 1; i < 7; i++){
            actualList.add(i);
        }
        Assertions.assertEquals(expectedList, actualList);
    }
}

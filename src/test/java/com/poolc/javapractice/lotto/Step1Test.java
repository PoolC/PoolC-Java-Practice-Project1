package com.poolc.javapractice.lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;
public class Step1Test {

    @Test
    public void makeLottoTest(){

        Step1 step1 = new Step1();
        ArrayList<Integer>[] lotto = step1.makeLotto(100);
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < lotto[i].size(); j++){
                Assertions.assertTrue(lotto[i].get(j) >= 1 && lotto[i].get(j) <= 44);
            }
        }

    }
}

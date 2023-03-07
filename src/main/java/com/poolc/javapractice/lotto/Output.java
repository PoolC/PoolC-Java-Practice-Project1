package com.poolc.javapractice.lotto;

import java.util.*;

public class Output {
    public void printLottoCount(int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다.");
    }

    public void printWinStatistics(ArrayList<Integer> resultList) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        System.out.println("3개 일치 (5000원) - " +  resultList.get(0) + "개");
        System.out.println("4개 일치 (50000원) - " + resultList.get(1) + "개");
        System.out.println("5개 일치 (1500000원) - " + resultList.get(2) + "개");
        System.out.println("6개 일치 (2000000000원) - " + resultList.get(3) + "개");
    }

    public void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %f%%입니다.", earningRate);
    }
}

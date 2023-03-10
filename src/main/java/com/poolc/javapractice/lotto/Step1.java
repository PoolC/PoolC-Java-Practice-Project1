package com.poolc.javapractice.lotto;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Step1 {
    public static void main(String[] args) {
        Input input = new Input();

        int buyAmount = input.getBuyAmount();
        int lottoCount = buyAmount / 1000;

        ArrayList<ArrayList<Integer>> lotto = makeLotto(lottoCount);
        ArrayList<Integer> winList = input.getWinNumbers();
        ArrayList<Integer> lottoResults = result(countResults(winList, lotto, lottoCount));

        Output output = new Output();
        output.printLottoCount(lottoCount);
        output.printWinStatistics(lottoResults);
        output.printEarningRate(earningRate(lottoResults, buyAmount));
    }


    public static ArrayList<ArrayList<Integer>> makeLotto(int lottoCount) {
        ArrayList<ArrayList<Integer>> lotto = new ArrayList<>(lottoCount);
        Random random = new Random();
        for (int i = 0; i < lottoCount; i++) {
            lotto.add(new ArrayList<>());

            while (lotto.get(i).size() < 6) {
                int num = random.nextInt(45) + 1;
                if (!lotto.get(i).contains(num)) {
                    lotto.get(i).add(num);
                }
            }

            System.out.println(lotto.get(i).toString());
        }
        return lotto;
    }

    public static ArrayList<Integer> winNumbers(String winNumbers) {

        String[] winList = winNumbers.split(",\\s*");
        ArrayList<Integer> winArrayList = new ArrayList<>(6);

        for (int i = 0; i < winList.length; i++){
            winArrayList.add(Integer.parseInt(winList[i]));
        }

        return winArrayList;
    }

    public static ArrayList<Integer> countResults(ArrayList<Integer> winList, ArrayList<ArrayList<Integer>> lotto, int lottoCount) {
        ArrayList<Integer> countedList = new ArrayList<>(lottoCount);

        for (int i = 0; i < lottoCount; i++) {
            ArrayList<Integer> eachLotto = lotto.get(i);
            countedList.add(countMatchedNumbers(winList, eachLotto));
        }

        return countedList;
    }

    public static int countMatchedNumbers(ArrayList<Integer> winList, ArrayList<Integer> lotto) {
        return (int) lotto.stream()
                    .filter(winList::contains)
                    .count();
    }

    public static ArrayList<Integer> result(ArrayList<Integer> countedList){
        ArrayList<Integer> lottoResults = new ArrayList<>(4);

        for (int i = 3; i <= 6; i++){
            lottoResults.add(Collections.frequency(countedList, i));
        }

        return lottoResults;
    }

    public static double earningRate(ArrayList<Integer> lottoResults, int buyAmount) {

        double totalMoney = 5000 * lottoResults.get(0) + 50000 * lottoResults.get(1)
                + 1500000 * lottoResults.get(2) + 2000000000 * lottoResults.get(3);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
        return Double.parseDouble(df.format(((totalMoney - buyAmount) / buyAmount * 100)));
    }
}

package com.poolc.javapractice.lotto;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Lotto {
    public static void main(String[] args) {
        Input input = new Input();
        Output output = new Output();

        int buyAmount = input.getBuyAmount();
        int lottoCount = buyAmount / 1000;
        output.printLottoCount(lottoCount);

        ArrayList<ArrayList<Integer>> lotto = makeLotto(lottoCount);
        ArrayList<Integer> winList = input.getWinNumbers();
        int bonusNumber = input.getBonusNumber();
        ArrayList<Integer> countedList = countResults(winList, lotto, lottoCount);
        ArrayList<Integer> lottoResults = result(countedList);

        int count2ndLotto = find2ndLotto(lotto, countedList, lottoResults, bonusNumber);

        output.printWinStatistics(lottoResults, count2ndLotto);
        output.printEarningRate(earningRate(lottoResults, buyAmount, count2ndLotto));
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
        System.out.println();
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

    public static int find2ndLotto(ArrayList<ArrayList<Integer>> lotto, ArrayList<Integer> countedList, ArrayList<Integer> lottoResults, int bonusNumber) {
        int indexFiveMatchedLotto = countedList.indexOf(5);
        int count2ndLotto = 0;

        while(indexFiveMatchedLotto != -1){
            BonusMatch bonusMatch = BonusMatch.isMatched(lotto.get(indexFiveMatchedLotto), bonusNumber);
            count2ndLotto += bonusMatch.run(lottoResults);
            ArrayList<Integer> tempList = new ArrayList<>(countedList.subList(indexFiveMatchedLotto + 1, countedList.size()));
            indexFiveMatchedLotto = tempList.indexOf(5);
        }

        return count2ndLotto;
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

    public static double earningRate(ArrayList<Integer> lottoResults, int buyAmount, int count2ndLotto) {

        double totalMoney = 5000 * lottoResults.get(0) + 50000 * lottoResults.get(1)
                + 1500000 * lottoResults.get(2) + 30000000 * count2ndLotto+ 2000000000 * lottoResults.get(3);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
        return Double.parseDouble(df.format(((totalMoney - buyAmount) / buyAmount * 100)));
    }

    enum BonusMatch {
        NOTMATCH(false){
            @Override
            int run(ArrayList<Integer> lottoResults) {
                return 0;
            }
        },
        MATCH(true){
            @Override
            int run(ArrayList<Integer> lottoResults) {
                lottoResults.set(2, lottoResults.get(2) - 1);
                return 1;
            }
        };

        private boolean matchedBonus;

        BonusMatch(boolean matchedBonus){
            this.matchedBonus = matchedBonus;
        }

        public static BonusMatch isMatched(ArrayList<Integer> lotto, int bonusNumber) {
            if(lotto.contains(bonusNumber)){
                return BonusMatch.MATCH;
            }
            return BonusMatch.NOTMATCH;
        }

        abstract int run(ArrayList<Integer> countedList);



    }

}

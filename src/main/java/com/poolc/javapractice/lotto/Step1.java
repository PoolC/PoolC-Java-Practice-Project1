package com.poolc.javapractice.lotto;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Step1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        int buyAmount = sc.nextInt();
        sc.nextLine();

        int lottoCount = buyAmount / 1000;
        System.out.println(lottoCount + "개를 구매했습니다.");

        ArrayList<ArrayList<Integer>> lotto = makeLotto(lottoCount);
        System.out.println();

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String win = sc.nextLine();

        ArrayList<Integer> winList = winNumbers(win);
        ArrayList<Integer> lottoResults = countResults(winList, lotto, lottoCount);

        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        System.out.println("3개 일치 (5000원) - " + Collections.frequency(lottoResults, 3) + "개");
        System.out.println("4개 일치 (50000원) - " + Collections.frequency(lottoResults, 4) + "개");
        System.out.println("5개 일치 (1500000원) - " + Collections.frequency(lottoResults, 5) + "개");
        System.out.println("6개 일치 (2000000000원) - " + Collections.frequency(lottoResults, 6) + "개");

        System.out.printf("총 수익률은 %f%%입니다.", earningRate(lottoResults, buyAmount));
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
        StringTokenizer tzk = new StringTokenizer(winNumbers, ", ");
        ArrayList<Integer> winList = new ArrayList<>(6);

        while (tzk.hasMoreTokens()) {
            winList.add(Integer.parseInt(tzk.nextToken()));
        }

        return winList;
    }

    public static ArrayList<Integer> countResults(ArrayList<Integer> winList, ArrayList<ArrayList<Integer>> lotto, int lottoCount) {
        ArrayList<Integer> lottoResults = new ArrayList<>(lottoCount);

        for (int i = 0; i < lottoCount; i++) {
            ArrayList<Integer> eachLotto = lotto.get(i);
            lottoResults.add(countMatchedNumbers(winList, eachLotto));
        }

        return lottoResults;
    }

    public static int countMatchedNumbers(ArrayList<Integer> winList, ArrayList<Integer> lotto){
        int matchedCount = 0;
        for (int i = 0; i < winList.size(); i++) {
            if (lotto.contains(winList.get(i))) {
                matchedCount++;
            }
        }
        return matchedCount;
    }

    public static double earningRate(ArrayList<Integer> lottoResults, int buyAmount) {
        double totalMoney = 5000 * Collections.frequency(lottoResults, 3) + 50000 * Collections.frequency(lottoResults, 4)
                + 1500000 * Collections.frequency(lottoResults, 5) + 2000000000 * Collections.frequency(lottoResults, 6);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
        return Double.parseDouble(df.format(((totalMoney - buyAmount) / buyAmount * 100)));
    }
}


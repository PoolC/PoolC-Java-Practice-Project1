package com.poolc.javapractice.lotto;

import java.util.*;

public class Step1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        int buyAmount = sc.nextInt();
        sc.nextLine();

        int lottoCount = buyAmount / 1000;
        System.out.println(lottoCount + "개를 구매했습니다.");

        ArrayList<Integer>[] lotto = makeLotto(lottoCount);
        System.out.println();

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String win = sc.nextLine();

        ArrayList<Integer> winList = winNumbers(win);
        ArrayList<Integer> lottoResults = countResults(winList, lotto, lottoCount);
    }

    private static ArrayList<Integer>[] makeLotto(int lottoCount) {
        ArrayList<Integer>[] lotto = new ArrayList[lottoCount];
        Random random = new Random();
        for (int i = 0; i < lottoCount; i++) {
            lotto[i] = new ArrayList<>();

            while (lotto[i].size() < 6) {
                int num = random.nextInt(44) + 1;
                if (!lotto[i].contains(num)) {
                    lotto[i].add(num);
                }
            }

            System.out.println(lotto[i].toString());
        }
        return lotto;
    }

    private static ArrayList<Integer> winNumbers(String winNumbers) {
        StringTokenizer tzk = new StringTokenizer(winNumbers, ", ");
        ArrayList<Integer> winList = new ArrayList<>(6);

        while (tzk.hasMoreTokens()) {
            winList.add(Integer.parseInt(tzk.nextToken()));
        }

        return winList;
    }

    private static ArrayList<Integer> countResults(ArrayList<Integer> winList, ArrayList<Integer>[] lotto, int lottoCount) {
        ArrayList<Integer> lottoResults = new ArrayList<>(lottoCount);

        for (int i = 0; i < lottoCount; i++) {
            ArrayList<Integer> eachLotto = lotto[i];
            lottoResults.add(countMatchedNumbers(winList, eachLotto));
        }

        return lottoResults;
    }

    private static int countMatchedNumbers(ArrayList<Integer> winList, ArrayList<Integer> lotto){
        int matchedCount = 0;
        for (int i = 0; i < winList.size(); i++) {
            if (lotto.contains(winList.get(i))) {
                matchedCount++;
            }
        }
        return matchedCount;
    }
}


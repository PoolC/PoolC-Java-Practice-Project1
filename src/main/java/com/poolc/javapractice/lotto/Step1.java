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
}


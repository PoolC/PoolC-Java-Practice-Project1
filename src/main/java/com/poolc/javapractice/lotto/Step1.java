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


    }
}


package com.poolc.javapractice.lotto;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    private final Scanner sc;

    public Input() {
        this.sc = new Scanner(System.in);
    }

    public int getBuyAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int buyAmount = sc.nextInt();
        sc.nextLine();
        return buyAmount;
    }

    public ArrayList<Integer> getWinNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String win = sc.nextLine();
        return Step1.winNumbers(win);
    }
}


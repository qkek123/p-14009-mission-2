package org.example.p_14009_mission_2;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int count = 0;

    public static void main(String[] args) {
        System.out.println("== 명언 앱==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("종료")) {
                sc.close();
                break;
            } else if (cmd.equals("등록")) {
                register();
            }
        }
    }

    public static void register() {
        System.out.print("명언: ");
        String wiseSaying = sc.nextLine().trim();
        System.out.print("작가: ");
        String author = sc.nextLine().trim();
        count++;
        System.out.printf("%d번 명언이 등록되었습니다.\n", count);
    }
}
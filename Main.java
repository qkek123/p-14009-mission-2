package org.example.p_14009_mission_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int count = 0;
    static List<String[]> list = new ArrayList<>();

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
            } else if (cmd.equals("목록")) {
                getList();
            } else if (cmd.startsWith("삭제?id=")) {
                delete(cmd);
            }
        }
    }

    public static void register() {
        System.out.print("명언: ");
        String wiseSaying = sc.nextLine().trim();
        if (wiseSaying.isEmpty()) {
            checkBlank();
            return;
        }
        System.out.print("작가: ");
        String author = sc.nextLine().trim();
        if (author.isEmpty()) {
            checkBlank();
            return;
        }
        count++;
        String[] arr = {String.valueOf(count), author, wiseSaying};
        list.add(arr);
        System.out.printf("%d번 명언이 등록되었습니다.\n", count);
    }

    public static void getList() {
        System.out.println("번호 / 작가 / 명언");

        for (int i = list.size() - 1; i >= 0; i--) {
            String[] el = list.get(i);
            System.out.printf("%s / %s / %s\n", el[0], el[1], el[2]);
        }
    }

    public static void checkBlank() {
        System.out.println("공란으로 둘 수 없습니다.");
        register();
    }

    public static void delete(String cmd) {
        try {
            int id = Integer.parseInt(cmd.substring(6));
            list.remove(id -1);
        } catch (Exception e) {
            System.out.println("잘못된 id입니다.");
        }
    }
}
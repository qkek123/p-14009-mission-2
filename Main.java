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
            String input = sc.nextLine();
            Rq rq = new Rq(input);
            String cmd = rq.getCommand();
            int id = rq.getId();

            switch(cmd) {
                case "종료"-> {
                    sc.close();
                    return;
                }
                case "등록" -> FunctionBoard.register();
                case "목록" -> FunctionBoard.getList();
                case "삭제" -> FunctionBoard.delete(id);
                case "수정" -> FunctionBoard.modify(id);
                case "빌드" -> FunctionBoard.build();
            }
        }
    }
}
package org.example.p_14009_mission_2;

import java.io.File;
import java.io.FileWriter;
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
            } else if (cmd.startsWith("수정?id=")) {
                modify(cmd);
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
        toJson(arr);
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
        System.out.println("공란으로 등록할 수 없습니다.");
        register();
    }

    public static void delete(String cmd) {
        try {
            int id = Integer.parseInt(cmd.substring(6));
            boolean found = false;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)[0].equals(String.valueOf(id))) {
                    System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
                    found = true;
                    list.remove(i);
                    break;
                }
            }

            if (!found) {
                System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            }
        } catch (Exception e) {
            System.out.println("id는 숫자로 작성해주세요.");
        }
    }

    public static void modify(String cmd) {
        try {
            int id = Integer.parseInt(cmd.substring(6));
            boolean found = false;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)[0].equals(String.valueOf(id))) {
                    System.out.printf("명언(기존) : %s\n", list.get(i)[2]);
                    System.out.print("명언 : ");
                    String wiseSaying = sc.nextLine();
                    System.out.printf("작가(기존) : %s\n", list.get(i)[1]);
                    System.out.print("작가 : ");
                    String author = sc.nextLine();
                    System.out.printf("%d번 명언이 수정되었습니다.\n", id);
                    found = true;
                    String[] arr = {list.get(i)[0], author, wiseSaying};
                    list.set(i, arr);
                    break;
                }
            }

            if (!found) {
                System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            }
        } catch (Exception e) {
            System.out.println("id는 숫자로 작성해주세요.");
        }
    }

    public static void toJson(String[] arr) {
        String dirPath = "da/wiseSaying/";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = dirPath + arr[0]+".json";
        String content = "{" +
                "    \"id\": " + arr[0] + "," +
                "    \"content\": \"" + arr[2] + "\"," +
                "    \"author\": \"" + arr[1] + "\"" +
                "}";
        String idFileName = dirPath+ "lastId.txt";
        String lastId = arr[0];

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            System.out.println("파일 저장 실패");
        }

        try {
            FileWriter writer = new FileWriter(idFileName);
            writer.write(lastId);
            writer.close();
        } catch (Exception e) {
            System.out.println("파일 저장 실패");
        }

    }
}
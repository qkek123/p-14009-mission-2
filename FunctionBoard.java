package org.example.p_14009_mission_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FunctionBoard {
    static Scanner sc = new Scanner(System.in);
    static int count = 0;
    static List<WiseSaying> list = new ArrayList<>();

    public static void register() {
        System.out.print("명언: ");
        String wiseSaying = sc.nextLine().trim();
        System.out.print("작가: ");
        String author = sc.nextLine().trim();

        count++;
        WiseSaying WiseSayings = new WiseSaying(count, wiseSaying, author);
        list.add(WiseSayings);
        toJson(WiseSayings);
        System.out.printf("%d번 명언이 등록되었습니다.\n", count);
    }

    public static void getList() {
        System.out.println("번호 / 작가 / 명언");

        for (int i = list.size() - 1; i >= 0; i--) {
            WiseSaying el = list.get(i);
            System.out.printf("%s / %s / %s\n", el.count, el.author, el.saying);
        }
    }

    public static void delete(int id) {
        try {
            boolean found = false;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).count == id) {
                    System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
                    found = true;
                    list.remove(i);
                    break;
                }
            }

            if (!found && id != -1) {
                System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            }
        } catch (Exception e) {
            System.out.println("id는 숫자로 작성해주세요.");
        }
    }

    public static void modify(int id) {
        try {
            boolean found = false;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).count == id) {
                    System.out.printf("명언(기존) : %s\n", list.get(i).saying);
                    System.out.print("명언 : ");
                    String wiseSaying = sc.nextLine();
                    System.out.printf("작가(기존) : %s\n", list.get(i).author);
                    System.out.print("작가 : ");
                    String author = sc.nextLine();
                    System.out.printf("%d번 명언이 수정되었습니다.\n", id);
                    WiseSaying WiseSayings = new WiseSaying(count, wiseSaying, author);
                    toJson(WiseSayings);
                    found = true;

                    WiseSaying newWiseSaying = new WiseSaying(list.get(i).count, author, wiseSaying);
                    list.set(i, newWiseSaying);
                    break;
                }
            }

            if (!found && id != -1) {
                System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            }
        } catch (Exception e) {
            System.out.println("id는 숫자로 작성해주세요.");
        }
    }

    public static void toJson(WiseSaying wiseSayings) {
        String dirPath = "db/wiseSaying/";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = dirPath + wiseSayings.count+".json";
        String content = "{" +
                "    \"id\": " + wiseSayings.count + "," +
                "    \"content\": \"" + wiseSayings.saying + "\"," +
                "    \"author\": \"" + wiseSayings.author + "\"" +
                "}";
        String idFileName = dirPath+ "lastId.txt";
        int newLastId = wiseSayings.count;

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            System.out.println("파일 저장 실패");
        }

        try {
            FileWriter writer = new FileWriter(idFileName);
            writer.write(String.valueOf(newLastId));
            writer.close();
        } catch (Exception e) {
            System.out.println("파일 저장 실패");
        }

    }

    public static void build() {
        List <String> arr = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            arr.add("db/wiseSaying/"+list.get(i).count+".json");
            //arr에 파일 이름들 모임
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.json"))) {
            bw.write("[\n");

            for (int i = 0; i < arr.size(); i++) {
                String el = arr.get(i);
                try (BufferedReader reader = new BufferedReader(new FileReader(el))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        bw.write(line);
                        bw.newLine();
                    }
                } catch (Exception e) {
                    System.out.println("파일 읽기 실패: " + el);
                }

                if (i < arr.size() - 1) {
                    bw.write(",");
                    bw.newLine();
                }
            }

            bw.write("]");
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch(Exception e) {
            System.out.println("파일 저장 실패");
        }
    }

}
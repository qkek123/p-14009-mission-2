package org.example.p_14009_mission_2;

public class Rq {
    private String command;
    private int id = -1;

    public Rq(String input) {
        String[] arr = input.trim().split("\\?");

        this.command = arr[0];

        if (arr.length > 1 && arr[1].startsWith("id=")) {
            try {
                this.id = Integer.parseInt(arr[1].substring(3));
            } catch (NumberFormatException e) {
                System.out.println("id가 숫자가 아닙니다: " + arr[1]);
            }
        }
    }

    public String getCommand() {
        return command;
    }

    public int getId() {
        return id;
    }
}
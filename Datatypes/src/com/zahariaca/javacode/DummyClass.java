package com.zahariaca.javacode;

public class DummyClass {
    public String isVacationTime(Boolean onVacation) {
        return onVacation ? "I'm on vacaction" : "I'm working";
    }

    public void printNumbers(int[] numbers) {
        for (int number : numbers) {
            System.out.println(number);
        }
    }
}

package com.zahariaca.javacode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JavaGenerics {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
//        list.add(BigDecimal.ONE);
        list.get(0).toUpperCase();

        List list1 = new ArrayList<>();
        list1.add("Goodbye");    }
}

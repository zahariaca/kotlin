package com.zahariaca.javacode;

import java.util.ArrayList;
import java.util.List;

public class JavaGenerics {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("Hello");

        // JVM cannot check at runtime that strings is a List<String>
        // this is due to type erasure at compile time
        // generics do not make it into the compiled application
        //        boolean b = strings instanceof List<String>
    }
}

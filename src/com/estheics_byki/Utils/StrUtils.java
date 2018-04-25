package com.estheics_byki.Utils;

import java.util.stream.Stream;

public class StrUtils {
    public static String titleCase(String str) {
        String[] strs = str.toLowerCase().split("");
        strs[0] = strs[0].toUpperCase();
        return Stream.of(strs).reduce("", (a, b) -> a+b);
    }
}

package com.tigerit.exam;

import java.util.Map;
import java.util.Objects;

/**
* All of the helper function for this solution
* is placed in this class
* */

public class Utils {

    public static Integer[] getIntFromLine(String line) {
        String[] strs = line.trim().split("\\s+");
        Integer[] array = new Integer[strs.length];
        for(int i = 0; i < strs.length; i++) {
            array[i] = Integer.parseInt(strs[i]);
        }
        return array;
    }

    public static String[] getStringArrayFromLine(String line) {
        return line.trim().split("\\s+");
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}

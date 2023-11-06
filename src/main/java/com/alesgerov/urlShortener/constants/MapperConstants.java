package com.alesgerov.urlShortener.constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tofig Alasgarov
 * @created 06.11.23
 */
public final class MapperConstants {
    private static Map<Integer, String> mappings = null;
    private static final List<String> charactersForMapping = List.of(
             "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
            );


    public static Map<Integer, String> getMappings() {
        if (mappings != null) {
            return mappings;
        }

        mappings=new HashMap<>();

        for (int i = 1; i <=61; i++) {
            mappings.put(i,charactersForMapping.get(i-1));
        }


        return mappings;
    }

    public static String getMappingOfValue(int key){
        return getMappings().get(key);
    }
}

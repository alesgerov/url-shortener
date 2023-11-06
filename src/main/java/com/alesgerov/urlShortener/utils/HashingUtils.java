package com.alesgerov.urlShortener.utils;

import com.alesgerov.urlShortener.constants.MapperConstants;

/**
 * @author Alasgarov Tofig
 * @date 11/5/2023
 */
public final class HashingUtils {
    private HashingUtils() {
    }

    //Information about base 62 in Readme.md
    public static String hashByBase62(long uniqueId){
        StringBuilder responseString= new StringBuilder();

        while (uniqueId!=0){
            int reminder= (int) (uniqueId%62);

            String valueForUrl= MapperConstants.getMappingOfValue(reminder);
            responseString.append(valueForUrl);

            uniqueId=uniqueId/62;
        }

        return responseString.toString();
    }

}

package com.alesgerov.demo.utils;

import com.alesgerov.urlShortener.utils.HashingUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Tofig Alasgarov
 * @created 06.11.23
 */
@ExtendWith(SpringExtension.class)
public class HashingUtilsTest {

    @Test
    @DisplayName("hash 2009215674938")
    public void testCase1(){

        var url= HashingUtils.hashByBase62(2009215674938L);

        assertEquals("ucde9nz",url);
    }

    @Test
    @DisplayName("hash 11157")
    public void testCase2(){

        var url= HashingUtils.hashByBase62(11157L);

        assertEquals("XT2",url);
    }
}

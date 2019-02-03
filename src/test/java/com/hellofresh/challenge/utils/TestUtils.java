package com.hellofresh.challenge.utils;

import org.apache.commons.lang3.StringUtils;

import static com.hellofresh.challenge.constants.TestConstant.DOT_SEPARATOR;
import static com.hellofresh.challenge.constants.TestConstant.UNDERSCORE;

public class TestUtils {

    public static String getTestName(String className, String methodName) {
        return StringUtils.join(
                StringUtils.substringAfterLast(className, DOT_SEPARATOR), UNDERSCORE, methodName);
    }
}

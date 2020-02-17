package com.bangla.snacks.customer.util;

import com.bangla.snacks.customer.constants.ApplicationConstants;

import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CommonUtil {
    private CommonUtil() {
    }

    private static final Random RANDOM = new Random();

    public static <E, R> Collection<R> toCollection(Collection<E> items, Function<? super E, R> function) {
        return items.stream().map(function).collect(Collectors.toList());
    }

    public static String getCurrentDateAsString() {
        return ApplicationConstants.DATE_FORMATTER.format(new Date());
    }

    public static long generateId() {
        return (System.currentTimeMillis() + RANDOM.nextInt());
    }

    public static String mask(String stringToMask) {
        char[] chars = stringToMask.toCharArray();
        if (chars.length < 5) {
            return "XXXX" + chars[chars.length - 1];
        }
        return mask(stringToMask, chars.length/2);
    }
    public static String mask(String stringToMask, int charactersToMask) {
        char[] chars = stringToMask.toCharArray();
        for (int i = 0; i < charactersToMask; i++) {
            chars[i] = 'X';
        }
        return new String(chars);
    }
}

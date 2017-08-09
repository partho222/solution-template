package com.tigerit.exam.tariq.solution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to parse query
 * patterns using regex
 */

public class QueryParser {
    private static final Pattern selectType = Pattern.compile("SELECT\\s+(.*)\\s*");
    private static final Pattern fromType = Pattern.compile("FROM\\s+(.*)\\s*");
    private static final Pattern joinType = Pattern.compile("JOIN\\s+(.*)\\s*");
    private static final Pattern onType = Pattern.compile("ON\\s+(.*)\\s*");
    private static Matcher matcher;

    public static String selectLineData(String line) {
        matcher = selectType.matcher(line.trim());
        matcher.matches();
        return matcher.group(1);
    }

    public static String fromLineData(String line) {
        matcher = fromType.matcher(line.trim());
        matcher.matches();
        return matcher.group(1);
    }

    public static String joinLineData(String line) {
        matcher = joinType.matcher(line.trim());
        matcher.matches();
        return matcher.group(1);
    }

    public static String onLineData(String line) {
        matcher = onType.matcher(line.trim());
        matcher.matches();
        return matcher.group(1);
    }
}
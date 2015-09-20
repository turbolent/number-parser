package com.turbolent.numberParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;


public class NumberParser {

    private static Map<String, Double> NUMERALS = new HashMap<String, Double>() {{
        put("zero", 0.0);
        put("a", 1.0);
        put("one", 1.0);
        put("two", 2.0);
        put("three", 3.0);
        put("four", 4.0);
        put("five", 5.0);
        put("six", 6.0);
        put("seven", 7.0);
        put("eight", 8.0);
        put("nine", 9.0);
        put("ten", 10.0);
        put("eleven", 11.0);
        put("twelve", 12.0);
        put("thirteen", 13.0);
        put("fourteen", 14.0);
        put("fifteen", 15.0);
        put("sixteen", 16.0);
        put("seventeen", 17.0);
        put("eighteen", 18.0);
        put("nineteen", 19.0);
        put("twenty", 20.0);
        put("thirty", 30.0);
        put("forty", 40.0);
        // SIC
        put("fourty", 40.0);
        put("fifty", 50.0);
        put("sixty", 60.0);
        put("seventy", 70.0);
        put("eighty", 80.0);
        put("ninety", 90.0);
    }};

    private static Map<String, Double> SHORT_SCALE = new HashMap<>();
    static {
        SHORT_SCALE.put("hundred", 100.0);
        SHORT_SCALE.put("thousand", 1_000.0);

        String[] rest = {
            "million",
            "billion",
            "trillion",
            "quadrillion",
            "quintillion",
            "sextillion",
            "septillion",
            "octillion",
            "nonillion",
            "decillion",
            "undecillion",
            "duodecillion",
            "tredecillion",
            "quattuordecillion",
            "quindecillion",
            "sexdecillion",
            "septemdecillion",
            "octodecillion",
            "novemdecillion",
            "vigintillion"
        };

        for (int i = 0; i < rest.length; i++)
            SHORT_SCALE.put(rest[i], 1_000_000.0 * Math.pow(1_000.0, i));
    }

    private static Pattern SEPARATOR = Pattern.compile("[ ,-]");

    private static Set<String> IGNORED = new HashSet<String>() {{
        add("and");
    }};

    public static double parse(String words) {
        final String[] parts = SEPARATOR.split(words.trim().toLowerCase());

        double sub = 0;
        double total = 0;
        for (int partIndex = 0; partIndex < parts.length; partIndex++) {
            String part = parts[partIndex];

            Double numeral = NUMERALS.get(part);
            if (numeral != null) {
                sub += numeral;
                continue;
            }

            Double scale = SHORT_SCALE.get(part);
            if (scale != null) {
                sub *= scale;

                if (partIndex < parts.length - 1
                    && SHORT_SCALE.get(parts[partIndex + 1]) != null)
                {
                    continue;
                }

                if (scale > 100.0) {
                    total += sub;
                    sub = 0;
                }

                continue;
            }

            if (IGNORED.contains(part))
                continue;

            sub += Double.valueOf(part);
        }

        return total + sub;
    }
}
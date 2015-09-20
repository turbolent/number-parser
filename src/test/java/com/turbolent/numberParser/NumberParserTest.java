package com.turbolent.numberParser;

import junit.framework.TestSuite;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static com.turbolent.numberParser.NumberParser.parse;

public class NumberParserTest {

    private static final double DELTA = 1e-15;

    private void check(String words, double expected) {
        assertEquals(expected, parse(words), DELTA);
    }

    @Test
    public void test() {
        check("twenty seven", 27.0);
        check("thirty-one", 31.0);
        check("thirty-seven", 37.0);
        check("thirty seven", 37.0);
        check("fifty-three", 53.0);
        check("fifty nine", 59.0);
        check("forty two", 42.0);
        check("fourty two", 42.0);
        check("a hundred", 100.0);
        check("one hundred", 100.0);
        check("one hundred and fifty", 150.0);
        check("two-hundred", 200.0);
        check("5 hundred", 500.0);
        check("five hundred eighty three", 583.0);
        check("six hundred", 600.0);
        check("seven hundred twenty six", 726.0);
        check("nine hundred and ninety nine", 999.0);
        check("one thousand", 1_000.0);
        check("twelve hundred", 1_200.0);
        check("one thousand two hundred", 1_200.0);
        check("eight thousand", 8_000.0);
        check("fifteen thousand", 15_000.0);
        check("seventeen thousand", 17_000.0);
        check("thirty five thousand", 35_000.0);
        check("sixty thousand", 60_000.0);
        check("seventy four thousand and two", 74_002.0);
        check("seventy nine thousand", 79_000.0);
        check("ninety nine thousand nine hundred ninety nine", 99_999.0);
        check("100 thousand", 100_000.0);
        check("two hundred one thousand", 201_000.0);
        check("two hundred forty seven thousand nine hundred fourteen", 247_914.0);
        check("two hundred fifty thousand", 250_000.0);
        check("three hundred thousand", 300_000.0);
        check("six hundred seventy one thousand", 671_000.0);
        check("one million", 1_000_000.0);
        check("one million two hundred fifty thousand and seven", 1_250_007.0);
        check("one million six hundred fifty two thousand forty three", 1_652_043.0);
        check("one million six hundred fifty-two thousand forty three", 1_652_043.0);
        check("one million six hundred fifty-two thousand forty-three", 1_652_043.0);
        check("four million eight hundred eighty thousand", 4_880_000.0);
        check("five million nine hundred thousand", 5_900_000.0);
        check("seven million", 7_000_000.0);
        check("one billion", 1_000_000_000.0);
        check("one billion and one", 1_000_000_001.0);
        check("four hundred seventy five billion", 475_000_000_000.0);
        check("four hundred seventy five billion eight million three hundred six thousand forty two", 475_008_306_042.0);
    }
}

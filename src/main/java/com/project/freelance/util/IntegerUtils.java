package com.project.freelance.util;

import java.util.TreeMap;

public class IntegerUtils {
	private static final TreeMap<Integer, String> map = new TreeMap<Integer, String>();
	static {
		map.put(1000, "M");
		map.put(900, "CM");
		map.put(500, "D");
		map.put(400, "CD");
		map.put(100, "C");
		map.put(90, "XC");
		map.put(50, "L");
		map.put(40, "XL");
		map.put(10, "X");
		map.put(9, "IX");
		map.put(5, "V");
		map.put(4, "IV");
		map.put(1, "I");
	}

	public static final String toRoman(int number) {
		int l =  map.floorKey(number);
		if ( number == l ) {
			return map.get(number);
		}
		return map.get(l) + toRoman(number-l);
	}

	public static boolean isNotNullAndNotZero(Integer input) {
		return input != null && input != 0;
	}
	
	public static boolean isNotNullAndPositive(Integer input) {
		return input != null && input > 0;
	}
	
	public static boolean isNullOrZero(Integer input) {
		return input == null || input == 0;
	}
	
	public static boolean isNullOrNegative(Integer input) {
		return input == null || input < 0;
	}
	
	public static boolean isNullOrZeroOrNegative(Integer input) {
		return input == null || input <= 0;
	}

	public static boolean isNotNullAndNotNegative(Integer input) {
		return input != null && input >= 0;
	}

	public static boolean isNotNullAndLowerThanOne(Integer input) {
		return input != null && input < 1;
	}

	public static boolean isEvenNumber(Integer input) { return (input != null) && ((input % 2) == 0); }

	public static boolean isOddNumber(Integer input) { return (input != null) && ((input % 2) == 1); }

}

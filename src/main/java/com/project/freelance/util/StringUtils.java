package com.project.freelance.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.WordUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringUtils {

	private final static ObjectMapper mapper = new ObjectMapper();

	public static String joinStrings(String[] stringsToBeJoined) {
		String result = "";
		boolean firstDest = true;
		for (String str : stringsToBeJoined) {
			if (firstDest) {
				firstDest = false;
			} else {
				result += ",";
			}
			result += str;
		}
		return result;
	}

	public static String joinStringsWithDelimiter(String[] stringsToBeJoined, String delimiter) {
		String result = "";
		boolean firstDest = true;
		for (String str : stringsToBeJoined) {
			if (firstDest) {
				firstDest = false;
			} else {
				result += delimiter;
			}
			result += str;
		}
		return result;
	}

	
	public static String joinStrings(List<String> stringsToBeJoined) {
		String result = "";
		boolean firstDest = true;
		for (String str : stringsToBeJoined) {
			if (firstDest) {
				firstDest = false;
			} else {
				result += ",";
			}
			result += str;
		}
		return result;
	}
	
	public static String joinStringsWithDelimiter(List<String> stringsToBeJoined, String delimiter) {
		String result = "";
		boolean firstDest = true;
		for (String str : stringsToBeJoined) {
			if (firstDest) {
				firstDest = false;
			} else {
				result += delimiter;
			}
			result += str;
		}
		return result;
	}
	
	public static String joinIntegersWithDelimiter(List<Integer> integersToBeJoined, String delimiter) {
		String result = "";
		boolean firstDest = true;
		for (Integer str : integersToBeJoined) {
			if (firstDest) {
				firstDest = false;
			} else {
				result += delimiter;
			}
			result += str;
		}
		return result;
	}

	public static String joinIntegers(Collection<Integer> integersToBeJoined) {
		String result = "";
		boolean firstDest = true;
		for (Integer str : integersToBeJoined) {
			if (firstDest) {
				firstDest = false;
			} else {
				result += ",";
			}
			result += str;
		}
		return result;
	}

	public static String joinLongs(Collection<Long> longsToBeJoined) {
		String result = "";
		boolean firstDest = true;
		for (Long str : longsToBeJoined) {
			if (firstDest) {
				firstDest = false;
			} else {
				result += ",";
			}
			result += str;
		}
		return result;
	}

	public static boolean isNotNullAndNotEmpty(String token) {
		boolean isNOE = true;

		isNOE = (token == null || token.isEmpty() || token.equals("null") || token.equals("[null]")) ? false : true;

		return isNOE;
	}

	public static boolean isNullOrEmpty(String token) {
		return token == null || token.isEmpty() || token.equals("null");
	}

	public static boolean isNotNull(Object object) {
		return (object != null);
	}

	public static String getStringValue(String token) {

		String stringValue = "";
		if (token != null && !token.isEmpty()) {

			stringValue = token;

		}
		return stringValue;
	}

	/**
	 * check if string is null, empty or just empty spaces
	 * @param val
	 * @return
	 */
	public static boolean isBlank(String val){
		return val == null || val.trim().isEmpty();
	}

	/**
	 * check if string is not null, empty or just empty spaces
	 * @param val
	 * @return
	 */
	public static boolean isNotBlank(String val){
		return !isBlank(val);
	}

	/**
	 * Get boxing integer from String.
	 * for un-boxing int use {@link #toInt(String)} instead
	 * @param token
	 * @return boxing Integer or null if it's not a number.
	 */
	public static Integer toBoxInt(String token){
		return toBoxInt(token, null);
	}

	/**
	 * Get boxing integer from String.
	 * for un-boxing int use {@link #toInt(String, int)} instead
	 * @param token
	 * @param defaultValue
	 * @return boxing Integer or defaultValue if it's not a number.
	 */
	public static Integer toBoxInt(String token, Integer defaultValue){
		if(isBlank(token)){
			return defaultValue;
		}
		try{
			return Integer.valueOf(token.trim());
		} catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * Get un-boxing integer from String.
	 * for boxing int use {@link #toBoxInt(String)} instead
	 * @param token
	 * @return primitive int or 0 if it's not a number.
	 */
	public static int toInt(String token){
		return toInt(token, 0);
	}

	/**
	 * Get un-boxing integer from String.
	 * for boxing int use {@link #toBoxInt(String, Integer)} instead
	 * @param token
	 * @param defaultValue
	 * @return primitive int or defaultValue if it's not a number.
	 */
	public static int toInt(String token, int defaultValue){
		if(isBlank(token)){
			return defaultValue;
		}
		try{
			return Integer.parseInt(token);
		} catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * Get un-boxing positive integer from String.
	 * for boxing int use {@link #toBoxInt(String)} instead
	 * @param token
	 * @return primitive int or defaultValue if it's not a number.
	 */
	public static int toPositiveInt(String token, int defaultValue){
		int result = toInt(token);
		return result > 0 ? result : defaultValue;
	}

	/**
	 * Get un-boxing positive integer from String.
	 * for boxing int use {@link #toBoxInt(String)} instead
	 * @param token
	 * @return primitive int or 1 if it's not a number.
	 */
	public static int toPositiveInt(String token){
		return toPositiveInt(token, 1);
	}

	/**
	 * Get un-boxing long from String.
	 * @param token
	 * @return primitive long or 0 if it's not a number.
	 */
	public static long toLong(String token){
		return toLong(token, 0L);
	}

	/**
	 * Get un-boxing long from String.
	 * @param token
	 * @param defaultValue
	 * @return primitive long or defaultValue if it's not a number.
	 */
	public static long toLong(String token, long defaultValue){
		if(isBlank(token)){
			return defaultValue;
		}
		try{
			return Long.parseLong(token);
		} catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * Get boxing long from String.
	 * @param token
	 * @return non-primitive long or null if it's not a number.
	 */
	public static Long toBoxLong(String token, Long defaultValue){
		if(isBlank(token)){
			return defaultValue;
		}
		try{
			return Long.valueOf(token);
		} catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * Get boxing long from String.
	 * @param token
	 * @return non-primitive long or null if it's not a number.
	 */
	public static Long toBoxLong(String token){
		return toBoxLong(token, null);
	}

	/**
	 * Get primitive boolean from String.
	 * @param token
	 * @param defaultValue
	 * @return primitive boolean or defaultValue if it's not a boolean.
	 */
	public static boolean toBol(String token, boolean defaultValue){
		boolean trueString = "true".equalsIgnoreCase(token);
		if(!trueString && !"false".equalsIgnoreCase(token)) return defaultValue;
		return trueString;
	}

	/**
	 * Get primitive boolean from String.
	 * @param token
	 * @return primitive boolean or null if it's not a boolean.
	 */
	public static boolean toBol(String token){
		return toBol(token, false);
	}

	/**
	 * Get non-primitive boolean from String.
	 * @param token
	 * @param defaultValue
	 * @return non-primitive boolean or defaultValue if it's not a boolean.
	 */
	public static Boolean toBoxBol(String token, Boolean defaultValue){
		boolean trueString = "true".equalsIgnoreCase(token);
		if(!trueString && !"false".equalsIgnoreCase(token)) return defaultValue;
		return trueString;
	}

	/**
	 * Get non-primitive boolean from String.
	 * @param token
	 * @return non-primitive boolean or null if it's not a boolean.
	 */
	public static Boolean toBoxBol(String token){
		return toBoxBol(token, null);
	}

	public static String insertString(String original, String insert, int index){
		if(isBlank(original) || isBlank(insert) || index < 0){
			return original;
		}
		StringBuilder newString = new StringBuilder();
		for (int i = 0; i < original.length(); i++) {
			newString.append(original.charAt(i));
			if (i == index) {
				newString.append(insert);
			}
		}
		return newString.toString();
	}

	public static boolean isValidInt(String token) {
		boolean isInt = true;
		try {
			Integer.parseInt(token);
		} catch (Exception e) {
			isInt = false;
		}
		return isInt;
	}

	/**
	 * 0 is excluded
	 * @param numberStr
	 * @return
	 */
	public static boolean isNaturalNumber(String numberStr) {
		try {
			return isNaturalNumber(Integer.valueOf(numberStr));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 0 is excluded
	 * @param number
	 * @return
	 */
	public static boolean isNaturalNumber(Integer number) {
		return number != null && number > 0;
	}

	public static boolean isValidDouble(String token) {
		boolean isDouble = true;
		try {
			Double.parseDouble(token);
		} catch (Exception e) {
			isDouble = false;
		}
		return isDouble;
	}

	public static boolean isValidLong(String token) {
		boolean isLong = true;
		try {
			Long.parseLong(token);
		} catch (Exception e) {
			isLong = false;
		}
		return isLong;
	}
	
	public static boolean isValidTimestamp(String token) {
		boolean isTimestamp = true;
		try {
			Timestamp.valueOf(token);
		} catch (Exception e) {
			isTimestamp = false;
		}
		return isTimestamp;
	}
	
	public static boolean isValidBoolean(String token) {
		boolean isBoolean = true;
		try {
			Boolean.parseBoolean(token);
		} catch (Exception e) {
			isBoolean = false;
		}
		return isBoolean;
	}
	
	public static boolean isValidCharacter(String token) {
		boolean isBoolean = true;
		try {
			isBoolean = isValidCharacter(token.charAt(0));
		} catch (Exception e) {
			isBoolean = false;
		}
		return isBoolean;
	}
	
	public static boolean isValidCharacter(Character token) {
		boolean isBoolean = true;
		try {
			isBoolean = token == 'Y' || token == 'N';
		} catch (Exception e) {
			isBoolean = false;
		}
		return isBoolean;
	}

	public static String getDigitOnly(String token) {
		return token.replaceAll("[^\\d.]", "");
	}

	public static boolean isValidCommaSeparatedInteger(String token) {
		return token.matches("^([0-9]|(\\-[0-9]+)*,*)+$");
	}

	public static boolean isValidCommaSeparatedPositiveInteger(String token) {
		return token.matches("^([0-9]*,*)+$");
	}
	
	public static boolean isValidCommaSeparatedString(String token) {
		return token.matches("^(.*,*)+$");
	}

	public static String cleanNonAlphanumeric(String token) {

		String cleanString = "";

		if ((token == null) || (token.isEmpty())) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			cleanString = token.replaceAll("[^a-zA-Z0-9.\\/\\-\\:@ ]", "");
		}
		return cleanString;
	}

	public static String cleanNonAlphanumericWithoutUnderscore(String token) {

		String cleanString = "";

		if ((token == null) || (token.isEmpty())) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			cleanString = token.replaceAll("[^a-zA-Z0-9.\\/\\-\\:@_ ]", "");
		}
		return cleanString;
	}

	public static String cleanNonAlphanumericWithoutPercent(String token) {

		String cleanString = "";

		if ((token == null) || (token.isEmpty())) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			cleanString = token.replaceAll("[^a-zA-Z0-9.\\/\\-\\_:@% ]", "");
		}
		return cleanString;
	}

	public static String cleanCR(String token) {

		String cleanString = "";

		if ((token == null) || (token.isEmpty())) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			cleanString = token.replaceAll("\n", "");
		}
		return cleanString;
	}

	public static String cleanLF(String token) {

		String cleanString = "";

		if ((token == null) || (token.isEmpty())) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			cleanString = token.replaceAll("\r", "");
		}

		return cleanString;
	}

	public static String cleanNonAlphanumericCRLF(String token) {

		String cleanString = "";

		if ((token == null) || token.isEmpty()) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			cleanString = cleanLF(cleanCR(cleanNonAlphanumeric(token))).trim();
		}

		return cleanString;
	}

	public static String cleanNonAlphanumericCRLFWithoutUnderscore(String token) {

		String cleanString = "";

		if ((token == null) || token.isEmpty()) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			cleanString = cleanLF(cleanCR(cleanNonAlphanumericWithoutUnderscore(token))).trim();
		}

		return cleanString;
	}

	public static String cleanNonAlphanumericCRLFWithoutPercent(String token) {

		String cleanString = "";

		if ((token == null) || token.isEmpty()) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			cleanString = cleanLF(cleanCR(cleanNonAlphanumericWithoutPercent(token))).trim();
		}

		return cleanString;
	}

	public static String cleanCRLF(String token) {
		String cleanString = "";
		if ((token == null) || (token.isEmpty())) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			cleanString = cleanLF(cleanCR(token)).trim();
		}

		return cleanString;
	}

	public static String[] cleanNonAlphanumericCRLF(String[] token) {
		int length = token == null ? 0 : token.length;

		String[] cleanStrings = new String[length];

		if ((token == null) || (token.length == 0)) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			for (int i = 0; i < token.length; i++) {
				cleanStrings[i] = cleanNonAlphanumericCRLF(token[i]);
			}
		}
		return cleanStrings;
	}

	public static String[] cleanNonAlphanumericCRLFWithoutPercent(String[] token) {
		int length = token == null ? 0 : token.length;

		String[] cleanStrings = new String[length];

		if ((token == null) || (token.length == 0)) {
			log.warn("Null or empty string input detected: " + token);
		} else {
			for (int i = 0; i < token.length; i++) {
				cleanStrings[i] = cleanNonAlphanumericCRLFWithoutPercent(token[i]);
			}
		}
		return cleanStrings;
	}

	public static boolean isAlphaNumericWithDot(String str) {
		return str.matches("^[a-zA-Z0-9.]*$");
	}

	/**
	 * Get string between two characters
	 * @param str
	 * @param firstCharacter
	 * @param lastCharacter
	 * @return 
	 */
	public static String extractStringBetweenTwoCharacters(String str, char firstCharacter, char lastCharacter) {

		if (str != null) {
			try {
				int positionFirstChar = str.indexOf(firstCharacter);
				int positionSecondChar  = str.indexOf(lastCharacter);
				
				if((positionFirstChar > 0) && (positionSecondChar > 0) && (positionFirstChar < positionSecondChar)){
					str = str.substring(str.indexOf(firstCharacter) + 1);
					str = str.substring(0, str.indexOf(lastCharacter));
					
					return str;
				}
				
			} catch (Exception e) {
				log.warn("String between " + firstCharacter + " and " + lastCharacter + " cannot be extracted from "+str);

			}
		}
		return null;

	}

	public static boolean isNumber (String value) {
		String regex = "^[0-9]*$";
		return value.matches(regex);
	}
	
	public static boolean isFloatingPointNumber(String value) {
		String regexPoint = "^[-+]?[0-9]*(\\.|\\,)[0-9]+$";
		return value.matches(regexPoint);
	}

	public static boolean isPhoneNumber (String value) {
		String regex = "\\+?([ -]?\\d+)+|\\(\\d+\\)([ -]\\d+)";
		return value.matches(regex);
	}

	public static boolean validatePhoneNumber (String phoneNumber) {
		Pattern pattern = Pattern.compile("\\(?(?:\\+62|62|[0-9])(?:\\d{2,4})?\\)?[ .-]?\\d{1,4}[ .-]?\\d{1,4}[ .-]?\\d{1,4}");
		return pattern.matcher(phoneNumber).matches();
	}
	
	public static List<Integer> stringToIntegerList(String str, String delimiter) {
		List<Integer> result = new ArrayList<>();
		if (str == null || str.isEmpty()) {
			return result;
		}
		String[] splitted = str.split("\\s*" + delimiter + "\\s*");
		for (String s : splitted) {
			result.add(Integer.parseInt(s));
		}
		return result;
	}

	public static List<Long> stringToLongList(String str, String delimiter) {
		List<Long> result = new ArrayList<>();
		if (str == null || str.isEmpty()) {
			return result;
		}
		String[] splitted = str.split("\\s*" + delimiter + "\\s*");
		for (String s : splitted) {
			result.add(Long.parseLong(s));
		}
		return result;
	}
	
	public static List<String> stringToList(String str, String delimiter) {
		List<String> result = new ArrayList<>();
		if (str == null || str.isEmpty()) {
			return result;
		}
		String[] splitted = str.split("\\s*" + delimiter + "\\s*");
		result = new ArrayList<>(Arrays.asList(splitted));
		return result;
	}	
	
	public static List<String> stringToLinkedList(String str, String delimiter) {
		List<String> result = new LinkedList<>();
		if (str == null || str.isEmpty()) {
			return result;
		}
		String[] splitted = str.split("\\s*" + delimiter + "\\s*");
		return new LinkedList<>(Arrays.asList(splitted));
	}

	public static String mapToString(Map<?, ?> map) {
		return Arrays.toString(map.entrySet().toArray());
	}
	
	public static boolean isSingleWord(String word) {
		return (word != null && word.length() > 0 && word.split("\\s+").length == 1);
	}
	
	public static String toTitleCase(String input) {
		
		if(StringUtils.isNullOrEmpty(input)) {
			return input;
		}
		
		input = input.toLowerCase();
	    StringBuilder titleCase = new StringBuilder(input.length());
	    boolean nextTitleCase = true;

	    for (char c : input.toCharArray()) {
	        if (Character.isSpaceChar(c)) {
	            nextTitleCase = true;
	        } else if (nextTitleCase) {
	            c = Character.toUpperCase(c);
	            nextTitleCase = false;
	        }

	        titleCase.append(c);
	    }

	    return titleCase.toString();
	}
	
	public static String getWholeNumberFromFloatString(String floatString) {
		String returnValue = floatString;
		try {
			// will ONLY catch float, e.g. accepts 1.1 but not 1.2.3
			if (isFloatingPointNumber(floatString)) { 
				String[] temp = floatString.split("(\\.|\\,)");
				returnValue = temp[0];
			} else {
				throw new Exception("not a floating point");
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return returnValue;
	}
	
	public static String convertIntToStringWithMaxOneLeadingZero(int number)
	{
		int base10Value = (int) Math.log10(number);

		String formatter =
			(base10Value < 2) ? "%02d" : ( "%0" + String.valueOf(base10Value+1) + "d" );

		String result = String.format(formatter, number);

		return result;
	}


	/**
	 * Convert String formated "1,2,3"
	 * into [1,2,3]
	 * @param loanNumbersStr
	 * @return List<Integer>
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static List<Integer> getListLoanNumbers(String loanNumbersStr){
		List<Integer> listLoanNumber = new ArrayList<Integer>();
		if(listLoanNumber.contains(",")){
			listLoanNumber.add(Integer.parseInt(loanNumbersStr));
			return listLoanNumber;
		}

		String[] arrLoanStr = loanNumbersStr.split(",");
		List<Integer> arrLoanInt = new ArrayList<Integer>();
		for(String loanId : arrLoanStr){
			arrLoanInt.add( Integer.parseInt(loanId) );
		}
		return arrLoanInt;
	}

	public static String snakeCaseToTitleCase(String key) {
		String result = "";
		if (StringUtils.isNotNullAndNotEmpty(key)) {
			String[] separated = key.split("_");
			for(int index = 0; index < separated.length; index++) {
				result += separated[index] + " ";
			}
			result = WordUtils.uncapitalize(result, null);
			result = WordUtils.capitalizeFully(result, null);
			
			return result;
		}
		
		return result;
	}

	/**
	 * Get boxing integer from String.
	 * for un-boxing int use {@link #toInt(String)} instead
	 * @param token
	 * @return boxing Integer or null if it's not a number.
	 */
	public static Double toBoxDouble(String token){
		return toBoxDouble(token, null);
	}


	/**
	 * Get boxing integer from String.
	 * for un-boxing double use {@link #toDouble(String, double)} instead
	 * @param token
	 * @param defaultValue
	 * @return boxing Integer or defaultValue if it's not a number.
	 */
	public static Double toBoxDouble(String token, Double defaultValue){
		if(isBlank(token)){
			return defaultValue;
		}
		try{
			return Double.valueOf(token);
		} catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * Get un-boxing integer from String.
	 * for boxing int use {@link #toBoxDouble(String)} instead
	 * @param token
	 * @return primitive int or 0 if it's not a number.
	 */
	public static double toDouble(String token){
		return toDouble(token, 0);
	}

	/**
	 * Get un-boxing integer from String.
	 * for boxing int use {@link #toBoxDouble(String)} instead
	 * @param token
	 * @param defaultValue
	 * @return primitive int or defaultValue if it's not a number.
	 */
	public static double toDouble(String token, double defaultValue){
		if(isBlank(token)){
			return defaultValue;
		}
		try{
			return Double.parseDouble(token);
		} catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * Get Big Decimal from String.
	 *
	 * @param token
	 * @return primitive int or 0 if it's not a number.
	 */
	public static BigDecimal toBigDecimal(String token){
		return toBigDecimal(token, BigDecimal.ZERO);
	}

	/**
	 * Get un-boxing integer from String.
	 * for boxing int use {@link #toBoxDouble(String)} instead
	 * @param token
	 * @param defaultValue
	 * @return primitive int or defaultValue if it's not a number.
	 */
	public static BigDecimal toBigDecimal(String token, BigDecimal defaultValue){
		if(isBlank(token)){
			return defaultValue;
		}
		try{
			return new BigDecimal(token);
		} catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * Get un-boxing integer from String.
	 * for boxing int use {@link #toBoxDouble(String)} instead
	 * @param token
	 * @param defaultValue
	 * @return primitive int or defaultValue if it's not a number.
	 */
	public static Character toBoxChar(String token){
		if(isBlank(token)){
			return null;
		}
		try{
			return token.charAt(0);
		} catch(Exception e){
			return null;
		}
	}

	public static List<Integer> toIntList(String token) {
		if(StringUtils.isNullOrEmpty(token)) return new ArrayList<>();
		
		List<Integer> result = new ArrayList<>();
		for(String string : token.split("\\s*,\\s*"))
			result.add(toInt(string.trim()));
		
		return result;
	}

	public static Set<Integer> toIntSet(String token) {
		if(isNullOrEmpty(token)) return Collections.emptySet();

		Set<Integer> result = new HashSet<>();
		for(String string : token.split(",")){
			result.add(toInt(string.trim()));
		}

		return result;
	}

	public static String joinStringsWithOxfordComma(List<String> strings){
	    StringBuilder builder = new StringBuilder();

	    for (int i = 0; i < strings.size(); i++) { 
	        if (i > 0) {
	            builder.append(" ");

	            if (i == strings.size() - 1) {
	                builder.append("and ");
	            }
	            else {
	                builder.append(", ");
	            }
	        }

	        builder.append(strings.get(i));
	    }

	    return builder.toString();
	}

	public static Character toIsActive(String token) {
		if(isNullOrEmpty(token)) return null;
		return token.charAt(0);
	}

	public static boolean containsUppercase(String token) {
		Pattern pattern = Pattern.compile("(?=.*[A-Z]).*");
		return pattern.matcher(token).matches();
	}

	public static boolean containsLowercase(String token) {
		Pattern pattern = Pattern.compile("(?=.*[a-z]).*");
		return pattern.matcher(token).matches();
	}

	public static boolean containsNumeric(String token) {
		Pattern pattern = Pattern.compile("(?=.*[0-9]).*");
		return pattern.matcher(token).matches();
	}

	public static boolean isValidEmail(String email){
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher mat = pattern.matcher(email);
		return mat.matches();
	}
}

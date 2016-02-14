/**
 * 
 */
package com.sahaj.assignment.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author asgs
 *
 */
public class RegexUtils {

	/**
	 * Pattern arrived at based on the input strings provided by the External
	 * Sensors.
	 */
	private static final Pattern SENSOR_INPUT_COUNTS = Pattern
			.compile(".*\\sFloor\\s([\\d]+),.*\\sSub\\scorridor\\s([\\d]+).*");

	/**
	 * Checks if a given inputText matches the Pattern compiled above.
	 * 
	 * @param inputText
	 * @return Match found or not.
	 */
	public static boolean matches(String inputText) {
		Matcher matcher = SENSOR_INPUT_COUNTS.matcher(inputText);
		return matcher.matches();
	}

	/**
	 * Checks if the given inputText matches the @code Pattern compiled above.
	 * If so, finds the string corresponding to the given groupNumber.
	 * 
	 * @param groupNumber
	 * @param inputText
	 * @return Return the match or null if no match is found.
	 */
	public static String getMatchAtGroupIfExists(int groupNumber,
			String inputText) {
		Matcher matcher = SENSOR_INPUT_COUNTS.matcher(inputText);
		if (matcher.matches() && matcher.groupCount() >= groupNumber) {
			return matcher.group(groupNumber);
		} else {
			return null;
		}
	}
}

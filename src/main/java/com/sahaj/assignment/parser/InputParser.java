/**
 * 
 */
package com.sahaj.assignment.parser;

import java.util.Objects;
import java.util.Scanner;

import com.sahaj.assignment.entity.Hotel;
import com.sahaj.assignment.util.RegexUtils;

/**
 * Parses the data input from the System console to collect the necessary
 * information about the {@link Hotel}'s initial state and subsequent states as
 * reported by the external sensors attached to the Hotel.
 * 
 * @author asgs
 *
 */
public class InputParser {

	private static final String INPUT_STRING_FLOOR_COUNT = "Number of floors: ";
	private static final String INPUT_STRING_MAIN_CORRIDOR_COUNT = "Main Corridors per floor: ";
	private static final String INPUT_STRING_SUB_CORRIDOR_COUNT = "Sub Corridors per floor: ";
	private static final String INPUT_STRING_NO_MOVEMENT_PREFIX = "No movement";
	private static final String INPUT_STRING_EXIT = "exit";
	private static final String INPUT_STRING_INVALID_INTEGER = "Please enter an integer.";
	private static final String INPUT_STRING_MALFORMED_FORMAT = "Input malformed. Please try again.";
	private static final Scanner SCANNER = new Scanner(System.in);

	/**
	 * Validates and parses the inputs passed on by the External Sensors and
	 * constructs an {@link InitialHotelState} for the other classes to work on.
	 * 
	 * @return An instance of {@link InitialHotelState}
	 */
	public static InitialHotelState parseInitialInputs() {
		System.out.println(INPUT_STRING_FLOOR_COUNT);
		throwExceptionForInvalidValue();
		int floorCount = Integer.parseInt(SCANNER.nextLine());
		System.out.println(INPUT_STRING_MAIN_CORRIDOR_COUNT);
		throwExceptionForInvalidValue();
		int mainCorridorCount = Integer.parseInt(SCANNER.nextLine());
		System.out.println(INPUT_STRING_SUB_CORRIDOR_COUNT);
		throwExceptionForInvalidValue();
		int subCorridorCount = Integer.parseInt(SCANNER.nextLine());
		InitialHotelState hotelState = new InitialHotelState(floorCount,
				mainCorridorCount, subCorridorCount);
		return hotelState;
	}

	/**
	 * @param powerController
	 * @return
	 */
	public static IntermediateHotelState parseSensorInputs() {
		String sensorInputLine = SCANNER.nextLine();
		if (sensorInputLine.isEmpty()) {
			throw new IllegalArgumentException(INPUT_STRING_MALFORMED_FORMAT);
		} else if (sensorInputLine.equalsIgnoreCase(INPUT_STRING_EXIT)) {
			closeInputStream();
			System.out.println("Terminating...");
			System.exit(0);
		}
		String floorNumberString = RegexUtils.getMatchAtGroupIfExists(1,
				sensorInputLine);
		String subCorridorNumberString = RegexUtils.getMatchAtGroupIfExists(2,
				sensorInputLine);
		if (Objects.isNull(floorNumberString)
				|| Objects.isNull(subCorridorNumberString)) {
			// Nothing can be done. Sensor inputs are possibly malformed.
			throw new IllegalArgumentException(INPUT_STRING_MALFORMED_FORMAT);
		}
		boolean turnOnLightBulbSwitch;
		if (sensorInputLine.startsWith(INPUT_STRING_NO_MOVEMENT_PREFIX)) {
			// Motion apparently ended after a minute.
			turnOnLightBulbSwitch = false;
		} else {
			// Motion Detected.
			turnOnLightBulbSwitch = true;

		}

		return new IntermediateHotelState(Integer.parseInt(floorNumberString),
				Integer.parseInt(subCorridorNumberString),
				turnOnLightBulbSwitch);
	}

	private static void closeInputStream() {
		SCANNER.close();
	}

	private static void throwExceptionForInvalidValue() {
		while (!SCANNER.hasNextInt()) {
			System.err.println(INPUT_STRING_INVALID_INTEGER);
			SCANNER.nextLine();
		}
	}

}

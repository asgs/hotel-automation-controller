/**
 * 
 */
package com.sahaj.assignment.criteria;

import java.util.List;

import com.sahaj.assignment.components.Floor;
import com.sahaj.assignment.components.MainCorridor;
import com.sahaj.assignment.components.SubCorridor;

/**
 * Implementation of a {@link Criteria} to know if the Power consumption within
 * a {@link Floor} is well within the limits.
 * 
 * @author asgs
 *
 */
public class PowerConsumptionCriteria implements Criteria {

	public static final int LIGHTBULB_POWER_RATING = 5;

	public static final int AIRCONDITIONER_POWER_RATING = 10;

	@Override
	public boolean criteriaMetFor(Floor floor) {
		return powerConsumptionForFloor(floor) <= getMaxPowerAllowedForFloor(floor);
	}

	/**
	 * Calculates the Maximum Power a Floor could consume at any given point of
	 * time. This implementation may change depending upon the Hotel
	 * requirements.
	 * 
	 * @param floor
	 *            The Floor to check for.
	 * @return Combined value of the Power consumed.
	 */
	private int getMaxPowerAllowedForFloor(Floor floor) {
		List<MainCorridor> mainCorridors = floor.getMainCorridors();
		List<SubCorridor> subCorridors = floor.getSubCorridors();
		return (mainCorridors.size() * 15) + subCorridors.size() * 10;
	}

	/**
	 * Calculates the Electrical power consumed by the equipments found in the
	 * given Floor at this current instant. Which means the equipments which are
	 * not switched on (or running) at this moment are excluded from this
	 * calculation.
	 * 
	 * @param floor
	 *            The Floor to check for.
	 * @return The Total power consumed at this moment.
	 */
	private int powerConsumptionForFloor(Floor floor) {
		List<MainCorridor> mainCorridors = floor.getMainCorridors();
		List<SubCorridor> subCorridors = floor.getSubCorridors();

		int sumOfPowersOfLightBulbsinMainCorridors = mainCorridors.stream()
				.flatMap(mainCorridor -> mainCorridor.getLightBulbs().stream())
				.filter(lightBulb -> lightBulb.isSwitchedOn())
				.mapToInt(lightBulb -> lightBulb.getPowerRating()).sum();

		int sumOfPowersOfAirConditionersinMainCorridors = mainCorridors
				.stream()
				.flatMap(
						mainCorridor -> mainCorridor.getAirConditioners()
								.stream())
				.filter(lightBulb -> lightBulb.isSwitchedOn())
				.mapToInt(airConditioner -> airConditioner.getPowerRating())
				.sum();

		int sumOfPowersOfLightBulbsinSubCorridors = subCorridors.stream()
				.flatMap(subCorridor -> subCorridor.getLightBulbs().stream())
				.filter(lightBulb -> lightBulb.isSwitchedOn())
				.mapToInt(lightBulb -> lightBulb.getPowerRating()).sum();

		int sumOfPowersOfAirConditionersinSubCorridors = subCorridors
				.stream()
				.flatMap(
						subCorridor -> subCorridor.getAirConditioners()
								.stream())
				.filter(airConditioner -> airConditioner.isSwitchedOn())
				.mapToInt(airConditioner -> airConditioner.getPowerRating())
				.sum();

		return sumOfPowersOfLightBulbsinMainCorridors
				+ sumOfPowersOfLightBulbsinSubCorridors
				+ sumOfPowersOfAirConditionersinMainCorridors
				+ sumOfPowersOfAirConditionersinSubCorridors;
	}

}

/**
 * 
 */
package com.sahaj.assignment.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sahaj.assignment.criteria.PowerConsumptionCriteria;

/**
 * Represents a passageway for a Floor.
 * 
 * @author asgs
 *
 */
public abstract class Corridor {

	/**
	 * Added for intending the text in the Output.
	 */
	static final String DOUBLE_SPACES = "  ";

	/**
	 * @return the lightBulbs
	 */
	public List<LightBulb> getLightBulbs() {
		return lightBulbs;
	}

	/**
	 * @return the airConditioners
	 */
	public List<AirConditioner> getAirConditioners() {
		return airConditioners;
	}

	private List<LightBulb> lightBulbs;

	private List<AirConditioner> airConditioners;

	/**
	 * Create a new Corridor with the default equipments.
	 */
	public Corridor(CorridorType type) {
		lightBulbs = new ArrayList<>();
		lightBulbs.add(new LightBulb(
				PowerConsumptionCriteria.LIGHTBULB_POWER_RATING, lightBulbs
						.size(), type == CorridorType.MAIN));
		airConditioners = new ArrayList<>();
		airConditioners.add(new AirConditioner(
				PowerConsumptionCriteria.AIRCONDITIONER_POWER_RATING,
				airConditioners.size(), true));
		setType(type);
	}

	public void addLightBulbs(LightBulb... lightBulbs) {
		this.lightBulbs.addAll(Arrays.asList(lightBulbs));
	}

	public void addAirConditioners(AirConditioner... airConditioners) {
		this.airConditioners.addAll(Arrays.asList(airConditioners));
	}

	/**
	 * @return the type
	 */
	public abstract CorridorType getType();

	/**
	 * @param type
	 *            the type to set
	 */
	public abstract void setType(CorridorType type);

	static enum CorridorType {
		MAIN, SUB
	}

}

/**
 * 
 */
package com.sahaj.assignment.components;

/**
 * @author asgs
 *
 */
public class AirConditioner {

	private int powerRating;

	private String powerRatingUnit;

	private boolean switchedOn;

	private int acNumber;

	/**
	 * @return the lightNumber
	 */
	public int getLightNumber() {
		return acNumber;
	}

	public AirConditioner(int powerRating, int lightNumber, boolean switchedOn) {
		this.powerRating = powerRating;
		this.acNumber = lightNumber;
		this.switchedOn = switchedOn;
	}

	/**
	 * @return the switchedOn
	 */
	public boolean isSwitchedOn() {
		return switchedOn;
	}

	/**
	 * @param switchedOn
	 *            the switchedOn to set
	 */
	public void setSwitchedOn(boolean switchedOn) {
		this.switchedOn = switchedOn;
	}

	/**
	 * @return the powerRating
	 */
	public int getPowerRating() {
		return powerRating;
	}

	/**
	 * @param powerRating
	 *            the powerRating to set
	 */
	public void setPowerRating(int powerRating) {
		this.powerRating = powerRating;
	}

	/**
	 * @return the powerRatingUnit
	 */
	public String getPowerRatingUnit() {
		return powerRatingUnit;
	}

	/**
	 * @param powerRatingUnit
	 *            the powerRatingUnit to set
	 */
	public void setPowerRatingUnit(String powerRatingUnit) {
		this.powerRatingUnit = powerRatingUnit;
	}

	@Override
	public String toString() {
		return Corridor.DOUBLE_SPACES + Corridor.DOUBLE_SPACES + "AC : "
				+ (switchedOn ? "ON" : "OFF");
	}

}

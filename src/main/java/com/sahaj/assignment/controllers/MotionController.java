/**
 * 
 */
package com.sahaj.assignment.controllers;

import java.util.Observable;

import com.sahaj.assignment.components.Motion;

/**
 * Manages the {@link Motion} events and registers the {@link PowerController}
 * as an Observer to the {@link Observable} <code>Motion.
 * 
 * @author asgs
 *
 */
public class MotionController {

	private Motion motion;

	private PowerController powerController;

	/**
	 * Default Constructor.
	 */
	public MotionController(int floorNumber, int subCorridorNumber,
			PowerController powerController) {
		motion = new Motion(floorNumber, subCorridorNumber);
		this.powerController = powerController;
	}

	/**
	 * Raises an event that a Motion has been detected to all the Observers.
	 * 
	 * @param turnOnSwitch
	 *            Turn on or not.
	 */
	public void raiseMotionDetectedEvent(boolean turnOnSwitch) {
		motion.addObserver(powerController);
		motion.notifyObservers(turnOnSwitch);
	}

	/**
	 * @return
	 */
	public Motion getMotion() {
		return motion;
	}
}

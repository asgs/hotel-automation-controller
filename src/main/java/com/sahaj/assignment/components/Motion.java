/**
 * 
 */
package com.sahaj.assignment.components;

import java.util.Observable;
import java.util.Observer;

/**
 * It's an object representing a Human's motion in some floor/corridor. It's an
 * Observable, so such an event will be notified to all observers subscribed to
 * this event.
 * 
 * @author asgs
 *
 */
public class Motion extends Observable {

	private int floorNumber;

	private int subCorridorNumber;

	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
	}

	@Override
	public void notifyObservers() {
		this.notifyObservers(null);
	}

	@Override
	public void notifyObservers(Object object) {
		setChanged();
		super.notifyObservers(object);
	}

	/**
	 * Default constructor.
	 */
	public Motion(int floorNumber, int subCorridorNumber) {
		this.floorNumber = floorNumber;
		this.subCorridorNumber = subCorridorNumber;
	}

	/**
	 * @return the floorNumber
	 */
	public int getFloorNumber() {
		return floorNumber;
	}

	/**
	 * @return the subCorridorNumber
	 */
	public int getSubCorridorNumber() {
		return subCorridorNumber;
	}

}

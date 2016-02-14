/**
 * 
 */
package com.sahaj.assignment.entity;

import java.util.List;

import com.sahaj.assignment.components.Floor;

/**
 * 
 * Hotel is an entity in this application. Represents a real-life Hotel which
 * will have components associated with it and probably a few actions as well.
 * 
 * @author asgs
 *
 */
public class Hotel {

	private List<Floor> floors;

	private String name;

	public Hotel(String name) {
		this.name = name;
	}

	/**
	 * @return the floors
	 */
	public List<Floor> getFloors() {
		return floors;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param floors
	 *            the floors to set
	 */
	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Traverses through the Tree-like <code>Hotel</code> object and prints out
	 * the state of each individual node.
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		getFloors().forEach(floor -> {
			stringBuilder.append(floor.toString());
			stringBuilder.append(System.lineSeparator());
			floor.getMainCorridors().forEach(mainCorridor -> {
				stringBuilder.append(mainCorridor.toString());
				stringBuilder.append(System.lineSeparator());
				mainCorridor.getLightBulbs().forEach(lightBulb -> {
					stringBuilder.append(lightBulb.toString());
					stringBuilder.append(System.lineSeparator());
				});
				mainCorridor.getAirConditioners().forEach(airConditioner -> {
					stringBuilder.append(airConditioner.toString());
					stringBuilder.append(System.lineSeparator());
				});
			});
			floor.getSubCorridors().forEach(subCorridor -> {
				stringBuilder.append(subCorridor.toString());
				stringBuilder.append(System.lineSeparator());
				subCorridor.getLightBulbs().forEach(lightBulb -> {
					stringBuilder.append(lightBulb.toString());
					stringBuilder.append(System.lineSeparator());
				});
				subCorridor.getAirConditioners().forEach(airConditioner -> {
					stringBuilder.append((airConditioner).toString());
					stringBuilder.append(System.lineSeparator());
				});
			});
		});
		return stringBuilder.toString();
	}

}

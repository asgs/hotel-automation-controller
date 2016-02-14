/**
 * 
 */
package com.sahaj.assignment.criteria;

import com.sahaj.assignment.components.Floor;

/**
 * Represents the rules to satisfy a condition.
 * 
 * @author asgs
 *
 */
public interface Criteria {

	/**
	 * Generic method to determine if all the criteria devised for a
	 * {@link Floor} is met or not.
	 * 
	 * @param floor
	 *            The Floor instance.
	 * @return True or false.
	 */
	boolean criteriaMetFor(Floor floor);

}

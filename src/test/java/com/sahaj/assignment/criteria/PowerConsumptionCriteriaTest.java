/**
 * 
 */
package com.sahaj.assignment.criteria;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sahaj.assignment.builder.HotelBuilder;
import com.sahaj.assignment.components.Floor;
import com.sahaj.assignment.entity.Hotel;

/**
 * @author asgs
 *
 */
public class PowerConsumptionCriteriaTest {
	
	private PowerConsumptionCriteria powerConsumptionCriteria;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		powerConsumptionCriteria = new PowerConsumptionCriteria();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.sahaj.assignment.criteria.PowerConsumptionCriteria#criteriaMetFor(com.sahaj.assignment.components.Floor)}.
	 */
	@Test
	public void testCriteriaMetFor() {
		Hotel hotel = new HotelBuilder().addFloors(1).addMainCorridors(1).addSubCorridors(1).build();
		Floor floor = hotel.getFloors().get(0);
		assertTrue("Criteria is not met!", powerConsumptionCriteria.criteriaMetFor(floor));
		floor.getSubCorridors().get(0).getAirConditioners().get(0).setSwitchedOn(true);
		floor.getSubCorridors().get(0).getLightBulbs().get(0).setSwitchedOn(true);
		powerConsumptionCriteria.criteriaMetFor(floor);
		assertFalse("Criteria is somehow met!", powerConsumptionCriteria.criteriaMetFor(floor));
	}

}

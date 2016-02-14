/**
 * 
 */
package com.sahaj.assignment.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sahaj.assignment.builder.HotelBuilder;
import com.sahaj.assignment.components.Motion;
import com.sahaj.assignment.entity.Hotel;

/**
 * @author asgs
 *
 */
public class PowerControllerTest {

	private PowerController powerController;

	private Hotel hotel;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		hotel = new HotelBuilder().addFloors(2)
				.addMainCorridors(2).addSubCorridors(2).build();
		powerController = new PowerController(hotel);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.controllers.PowerController#PowerController(com.sahaj.assignment.entity.Hotel)}
	 * .
	 */
	@Test
	public void testPowerController() {
		assertEquals(hotel, powerController.getHotel());
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.controllers.PowerController#update(java.util.Observable, java.lang.Object)}
	 * .
	 */
	@Test
	public void testUpdate() {
		powerController.update(new Motion(1, 2), true);
		assertTrue("Not switched on!", hotel.getFloors().get(0)
				.getSubCorridors().get(1).getLightBulbs().get(0).isSwitchedOn());
		powerController.update(new Motion(1, 2), true);
		assertTrue("Not switched on!", hotel.getFloors().get(0)
				.getSubCorridors().get(1).getLightBulbs().get(0).isSwitchedOn());
		powerController.update(new Motion(1, 2), false);
		assertFalse("Still switched on!", hotel.getFloors().get(0)
				.getSubCorridors().get(1).getLightBulbs().get(0).isSwitchedOn());
		powerController.update(new Motion(1, 2), false);
		assertFalse("Still switched on!", hotel.getFloors().get(0)
				.getSubCorridors().get(1).getLightBulbs().get(0).isSwitchedOn());

	}

}

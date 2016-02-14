/**
 * 
 */
package com.sahaj.assignment.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sahaj.assignment.builder.HotelBuilder;
import com.sahaj.assignment.entity.Hotel;

/**
 * @author asgs
 *
 */
public class MotionControllerTest {

	private MotionController motionController;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Nothing to do. Individual tests will ensure the creation.
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		motionController = null;
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.controllers.MotionController#MotionController(int, int, com.sahaj.assignment.controllers.PowerController)}
	 * .
	 */
	@Test
	public void testMotionController() {
		motionController = new MotionController(1, 1, new PowerController(
				new HotelBuilder().build()));
		assertNotNull("Motion is not generated!", motionController.getMotion());
		assertTrue("Floor number is incorrect!", motionController.getMotion()
				.getFloorNumber() == 1);
		assertTrue("SubCorridor number is incorrect!", motionController
				.getMotion().getSubCorridorNumber() == 1);
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.controllers.MotionController#raiseMotionDetectedEvent(boolean)}
	 * .
	 */
	@Test
	public void testRaiseMotionDetectedEvent() {
		Hotel hotel = new HotelBuilder().addFloors(2)
				.addMainCorridors(2).addSubCorridors(2).build();
		motionController = new MotionController(1, 1,
				new PowerController(hotel));
		motionController.raiseMotionDetectedEvent(true);
		assertTrue("Not switched on!", hotel.getFloors().get(0)
				.getSubCorridors().get(0).getLightBulbs().get(0).isSwitchedOn());
		assertFalse("Accidentally switched on!", hotel.getFloors().get(1)
				.getSubCorridors().get(0).getLightBulbs().get(0).isSwitchedOn());
		motionController.raiseMotionDetectedEvent(false);
		assertFalse("Still switched on!", hotel.getFloors().get(0)
				.getSubCorridors().get(0).getLightBulbs().get(0).isSwitchedOn());
	}

}

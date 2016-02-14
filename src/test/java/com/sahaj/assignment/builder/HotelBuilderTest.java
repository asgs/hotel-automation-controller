/**
 * 
 */
package com.sahaj.assignment.builder;

import com.sahaj.assignment.entity.Hotel;

import junit.framework.TestCase;

/**
 * @author asgs
 *
 */
public class HotelBuilderTest extends TestCase {

	private HotelBuilder hotelBuilder;

	protected void setUp() throws Exception {
		super.setUp();
		hotelBuilder = new HotelBuilder();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.builder.HotelBuilder#HotelBuilder()}.
	 */
	public void testConstructor() {
		Hotel hotel = hotelBuilder.build();
		assertNotNull(hotel.getName(), "Hotel name is not inited!");
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.builder.HotelBuilder#addFloors(int)}.
	 */
	public void testAddFloors() {
		Hotel hotel = hotelBuilder.addFloors(2).build();
		assertTrue("Invalid floor count", hotel.getFloors().size() == 2);
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.builder.HotelBuilder#addMainCorridors(int)}.
	 */
	public void testAddMainCorridors() {
		Hotel hotel = hotelBuilder.addFloors(2).addMainCorridors(2).build();
		assertTrue("Invalid floor count", hotel.getFloors().get(0)
				.getMainCorridors().size() == 2);
		assertTrue("Invalid floor count", hotel.getFloors().get(1)
				.getMainCorridors().size() == 2);
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.builder.HotelBuilder#addSubCorridors(int)}.
	 */
	public void testAddSubCorridors() {
		Hotel hotel = hotelBuilder.addFloors(2).addSubCorridors(2).build();
		assertTrue("Invalid floor count", hotel.getFloors().get(0)
				.getSubCorridors().size() == 2);
		assertTrue("Invalid floor count", hotel.getFloors().get(1)
				.getSubCorridors().size() == 2);
	}

	/**
	 * Test method for {@link com.sahaj.assignment.builder.HotelBuilder#build()}
	 * .
	 */
	public void testBuild() {
		Hotel hotel = hotelBuilder.build();
		assertNotNull("Hotel is null!", hotel);
	}

}

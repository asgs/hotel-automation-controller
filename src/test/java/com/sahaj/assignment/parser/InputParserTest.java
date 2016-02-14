/**
 * 
 */
package com.sahaj.assignment.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author asgs
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InputParserTest {
	static CustomByteArrayInputStream customStream;
	static StringBuilder stringBuilder = new StringBuilder();

	static {
		stringBuilder.append("2");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("1s");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("1");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("2");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("Movement in Floor 1, Sub corridor 2");
		stringBuilder.append(System.lineSeparator());
		stringBuilder
				.append("No movement in Floor 1, Sub corridor 2 for a minute");
		stringBuilder.append("Movement in Floor, Sub corridor");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("Movement in Floor 1, Sub corridor");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append(System.lineSeparator());
		try {
			customStream = new CustomByteArrayInputStream(stringBuilder
					.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setIn(customStream);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.parser.InputParser#parseInitialInputs()}.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testParseInitialInputs() throws IOException {
		InitialHotelState initialHotelState = InputParser.parseInitialInputs();
		assertTrue("Invalid Floor Count!",
				2 == initialHotelState.getFloorCount());
		assertTrue("Invalid MainCorridor Count!",
				1 == initialHotelState.getMainCorridorCount());
		assertTrue("Invalid SubCorridor Count!",
				2 == initialHotelState.getSubCorridorCount());
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.parser.InputParser#parseSensorInputs(com.sahaj.assignment.controllers.PowerController)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testParseSensorInputs() throws IOException {

		IntermediateHotelState intermediateHotelState = InputParser
				.parseSensorInputs();
		assertTrue("Invalid Floor Number!",
				1 == intermediateHotelState.getFloorNumber());
		assertTrue("Invalid MainCorridor Number!",
				2 == intermediateHotelState.getSubCorridorNumber());
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.parser.InputParser#parseSensorInputs(com.sahaj.assignment.controllers.PowerController)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testParseSensorInputsForMotionDisappearance()
			throws IOException {

		IntermediateHotelState intermediateHotelState = InputParser
				.parseSensorInputs();
		assertTrue("Invalid Floor Number!",
				1 == intermediateHotelState.getFloorNumber());
		assertTrue("Invalid MainCorridor Number!",
				2 == intermediateHotelState.getSubCorridorNumber());
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.parser.InputParser#parseSensorInputs(com.sahaj.assignment.controllers.PowerController)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testParseSensorInputsForWrongValues() throws IOException {
		try {
			InputParser.parseSensorInputs();
			fail("Expected exception!");
		} catch (IllegalArgumentException exception) {
			// Good. Nothing to do.
		}

	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.parser.InputParser#parseSensorInputs(com.sahaj.assignment.controllers.PowerController)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Ignore
	public void testParseSensorInputsForTheExitScenario() throws IOException {

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("About to assert.");
			assertTrue(customStream.isClosed());
		}));
		InputParser.parseSensorInputs();
	}

}

class CustomByteArrayInputStream extends ByteArrayInputStream {

	private boolean closed;

	/**
	 * @param buf
	 */
	public CustomByteArrayInputStream(byte[] buf) {
		super(buf);
	}

	public boolean isClosed() {
		return closed;
	}

	public void close() throws IOException {
		super.close();
		closed = true;
	}
}

/**
 * 
 */
package com.sahaj.assignment.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author asgs
 *
 */
public class RegexUtilsTest {

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
	 * {@link com.sahaj.assignment.util.RegexUtils#matches(java.lang.String)}.
	 */
	@Test
	public void testMatches() {
		assertFalse("Invalid input matched!", RegexUtils.matches("sdsdsa"));
		assertTrue(
				"Valid input didn't match!",
				RegexUtils
						.matches("Blah blah Floor 34, Sub corridor 4 blah blah"));
	}

	/**
	 * Test method for
	 * {@link com.sahaj.assignment.util.RegexUtils#getMatchAtGroupIfExists(int, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetMatchAtGroupIfExists() {
		assertNull("Invalid input has a matching group!",
				RegexUtils.getMatchAtGroupIfExists(1, "sdsdsa"));
		assertTrue(
				"Valid input doesn't have a matching group!",
				RegexUtils.getMatchAtGroupIfExists(1,
						"Blah blah Floor 34, Sub corridor 4 blah blah").equals(
						"34"));
		assertTrue(
				"Valid input doesn't have a matching group!",
				RegexUtils.getMatchAtGroupIfExists(2,
						"Blah blah Floor 34, Sub corridor 4 blah blah").equals(
						"4"));
	}

}

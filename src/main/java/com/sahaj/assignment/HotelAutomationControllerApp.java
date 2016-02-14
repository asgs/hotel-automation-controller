package com.sahaj.assignment;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sahaj.assignment.builder.HotelBuilder;
import com.sahaj.assignment.controllers.MotionController;
import com.sahaj.assignment.controllers.PowerController;
import com.sahaj.assignment.entity.Hotel;
import com.sahaj.assignment.parser.InitialHotelState;
import com.sahaj.assignment.parser.InputParser;
import com.sahaj.assignment.parser.IntermediateHotelState;

/**
 * The Entry point to this application.
 * <ul>
 * <li>Reads the input from the User about the {@link Hotel}.
 * <li>Calls the {@link HotelBuilder} to construct the virtual
 * <code>Hotel instance.
 * 	<li>Instantiates the {@link PowerController} and prints the initial <code>Hotel state.
 * 	<li>Instantiates the {@link ScheduledExecutorService} to start off the periodic Sensor input polling.
 * 
 * @author asgs
 *
 */
public class HotelAutomationControllerApp {

	private static final long SENSOR_INPUT_READ_INTERVAL = 5;

	public static void main(String[] args) {
		System.out
				.println("Hello and Welcome to Hotel Automation Application! Please enter the data below.");

		InitialHotelState hotelState = InputParser.parseInitialInputs();

		HotelBuilder builder = new HotelBuilder();
		Hotel hotel = builder.addFloors(hotelState.getFloorCount())
				.addMainCorridors(hotelState.getMainCorridorCount())
				.addSubCorridors(hotelState.getSubCorridorCount()).build();

		PowerController powerController = new PowerController(hotel);
		System.out.println(hotel);
		// The external sensor inputs are read every 5 seconds so as not to hog
		// the CPU.
		ScheduledExecutorService scheduledExecutorService = Executors
				.newScheduledThreadPool(1);
		scheduledExecutorService
				.scheduleAtFixedRate(
						() -> {
							try {
								IntermediateHotelState state = InputParser
										.parseSensorInputs();
								if (state != null) {
									MotionController motionController = new MotionController(
											state.getFloorNumber(), state
													.getSubCorridorNumber(),
											powerController);
									motionController.raiseMotionDetectedEvent(state
											.isLightBulbToTurnOn());

									System.out.println(hotel);	
								}
								
							} catch (IllegalArgumentException exception) {
								System.err.println(exception.getMessage());
							}
						}, SENSOR_INPUT_READ_INTERVAL,
						SENSOR_INPUT_READ_INTERVAL, TimeUnit.SECONDS);

		cleanUpOnShutdown(scheduledExecutorService);
	}

	/**
	 * Adds a ShutdownHook to the {@link Runtime} which will clean up any
	 * resources used in this application during the JVM Shutdown.
	 * 
	 * @param scheduledExecutorService
	 */
	private static void cleanUpOnShutdown(
			ScheduledExecutorService scheduledExecutorService) {
		Runtime.getRuntime().addShutdownHook(
				new Thread(() -> scheduledExecutorService.shutdownNow()));
	}
}

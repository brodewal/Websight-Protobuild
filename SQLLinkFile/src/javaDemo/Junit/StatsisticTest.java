/**
 * 
 */
package javaDemo.Junit;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javaDemo.Reports;
import javaDemo.Statistics;
/**
 * @author gce
 *
 */

class StatsisticTest {

	protected String variabule;
	protected static double[] input = {1, 4, 3, 6, 2, 5, 1, 9, 11, 8, 7, 12, 10};
	protected static double[] even = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	protected static double value=79.0/13.0;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	protected String message ="No reconized Input enered for the given function\r\n";
	protected String errMessage ="No reconized Input enered for the given function\r\n";

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		
	}
	
	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	/**
	 * Test method for {@link javaDemo.Statistics#sortEX(double[], String)}.
	 */
	@Test
	void testSortEX_SortHighLow() {
		//Given
		double[] outputH = {12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1};
		variabule ="hi";
		
		//When
		double[] result=Statistics.sortEX(input, variabule);

		//Then
		assertArrayEquals(outputH, result, 0.0);
	}
	
	@Test
	void testSortEX_SortLowHigh() {
		//Given
		double[] outputL = {1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		variabule ="lo";
		
		//When
		double[] result=Statistics.sortEX(input, variabule);
		
		//Then
		assertArrayEquals(outputL, result, 0.0);
	}
	
	@Test
	void testSortEX_SortVoid() {
		//Given
		variabule ="void";
		
		//When
		double[] result=Statistics.sortEX(input, variabule);

		//Then
		assertArrayEquals(input, result, 0.0);
		assertThat(outContent.toString(), is(message));
	}

	/**
	 * Test method for {@link javaDemo.Statistics#average(double[])}.
	 */
	@Test
	void testAverage() {
		assertEquals(value, Statistics.average(input), 0.0);
	}

	/**
	 * Test method for {@link javaDemo.Statistics#minMax(double[], java.lang.String)}.
	 */
	@Test
	void testMinMax_getMax() {
		//Given
		variabule = "max";
		
		//Then
		assertEquals(message, 12.0, Statistics.minMax(input, variabule), 0.0);
	}
	
	@Test
	void testMinMax_getMin() {
		//Given
		variabule = "min";
		
		//Then
		assertEquals(1.0, Statistics.minMax(input, variabule), 0.0);
	}
	
	@Test
	void testMinMax_getVoid() {
		//Given
		variabule = "void";
		assertNull(Statistics.minMax(input, variabule));
		assertEquals(message, outContent.toString());
	}

	/**
	 * Test method for {@link javaDemo.Statistics#median(double[])}.
	 */
	@Test
	void testMedian_oddArrayLength() {
		assertEquals(6.0, Statistics.median(input), 0.0);
	}
	
	@Test
	void testMedian_evenArrayLength() {
		assertEquals(6.5, Statistics.median(even), 0.0);
	}

	/**
	 * Test method for {@link javaDemo.Statistics#mode(double[])}.
	 */
	@Test
	void testMode_getmode() {
		List<Double> output = Arrays.asList(1.0, 1.0);
		assertThat(output, is(Statistics.mode(input)));
	}
	
	void testMode_getNull() {
		assertTrue(Statistics.mode(even).isEmpty());
		assertThat(errMessage, outContent.toString(), is("the given array has no mode\r\n"));
	}

	/**
	 * Test method for {@link javaDemo.Statistics#SampleVariance(javaDemo.Reports)}.
	 */
	@Test
	void testSampleVariance() {
		//given
		Map<String, Double> map= new HashMap<String, Double>();
		final Reports file= new Reports();
		file.setreportId("name");
		file.setlowC(input);
		file.setaverage(value);
		map.put("Sample Variance", 14.243589743589743);
		map.put("Standard Deveation", 3.7740680629249046);
		
		//when
		Map<String, Double> result=Statistics.SampleVariance(file);
		
		//then
		assertEquals(map, result);
		assertThat(outContent.toString(), is("the Sample Variance of name is: 14.243589743589743\r\n"
		+"the Standard Deveation of name is: 3.7740680629249046\r\n"));	    
	}

	/**
	 * Test method for {@link javaDemo.Statistics#Range(javaDemo.Reports)}.
	 */
	@Test
	void testRange_oddArrayLength() {
		//Given 
		Map<String, Double> map= new HashMap<String, Double>();
		final Reports file= new Reports();
		file.setreportId("name");
		file.setlowC(input);
		file.setmax(12.0);
		file.setmin(1.0);
		file.setmedian(6.0);
		
		map.put("min", 1.0);
		map.put("lower interquartile range", 2.5);
		map.put("median", 6.0);
		map.put("upper interquartile range", 9.5);
		map.put("max", 12.0);
		map.put("interquartile range", 7.0);
		
		//When
		Map<String, Double> result=Statistics.Range(file);
		
		//Then
		assertEquals(map, result);
		assertThat(outContent.toString(), is(
				"min: 1.0"+"\r\n"
				+"lower interquartile range: 2.5"+"\r\n"
				+"median: 6.0"+"\r\n"
				+"upper interquartile range: 9.5"+"\r\n"
				+"max: 12.0"+"\r\n"
				+"interquartile range: 7.0"+"\r\n"
				+"range: 11.0"+"\r\n"
				));
	}
	
	@Test
	void testRange_evenArrayLength() {
		Map<String, Double> map= new HashMap<String, Double>();
		final Reports file= new Reports();
		file.setreportId("name");
		file.setlowC(even);
		file.setmax(12.0);
		file.setmin(1.0);
		file.setmedian(6.5);
		
		map.put("min", 1.0);
		map.put("lower interquartile range", 3.5);
		map.put("median", 6.5);
		map.put("upper interquartile range", 9.5);
		map.put("max", 12.0);
		map.put("interquartile range", 6.0);
		
		//When
		Map<String, Double> result=Statistics.Range(file);
		
		//Then
		assertEquals(map, result);
		assertThat(outContent.toString(), is(
				"min: 1.0"+"\r\n"
				+"lower interquartile range: 3.5"+"\r\n"
				+"median: 6.5"+"\r\n"
				+"upper interquartile range: 9.5"+"\r\n"
				+"max: 12.0"+"\r\n"
				+"interquartile range: 6.0"+"\r\n"
				+"range: 11.0"+"\r\n"
				));
	}

	/**
	 * Test method for {@link javaDemo.Statistics#FrequencyTable(javaDemo.Reports[])}.
	 */
	@Test
	void testFrequencyTable() {
		//Given
		Map<Double, Integer> map= new HashMap<Double, Integer>();
		List<Double> output = Arrays.asList(1.0, 1.0);
		map.put(1.0, 2);
		final Reports[] file= new Reports[1];
		file[0]=new Reports();
		file[0].setreportId("name");
		file[0].setmode(output);
		
		//When
		Map<Double, Integer> result=Statistics.FrequencyTable(file);
		
		//Then
		assertEquals(map, result);
		assertThat(outContent.toString(), is("{1.0=2}"+"\r\n"));	
	}
}

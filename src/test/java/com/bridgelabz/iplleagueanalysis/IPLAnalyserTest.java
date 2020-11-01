package com.bridgelabz.iplleagueanalysis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPLAnalyserTest {
	private static final String IPL_MOST_WICKETS_CSV_FILE_PATH = "C:\\Users\\Akash Gupta\\Downloads\\WP DP Data_02 IPL2019FactsheetMostWkts.csv";
	private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "C:\\Users\\Akash Gupta\\Downloads\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private static final String WRONG_CSV_FILE_PATH_FOR_BOWLING = "C:\\Users\\Akash Gupta\\Download\\WP DP Data_02 IPL2019FactsheetMostWkts.csv";
	private static final String WRONG_CSV_FILE_PATH_FOR_BATTING = "C:\\Users\\Akash Gupta\\Download\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private IPLAnalyser iplAnalyser;

	@Before
	public void initialize() {
		iplAnalyser = new IPLAnalyser();
	}

	@Test
	public void givenBowlingCSVFile_ShouldReturnExactCount() {
		try {
			int playerCount = iplAnalyser.loadBowlingData(IPL_MOST_WICKETS_CSV_FILE_PATH);
			Assert.assertEquals(99, playerCount);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenBowlingCSVFile_WithWrongFile_ShouldThrowException() {
		try {
			iplAnalyser.loadBowlingData(WRONG_CSV_FILE_PATH_FOR_BOWLING);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals(IPLAnalyserException.ExceptionType.BOWLING_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenBattingCSVFile_ShouldReturnExactCount() {
		try {
			int playerCount = iplAnalyser.loadBattingData(IPL_MOST_RUNS_CSV_FILE_PATH);
			Assert.assertEquals(101, playerCount);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenBattingCSVFile_WithWrongFile_ShouldThrowException() {
		try {
			iplAnalyser.loadBattingData(WRONG_CSV_FILE_PATH_FOR_BATTING);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals(IPLAnalyserException.ExceptionType.BATTING_FILE_PROBLEM, e.type);
		}
	}
}

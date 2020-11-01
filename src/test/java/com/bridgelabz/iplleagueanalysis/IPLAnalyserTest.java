package com.bridgelabz.iplleagueanalysis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPLAnalyserTest {
	private static final String IPL_MOST_WICKETS_CSV_FILE_PATH = "C:\\Users\\Akash Gupta\\Downloads\\WP DP Data_02 IPL2019FactsheetMostWkts.csv";
	private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "C:\\Users\\Akash Gupta\\Downloads\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private static final String WRONG_CSV_FILE_PATH_FOR_BOWLING = "C:\\Users\\Akash Gupta\\Download\\WP DP Data_02 IPL2019FactsheetMostWkts.csv";
	private static final String WRONG_CSV_FILE_PATH_FOR_BATTING = "C:\\Users\\Akash Gupta\\Download\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private static final String WRONG_DELIMITER_IN_MOST_WICKETS_CSV_FILE = "C:\\Users\\Akash Gupta\\Downloads\\Most_Wickets_Csv_File_With_Wrong_Delimeter.csv";
	private static final String MOST_WICKETS_CSV_FILE_WITHOUT_HEADER = "C:\\Users\\Akash Gupta\\Downloads\\Most_Wickets_Csv_File_Without_Header.csv";
	private static final String WRONG_DELIMITER_IN_MOST_RUNS_CSV_FILE = "C:\\Users\\Akash Gupta\\Downloads\\Most_Runs_Csv_File_With_Wrong_Delimeter.csv";
	private static final String MOST_RUNS_CSV_FILE_WITHOUT_HEADER = "C:\\Users\\Akash Gupta\\Downloads\\Most_Runs_Csv_File_Without_Header.csv";
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
	public void givenBowlingCSVFile_WithIncorrectDelimiter_ShouldThrowException() {
		try {
			iplAnalyser.loadBowlingData(WRONG_DELIMITER_IN_MOST_WICKETS_CSV_FILE);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals("UNABLE_TO_PARSE", e.exceptionType);
		}
	}

	@Test
	public void givenBowlingCSVFile_WithIncorrectHeader_ShouldThrowException() {
		try {
			iplAnalyser.loadBowlingData(MOST_WICKETS_CSV_FILE_WITHOUT_HEADER);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals("UNABLE_TO_PARSE", e.exceptionType);
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

	@Test
	public void givenBattingCSVFile_WithIncorrectDelimiter_ShouldThrowException() {
		try {
			iplAnalyser.loadBattingData(WRONG_DELIMITER_IN_MOST_RUNS_CSV_FILE);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals("UNABLE_TO_PARSE", e.exceptionType);
		}
	}

	@Test
	public void givenBattingCSVFile_WithIncorrectHeader_ShouldThrowException() {
		try {
			iplAnalyser.loadBattingData(MOST_RUNS_CSV_FILE_WITHOUT_HEADER);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals("UNABLE_TO_PARSE", e.exceptionType);
		}
	}
}
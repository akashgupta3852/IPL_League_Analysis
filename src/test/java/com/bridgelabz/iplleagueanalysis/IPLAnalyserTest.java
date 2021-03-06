package com.bridgelabz.iplleagueanalysis;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class IPLAnalyserTest {
	private static final String BOWLING_CSV_FILE_PATH = "C:\\Users\\Akash Gupta\\Downloads\\WP DP Data_02 IPL2019FactsheetMostWkts.csv";
	private static final String BATTING_CSV_FILE_PATH = "C:\\Users\\Akash Gupta\\Downloads\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private static final String WRONG_CSV_FILE_PATH_FOR_BOWLING = "C:\\Users\\Akash Gupta\\Download\\WP DP Data_02 IPL2019FactsheetMostWkts.csv";
	private static final String WRONG_CSV_FILE_PATH_FOR_BATTING = "C:\\Users\\Akash Gupta\\Download\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private static final String WRONG_DELIMITER_IN_BOWLING_CSV_FILE = "C:\\Users\\Akash Gupta\\Downloads\\Most_Wickets_Csv_File_With_Wrong_Delimeter.csv";
	private static final String BOWLING_CSV_FILE_WITHOUT_HEADER = "C:\\Users\\Akash Gupta\\Downloads\\Most_Wickets_Csv_File_Without_Header.csv";
	private static final String WRONG_DELIMITER_IN_BATTING_CSV_FILE = "C:\\Users\\Akash Gupta\\Downloads\\Most_Runs_Csv_File_With_Wrong_Delimeter.csv";
	private static final String BATTING_CSV_FILE_WITHOUT_HEADER = "C:\\Users\\Akash Gupta\\Downloads\\Most_Runs_Csv_File_Without_Header.csv";
	private static final String INCORRECT_TYPE_OF_BOWLING_FILE = "C:\\Users\\Akash Gupta\\eclipse-workspace\\IPLLeagueAnalysis\\WP DP Data_02 IPL2019FactsheetMostWkts.txt";
	private static final String INCORRECT_TYPE_OF_BATTING_FILE = "C:\\Users\\Akash Gupta\\eclipse-workspace\\IPLLeagueAnalysis\\WP DP Data_01 IPL2019FactsheetMostRuns.txt";
	private IPLAnalyser iplAnalyser;

	// Initialising the iplAnalyser variable before every test cases
	@Before
	public void initialize() {
		iplAnalyser = new IPLAnalyser();
	}

	// Returning the exact number of entries in the Bowling CSV File
	@Test
	public void givenBowlingCSVFile_ShouldReturnExactCount() {
		try {
			int playerCount = iplAnalyser.loadBowlingData(BOWLING_CSV_FILE_PATH);
			Assert.assertEquals(99, playerCount);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	// Catching the exception when passing the incorrect file path of Bowling CSV File
	@Test
	public void givenBowlingCSVFile_WithWrongFile_ShouldThrowException() {
		try {
			iplAnalyser.loadBowlingData(WRONG_CSV_FILE_PATH_FOR_BOWLING);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals(IPLAnalyserException.ExceptionType.BOWLING_FILE_PROBLEM, e.type);
		}
	}

	// Catching the exception when Bowling CSV File has incorrect delimiter
	@Test
	public void givenBowlingCSVFile_WithIncorrectDelimiter_ShouldThrowException() {
		try {
			iplAnalyser.loadBowlingData(WRONG_DELIMITER_IN_BOWLING_CSV_FILE);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals("UNABLE_TO_PARSE", e.exceptionType);
		}
	}

	// Catching the exception when Bowling CSV File has wrong header
	@Test
	public void givenBowlingCSVFile_WithIncorrectHeader_ShouldThrowException() {
		try {
			iplAnalyser.loadBowlingData(BOWLING_CSV_FILE_WITHOUT_HEADER);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals("UNABLE_TO_PARSE", e.exceptionType);
		}
	}

	// Catching the exception when passing the wrong file type of Bowling Data
	@Test
	public void givenBowlingCSVFile_WithWrongFileType_ShouldThrowException() {
		try {
			iplAnalyser.loadBowlingData(INCORRECT_TYPE_OF_BOWLING_FILE);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals(IPLAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
		}
	}

	// Returning the exact number of entries in the Batting CSV File
	@Test
	public void givenBattingCSVFile_ShouldReturnExactCount() {
		try {
			int playerCount = iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			Assert.assertEquals(101, playerCount);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	// Catching the exception when passing the incorrect file path of Batting CSV File
	@Test
	public void givenBattingCSVFile_WithWrongFile_ShouldThrowException() {
		try {
			iplAnalyser.loadBattingData(WRONG_CSV_FILE_PATH_FOR_BATTING);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals(IPLAnalyserException.ExceptionType.BATTING_FILE_PROBLEM, e.type);
		}
	}

	// Catching the exception when Batting CSV File has incorrect delimiter
	@Test
	public void givenBattingCSVFile_WithIncorrectDelimiter_ShouldThrowException() {
		try {
			iplAnalyser.loadBattingData(WRONG_DELIMITER_IN_BATTING_CSV_FILE);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals("UNABLE_TO_PARSE", e.exceptionType);
		}
	}

	// Catching the exception when Batting CSV File has wrong header
	@Test
	public void givenBattingCSVFile_WithIncorrectHeader_ShouldThrowException() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_WITHOUT_HEADER);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals("UNABLE_TO_PARSE", e.exceptionType);
		}
	}

	// Catching the exception when passing the wrong file type of Batting Data
	@Test
	public void givenBattingCSVFile_WithWrongFileType_ShouldThrowException() {
		try {
			iplAnalyser.loadBattingData(INCORRECT_TYPE_OF_BATTING_FILE);
		} catch (IPLAnalyserException e) {
			Assert.assertEquals(IPLAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
		}
	}

	// UC1 - Getting the top batting average of the Cricketers
	@Test
	public void givenBattingCSVFile_WhenSortedByAvgRun_ShouldReturnSortedResult_CheckTopAvgRun()
			throws IPLAnalyserException {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			String sortedBattingCSVByAvgRun = iplAnalyser.getAvgRunWiseSortedData();
			BattingCSV[] battingCSV = new Gson().fromJson(sortedBattingCSVByAvgRun, BattingCSV[].class);
			Assert.assertEquals(83.2, battingCSV[0].avg, 0.00);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	// UC2 - Getting the top striking rates of the Batsman
	@Test
	public void givenBattingCSVFile_WhenSortedByStrikingRate_ShouldReturnSortedResult_CheckTopStrikingRate() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			String sortedBattingCSVByStrikeRate = iplAnalyser.getStrikeRateWiseSortedData();
			BattingCSV[] battingCSV = new Gson().fromJson(sortedBattingCSVByStrikeRate, BattingCSV[].class);
			Assert.assertEquals(333.33, battingCSV[0].strikeRate, 0.0);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	// UC3.1 - Getting the Cricketer who hits maximum 6s
	@Test
	public void givenBattingCSVFile_WhenSortedBySixes_ShouldReturnSortedResult_CheckTopBatsman() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			String sortedBattingCSVBySixes = iplAnalyser.getSixesWiseSortedData();
			BattingCSV[] battingCSV = new Gson().fromJson(sortedBattingCSVBySixes, BattingCSV[].class);
			Assert.assertEquals("Andre Russell", battingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	// UC3.2 - Getting the Cricketer who hits maximum 4s
	@Test
	public void givenBattingCSVFile_WhenSortedByFours_ShouldReturnSortedResult_CheckTopBatsman() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			String sortedBattingCSVByFours = iplAnalyser.getFoursWiseSortedData();
			BattingCSV[] battingCSV = new Gson().fromJson(sortedBattingCSVByFours, BattingCSV[].class);
			Assert.assertEquals("Shikhar Dhawan", battingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	// UC4 - Getting the Cricketer who had best striking rates with 6s and 4s
	@Test
	public void givenBattingCSVFile_WhenSortedByStrikeRateWithFoursAndSixes_ShouldReturnSortedResult_CheckTopBatsman() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			String sortedBatsmanDataOBySRWith4sAnd6s = iplAnalyser.getStrikeRateWith4sAnd6sWiseSortedData();
			BattingCSV[] battingCSV = new Gson().fromJson(sortedBatsmanDataOBySRWith4sAnd6s, BattingCSV[].class);
			Assert.assertEquals("Ishant Sharma", battingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	// UC5 - Getting the Cricketer who had great averages with the best striking rates
	@Test
	public void givenBattingCSVFile_WhenSortedByAvgAndSR_ShouldReturnSortedResult_CheckTopBatsman() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			String sortedBattingCSVByAvgAndSR = iplAnalyser.getAvgRunsAndStrikeRateWiseSortedData();
			BattingCSV[] battingCSV = new Gson().fromJson(sortedBattingCSVByAvgAndSR, BattingCSV[].class);
			Assert.assertEquals("MS Dhoni", battingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	// UC6 - Getting the Cricketer who hit maximum runs with the best averages
	@Test
	public void givenBattingCSVFile_WhenSortedByRunsAndAvg_ShouldReturnSortedResult_CheckTopBatsman() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			String sortedBattingCSVByRunsAndAvg = iplAnalyser.getRunsAndAvgWiseSortedData();
			BattingCSV[] battingCSV = new Gson().fromJson(sortedBattingCSVByRunsAndAvg, BattingCSV[].class);
			Assert.assertEquals("David Warner ", battingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	// UC7 - Getting the top bowling average of the Cricketers
	@Test
	public void givenBowlingCSVFile_WhenSortedByAvgWickets_ShouldReturnSortedResult_CheckTopBowler() {
		try {
			iplAnalyser.loadBowlingData(BOWLING_CSV_FILE_PATH);
			String sortedBowlingCSVByAvgWickets = iplAnalyser.getAvgWicketsWiseSortedData();
			BowlingCSV[] bowlingCSV = new Gson().fromJson(sortedBowlingCSVByAvgWickets, BowlingCSV[].class);
			Assert.assertEquals("Krishnappa Gowtham", bowlingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	// UC8 - Getting the top striking rates of the Bowlers
	@Test
	public void givenBowlingCSVFile_WhenSortedByStrikingRate_ShouldReturnSortedResult_CheckTopStrikingRate() {
		try {
			iplAnalyser.loadBowlingData(BOWLING_CSV_FILE_PATH);
			String sortedBowlingCSVByStrikeRate = iplAnalyser.getStrikeRateWiseSortedBowlingData();
			BowlingCSV[] bowlingCSV = new Gson().fromJson(sortedBowlingCSVByStrikeRate, BowlingCSV[].class);
			Assert.assertEquals(120.0, bowlingCSV[0].strikeRate, 0.0);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	// UC9 - Getting the Bowler who had the best economy rate
	@Test
	public void givenBowlingCSVFile_WhenSortedByEconomyRate_ShouldReturnSortedResult_CheckTopBowler() {
		try {
			iplAnalyser.loadBowlingData(BOWLING_CSV_FILE_PATH);
			String sortedBowlingCSVByEconomyRate = iplAnalyser.getEconomyRateWiseSortedBowlingData();
			BowlingCSV[] bowlingCSV = new Gson().fromJson(sortedBowlingCSVByEconomyRate, BowlingCSV[].class);
			Assert.assertEquals("Ben Cutting", bowlingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	// UC10 - Getting the Bowler who had the best striking rate with fourWickets and fiveWickets
	@Test
	public void givenBowlingCSVFile_WhenSortedByStrikingRateWith4wAnd5w_ShouldReturnSortedResult_CheckTopBowler() {
		try {
			iplAnalyser.loadBowlingData(BOWLING_CSV_FILE_PATH);
			String sortedBowlingCSVByStrikeRateWith4wAnd5w = iplAnalyser
					.getStrikeRateWith4wAnd5wWiseSortedBowlingData();
			BowlingCSV[] bowlingCSV = new Gson().fromJson(sortedBowlingCSVByStrikeRateWith4wAnd5w, BowlingCSV[].class);
			Assert.assertEquals("Mohammad Nabi", bowlingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	// UC11 - Getting the Bowler who had the great bowling averages with the best striking rate
	@Test
	public void givenBowlingCSVFile_WhenSortedByAvgWktsAndStrikeRates_ShouldReturnSortedResult_CheckTopBowler() {
		try {
			iplAnalyser.loadBowlingData(BOWLING_CSV_FILE_PATH);
			String sortedBowlingCSVByAvgWktsAndSR = iplAnalyser.getAvgWktsAndSRWiseSortedBowlingData();
			BowlingCSV[] bowlingCSV = new Gson().fromJson(sortedBowlingCSVByAvgWktsAndSR, BowlingCSV[].class);
			Assert.assertEquals("Krishnappa Gowtham", bowlingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	// UC12 - Getting the Bowler who took the maximum wickets with the best bowling averages
	@Test
	public void givenBowlingCSVFile_WhenSortedByMaxAndAvgWkts_ShouldReturnSortedResult_CheckTopBowler() {
		try {
			iplAnalyser.loadBowlingData(BOWLING_CSV_FILE_PATH);
			String sortedBowlingCSVByMaxAndAvgWkts = iplAnalyser.getMaxAndAvgWktsWiseSortedBowlingData();
			BowlingCSV[] bowlingCSV = new Gson().fromJson(sortedBowlingCSVByMaxAndAvgWkts, BowlingCSV[].class);
			Assert.assertEquals("Imran Tahir", bowlingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	// UC13 - Getting the player who had the best batting and bowling averages
	@Test
	public void givenBattingAndBowlingCSVFile_WhenRetrieved_ShouldReturnBestAveragePlayer() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			iplAnalyser.loadBowlingData(BOWLING_CSV_FILE_PATH);
			String playerWithBestAverage = iplAnalyser.getPlayerWithBestAverage();
			Assert.assertEquals("Andre Russell", playerWithBestAverage);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

	// UC14 - Getting the player who is the best all rounder
	@Test
	public void givenBattingAndBowlingCSVFile_WhenRetrieved_ShouldReturnAllRounderPlayer() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			iplAnalyser.loadBowlingData(BOWLING_CSV_FILE_PATH);
			String allRounderPlayer = iplAnalyser.getPlayerHavingMostRunsAndMostWkts();
			Assert.assertEquals("Andre Russell", allRounderPlayer);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	//UC15 - Getting the player who hit maximum hundreds and had best batting average
	@Test
	public void givenBattingCSVFile_WhenSortedByHundredsAndAvgRuns_ShouldReturnSortedResult_CheckTopBatsman() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			String sortedBattingCSVByHundredsAndAvgRuns = iplAnalyser.getHundredsAndAvgRunsWiseSortedData();
			BattingCSV[] battingCSV = new Gson().fromJson(sortedBattingCSVByHundredsAndAvgRuns, BattingCSV[].class);
			Assert.assertEquals("David Warner ", battingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	//UC16 - Getting the player who hit zero 100s and 50s but had best batting averages
	@Test
	public void givenBattingCSVFile_WhenSortedByZero100sZeo50sAndBattingAvg_ShouldReturnSortedResult_CheckTopBatsman() {
		try {
			iplAnalyser.loadBattingData(BATTING_CSV_FILE_PATH);
			String sortedBattingCSVByZero100sZero50sAndAvg = iplAnalyser.getZero100s50sAndAvgWiseSortedData();
			BattingCSV[] battingCSV = new Gson().fromJson(sortedBattingCSVByZero100sZero50sAndAvg, BattingCSV[].class);
			Assert.assertEquals("Marcus Stoinis", battingCSV[0].player);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
}
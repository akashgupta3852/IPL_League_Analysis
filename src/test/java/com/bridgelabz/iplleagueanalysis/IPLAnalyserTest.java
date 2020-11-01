package com.bridgelabz.iplleagueanalysis;

import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {
    private static final String IPL_MOST_WICKETS_CSV_FILE_PATH  = "C:\\Users\\Akash Gupta\\Downloads\\WP DP Data_02 IPL2019FactsheetMostWkts.csv";
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH  = "C:\\Users\\Akash Gupta\\Downloads\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";

    @Test
	public void givenBowlingCSVFile_ShouldReturnExactCount() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			int playerCount  = iplAnalyser.loadBowlingData(IPL_MOST_WICKETS_CSV_FILE_PATH);
			Assert.assertEquals(99, playerCount);
			System.out.println("EQEsdghgggggggggggRF");
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
    
    @Test
	public void givenBattingCSVFile_ShouldReturnExactCount() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			int playerCount  = iplAnalyser.loadBattingData(IPL_MOST_RUNS_CSV_FILE_PATH);
			Assert.assertEquals(101, playerCount);
			System.out.println("EQERF");
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
}

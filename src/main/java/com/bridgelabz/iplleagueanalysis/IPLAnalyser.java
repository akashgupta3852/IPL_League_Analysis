package com.bridgelabz.iplleagueanalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IPLAnalyser {
	List<BattingCSV> battingCSVList = null;
	List<BowlingCSV> bowlingCSVList = null;

	public int loadBowlingData(String csvFilePath) throws IPLAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder<BowlingCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			return csvBuilder.getCSVFileList(reader, BowlingCSV.class).size();
		} catch (IOException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.BOWLING_FILE_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new IPLAnalyserException(e.getMessage(), e.type.name());
		}
	}

	public int loadBattingData(String csvFilePath) throws IPLAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder<BattingCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			return csvBuilder.getCSVFileList(reader, BattingCSV.class).size();
		} catch (IOException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.BATTING_FILE_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new IPLAnalyserException(e.getMessage(), e.type.name());
		}
	}

	public void checkFileType(String csvFilePath) throws IPLAnalyserException {
		if (!csvFilePath.endsWith(".csv"))
			throw new IPLAnalyserException("Incorrect file type", IPLAnalyserException.ExceptionType.SOME_FILE_ISSUE);
	}
}

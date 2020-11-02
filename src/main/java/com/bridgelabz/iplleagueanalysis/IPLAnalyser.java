package com.bridgelabz.iplleagueanalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

public class IPLAnalyser {
	List<BattingCSV> battingCSVList = null;
	List<BowlingCSV> bowlingCSVList = null;

	public int loadBowlingData(String csvFilePath) throws IPLAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder<BowlingCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			bowlingCSVList = csvBuilder.getCSVFileList(reader, BowlingCSV.class);
			return bowlingCSVList.size();
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
			battingCSVList = csvBuilder.getCSVFileList(reader, BattingCSV.class);
			return battingCSVList.size();
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

	public String getAvgRunWiseSortedData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(battingCSVList);
		Comparator<BattingCSV> runDataComparator = Comparator.comparing(battingCSV -> battingCSV.avg);
		this.descendingOrderSort(runDataComparator, battingCSVList);
		String sortedAvgRunsDataJson = new Gson().toJson(battingCSVList);
		return sortedAvgRunsDataJson;
	}

	private <E> void descendingOrderSort(Comparator<E> comparator, List<E> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - i - 1; j++) {
				E runs1 = list.get(j);
				E runs2 = list.get(j + 1);
				if (comparator.compare(runs1, runs2) < 0) {
					list.set(j, runs2);
					list.set(j + 1, runs1);
				}
			}
		}
	}

	public void checkCSVListNullOrEmpty(List csvList) throws IPLAnalyserException {
		if (battingCSVList == null || battingCSVList.size() == 0)
			throw new IPLAnalyserException("No Runs Data", IPLAnalyserException.ExceptionType.NO_BATTING_DATA);
	}

	public String getStrikeRateWiseSortedData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(battingCSVList);
		Comparator<BattingCSV> runDataComparator = Comparator.comparing(battingCSV -> battingCSV.strikeRate);
		this.descendingOrderSort(runDataComparator, battingCSVList);
		String sortedAvgRunsDataJson = new Gson().toJson(battingCSVList);
		return sortedAvgRunsDataJson;
	}
}

package com.bridgelabz.iplleagueanalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
		getListSortedByAvgRunsOrWkts(battingCSVList);
		String sortedJson = new Gson().toJson(battingCSVList);
		return sortedJson;
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
		if (csvList == null || csvList.size() == 0) {
			if (csvList.equals(battingCSVList))
				throw new IPLAnalyserException("No Batting Data", IPLAnalyserException.ExceptionType.NO_BATTING_DATA);
			else
				throw new IPLAnalyserException("No Bowling Data", IPLAnalyserException.ExceptionType.NO_BOWLING_DATA);
		}
	}

	public String getStrikeRateWiseSortedData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(battingCSVList);
		Comparator<BattingCSV> battingCSVComparator = Comparator.comparing(battingCSV -> battingCSV.strikeRate);
		this.descendingOrderSort(battingCSVComparator, battingCSVList);
		String sortedJson = new Gson().toJson(battingCSVList);
		return sortedJson;
	}

	public String getSixesWiseSortedData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(battingCSVList);
		Comparator<BattingCSV> battingCSVComparator = Comparator.comparing(battingCSV -> battingCSV.sixes);
		this.descendingOrderSort(battingCSVComparator, battingCSVList);
		String sortedJson = new Gson().toJson(battingCSVList);
		return sortedJson;
	}

	public String getFoursWiseSortedData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(battingCSVList);
		Comparator<BattingCSV> battingCSVComparator = Comparator.comparing(battingCSV -> battingCSV.fours);
		this.descendingOrderSort(battingCSVComparator, battingCSVList);
		String sortedJson = new Gson().toJson(battingCSVList);
		return sortedJson;
	}

	public String getStrikeRateWith4sAnd6sWiseSortedData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(battingCSVList);
		List<BattingCSV> battingCSVListWith4sAnd6s = new ArrayList<>();
		for (BattingCSV battingCSV : battingCSVList) {
			if ((battingCSV.fours != 0 || battingCSV.sixes != 0))
				battingCSVListWith4sAnd6s.add(battingCSV);
		}
		Comparator<BattingCSV> battingCSVComparator = Comparator.comparing(battingCSV -> battingCSV.strikeRate);
		this.descendingOrderSort(battingCSVComparator, battingCSVListWith4sAnd6s);
		String sortedJson = new Gson().toJson(battingCSVListWith4sAnd6s);
		return sortedJson;
	}

	public String getAvgRunsAndStrikeRateWiseSortedData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(battingCSVList);
		Comparator<BattingCSV> battingCSVComparator = Comparator.comparing(battingCSV -> battingCSV.avg);
		battingCSVComparator = battingCSVComparator.thenComparing(battingCSV -> battingCSV.strikeRate);
		this.descendingOrderSort(battingCSVComparator, battingCSVList);
		String sortedJson = new Gson().toJson(battingCSVList);
		return sortedJson;
	}

	public String getRunsAndAvgWiseSortedData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(battingCSVList);
		Comparator<BattingCSV> battingCSVComparator = Comparator.comparing(battingCSV -> battingCSV.runs);
		battingCSVComparator = battingCSVComparator.thenComparing(battingCSV -> battingCSV.avg);
		this.descendingOrderSort(battingCSVComparator, battingCSVList);
		String sortedJson = new Gson().toJson(battingCSVList);
		return sortedJson;
	}

	public String getAvgWicketsWiseSortedData() throws IPLAnalyserException {
		getListSortedByAvgRunsOrWkts(bowlingCSVList);
		String sortedJson = new Gson().toJson(bowlingCSVList);
		return sortedJson;
	}

	public String getStrikeRateWiseSortedBowlingData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(bowlingCSVList);
		Comparator<BowlingCSV> bowlingCSVComparator = Comparator.comparing(bowlingCSV -> bowlingCSV.strikeRate);
		this.descendingOrderSort(bowlingCSVComparator, bowlingCSVList);
		String sortedJson = new Gson().toJson(bowlingCSVList);
		return sortedJson;
	}

	public String getEconomyRateWiseSortedBowlingData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(bowlingCSVList);
		Comparator<BowlingCSV> bowlingCSVComparator = Comparator.comparing(bowlingCSV -> bowlingCSV.economyRate);
		this.descendingOrderSort(bowlingCSVComparator, bowlingCSVList);
		String sortedJson = new Gson().toJson(bowlingCSVList);
		return sortedJson;
	}

	public String getStrikeRateWith4wAnd5wWiseSortedBowlingData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(bowlingCSVList);
		List<BowlingCSV> bowlingCSVListWith4wAnd5w = new ArrayList<>();
		for (BowlingCSV bowlingCSV : bowlingCSVList) {
			if ((bowlingCSV.fourWickets != 0 || bowlingCSV.fiveWickets != 0))
				bowlingCSVListWith4wAnd5w.add(bowlingCSV);
		}
		Comparator<BowlingCSV> bowlingCSVComparator = Comparator.comparing(bowlingCSV -> bowlingCSV.strikeRate);
		this.descendingOrderSort(bowlingCSVComparator, bowlingCSVListWith4wAnd5w);
		String sortedJson = new Gson().toJson(bowlingCSVListWith4wAnd5w);
		return sortedJson;
	}

	public String getAvgWktsAndSRWiseSortedBowlingData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(bowlingCSVList);
		Comparator<BowlingCSV> bowlingCSVComparator = Comparator.comparing(bowlingCSV -> bowlingCSV.average);
		bowlingCSVComparator = bowlingCSVComparator.thenComparing(bowlingCSV -> bowlingCSV.strikeRate);
		this.descendingOrderSort(bowlingCSVComparator, bowlingCSVList);
		String sortedJson = new Gson().toJson(bowlingCSVList);
		return sortedJson;
	}

	public String getMaxAndAvgWktsWiseSortedBowlingData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(bowlingCSVList);
		Comparator<BowlingCSV> bowlingCSVComparator = Comparator.comparing(bowlingCSV -> bowlingCSV.wickets);
		bowlingCSVComparator = bowlingCSVComparator.thenComparing(bowlingCSV -> bowlingCSV.average);
		this.descendingOrderSort(bowlingCSVComparator, bowlingCSVList);
		String sortedJson = new Gson().toJson(bowlingCSVList);
		return sortedJson;
	}

	public String getPlayerWithBestAverage() throws IPLAnalyserException {
		getListSortedByAvgRunsOrWkts(bowlingCSVList);
		getListSortedByAvgRunsOrWkts(battingCSVList);
		for (BattingCSV battingCSV : battingCSVList) {
			for (BowlingCSV bowlingCSV : bowlingCSVList) {
				if (battingCSV.player.equals(bowlingCSV.player)) {
					return battingCSV.player;
				}
			}
		}
		return null;
	}

	public String getPlayerHavingMostRunsAndMostWkts() throws IPLAnalyserException {
		getListSortedByMaxRunsOrWkts(bowlingCSVList);
		getListSortedByMaxRunsOrWkts(battingCSVList);
		for (BattingCSV battingCSV : battingCSVList) {
			for (BowlingCSV bowlingCSV : bowlingCSVList) {
				if (battingCSV.player.equals(bowlingCSV.player)) {
					return battingCSV.player;
				}
			}
		}
		return null;
	}

	public String getHundredsAndAvgRunsWiseSortedData() throws IPLAnalyserException {
		checkCSVListNullOrEmpty(battingCSVList);
		Comparator<BattingCSV> battingCSVComparator = Comparator.comparing(battingCSV -> battingCSV.hundreds);
		battingCSVComparator = battingCSVComparator.thenComparing(battingCSV -> battingCSV.avg);
		this.descendingOrderSort(battingCSVComparator, battingCSVList);
		String sortedJson = new Gson().toJson(battingCSVList);
		return sortedJson;
	}

	private void getListSortedByAvgRunsOrWkts(List csvList) throws IPLAnalyserException {
		checkCSVListNullOrEmpty(csvList);
		if (csvList == bowlingCSVList) {
			Comparator<BowlingCSV> bowlingCSVComparator = Comparator.comparing(bowlingCSV -> bowlingCSV.average);
			this.descendingOrderSort(bowlingCSVComparator, csvList);
		} else {
			Comparator<BattingCSV> battingCSVComparator = Comparator.comparing(battingCSV -> battingCSV.avg);
			this.descendingOrderSort(battingCSVComparator, csvList);
		}
	}

	private void getListSortedByMaxRunsOrWkts(List csvList) throws IPLAnalyserException {
		checkCSVListNullOrEmpty(csvList);
		if (csvList == bowlingCSVList) {
			Comparator<BowlingCSV> bowlingCSVComparator = Comparator.comparing(bowlingCSV -> bowlingCSV.wickets);
			this.descendingOrderSort(bowlingCSVComparator, csvList);
		} else {
			Comparator<BattingCSV> battingCSVComparator = Comparator.comparing(battingCSV -> battingCSV.runs);
			this.descendingOrderSort(battingCSVComparator, csvList);
		}
	}
}

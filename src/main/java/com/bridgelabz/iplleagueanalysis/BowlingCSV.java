package com.bridgelabz.iplleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class BowlingCSV {
	@CsvBindByName(column = "POS")
	public String pos;

	@CsvBindByName(column = "PLAYER", required = true)
	public String player;

	@CsvBindByName(column = "Mat", required = true)
	public int match;

	@CsvBindByName(column = "Inns", required = true)
	public int innings;

	@CsvBindByName(column = "Ov", required = true)
	public double overs;

	@CsvBindByName(column = "Runs", required = true)
	public int runs;

	@CsvBindByName(column = "Wkts", required = true)
	public int wickets;

	@CsvBindByName(column = "BBI", required = true)
	public double bbi;

	@CsvBindByName(column = "Avg", required = true)
	public double average;

	@CsvBindByName(column = "Econ", required = true)
	public double economyRate;

	@CsvBindByName(column = "SR", required = true)
	public double strikeRate;

	@CsvBindByName(column = "4w", required = true)
	public int fourWickets;

	@CsvBindByName(column = "5w", required = true)
	public int fiveWickets;

	public BowlingCSV() {
	}

	public BowlingCSV(String pos, String player, int match, int innings, double overs, int runs, int wickets,
			double bbi, double average, double economyRate, double strikeRate, int fourWickets, int fiveWickets) {
		this.pos = pos;
		this.player = player;
		this.match = match;
		this.innings = innings;
		this.overs = overs;
		this.runs = runs;
		this.wickets = wickets;
		this.bbi = bbi;
		this.average = average;
		this.economyRate = economyRate;
		this.strikeRate = strikeRate;
		this.fourWickets = fourWickets;
		this.fiveWickets = fiveWickets;
	}
}
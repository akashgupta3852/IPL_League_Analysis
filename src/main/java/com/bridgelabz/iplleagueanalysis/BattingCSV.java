package com.bridgelabz.iplleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class BattingCSV {
	@CsvBindByName(column = "POS")
	public String pos;

	@CsvBindByName(column = "PLAYER", required = true)
	public String player;

	@CsvBindByName(column = "Mat", required = true)
	public int match;

	@CsvBindByName(column = "Inns", required = true)
	public int innings;

	@CsvBindByName(column = "NO", required = true)
	public int no;

	@CsvBindByName(column = "Runs", required = true)
	public int runs;

	@CsvBindByName(column = "HS", required = true)
	public String highScore;

	@CsvBindByName(column = "Avg", required = true)
	public double avg;

	@CsvBindByName(column = "BF", required = true)
	public int ballFaced;

	@CsvBindByName(column = "SR", required = true)
	public double strikeRate;

	@CsvBindByName(column = "100", required = true)
	public int hundreds;

	@CsvBindByName(column = "50", required = true)
	public int fiftys;

	@CsvBindByName(column = "4s", required = true)
	public int fours;

	@CsvBindByName(column = "6s", required = true)
	public int sixes;

	public BattingCSV() {
	}

	public BattingCSV(String pos, String player, int match, int innings, int no, int runs, String highScore, double avg,
			int ballFaced, double strikeRate, int hundreds, int fiftys, int fours, int sixes) {
		this.pos = pos;
		this.player = player;
		this.match = match;
		this.innings = innings;
		this.no = no;
		this.runs = runs;
		this.highScore = highScore;
		this.avg = avg;
		this.ballFaced = ballFaced;
		this.strikeRate = strikeRate;
		this.hundreds = hundreds;
		this.fiftys = fiftys;
		this.fours = fours;
		this.sixes = sixes;
	}
}
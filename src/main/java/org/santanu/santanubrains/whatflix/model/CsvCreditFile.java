package org.santanu.santanubrains.whatflix.model;

import com.opencsv.bean.CsvBindByName;

public class CsvCreditFile {

	@CsvBindByName(column = "movie_id")
	private int movieId;

	@CsvBindByName(column = "title")
	private String title;

	@CsvBindByName(column = "cast")
	private String cast;

	@CsvBindByName(column = "crew")
	private String crew;

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

}

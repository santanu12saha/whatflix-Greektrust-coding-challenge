package org.santanu.santanubrains.whatflix.model;

import java.util.List;

public class CreditFile {

	private int movieId;
	private String title;
	private List<Cast> casts;
	private List<Crew> crews;

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

	public List<Cast> getCasts() {
		return casts;
	}

	public void setCasts(List<Cast> casts) {
		this.casts = casts;
	}

	public List<Crew> getCrews() {
		return crews;
	}

	public void setCrews(List<Crew> crews) {
		this.crews = crews;
	}

}

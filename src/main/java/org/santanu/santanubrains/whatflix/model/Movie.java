package org.santanu.santanubrains.whatflix.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;

public class Movie {

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("language")
	private String language;

	@SerializedName("genres")
	private List<BaseEntity> genres;

	@SerializedName("keywords")
	private List<BaseEntity> keywords;

	@SerializedName("casts")
	private List<Cast> casts;

	@SerializedName("crews")
	private List<Crew> crews;

	@SerializedName("releaseDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date releaseDate;

	@SerializedName("runtime")
	private float runtime;

	@SerializedName("url")
	private String url;

	@SerializedName("productionCompanies")
	private List<BaseEntity> productionCompanies;

	@SerializedName("budget")
	private long budget;

	@SerializedName("revenue")
	private long revenue;

	@SerializedName("popularity")
	private float popularity;

	@SerializedName("voteAverage")
	private float voteAverage;

	@SerializedName("voteCount")
	private int voteCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<BaseEntity> getGenres() {
		return genres;
	}

	public void setGenres(List<BaseEntity> genres) {
		this.genres = genres;
	}

	public List<BaseEntity> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<BaseEntity> keywords) {
		this.keywords = keywords;
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public float getRuntime() {
		return runtime;
	}

	public void setRuntime(float runtime) {
		this.runtime = runtime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<BaseEntity> getProductionCompanies() {
		return productionCompanies;
	}

	public void setProductionCompanies(List<BaseEntity> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public long getRevenue() {
		return revenue;
	}

	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}

	public float getPopularity() {
		return popularity;
	}

	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}

	public float getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(float voteAverage) {
		this.voteAverage = voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

}

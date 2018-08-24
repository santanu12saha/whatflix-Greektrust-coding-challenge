package org.santanu.santanubrains.whatflix.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPreference {

	@JsonProperty
	private List<String> preferred_languages = new ArrayList<>();

	@JsonProperty
	private List<String> favourite_actors = new ArrayList<>();

	@JsonProperty
	private List<String> favourite_directors = new ArrayList<>();

	public List<String> getPreferred_languages() {
		return preferred_languages;
	}

	public void setPreferred_languages(List<String> preferred_languages) {
		this.preferred_languages = preferred_languages;
	}

	public List<String> getFavourite_actors() {
		return favourite_actors;
	}

	public void setFavourite_actors(List<String> favourite_actors) {
		this.favourite_actors = favourite_actors;
	}

	public List<String> getFavourite_directors() {
		return favourite_directors;
	}

	public void setFavourite_directors(List<String> favourite_directors) {
		this.favourite_directors = favourite_directors;
	}

	@Override
	public String toString() {
		return "UserPreference [preferred_languages=" + preferred_languages + ", favourite_actors=" + favourite_actors
				+ ", favourite_directors=" + favourite_directors + "]";
	}

}

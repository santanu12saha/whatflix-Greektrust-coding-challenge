package org.santanu.santanubrains.whatflix.response;

import java.util.Set;
import java.util.TreeSet;

public class UserPrefMovie {

	private String user;
	private Set<String> movies;

	public UserPrefMovie() {
		super();
		movies = new TreeSet<>();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Set<String> getMovies() {
		return movies;
	}

	public void setMovies(Set<String> movies) {
		this.movies = movies;
	}

}

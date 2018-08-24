package org.santanu.santanubrains.whatflix.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.santanu.santanubrains.whatflix.model.UserPreference;
import org.santanu.santanubrains.whatflix.response.UserPrefMovie;

public class UserPrefMovieDao {

	private QueryPrefMovieDao queryPrefMovieDao;

	public UserPrefMovieDao() {
		super();

	}

	@Inject
	public UserPrefMovieDao(QueryPrefMovieDao queryPrefMovieDao) {
		super();
		this.queryPrefMovieDao = queryPrefMovieDao;
	}

	public List<UserPrefMovie> getPreferenceMovieWithUser(Map<String, UserPreference> userPref) {
		List<UserPrefMovie> userMovie = new ArrayList<>();
		for (Map.Entry<String, UserPreference> entry : userPref.entrySet()) {
			UserPrefMovie userPrefMovie = new UserPrefMovie();
			userPrefMovie.setUser(entry.getKey());
			Set<String> movies = queryPrefMovieDao.searchMovieByPref(entry.getValue().getPreferred_languages(),
					entry.getValue().getFavourite_actors(), entry.getValue().getFavourite_directors());
			if (movies != null) {
				int count = 1;
				for(String m : movies) {
					if(count <= 3) {
						userPrefMovie.getMovies().add(m);
					}else {
						break;
					}
					++count;
				}
				
			}
			userMovie.add(userPrefMovie);
		}

		return userMovie;
	}

}

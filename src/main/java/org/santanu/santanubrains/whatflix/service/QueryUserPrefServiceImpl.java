package org.santanu.santanubrains.whatflix.service;

import java.util.List;

import javax.inject.Inject;

import org.santanu.santanubrains.whatflix.dataAdapter.QueryMovieAdapterByUserPref;
import org.santanu.santanubrains.whatflix.response.UserPrefMovie;

import io.reactivex.Single;

public class QueryUserPrefServiceImpl implements QueryUserPrefService {

	private QueryMovieAdapterByUserPref queryMovieAdapterByUserPref;

	@Inject
	public QueryUserPrefServiceImpl(QueryMovieAdapterByUserPref queryMovieAdapterByUserPref) {
		super();
		this.queryMovieAdapterByUserPref = queryMovieAdapterByUserPref;
	}

	@Override
	public Single<List<UserPrefMovie>> getMoviesByUserPref() {

		return queryMovieAdapterByUserPref.getMoviesByUserPref();
	}

}

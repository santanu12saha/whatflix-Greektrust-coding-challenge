package org.santanu.santanubrains.whatflix.service;

import java.util.Set;

import javax.inject.Inject;

import org.santanu.santanubrains.whatflix.dataAdapter.QueryMovieAdapterByUserPrefSearchText;

import io.reactivex.Single;

public class QueryUserPrefSearchTextServiceImpl implements QueryUserPrefSearchTextService {

	private QueryMovieAdapterByUserPrefSearchText queryMovieAdapterByUserPrefSearchText;

	@Inject
	public QueryUserPrefSearchTextServiceImpl(QueryMovieAdapterByUserPrefSearchText queryMovieAdapterByUserPrefSearchText) {
		super();
		this.queryMovieAdapterByUserPrefSearchText = queryMovieAdapterByUserPrefSearchText;
	}

	@Override
	public Single<Set<String>> getMoviesByUserPrefAndSeachText(String userId, String searchText) {

		return queryMovieAdapterByUserPrefSearchText.getMovieSearchByUIDText(userId, searchText);
	}

}

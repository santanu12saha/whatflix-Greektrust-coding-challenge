package org.santanu.santanubrains.whatflix.service;

import java.util.Set;

import io.reactivex.Single;

public interface QueryUserPrefSearchTextService {

	Single<Set<String>> getMoviesByUserPrefAndSeachText(String userId, String searchText);
}

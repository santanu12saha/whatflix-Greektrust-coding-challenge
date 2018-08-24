package org.santanu.santanubrains.whatflix.dataAdapter;

import java.util.Set;

import io.reactivex.Single;

public interface QueryMovieAdapterByUserPrefSearchText {

	Single<Set<String>> getMovieSearchByUIDText(String userId, String text);
}

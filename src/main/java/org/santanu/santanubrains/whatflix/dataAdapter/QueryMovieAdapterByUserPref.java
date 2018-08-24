package org.santanu.santanubrains.whatflix.dataAdapter;

import java.util.List;

import org.santanu.santanubrains.whatflix.response.UserPrefMovie;

import io.reactivex.Single;

public interface QueryMovieAdapterByUserPref {
	
	Single<List<UserPrefMovie>> getMoviesByUserPref();
}

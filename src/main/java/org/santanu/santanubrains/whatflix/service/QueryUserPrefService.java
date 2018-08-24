package org.santanu.santanubrains.whatflix.service;

import java.util.List;

import org.santanu.santanubrains.whatflix.response.UserPrefMovie;

import io.reactivex.Single;

public interface QueryUserPrefService {

	Single<List<UserPrefMovie>> getMoviesByUserPref();
}

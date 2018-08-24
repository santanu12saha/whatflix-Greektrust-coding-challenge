package org.santanu.santanubrains.whatflix.dataAdapter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.santanu.santanubrains.whatflix.cache.UserCacheStorage;
import org.santanu.santanubrains.whatflix.dao.UserDao;
import org.santanu.santanubrains.whatflix.dao.UserPrefMovieDao;

import org.santanu.santanubrains.whatflix.model.UserPreference;
import org.santanu.santanubrains.whatflix.response.UserPrefMovie;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class QueryMovieAdapterByUserPrefImpl implements QueryMovieAdapterByUserPref{

	private UserDao userDao;
	private UserPrefMovieDao userPrefMovieDao;
	private UserCacheStorage userCacheStorage;
	private static final String TAG_LIST_USER = "LIST_USER";
	
	
	@Inject
	public QueryMovieAdapterByUserPrefImpl(UserDao userDao, UserPrefMovieDao userPrefMovieDao ,UserCacheStorage userCacheStorage) {
		super();
		this.userDao = userDao;
		this.userPrefMovieDao = userPrefMovieDao;
		this.userCacheStorage = userCacheStorage;
	}



	@Override
	public Single<List<UserPrefMovie>> getMoviesByUserPref() {
		
		return Single.create(new SingleOnSubscribe<List<UserPrefMovie>>() {

			@SuppressWarnings("unchecked")
			@Override
			public void subscribe(SingleEmitter<List<UserPrefMovie>> subscriber) throws Exception {
				Map<String, UserPreference> userPref = null;
				ConcurrentMap<String, Object> map = userCacheStorage.getAsMap();
				if(map.containsKey(TAG_LIST_USER)) {
					userPref = (Map<String, UserPreference>) map.get(TAG_LIST_USER);
				}else {
					userPref = userDao.getAllUsersFromPreferenceFile();
					userCacheStorage.add(TAG_LIST_USER, userPref);
				}
				
				if(userPref != null) {
					List<UserPrefMovie> userPrefMovieList = userPrefMovieDao.getPreferenceMovieWithUser(userPref);
					subscriber.onSuccess(userPrefMovieList);
				}else {
					subscriber.onError(new NotFoundException("User Prefence Not found"));
				}
				
			}
		});
	}

}

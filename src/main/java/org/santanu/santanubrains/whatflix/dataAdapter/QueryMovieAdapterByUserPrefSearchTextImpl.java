package org.santanu.santanubrains.whatflix.dataAdapter;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.santanu.santanubrains.whatflix.cache.UserCacheStorage;
import org.santanu.santanubrains.whatflix.dao.UserDao;
import org.santanu.santanubrains.whatflix.dao.UserPrefSearchMovieDao;
import org.santanu.santanubrains.whatflix.model.UserPreference;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class QueryMovieAdapterByUserPrefSearchTextImpl implements QueryMovieAdapterByUserPrefSearchText {

	private UserDao userDao;
	private UserCacheStorage userCacheStorage;
	private UserPrefSearchMovieDao userPrefSearchMovieDao;

	@Inject
	public QueryMovieAdapterByUserPrefSearchTextImpl(UserDao userDao, UserCacheStorage userCacheStorage,
			UserPrefSearchMovieDao userPrefSearchMovieDao) {
		super();
		this.userDao = userDao;
		this.userCacheStorage = userCacheStorage;
		this.userPrefSearchMovieDao = userPrefSearchMovieDao;
	}

	@Override
	public Single<Set<String>> getMovieSearchByUIDText(String userId, String text) {

		return Single.create(new SingleOnSubscribe<Set<String>>() {

			@Override
			public void subscribe(SingleEmitter<Set<String>> subscriber) throws Exception {
				UserPreference userPreference = null;
				ConcurrentMap<String, Object> map = userCacheStorage.getAsMap();
				if (map.containsKey(userId)) {
					userPreference = (UserPreference) map.get(userId);
				} else {
					userPreference = userDao.getSingleUserFromPreferenceFile(userId);

				}
				if (userPreference != null) {
					if (!map.containsKey(userId))
						userCacheStorage.add(userId, userPreference);
					Set<String> movies = userPrefSearchMovieDao.searchMovieByUserPrefAndText(
							userPreference.getPreferred_languages(), userPreference.getFavourite_actors(),
							userPreference.getFavourite_directors(), text);
					if (movies.size() > 0) {
						subscriber.onSuccess(movies);
					} else {
						subscriber.onError(new NotFoundException("No Movies found."));
					}

				} else {
					subscriber.onError(new NotFoundException("User " + userId + " not found."));
				}

			}
		});
	}

}

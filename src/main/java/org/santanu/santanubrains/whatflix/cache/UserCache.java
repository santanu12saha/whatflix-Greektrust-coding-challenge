package org.santanu.santanubrains.whatflix.cache;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public abstract class UserCache {

	private LoadingCache<String, Object> userCache = null;

	protected UserCache(long maxSize, long expireInMinutes) {
		init(maxSize, expireInMinutes);
	}

	private void init(long maxSize, long expireInMinutes) {
		RemovalListener<String, Object> removalListener = new RemovalListener<String, Object>() {

			@Override
			public void onRemoval(RemovalNotification<String, Object> removal) {
				if (removal.getCause() == RemovalCause.EXPIRED) {
					processAfterExpire(removal.getKey(), removal.getValue());
				} else if (removal.getCause() == RemovalCause.REPLACED) {
					processAfterReplace(removal.getKey(), removal.getValue());
				} else {
					// TODO if necessary
				}

			}
		};

		userCache = CacheBuilder.newBuilder().maximumSize(maxSize).expireAfterWrite(expireInMinutes, TimeUnit.MINUTES)
				.removalListener(removalListener).build(new CacheLoader<String, Object>() {

					@Override
					public Object load(String key) throws Exception {
						// TODO Auto-generated method stub
						return getUnchecked(key);
					}

				});

	}

	public Object getUnchecked(String key) {
		return userCache.getUnchecked(key);
	}

	public void add(String key, Object o) {
		userCache.put(key, o);
	}

	public void delete(String key) {
		userCache.invalidate(key);
	}

	public long getSize() {
		return userCache.size();
	}

	public void cleanup() {
		userCache.cleanUp();
	}

	public ConcurrentMap<String, Object> getAsMap() {
		return userCache.asMap();
	}

	public LoadingCache<String, Object> getCache() {
		return userCache;
	}

	public abstract void processAfterExpire(String key, Object obj);

	public abstract void processAfterReplace(String key, Object obj);
}

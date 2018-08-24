package org.santanu.santanubrains.whatflix.cache;

import java.util.Date;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.santanu.santanubrains.whatflix.log4j.Log4jUtil;

@Singleton
public class UserCacheStorage extends UserCache{

	private final static long EXPIRE_IN_MINUTES = 10; // Timeout after 10 minutes
	private final static long MAX_SIZE = 1000;
	private static final Logger logger = Log4jUtil.getLogger(UserCacheStorage.class);
	
	public UserCacheStorage() {
		super(MAX_SIZE, EXPIRE_IN_MINUTES);
	}
	
	@Override
	public void processAfterExpire(String key, Object obj) {
		logger.info("TIMEOUT OCCURED! Key: " + key + ", Date: " + new Date().toString());

	}

	@Override
	public void processAfterReplace(String key, Object obj) {
		logger.info("REPLACE OCCURED! Key: " + key + ", Date: " + new Date().toString());

	}

}

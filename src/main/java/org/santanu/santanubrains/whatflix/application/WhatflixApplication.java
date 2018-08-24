package org.santanu.santanubrains.whatflix.application;

import java.util.Date;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

import org.apache.log4j.Logger;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.santanu.santanubrains.whatflix.cache.UserCacheStorage;
import org.santanu.santanubrains.whatflix.dao.QueryPrefMovieDao;
import org.santanu.santanubrains.whatflix.dao.UserDao;
import org.santanu.santanubrains.whatflix.dao.UserPrefMovieDao;
import org.santanu.santanubrains.whatflix.dao.UserPrefSearchMovieDao;
import org.santanu.santanubrains.whatflix.dataAdapter.QueryMovieAdapterByUserPref;
import org.santanu.santanubrains.whatflix.dataAdapter.QueryMovieAdapterByUserPrefImpl;
import org.santanu.santanubrains.whatflix.dataAdapter.QueryMovieAdapterByUserPrefSearchText;
import org.santanu.santanubrains.whatflix.dataAdapter.QueryMovieAdapterByUserPrefSearchTextImpl;
import org.santanu.santanubrains.whatflix.exception.GenericExceptionMapper;
import org.santanu.santanubrains.whatflix.exception.InternalErrorExceptionMapper;
import org.santanu.santanubrains.whatflix.exception.NotFoundExceptionMapper;
import org.santanu.santanubrains.whatflix.filter.LoggingFilter;
import org.santanu.santanubrains.whatflix.filter.PoweredByResponseFilter;
import org.santanu.santanubrains.whatflix.log4j.Log4jUtil;
import org.santanu.santanubrains.whatflix.resource.QueryMovieUserPrefAndSearchTextResource;
import org.santanu.santanubrains.whatflix.resource.QueryMovieUserPrefResource;
import org.santanu.santanubrains.whatflix.search.Indexer;
import org.santanu.santanubrains.whatflix.search.SearchEngine;
import org.santanu.santanubrains.whatflix.service.QueryUserPrefSearchTextService;
import org.santanu.santanubrains.whatflix.service.QueryUserPrefSearchTextServiceImpl;
import org.santanu.santanubrains.whatflix.service.QueryUserPrefService;
import org.santanu.santanubrains.whatflix.service.QueryUserPrefServiceImpl;
import org.santanu.santanubrains.whatflix.utility.FileConfig;
import org.santanu.santanubrains.whatflix.utility.GetFilePath;
import org.santanu.santanubrains.whatflix.utility.ReadCsvFiles;

import com.google.gson.Gson;

@ApplicationPath("api")
public class WhatflixApplication extends ResourceConfig{
	
	private static final Logger logger = Log4jUtil.getLogger(WhatflixApplication.class);
	
			public WhatflixApplication() {
		
		logger.info("Restful rxjava Whatflix application initializing : " + new Date().toString());
		
		register(JacksonFeature.class);
		register(QueryMovieUserPrefResource.class);
		register(QueryMovieUserPrefAndSearchTextResource.class);
		register(GenericExceptionMapper.class);
		register(InternalErrorExceptionMapper.class);
		register(NotFoundExceptionMapper.class);
		register(LoggingFilter.class);
		register(PoweredByResponseFilter.class);
		
		register(new AbstractBinder() {
			
			@Override
			protected void configure() {
				bindAsContract(Gson.class);
				bindAsContract(FileConfig.class);
				bindAsContract(Indexer.class).in(Singleton.class);
				bindAsContract(SearchEngine.class).in(Singleton.class);
				bindAsContract(GetFilePath.class).in(Singleton.class);
				bindAsContract(FileConfig.class).in(Singleton.class);
				bindAsContract(ReadCsvFiles.class).in(Singleton.class);
				bindAsContract(UserCacheStorage.class).in(Singleton.class);
				bindAsContract(UserDao.class);
				bindAsContract(UserPrefMovieDao.class);
				bindAsContract(QueryPrefMovieDao.class);
				bind(QueryMovieAdapterByUserPrefImpl.class).to(QueryMovieAdapterByUserPref.class);
				bind(QueryUserPrefServiceImpl.class).to(QueryUserPrefService.class);
				bindAsContract(UserPrefSearchMovieDao.class);
				bind(QueryMovieAdapterByUserPrefSearchTextImpl.class).to(QueryMovieAdapterByUserPrefSearchText.class);
				bind(QueryUserPrefSearchTextServiceImpl.class).to(QueryUserPrefSearchTextService.class);
				
			}
		});
	}
	
	
}

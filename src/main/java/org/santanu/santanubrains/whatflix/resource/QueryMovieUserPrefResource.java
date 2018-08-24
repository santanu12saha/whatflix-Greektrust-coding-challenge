package org.santanu.santanubrains.whatflix.resource;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ManagedAsync;
import org.santanu.santanubrains.whatflix.exception.InternalErrorException;
import org.santanu.santanubrains.whatflix.log4j.Log4jUtil;
import org.santanu.santanubrains.whatflix.response.UserPrefMovie;
import org.santanu.santanubrains.whatflix.service.QueryUserPrefService;

import com.google.gson.Gson;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@Path("movies")
public class QueryMovieUserPrefResource {

	private static final Logger logger = Log4jUtil.getLogger(QueryMovieUserPrefResource.class);
	private QueryUserPrefService queryUserPrefService;
	private Gson gson;

	@Inject
	public QueryMovieUserPrefResource(QueryUserPrefService queryUserPrefService, Gson gson) {
		super();
		this.queryUserPrefService = queryUserPrefService;
		this.gson = gson;
	}

	@Path("users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getMovieByUserPreference(@Suspended final AsyncResponse async) {

		final CountDownLatch outerLatch = new CountDownLatch(1);

		queryUserPrefService.getMoviesByUserPref().subscribe(new SingleObserver<List<UserPrefMovie>>() {

			@Override
			public void onSubscribe(Disposable d) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<UserPrefMovie> userPrefMovieResponse) {
				async.resume(gson.toJson(userPrefMovieResponse));
				outerLatch.countDown();

			}

			@Override
			public void onError(Throwable errorResponse) {
				logger.error(errorResponse);
				async.resume(errorResponse);
				outerLatch.countDown();

			}
		});

		try {
			if (!outerLatch.await(10, TimeUnit.SECONDS)) {
				async.resume(new InternalErrorException());
			}
		} catch (Exception e) {
			async.resume(new InternalErrorException());
		}
	}
}

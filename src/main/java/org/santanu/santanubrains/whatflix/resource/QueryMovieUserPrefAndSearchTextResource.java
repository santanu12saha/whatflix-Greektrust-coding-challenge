package org.santanu.santanubrains.whatflix.resource;

import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ManagedAsync;
import org.santanu.santanubrains.whatflix.exception.InternalErrorException;
import org.santanu.santanubrains.whatflix.log4j.Log4jUtil;
import org.santanu.santanubrains.whatflix.service.QueryUserPrefSearchTextService;

import com.google.gson.Gson;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Path("movies/user")
public class QueryMovieUserPrefAndSearchTextResource {

	private static final Logger logger = Log4jUtil.getLogger(QueryMovieUserPrefAndSearchTextResource.class);
	private QueryUserPrefSearchTextService queryUserPrefSearchTextService;
	private Gson gson;

	@Inject
	public QueryMovieUserPrefAndSearchTextResource(QueryUserPrefSearchTextService queryUserPrefSearchTextService,
			Gson gson) {
		super();
		this.queryUserPrefSearchTextService = queryUserPrefSearchTextService;
		this.gson = gson;
	}

	
	@Path("{userId}/search")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getMovieByUserPrefAndSearchText(@Suspended final AsyncResponse async, @PathParam("userId") final String userId, @QueryParam("text") final String searchText) {

		final CountDownLatch outerLatch = new CountDownLatch(1);

		queryUserPrefSearchTextService.getMoviesByUserPrefAndSeachText(userId, searchText.toLowerCase())
				.subscribeOn(Schedulers.io()).subscribe(new SingleObserver<Set<String>>() {

					@Override
					public void onSubscribe(Disposable d) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Set<String> userPrefSearchMovieResponse) {
						async.resume(gson.toJson(userPrefSearchMovieResponse));
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

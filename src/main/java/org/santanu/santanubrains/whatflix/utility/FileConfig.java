package org.santanu.santanubrains.whatflix.utility;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FileConfig {

	public static final String movies_file = "tmdb_5000_movies.csv";
	public static final String credits_file = "tmdb_5000_credits_test.csv";
	public static final String user_file = "user_preferences.json";
	public static String INDEX_PATH = "index-directory";

	private GetFilePath getFilePath;

	@Inject
	public FileConfig(GetFilePath getFilePath) {
		super();
		this.getFilePath = getFilePath;
	}

	public String getMovieFile() {
		return getFilePath.getPath(movies_file);

	}
	
	public String getCreditFile() {
		return getFilePath.getPath(credits_file);
	}
	
	public String getUserFile() {
		return getFilePath.getPath(user_file);
	}
}

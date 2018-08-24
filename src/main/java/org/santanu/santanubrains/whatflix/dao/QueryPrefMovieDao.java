package org.santanu.santanubrains.whatflix.dao;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.santanu.santanubrains.whatflix.search.SearchEngine;
import org.santanu.santanubrains.whatflix.utility.LanguageAbbrevation;

public class QueryPrefMovieDao {

	private SearchEngine searchEngine;
	private Set<String> movies = null;

	public QueryPrefMovieDao() {
		super();
	}

	@Inject
	public QueryPrefMovieDao(SearchEngine searchEngine) throws IOException {
		super();
		this.searchEngine = searchEngine;

	}

	public Set<String> searchMovieByPref(List<String> preferred_languages, List<String> favourite_actors,
			List<String> favourite_directors) {
		movies = new TreeSet<>();

		if (getUserMovieByLanguage(preferred_languages) != null) {
			movies.addAll(getUserMovieByLanguage(preferred_languages));
		}
		if (getUserMovieByFavActor(favourite_actors) != null && favourite_actors.size() > 0) {
			movies.addAll(getUserMovieByFavActor(favourite_actors));
		}
		if (getUserMovieByDirector(favourite_directors) != null && favourite_directors.size() > 0) {
			movies.addAll(getUserMovieByDirector(favourite_directors));
		}

		return movies;
	}

	private Set<String> getUserMovieByLanguage(List<String> preferred_languages) {
		Set<String> movie = new TreeSet<>();
		for (String pre_lang : preferred_languages) {

			for (String te : getMovieDataByLang(pre_lang)) {
				movie.add(te);
			}
		}
		return movie;
	}

	private Set<String> getMovieDataByLang(String pre_lang) {

		Set<String> movieByLanguage = new TreeSet<>();

		TermQuery queryLanguage = new TermQuery(new Term("language", LanguageAbbrevation.search(pre_lang)));
		TopDocs topDocs = searchEngine.performSingleSearch(queryLanguage, 1);

		ScoreDoc[] hits = topDocs.scoreDocs;
		for (int i = 0; i < hits.length; i++) {
			try {
				Document doc = searchEngine.getDocument(hits[i].doc);
				movieByLanguage.add(doc.get("title"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return movieByLanguage;
	}

	private Set<String> getUserMovieByFavActor(List<String> favourite_actors) {

		Set<String> movie = new TreeSet<>();
		for (String fav_actor : favourite_actors) {

			for (String te : getMovieDataByFavActor(fav_actor.toLowerCase())) {
				movie.add(te);
			}
		}
		return movie;

	}

	private Set<String> getMovieDataByFavActor(String fav_actor) {

		Set<String> movieByActor = new TreeSet<>();
		TermQuery queryActor = new TermQuery(new Term("Cast" + fav_actor, fav_actor));
		TopDocs topDocs = searchEngine.performSingleSearch(queryActor, 100);

		ScoreDoc[] hits = topDocs.scoreDocs;
		for (int i = 0; i < hits.length; i++) {
			try {
				Document doc = searchEngine.getDocument(hits[i].doc);
				movieByActor.add(doc.get("title"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return movieByActor;
	}

	private Set<String> getUserMovieByDirector(List<String> favourite_directors) {

		Set<String> movie = new TreeSet<>();
		for (String fav_director : favourite_directors) {

			for (String te : getMovieDataByFavDirector(fav_director.toLowerCase())) {
				movie.add(te);
			}
		}
		return movie;
	}

	private Set<String> getMovieDataByFavDirector(String fav_director) {

		Set<String> movieByDirector = new TreeSet<>();
		TermQuery queryDirector = new TermQuery(new Term("director", fav_director));
		TopDocs topDocs = searchEngine.performSingleSearch(queryDirector, 100);

		ScoreDoc[] hits = topDocs.scoreDocs;
		for (int i = 0; i < hits.length; i++) {
			try {
				Document doc = searchEngine.getDocument(hits[i].doc);
				movieByDirector.add(doc.get("title"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return movieByDirector;
	}
}

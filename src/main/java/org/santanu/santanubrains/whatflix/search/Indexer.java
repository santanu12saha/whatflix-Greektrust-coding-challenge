package org.santanu.santanubrains.whatflix.search;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.santanu.santanubrains.whatflix.model.Cast;
import org.santanu.santanubrains.whatflix.model.Crew;
import org.santanu.santanubrains.whatflix.model.Movie;
import org.santanu.santanubrains.whatflix.utility.FileConfig;
import org.santanu.santanubrains.whatflix.utility.ReadCsvFiles;

import com.google.gson.Gson;

@Singleton
public class Indexer {

	private IndexWriter indexWriter = null;
	private static final String DIRECTOR = "Director";
	private static Boolean b = false;
	private Gson gson;
	private ReadCsvFiles readCsvfile;

	@Inject
	public Indexer(Gson gson, ReadCsvFiles readCsvfile) {
		super();
		this.gson = gson;
		this.readCsvfile = readCsvfile;
	}

	public IndexWriter getIndexWriter() throws IOException {
		if (indexWriter == null) {
			Directory indexDir = FSDirectory.open(new File(FileConfig.INDEX_PATH).toPath());
			IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
			config.setOpenMode(OpenMode.CREATE);
			indexWriter = new IndexWriter(indexDir, config);
		}
		return indexWriter;
	}

	public void closeIndexWriter() throws IOException {
		if (indexWriter != null) {
			indexWriter.close();
		}
	}

	public void indexMovieData(Movie movie) throws IOException {

		
		IndexWriter writer = getIndexWriter();
		
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(movie.getId()), Field.Store.YES));
		doc.add(new StringField("title", movie.getTitle().toLowerCase(), Field.Store.YES));
		doc.add(new StringField("language", movie.getLanguage().toLowerCase(), Field.Store.YES));
		doc.add(new StringField("voterCount", String.valueOf(movie.getVoteCount()), Field.Store.YES));
		
		String castJson = gson.toJson(movie.getCasts());
		Cast[] castList = gson.fromJson(castJson, Cast[].class);
		for (Cast casts : castList) {
			doc.add(new StringField("Cast"+casts.getName().toLowerCase(), casts.getName().toLowerCase(), Field.Store.YES));
		}
		
		String crewJson = gson.toJson(movie.getCrews());
		Crew[] crewList = gson.fromJson(crewJson, Crew[].class);
		for (Crew crews : crewList) {
			if (crews.getJob().equalsIgnoreCase(DIRECTOR)) {
				doc.add(new StringField("director", crews.getName().toLowerCase(), Field.Store.YES));
				
			}
		}
		writer.addDocument(doc);

	}

	public void rebuildIndexes() throws IOException {

		if (!b) {
			getIndexWriter();

			List<Movie> movies = readCsvfile.buildMovieListFromCsv();
			for (Movie movie : movies) {
				indexMovieData(movie);
			}
			closeIndexWriter();
		}
		b = true;

	}
}

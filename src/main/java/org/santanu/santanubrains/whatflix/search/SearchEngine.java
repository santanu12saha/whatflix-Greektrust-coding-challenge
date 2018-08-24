package org.santanu.santanubrains.whatflix.search;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.santanu.santanubrains.whatflix.utility.FileConfig;

@Singleton
public class SearchEngine {

	private IndexSearcher searcher = null;
	private QueryParser contentGenreParser = null;
	private QueryParser contentkeywordParser = null;
	private QueryParser contentCastParser = null;
	
	
	private Indexer indexer;
	
	@Inject
	public SearchEngine(Indexer indexer) throws IOException {
		this.indexer = indexer;
		this.indexer.rebuildIndexes();
		this.searcher = new IndexSearcher(
				DirectoryReader.open(FSDirectory.open(new File(FileConfig.INDEX_PATH).toPath())));
		this.contentGenreParser = new QueryParser("contentGenre", new StandardAnalyzer());
		this.contentkeywordParser = new QueryParser("contentkeyword", new StandardAnalyzer());
		this.contentCastParser = new QueryParser("contentCast", new StandardAnalyzer());
	}

	public TopDocs performContentGenreSearch(String queryString, int n) throws IOException {
		Query query;
		try {
			query = contentGenreParser.parse(queryString);
			return searcher.search(query, n);
		} catch (org.apache.lucene.queryparser.classic.ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	public TopDocs performContentKeywordParser(String queryString, int n) throws IOException {
		Query query;
		try {
			query = contentkeywordParser.parse(queryString);
			return searcher.search(query, n);
		} catch (org.apache.lucene.queryparser.classic.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public TopDocs performContentCastParser(String queryString, int n) throws IOException {
		Query query;
		try {
			query = contentCastParser.parse(queryString);
			return searcher.search(query, n);
		} catch (org.apache.lucene.queryparser.classic.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TopDocs performSingleSearch(TermQuery query, int n) {
		try {
			return searcher.search(query, n);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TopDocs performMultipleSearch(BooleanQuery booleanQuery, int n) {
		try {
			return searcher.search(booleanQuery, n);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Document getDocument(int docId) throws IOException {
		return searcher.doc(docId);
	}

}

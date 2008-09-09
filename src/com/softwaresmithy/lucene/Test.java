package com.softwaresmithy.lucene;

import java.io.File;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

public class Test {

	/**
	 * @param args
	 */
	static final File INDEX_DIR = new File("index");
	public static void main(String[] args) {
		try{
			IndexWriter writer = new IndexWriter(INDEX_DIR, new StandardAnalyzer(), true);
			
			Document doc = new Document();
			doc.add(new Field("test","value",Field.Store.YES,Field.Index.UN_TOKENIZED));
			writer.addDocument(doc);
			writer.flush();
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

}

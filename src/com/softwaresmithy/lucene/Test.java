package com.softwaresmithy.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;

import com.amazonaws.a2s.AmazonA2S;
import com.amazonaws.a2s.AmazonA2SClient;
import com.amazonaws.a2s.model.ItemLookupRequest;
import com.amazonaws.a2s.model.ItemLookupResponse;
import com.softwaresmithy.acornweb.AcornWebQueryEngine;
import com.softwaresmithy.amazon.AmazonResult;

public class Test {

	/**
	 * @param args
	 */
	//static final File INDEX_DIR = new File("index");
        static final File INDEX_DIR = new File("F:\\Users\\Matt\\BookTracker\\src\\index");
	public static void main(String[] args) {
		IndexWriter writer = null;
		try{
	        /************************************************************************
	         * Access Key ID obtained from:
	         * http://aws.amazon.com
	         ***********************************************************************/
	        String accessKeyId = "1JE5WNWJ29J99WBX06G2";
	        String associateTag = "scumbkt19";
	        
	        /************************************************************************
	         * Instantiate Client Implementation of Amazon A2S 
	         ***********************************************************************/
	        AmazonA2S service = new AmazonA2SClient(accessKeyId, associateTag);

	        boolean stop = false;
	        writer = new IndexWriter(INDEX_DIR, new StandardAnalyzer(), false);
	        
	        AcornWebQueryEngine library = new AcornWebQueryEngine();
	        IndexSearcher searcher = new IndexSearcher(INDEX_DIR.getPath());
	        
	        while(!stop){
	        	System.out.print("query isbn: ");
	        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        	String isbn = br.readLine();
	        	if(isbn==null || isbn.equals("")) break;
		        ItemLookupRequest request = new ItemLookupRequest();
		         java.util.List<String> responseGroup = new java.util.ArrayList<String>();
			        responseGroup.add("Small");
			        responseGroup.add("AlternateVersions");
			        responseGroup.add("Images");
			        responseGroup.add("ItemAttributes");
			        responseGroup.add("Tags");
			        responseGroup.add("Reviews");
			        request.setResponseGroup(responseGroup);
		        
		        java.util.List<String> itemId = new ArrayList<String>();
		        	itemId.add(isbn);
		        request.setItemId(itemId);
                        if(isbn.length()==10){
                            //Standard ISBN
                        }
                        else if(isbn.length()==13){
                            //ISBN-13
                            request.setIdType("EAN");
                            request.setSearchIndex("Books");    
                        }
                        else{
                            //Not a valid number
                            System.out.println("Not a valid ISBN");
                            continue;
                        }
		        ItemLookupResponse response = service.itemLookup(request);
		        
		        if(response.getItems().size()>0){
		        	System.out.println(response.getItems().get(0).getItem().get(0).getItemAttributes().getTitle());
		        	
					AmazonResult result = new AmazonResult(response.getItems().get(0).getItem().get(0));
					String bookId = library.atLibrary(result);
					Document doc = result.getDocument();
					if(bookId != null)
						doc.add(new Field("bookId",bookId,Field.Store.YES,Field.Index.NO));
					if(searcher.search(new TermQuery(new Term("alternateVersion",result.getISBN()))).length()>0 ){
						System.out.println("Exists as AltVersion");
					}else {
						writer.updateDocument(new Term("ISBN",result.getISBN()),doc);
						System.out.println("Successfully added");
					}
		        }
		        else System.out.println("no results");
	        }
		}catch(Exception e){
			e.printStackTrace();

		}finally{
			try{
				writer.optimize();
				writer.flush();
				writer.close();
			}catch(Exception e){}
		}
	}
}

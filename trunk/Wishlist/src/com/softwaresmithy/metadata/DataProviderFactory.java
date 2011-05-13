package com.softwaresmithy.metadata;

import com.softwaresmithy.metadata.impl.GoogleBooks;

public class DataProviderFactory {
	public MetadataProvider getDataProvider(){
		return new GoogleBooks();
	}
}

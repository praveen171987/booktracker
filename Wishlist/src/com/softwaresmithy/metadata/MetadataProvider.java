package com.softwaresmithy.metadata;

import android.content.Context;

import com.softwaresmithy.BookJB;

public interface MetadataProvider {
	public BookJB getInfo(String isbn);
	public boolean saveThumbnail(Context context, String volumeId, String thumbUrl);
}

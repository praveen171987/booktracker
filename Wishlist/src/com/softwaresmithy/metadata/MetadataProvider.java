package com.softwaresmithy.metadata;

import android.content.Context;

import com.softwaresmithy.BookJB;

public interface MetadataProvider {
	BookJB getInfo(String isbn);
	boolean saveThumbnail(Context context, String volumeId, String thumbUrl);
}

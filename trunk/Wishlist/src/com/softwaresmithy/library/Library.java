package com.softwaresmithy.library;

import java.net.URISyntaxException;

public abstract class Library {
	public abstract void init(String...strings);
	public abstract boolean isCompatible(String url) throws URISyntaxException;
}

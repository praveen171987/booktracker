package com.softwaresmithy.library;

public interface LibStatus {
	public enum STATUS {
		NO_MATCH, //Not available at the library
		AVAILABLE, //Copies available
		SHORT_WAIT, //H = #Holds, C = #Copies, H < C
		WAIT, // C <= H <= 2C
		LONG_WAIT // H > 2C
	};
	public STATUS checkAvailability(String isbn);
}
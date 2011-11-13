package com.softwaresmithy.library;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public abstract class AndroidLibStatus extends Library {
    private Context context;

    public enum STATUS {
        NO_MATCH, //Not available at the library
        AVAILABLE, //Copies available
        SHORT_WAIT, //H = #Holds, C = #Copies, H < C
        WAIT, // C <= H <= 2C
        LONG_WAIT // H > 2C
    }

    protected abstract STATUS checkAvailability(String isbn);

    public abstract Uri getStatusPage(String isbn);

    private List<LibStatusListener> fListeners = new ArrayList<LibStatusListener>();

    public void addLibStatusListener(LibStatusListener aNewListener) {
        fListeners.add(aNewListener);
    }

    public void removeLibStatusListener(LibStatusListener aOldListener) {
        fListeners.remove(aOldListener);
    }

    /**
     * Because this is probably making HTTP calls, it must be invoked from a new thread,
     * typically just from the onHandleIntent method of the NotificationService
     *
     * @param isbn isbn number
     */
    public void checkStatus(String isbn) {
        STATUS result = checkAvailability(isbn);
        for (LibStatusListener listener : fListeners) {
            listener.onItemStatusChange(isbn, result);
        }
    }
}

package com.softwaresmithy.preferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.softwaresmithy.R;
import com.softwaresmithy.WishlistDbAdapter;

public class Preferences extends PreferenceActivity implements OnClickListener, OnCancelListener {

	private static final String XML_URL = "http://booktracker.googlecode.com/svn/trunk/libraries.xml";
	private HttpClient client;
	
	private Set<String> distinctStates = new HashSet<String>();
	private Node rootNode;
	private XPath xpath = XPathFactory.newInstance().newXPath();
	private WishlistClickListener listener = new WishlistClickListener();
	
	private String oldValue;
	
	public Preferences() {
		client = new DefaultHttpClient();
		
		new DownloadLibrariesTask(){

			@Override
			protected void onPostExecute(Boolean result) {
				super.onPostExecute(result);
				
				distinctStates = getDistinctStates();
					
				CharSequence[] distinctStatesArray = distinctStates.toArray(new String[]{});
				
				ListPreference stateList = (ListPreference) findPreference(getText(R.string.pref_key_stateName));	
				AutoCompletePreference libraries = (AutoCompletePreference) findPreference(getText(R.string.pref_key_libChoice));

				stateList.setEntries(distinctStatesArray);
				stateList.setEntryValues(distinctStatesArray);
				stateList.setEnabled(true);
				
				rootNode = getRootNode();
				
				String selectedState = stateList.getValue();
				if(selectedState != null){
					libraries.setAdapterContent(getLibraryNames(selectedState));
					libraries.setEnabled(true);

				}
			}
			
		}.execute(client, XML_URL);
		
	}
	@Override
	/* Steps:
	 * 1) Download the libraries.xml file from the interwebs
	 * 2) Parse out the list of unique states, populate the dropdown
	 * 3) When an entry is selected, populate the autopopulate list
	 * 
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.global_prefs);
		
		ListPreference stateList = (ListPreference) findPreference(getText(R.string.pref_key_stateName));	
		final AutoCompletePreference libraries = (AutoCompletePreference) findPreference(getText(R.string.pref_key_libChoice));
		
		stateList.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				libraries.setAdapterContent(getLibraryNames((String)newValue));
				libraries.setEnabled(true);
				return true;
			}
		});
		
		libraries.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				oldValue = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(preference.getKey(), null);
				if(!oldValue.equals(newValue)){
					showDialog(DIALOG_CHANGE_LIBRARY);
				}
				return true;
			}
		});
		
	}
	private List<String> getLibraryNames(String state) {
		try {
			NodeList names = (NodeList) xpath.evaluate("library[state='"+state+"']/name", rootNode, XPathConstants.NODESET);
			return nodeListToUtilList(names);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	private List<String> nodeListToUtilList(NodeList list) {
		List<String> utilList = new ArrayList<String>();
		for(int i=0; i<list.getLength(); i++){
			utilList.add(list.item(i).getTextContent());
		}
		
		return utilList;
	}
	
	private static final int DIALOG_CHANGE_LIBRARY = 0;
	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id){
		case DIALOG_CHANGE_LIBRARY:
			return new AlertDialog.Builder(this)
			.setTitle(R.string.switch_lib_title)
			.setMessage(R.string.switch_lib_message)
			.setNegativeButton(R.string.no, this)
			.setPositiveButton(R.string.yes, this)
			.setOnCancelListener(this)
			.create();
		}
		return super.onCreateDialog(id);
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which != DialogInterface.BUTTON_POSITIVE){
			PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
				.putString(getString(R.string.pref_key_libChoice), oldValue).commit();
			Toast.makeText(this, "Fine, keep it!", Toast.LENGTH_LONG).show();
		}else {
			WishlistDbAdapter db = new WishlistDbAdapter(getApplicationContext()).open();
			db.resetState();
			db.close();
			Toast.makeText(this, "Deleting status info", Toast.LENGTH_LONG).show();
		}
	}
	@Override
	public void onCancel(DialogInterface dialog) {
		PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
			.putString(getString(R.string.pref_key_libChoice), oldValue).commit();
		Toast.makeText(this, "Fine, keep it!", Toast.LENGTH_LONG).show();
	}
}

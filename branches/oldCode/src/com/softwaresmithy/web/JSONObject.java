package com.softwaresmithy.web;

import java.sql.ResultSet;
import java.util.Vector;

public class JSONObject {
	String objName;
	Vector<String> attributes = new Vector<String>();
	
	JSONObject(String newObjName){
		objName = newObjName;
	}
	public void addAttribute(ResultSet rs, String varName){
		String json = "";
		boolean exists = false;
		try{
			json += "'"+varName+"': ["; //Array Start
			while(rs.next()){
				exists = true;
				json += "{"; //Object Start
				int i=0;
				for(i=0;i<rs.getMetaData().getColumnCount()-1;i++){
					json += "'"+rs.getMetaData().getColumnName(i+1)+"'"+" : "+"'"+((rs.getString(i+1)==null)?"":rs.getString(i+1).replace("'", "\\'"))+"',";
				}
				json += "'"+rs.getMetaData().getColumnName(i+1)+"'"+" : "+"'"+((rs.getString(i+1)==null)?"":rs.getString(i+1).replace("'", "\\'"))+"'";
				json += "},"; //Ojbect End
			}
			json = (exists)?json.substring(0,json.length()-1):json;//Subtract last comma
			json += "]"; //Array End
		}catch(Exception e){
			e.printStackTrace();
		}
		attributes.add(json);
	}
	public String getJSONObject(){
		String json = objName + "= {";
		
		for(int i=0;i<attributes.size();i++){
			json += attributes.get(i)+",";
		}
		json = json.substring(0,json.length()-1);
		json += "}";
		return json;
	}
}

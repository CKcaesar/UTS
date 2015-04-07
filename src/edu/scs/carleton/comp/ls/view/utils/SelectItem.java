package edu.scs.carleton.comp.ls.view.utils;

public class SelectItem {

	private Integer key;
	private String value;
	private String skey;
	
	public SelectItem (Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public SelectItem (String value, String skey){
		this.value=value;
		this.skey=skey;
	}

	public final Integer getKey() {
		return key;
	}

	public final String getValue() {
		return value;
	}
	
	public final String getSkey() {
		return skey;
	}
}

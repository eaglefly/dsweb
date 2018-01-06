package org.dsweb.test;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String us ="";
	String pass ="";
	public String getUs() {
		return us;
	}
	public void setUs(String us) {
		this.us = us;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public static void main(String[] args) {
		String s= "index.html";
		System.out.println(s.indexOf(".")==-1);
	} 
	
			
}

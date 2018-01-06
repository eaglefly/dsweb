package org.dsweb.test;

import java.util.ArrayList;
import java.util.List;

import org.dsweb.annotation.GET;
import org.dsweb.annotation.PATH;
import org.dsweb.annotation.POST;
import org.dsweb.core.Resource;
import org.dsweb.test.model.Meinv;

@PATH("/user")
public class UserResource extends Resource{

	@GET("/list")
	public List<String> getUser(){
		List<String> ls = new ArrayList<String>();
		ls.add("a");
		ls.add("b");
		ls.add("c");
		ls.add("d");
		return ls;
	}
	
	@GET("/single")
	public String getSingleUser(){
		Meinv mv = Meinv.dao.getUserInfos();
		System.out.println(mv.get("id"));
		return "test";
	}
	
	@GET("/ul")
	public List<User> getUserList(){
		List<User> ls = new ArrayList<User>();
		User u = new User();
		u.setUs("a");
		u.setPass("1");
		User u1 = new User();
		u1.setUs("a");
		u1.setPass("1");
		User u2 = new User();
		u2.setUs("a");
		u2.setPass("1");
		User u3 = new User();
		u3.setUs("a");
		u3.setPass("1");
		ls.add(u);
		ls.add(u1);
		ls.add(u2);
		ls.add(u3);
		return ls;
	}
	
	@POST("/save")
	public String saveUser(){
		System.out.println("ddd");
		setPara("test","aaa");
		setPara("tt","aaa1");
		setPara("mvlist", Meinv.dao.findAll());
	    return "/index.html";
	}
}

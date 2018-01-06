package org.dsweb.test.model;

import java.util.List;

import org.dsweb.orm.Model;
import org.dsweb.orm.annotation.Table;


@Table(name = "MEINV", primaryKey = {"id"}, cached = false)
public class Meinv extends Model<Meinv> {
	
	public static Meinv dao = new Meinv();
	 
	public Meinv getUserInfos() {
		List<Meinv> ls =  dao.findAll();
		for (Meinv meinv : ls) {
			System.out.println(meinv.get("id"));
			System.out.println(meinv.get("name"));
			System.out.println(meinv.get("xiong"));
		}
		return ls.get(0);
	}
}

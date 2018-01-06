package org.dsweb.filter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) throws Exception {
		
		List<UserEntity> ls = new ArrayList<UserEntity>();
		UserEntity u = new UserEntity();
		u.setAge(1);
		u.setUser("a");
		u.setPass("b");
		ls.add(u);
		Method[] ms = UserRes.class.getDeclaredMethods();
		for (Method method : ms) {
			if (method.getName().equals("getU")) {
				method.invoke(UserRes.class.newInstance(),ls);
			}
		}
	}
}

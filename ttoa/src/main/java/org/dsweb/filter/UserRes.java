package org.dsweb.filter;

import java.util.List;

public class UserRes {

	
	public void getU(List<UserEntity> ls){
		System.out.println("getU");
		if(null != ls){
			for (UserEntity userEntity : ls) {
				System.out.println(userEntity.getAge());
				System.out.println(userEntity.getUser());
			}
		}
	}
}

package org.dsweb.action;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dsweb.annotation.PATH;
import org.dsweb.annotation.GET;
import org.dsweb.annotation.POST;
import org.dsweb.core.Resource;
import org.dsweb.core.Routes;
import org.dsweb.kit.ToolReflect;


public class ActionMapping {
	
	private final Map<String, Action> mapping = new HashMap<String, Action>();
	private Routes routes;
	private static final String SLASH = "/";
	
//	public ActionMapping(Routes routes) {
//		this.routes = routes;
//	}
	public Action getAction(String url, String[] urlPara) {
		Action action = mapping.get(url);
		if (action != null) {
			return action;
		}
		
		// --------
		int i = url.lastIndexOf(SLASH);
		if (i != -1) {
			action = mapping.get(url.substring(0, i));
			urlPara[0] = url.substring(i + 1);
		}
		
		return action;
	}

	public List<String> getAllActionKeys() {
		return null;
	}

	public void buildActionMapping() {
		mapping.clear();
		List<Class<? extends Resource>> controllerClasses = null;
		
		String classpath = this.getClass().getResource("/").getPath();
		List<String> classFileList = findFiles(classpath, "*.class");
	    controllerClasses = extraction(Resource.class, classFileList);
	    for (Class controller : controllerClasses) {
	    	String classPath = "";
	    	PATH ntc = (PATH) controller.getAnnotation(PATH.class); 
	    	if(null != ntc){
	    		classPath = ntc.value();
	    	}
	    	Method[] methods = controller.getDeclaredMethods();  
	        for (Method method : methods) {
	        	String methodPath = "";
	        	
	        	GET ntmget = method.getAnnotation(GET.class);
	        	POST ntmpost = method.getAnnotation(POST.class);
	        	
	        	if(null != ntmget){
	        		methodPath = ntmget.value();
	        		
	        		String tempActionPath = classPath + methodPath;
					Action action = new Action(tempActionPath, tempActionPath, controller, method, method.getName(), tempActionPath);
		        	mapping.put(tempActionPath,action);
	        	}
	        	if(null != ntmpost){
	        		methodPath = ntmpost.value();
	        		System.out.println(classPath + methodPath);
	        		String tempActionPath = classPath + methodPath;
					Action action = new Action(tempActionPath, tempActionPath, controller, method, method.getName(), tempActionPath);
		        	mapping.put(tempActionPath,action);
	        	}
	        }
		}
	    Action action = mapping.get("/");
		if (action != null)
			mapping.put("", action);
	}
	
	private static <T> List<Class<? extends T>> extraction(Class<T> clazz, List<String> classFileList) {
	        List<Class<? extends T>> classList = new ArrayList<Class<? extends T>>();
	        for (String classFile : classFileList) {
	            Class<?> classInFile = ToolReflect.on(classFile).get();
	            if (clazz.isAssignableFrom(classInFile) && clazz != classInFile) {
	                classList.add((Class<? extends T>) classInFile);
	            }
	        }

	        return classList;
	    }
	 private static List<String> findFiles(String baseDirName, String targetFileName) {
	        List<String> classFiles = new ArrayList<String>();
	        String tempName = null;
	        // 判断目录是否存在
	        File baseDir = new File(baseDirName);
	        if (!baseDir.exists() || !baseDir.isDirectory()) {
	            System.out.println("search error：" + baseDirName + "is not a dir！");
	        } else {
	            String[] filelist = baseDir.list();
	            for (int i = 0; i < filelist.length; i++) {
	                File readfile = new File(baseDirName + File.separator + filelist[i]);
	                if (readfile.isDirectory()) {
	                    classFiles.addAll(findFiles(baseDirName + File.separator + filelist[i], targetFileName));
	                } else {
	                    tempName = readfile.getName();
	                    if (wildcardMatch(targetFileName, tempName)) {
	                        String classname;
	                        String tem = readfile.getAbsoluteFile().toString().replaceAll("\\\\", "/");
	                        classname = tem.substring(tem.indexOf("/classes") + "/classes".length() + 1,
	                                tem.indexOf(".class"));
	                        classFiles.add(classname.replaceAll("/", "."));
	                    }
	                }
	            }
	        }
	        return classFiles;
	    }
	 
	 private static boolean wildcardMatch(String pattern, String str) {
	        int patternLength = pattern.length();
	        int strLength = str.length();
	        int strIndex = 0;
	        char ch;
	        for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
	            ch = pattern.charAt(patternIndex);
	            if (ch == '*') {
	                // 通配符星号*表示可以匹配任意多个字符
	                while (strIndex < strLength) {
	                    if (wildcardMatch(pattern.substring(patternIndex + 1), str.substring(strIndex))) {
	                        return true;
	                    }
	                    strIndex++;
	                }
	            } else if (ch == '?') {
	                // 通配符问号?表示匹配任意一个字符
	                strIndex++;
	                if (strIndex > strLength) {
	                    // 表示str中已经没有字符匹配?了。
	                    return false;
	                }
	            } else {
	                if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
	                    return false;
	                }
	                strIndex++;
	            }
	        }
	        return strIndex == strLength;
	    }
}

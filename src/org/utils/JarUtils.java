/*******************************************************************************
 * Copyright (C) 2017 g04tb0y
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class JarUtils {
	
	/*
     * Add a jar in class path. Remember that adding untrusted JAR 
     * could lead the program to execute malicious code.
     */
    public static synchronized void loadJAR(File fjar) {
        
    	if (fjar.length() != 0){

    		try {
    			URLClassLoader loader = (URLClassLoader)ClassLoader.getSystemClassLoader();
    			URL url = fjar.toURI().toURL();
    		
    			for (java.net.URL it : Arrays.asList(loader.getURLs())){ //check if it's already loaded
    				if (it.equals(url)){
    					return;
    				}
    			}
    			Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{java.net.URL.class});
    			method.setAccessible(true);
    			method.invoke(loader, new Object[]{url});
    		} catch (final java.lang.NoSuchMethodException | 
    				java.lang.IllegalAccessException | 
    				java.net.MalformedURLException | 
    				java.lang.reflect.InvocationTargetException e){
    			e.printStackTrace();
    		}
    	}
    	else {
    		System.out.println("[ERROR] Invalid JAR file!");
    	}
    }

}

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
package org.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.*;
public class CLI {

	Object obj;
	private int recursion = 0;
	
	public CLI(Object obj){
		
		this.obj = obj;
		
	}

	public void showOptions() {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String opt = null;
        
        System.out.println("\n"
				 		 + "|###########################################");
        System.out.println("|Object class: "+this.obj.getClass());
        System.out.println("|[+]Choose an option:\n"
				 		 + "|Methods - show alla methods;\n"
				 		 + "|Value - show value if native object type;\n"
				 		 + "|Q - to quit;\n"
				 		 + "|-------------------------------------------");
        
        try {
			while ((opt = br.readLine()) != null && !opt.equalsIgnoreCase("q"))
			{
				System.out.println("\n"
								 + "|###########################################");
				System.out.println("|Object class: "+this.obj.getClass());
				System.out.println("|[+]Choose an option:\n"
								 + "|Methods - show alla methods;\n"
								 + "|Value - show value of any native object type;\n"
								 + "|Q - to quit;\n"
								 + "|-------------------------------------------");
				
			  
				if (opt.equalsIgnoreCase("methods")) showMethods(); 
				else if (opt.equalsIgnoreCase("value")){ 
					System.out.println("\n"
							+ "|###########################################");
					
					System.out.println("|"+this.obj.getClass());
					recursion = 0;
					showValue(this.obj, null, null);
				}
			    
				
				
			}
		} catch (IOException e) {
			
			System.out.println("Error while reading from stdin buffer");
			e.printStackTrace();
			
		}
		
        
		System.out.println("Bye!");
	}
	
	private void showValue(Object mistObj, String fname, Class<?> classz) {
		
		
		
		if (mistObj instanceof ArrayList<?>){
			ArrayList<?> coolList = (ArrayList<?>) mistObj;
			for (int i = 0; i < coolList.size(); i++){
				showValue(coolList.get(i), null, null);
			}
			
		}
		
		else if (	mistObj instanceof List   ||
				mistObj instanceof Integer || 
				mistObj instanceof Double || 
				mistObj instanceof Float  ||
				mistObj instanceof String ||
				mistObj instanceof Long ||
				mistObj instanceof Boolean){
			
			System.out.print("│");
			for(int i = 0; i <= recursion; i++){
				System.out.print(" ");
			}
			System.out.println("└─>"+fname+" = "+ mistObj);
		}
		else if (mistObj instanceof Byte[]){
			System.out.print("│");
			for(int i = 0; i <= recursion; i++){
				System.out.print(" ");
			}
			System.out.print("└─>"+fname+" = ");
			byte xbyte[] = (byte[]) mistObj;
			for (int i = 0; i<xbyte.length; i++){
				System.out.printf("0x%02X", xbyte[i]);
			}
		}
		
		else {
			recursion++;
			for (Field field : mistObj.getClass().getDeclaredFields()) {
			    field.setAccessible(true); 
			    Object memObj = null;
				try {
					memObj = field.get(mistObj);
					if (memObj != null) {
						System.out.print("├");
						for(int i = 0; i <= recursion; i++){
							System.out.print("─");
						}
						System.out.println(memObj.getClass());
				        showValue(memObj, field.getName(), field.getType());
				    }
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				} 
			}
			recursion--;
			
		}
		
	}
	


	private void showMethods(){
		
		String opt = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Method[] methods = this.obj.getClass().getMethods();
		System.out.println("\n"
						 + "|###########################################");
		System.out.println("|Object's metheds are:");
		for (int i = 0; i < methods.length; i++){
			System.out.println("|- "+i+") "+methods[i].toString());
		}
		System.out.println("\n|[+]Choose an option:\n"
						 + "|invoke n - invoke the 'n' method;\n"
						 + "|b        - back;\n"
						 + "|--------------------------------------------");
		
		try {
			while ((opt = br.readLine()) != null && !opt.equalsIgnoreCase("b")){
				
				System.out.println("\n"
						 + "|###########################################");
		System.out.println("|Object's metheds are:");
		for (int i = 0; i < methods.length; i++){
			System.out.println("|- "+i+") "+methods[i].toString());
			//System.out.println("Object class is: "+ methods[i].invoke(mistObj, word));
		}
		System.out.println("\n|[+]Choose an option:\n"
						 + "|invoke n - invoke the 'n' method;\n"
						 + "|b        - back;\n"
						 + "|--------------------------------------------");
				
				if (opt.toLowerCase().contains("invoke")){
					
					String[] splitted = opt.split(" ");
					int nm = Integer.parseInt(splitted[splitted.length-1]);
					Object result = null;

					if (methods.length > nm){

						System.out.println("Invoke method: "+methods[nm].toString());

						Type[] paramList = methods[nm].getGenericParameterTypes();

						System.out.println("Argument type: "+Arrays.toString(paramList));
						System.out.println("Return type: "+methods[nm].getReturnType());

						if (paramList.length == 0 && !methods[nm].getReturnType().equals(Void.TYPE)){

							try {
								result = methods[nm].invoke(this.obj);
								System.out.println("Result is: "+result);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}

						}
					}
				}
				
			}
		} catch (IOException e) {
			
			System.out.println("Error while reading from stdin buffer");
			e.printStackTrace();
			
		}
	}
}

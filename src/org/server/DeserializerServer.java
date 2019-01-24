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
/*
 * Add this java option to use only IPv4:
 * -Djava.net.preferIPv4Stack=true
 */
package org.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.CLI.CLI;
import org.UI.UI;
import org.utils.JarUtils;

public class DeserializerServer {

	public static void main(String[] args) throws UnknownHostException {
		ServerSocket serverSocket = null;
		Socket socket = null;
		ObjectInputStream inStream = null;
		InetAddress addr = InetAddress.getByName("0.0.0.0");
		Object mistObj = null;
		boolean procede = false;
		
		
		try {
			System.out.println("\n###########################################");
			System.out.println("Starting deserializer server...");
			serverSocket = new ServerSocket(6666, 0, addr);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		while (!procede) {
			try {
				System.out.println("Waiting for serialized object on...");
				socket = serverSocket.accept();
				inStream = new ObjectInputStream(socket.getInputStream());

				// Deserialize and get the object
				mistObj = inStream.readObject();
				System.out.println("Object received!");
				procede = true;
				socket.close();


			} catch (IOException  e) {

				e.printStackTrace();
			} catch ( ClassNotFoundException e) {

				System.out.println("The object class is not in the class path.");
				e.printStackTrace();
				
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				// Load a the missing jar library in the class path
				System.out.println("[!]The order in which jars are loaded is important. Start from the most nested.[!]");
				File fjar = UI.ChooseFile();
				JarUtils.loadJAR(fjar);

			}
		}
		
		CLI cli = new CLI(mistObj);
		cli.showOptions();
	}

}

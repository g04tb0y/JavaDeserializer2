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
package org.UI;

import java.io.File;

import javax.swing.JFileChooser;

public class UI {
	
	public static File ChooseFile(){
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
	
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
		    file = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + file.getAbsolutePath());
		}

		return file;
	}
}

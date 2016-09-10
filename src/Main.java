/* 
 * Date: September 10th, 2016
 * Author: Marcos Alves
**/

import java.util.*;
import java.io.*;


public class Main {
	public static void main(String args[]){
		try {
			Scanner sc = new Scanner(new FileReader(args[0]));

		} catch(FileNotFoundException e){
			throw new RuntimeException("File doesn't exist: "+ e);
		} 
	}
}
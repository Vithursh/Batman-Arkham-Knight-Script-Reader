import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

public class Parsing {
	
	/*
	 * The Parsing class extracts dialogue spoken by different characters from the script file of 
	 * "Batman Arkham Knight" game. It uses HashMap objects to store the dialogue for each character. 
	 * The constructor reads the file, identifies the character speaking in each line, and adds the 
	 * corresponding dialogue to the respective HashMap. The class enables easy access to character 
	 * dialogue for further processing or analysis.
	 */
	
	public static HashMap<String, String> getBatman = new HashMap<>();
	public static HashMap<String, String> getJason = new HashMap<>();
	public static HashMap<String, String> getGordon= new HashMap<>();
	public static HashMap<String, String> getJoker = new HashMap<>();
	public static HashMap<String, String> getAlfred = new HashMap<>();
	
	public static String character1 = "Batman:";
	public static String character2 = "Jason:";
	public static String character3 = "Gordon:";
	public static String character4 = "Joker:";
	public static String character5 = "Alfred:";

	public Parsing() throws IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader("Batman Arkham Knight Script.txt"))) {
               while ((FileImport.line = br.readLine()) != null) {            	
                if (FileImport.line.contains(character1)) {
                    String something1 = character1 + " " + FileImport.line.replace(character1, "").trim();
                    getBatman.put(character1, something1);
                    for (Map.Entry<String, String> entry : getBatman.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
//                        System.out.println("character talking: " + key + ", What character1 is saying: " + value);
                    }
                }
                else if (FileImport.line.contains(character2)) {
                	String something2 = character2 + " " + FileImport.line.replace(character2, "").trim();
                	getJason.put(character2, something2);
                    for (Map.Entry<String, String> entry : getJason.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
//                        System.out.println("character talking: " + key + ", What character1 is saying: " + value);
                    }
                }
                else if (FileImport.line.contains(character3)) {
                	String something3 = character3 + " " + FileImport.line.replace(character3, "").trim();
                	getGordon.put(character3, something3);
                    for (Map.Entry<String, String> entry : getGordon.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
//                        System.out.println("character talking: " + key + ", What character1 is saying: " + value);
                    }
                }
                else if (FileImport.line.contains(character4)) {
                	String something4 = character4 + " " + FileImport.line.replace(character4, "").trim();
                	getJoker.put(character4, something4);
                    for (Map.Entry<String, String> entry : getJoker.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
//                        System.out.println("character talking: " + key + ", What character1 is saying: " + value);
                    }
                }
                else if (FileImport.line.contains(character5)) {
                	String something5 = character5 + " " + FileImport.line.replace(character5, "").trim();
                	getAlfred.put(character5, something5);
                    for (Map.Entry<String, String> entry : getAlfred.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
//                        System.out.println("character talking: " + key + ", What character1 is saying: " + value);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Batman Arkham Knight Script.txt not found");
            e.printStackTrace();
        }
    }
}
package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javafx.scene.control.TextArea;

public class WordCounter {
	private File file;
	private BufferedReader br;
	private BufferedWriter bw;
	private HashMap<String,Integer> map;
	private int nWords;
	private String resultText;
	
	
	public WordCounter(File file){
		this.file=file;
		map=new HashMap<String,Integer>();
		nWords=0;
	}
	public void readWords(TextArea uiTextArea,TextArea uiTextArea2){
		uiTextArea.setText(readFile());
		try {
			br=new BufferedReader(new FileReader(file));
			String s="";
			while((s=br.readLine())!=null){
				s=s.toLowerCase();
				String[] array=s.split("[^a-zA-Z’&&[^']]");
				for(String a:array){
					if(!a.equals("")){
						if(map.containsKey(a)) map.put(a,map.get(a)+1);
						else{
							map.put(a,1);
							nWords++;
						}
					}	
				}
			}
			uiTextArea2.setText(writeResult());
			br.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String writeResult(){
		StringBuilder stringBuffer=new StringBuilder();
		stringBuffer.append("Word Frequency Counter "+file.getName()+" Unique Word Count: "+nWords+System.getProperty("line.separator"));
		for(String key:map.keySet()){
			stringBuffer.append(key+": "+map.get(key)+System.getProperty("line.separator"));
		}
		resultText=stringBuffer.toString();
		return resultText;
	}
	private String readFile(){
        StringBuilder stringBuffer = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(file));
            String text;
            while ((text = br.readLine()) != null) {
                stringBuffer.append(text);
            }
        } catch (FileNotFoundException ex) {
        	ex.printStackTrace();
        } catch (IOException ex) {
        	ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
            	ex.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
	public void writeFile(File file){
		try {
			bw=new BufferedWriter(new FileWriter(file));
			bw.write(resultText);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String findWordCount(String key){
		if(map.containsKey(key)){
			return map.get(key).toString();
		}
		else return "0";
	}
	
	
	public String getFileName(){
		return file.getName();
	}
	public int getnWords(){
		return this.nWords;
	}
}

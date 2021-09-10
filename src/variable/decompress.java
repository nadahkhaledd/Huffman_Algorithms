package variable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class decompress {
    static String data = null;
    static HashMap<String, String> map = new HashMap<String, String>();

    public static String read() throws IOException {
        data = new String(Files.readAllBytes(Paths.get("variableOutput.txt")));
        return data;
    }

    public static void readtable() throws IOException {
        BufferedReader line;
        String[] kv = null;
        line = new BufferedReader(new FileReader("variableTable.txt"));
        String str = line.readLine();
        while (str != null) {
            kv = str.split(" ");
            map.put(kv[0], kv[1]);
            str = line.readLine();
        }
        line.close();
        //return kv[1].length();
    }

    public static void write(String add) throws IOException
    {
        FileWriter out = new FileWriter("variableDecompress.txt");
        out.write(add);
        out.flush();
        out.close();
    }

    public static String de() throws IOException {
        String check = "";
        String add = "";
        String a="";
        check=read();
        for(int i = 0; i < read().length(); i++)
        {
        	a+=check.charAt(i);
        	for(String s : map.keySet())
        	{
        		if(a.equals(map.get(s)))
        		{
        			add+=s;
        			a="";
        		}
        		
        			
        	}
        	
        	
        }
       
        return add;
    }

    public static void main(String[] args) throws IOException {
        readtable();
        write(de());
        System.out.println("\nDone\n");  
    }



}

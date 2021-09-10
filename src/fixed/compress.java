package fixed;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class compress {
	static HashMap<Character, String> count = new HashMap<Character, String>();
	static String data = "";
	static char[] split = null;
	static String bin = "";

	public static String read() throws IOException {
		data = new String(Files.readAllBytes(Paths.get("input.txt")));
		return data;
	}

	public static void write() throws IOException {
		FileWriter out = new FileWriter("output.txt");
		FileWriter table = new FileWriter("table.txt");
		for (char i : split)
			out.write(count.get(i));	
		for(char c : count.keySet())
			table.write(c + " " +  count.get(c) + "\n");
		out.flush();
		table.flush();
		out.close();
		table.close();
	}

	public static void generate(String data,  int bits) {

			split = data.toCharArray();
			int x = 0;
			for (char ii : split) {	
				bin = Integer.toBinaryString(x);	
				if (count.containsKey(ii))
					continue;
				else
					count.put(ii, bin);
				x++;
			}
			
			for(char j : count.keySet())
			{
				while(count.get(j).length()< bits)
					count.replace(j, "0" + count.get(j));
			}

	}

	public static void main(String[] args) throws IOException {
		
		long num = read().chars().distinct().count();
		int bitnum = (int) Math.ceil((Math.log((int)num) / Math.log(2)));
		generate(read(),  bitnum);
		write();
		System.out.println("\nDone\n");
	}

}

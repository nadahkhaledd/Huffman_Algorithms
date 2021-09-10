package variable;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;




public class compress {
	
	float num;
	char ch;
	compress Hright;
	compress Hleft;
	
	static HashMap<Character, String> finall = new HashMap<Character, String>();
	static HashMap<Character, Float> count = new HashMap<Character, Float>();
	static String data = "";
	static char[] split = null;

	public static String read() throws IOException {
		data = new String(Files.readAllBytes(Paths.get("input.txt")));
		return data;
	}
	
	public static void calcProb() {
		split = data.toCharArray();
		for (char c : split) {
			if (count.containsKey(c))
				count.replace(c, count.get(c) + 1);
			else
				count.put(c, (float)1);
		}
		for (char cc : count.keySet()) {
			count.replace(cc, count.get(cc) / data.length());
		}
	}
	
    public static HashMap<Character, Float> sort(HashMap<Character, Float> hm) 
    { 
		List<Map.Entry<Character, Float> > list = new LinkedList<Map.Entry<Character, Float>>(hm.entrySet()); 
	    Collections.sort(list, new Comparator<Map.Entry<Character, Float> >() 
	    { 
	            public int compare(Map.Entry<Character, Float> o1,  
	                               Map.Entry<Character, Float> o2) 
	            { 
	                return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	     }
	    );
	        HashMap<Character, Float> temp = new LinkedHashMap<Character, Float>(); 
	        for (Map.Entry<Character, Float> aa : list) 
	        { 
	            temp.put(aa.getKey(), aa.getValue()); 
	        } 
	        return temp;
	       
	    } 
	
    public static void code(compress root, String s) throws IOException 
    { 
        if (root.Hleft == null && root.Hright == null) { 
        	finall.put(root.ch, s);
            return; 
        } 
        code(root.Hleft, s + "0"); 
        code(root.Hright, s + "1"); 
    } 
    
    public static void write() throws IOException {
    	FileWriter out = new FileWriter("variableOutput.txt");
		FileWriter table = new FileWriter("variableTable.txt");
		for (char i : split)
			out.write(finall.get(i));	
		for(char c : finall.keySet())
			table.write(c + " " +  finall.get(c) + "\n");
		out.flush();
		table.flush();
		out.close();
		table.close();
		System.out.println("Done");
	}
    
    
    public static PriorityQueue<compress> sortq(PriorityQueue<compress> q)
    {
    	int s=q.size();
    	compress temp;
    	compress[] c=new compress[s];
    	for(int i=0;i<s;i++)
    	{
    		c[i]=q.remove();
    	}
    	for(int i=0;i<s;i++)
    	{
    		for(int j=1;j<s;j++)
    		{
    			if(c[i].num>c[j].num)
    			{
    				temp=c[i];
    				c[i]=c[j];
    				c[j]=temp;
    			}
    		}
    	}
    	q.clear();
    	for(int i=0;i<s;i++)
    	{
    		q.add(c[i]);
    	
    	}
    	
    	return q;
    }

	public static void main(String[] args) throws IOException {
		read();
		calcProb(); 
		count = sort(count);
		PriorityQueue<compress> q = new PriorityQueue<>(count.size(),new MyComparator());
		compress root = null;
		for(Map.Entry<Character, Float> e : count.entrySet())
		{
			compress obj = new compress();
			obj.ch = e.getKey();
			obj.num = e.getValue();
			obj.Hleft = null;
			obj.Hright = null;
			q.add(obj);
		}
		
		 

		while(q.size()>1)
		{
			compress x = q.peek();
            q.poll(); 
            q=sortq(q);
            compress y = q.peek();
            q.poll(); 
            compress newnode = new compress();
            newnode.num = x.num + y.num;
            newnode.ch = '-'; 
            newnode.Hleft = x;
            newnode.Hright = y;
            root = newnode;
            q.add(newnode);          
		}
		 code(root,"");
		 write();
	}

	@Override
	public String toString() {
		return "compress [num=" + num + ", ch=" + ch + "]";
	}

}
class MyComparator implements Comparator<compress> { 
    public int compare(compress x, compress y) 
    { 
  
        return (int) (x.num - y.num); 
    } 
} 

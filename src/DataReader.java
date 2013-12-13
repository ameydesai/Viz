package parse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class DataReader {

	String filename;
	HashMap<String, TreeNode> allNodes;
	int[][] adjMat = new int[43][43];
	
	DataReader(String fn)
	{
		this.filename = fn;
	}
	
	public void printMap()
	{
		for(Map.Entry<String, TreeNode> m : allNodes.entrySet())
		{
			System.out.print(m.getKey());
			m.getValue().printChildren();
		}
	}
	
	public void parseData()
	{
		BufferedReader br = null;
		String curLine;
		String[] pcr = new String[3];
		String[] nodes = new String[2];
		String prev  = "";
		String nodeKey = "";
		String childKey = "";
		TreeNode t = null;
		TreeNode c = null;
		this.allNodes = new HashMap<String, TreeNode>();
		
		try {
			br = new BufferedReader(new FileReader(this.filename));
			while( (curLine=br.readLine()) != null)
			{
				curLine = curLine.trim();
				if (curLine.length() == 0)
					continue;
				if (curLine.contains("H"))
				{
					nodes = curLine.split(" ");
					
					nodeKey = nodes[1].replaceAll("^\"|\"$", "");
					t = new TreeNode(nodeKey);
					allNodes.put(nodeKey, t);
					
				}
				
				if (curLine.contains("PCR"))
				{
					pcr = curLine.split(" ");	
					nodeKey = pcr[1].replaceAll("^\"|\"$", "");
					childKey = pcr[2].replaceAll("^\"|\"$", "");
					if(prev != nodeKey)
					{		
						prev = nodeKey;
						t = allNodes.get(nodeKey);
					}
					c = allNodes.get(childKey);
					t.setChildren(c);
					c.setParent(t);
				}
				
				if(curLine.charAt(1)== 'R')
				{
					
					pcr = curLine.split(" ");
					nodeKey = pcr[1].replaceAll("^\"|\"$", "");
					childKey = pcr[2].replaceAll("^\"|\"$", "");
					adjMat[Integer.parseInt(nodeKey)][Integer.parseInt(childKey)] = 1;
					adjMat[Integer.parseInt(childKey)][Integer.parseInt(nodeKey)] = 1;
				}

			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataReader r = new DataReader("/root/School/Viz/EdgeBundle-master/bin/Data/Graphs/art.rsf");
		r.parseData();
	}

}

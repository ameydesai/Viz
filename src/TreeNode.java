package parse;

import java.util.ArrayList;

public class TreeNode {
	
	String key;
	ArrayList<TreeNode> children= null;
	TreeNode parent = null;
	
	TreeNode(String key)
	{
		this.key = key;
	}
	
	public void setChildren(TreeNode t)
	{
		if (this.children == null)
			this.children = new ArrayList<TreeNode>();	
		
		this.children.add(t);
		
	}
	
	public ArrayList<TreeNode> getChildren()
	{
		return children;
	}
	
	public void printChildren()
	{
		for(TreeNode t: children)
		{
			System.out.println("Node-Key: " + t.key);
		}
	}
	
	public int numberOfChildren()
	{
		return children.size();
	}
	
	public void setParent(TreeNode t)
	{
		this.parent = t;
	}
	
	public TreeNode getParent()
	{
		return this.parent;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

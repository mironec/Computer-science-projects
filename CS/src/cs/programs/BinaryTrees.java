package cs.programs;

import cs.CSProgram;

public class BinaryTrees extends CSProgram{
	
	private class BinaryTree<T extends Comparable<T>>{
		private Node root;
		
		public BinaryTree(){
			
		}
		
		public void add(T val){
			if(root==null) root=new Node(val);
			else root.add(val);
		}
		
		public boolean contains(T val){
			return true;
		}
		
		private class Node implements Comparable<Node>{
			private T val;
			private Node lesser;
			private Node greater;
			
			public Node(T val){
				this.val = val;
			}
			
			public void add(T val){
				add(new Node(val));
			}
			
			public void add(Node nod){
				Node whereToAdd;
				if(this.compareTo(nod)>=0)
					whereToAdd=greater;
				else
					whereToAdd=lesser;
				if(whereToAdd==null) whereToAdd=nod;
				else whereToAdd.add(nod);
			}
			
			@Override
			public int compareTo(Node nod) {
				return val.compareTo(nod.val);
			}
		}
	}
	
	public BinaryTrees(int id) {
		super("binaryTrees",id);
	}
	
	public void start(){
		BinaryTree<Integer> BT = new BinaryTree<Integer>();
		BT.add(1);BT.add(2);BT.add(3);BT.add(4);BT.add(5);
		for(int x=0;x<51;x++){
			System.out.println(x+": "+BT.contains(x));
		}
	}
	
	public void help(){}
}
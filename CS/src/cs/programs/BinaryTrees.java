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
		
		public int lvlSize(int lvl){
			return root==null?0:root.lvlSize(lvl);
		}
		
		public boolean contains(T val){
			return root==null?false:root.contains(val);
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
				if(this.compareTo(nod)>=0){
					if(greater==null) greater=nod; else greater.add(nod);
				}
				else{
					if(lesser==null) lesser=nod; else lesser.add(nod);;
				}
			}
			
			public int lvlSize(int lvl){
				if(lvl==0) return 1;
				else{
					return (lesser==null?0:lesser.lvlSize(lvl-1)) + (greater==null?0:greater.lvlSize(lvl-1));
				}
			}
			
			public boolean contains(T val){
				if(this.compareTo(val)>0) return greater==null?false:greater.contains(val);
				if(this.compareTo(val)<0) return lesser==null?false:lesser.contains(val);
				if(val.equals(this.val)) return true;
				return false;
			}
			
			public int compareTo(Node nod) {
				return val.compareTo(nod.val);
			}
			
			public int compareTo(T val) {
				return this.val.compareTo(val);
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
		for(int x=0;true;x++){
			int lvl = BT.lvlSize(x);
			System.out.println(x+" lvl: "+lvl);
			if(lvl==0) break;
		}
	}
	
	public void help(){}
}
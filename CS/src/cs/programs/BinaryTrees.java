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
		
		public T findMax(){
			if(root==null) return null;
			return root.findMax();
		}
		
		public void dislay(int spaceWidth){
			int space = maxWidth()*spaceWidth;
			for(int x=0;true;x++){
				if(displayLvl(x,space,spaceWidth)==0) break;
			}
		}
		
		private int displayLvl(int lvl,int space,int spaceWidth){
			int s = lvlWidth(lvl);
			int mySpace = (int)Math.pow(2, lvl)*spaceWidth;
			for(int x=0;x<(space-mySpace)/2;x++){
				System.out.print(" ");
			}
			for(int x=0;x<mySpace;x++){
				Node nod = root.get(lvl,x);
				if(nod==null) for(int t=0;t<spaceWidth;t++) System.out.print(" ");
				nod.display(spaceWidth);
			}
			for(int x=0;x<(space-mySpace)/2;x++){
				System.out.print(" ");
			}
			return s;
		}
		
		private int maxWidth(){
			int max = 0;
			for(int x=0;true;x++){
				int s = lvlWidth(x);
				if(s>max) max = s;
				if(s==0) break;
			}
			return max;
		}
		
		private int lvlWidth(int lvl){
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
			
			public Node get(int lvl, int num){
				//if(num>=)
				return null;
			}
			
			public void display(int spaceWidth){
				
			}
			
			public void add(Node nod){
				if(this.compareTo(nod)>=0){
					if(greater==null) greater=nod; else greater.add(nod);
				}
				else{
					if(lesser==null) lesser=nod; else lesser.add(nod);;
				}
			}
			
			public T findMax(){
				if(greater==null) return this.val;
				else return greater.findMax();
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
			int lvl = BT.lvlWidth(x);
			System.out.println(x+" lvl: "+lvl);
			if(lvl==0) break;
		}
	}
	
	public void help(){}
}
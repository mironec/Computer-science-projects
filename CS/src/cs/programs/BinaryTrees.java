package cs.programs;

import java.util.ArrayList;
import java.util.Stack;

import cs.CSProgram;

public class BinaryTrees extends CSProgram{
	
	private class Stack<T>{
		Node first;
		
		public void push(T val){
			Node nod = new Node(val);
			nod.setNext(first);
			first = nod;
		}
		
		public T pop(){
			T val = first.getValue();
			first = first.getNext();
			return val;
		}
		
		public boolean empty(){
			return first==null;
		}
		
		public T peek(){
			return first.getValue();
		}
		
		private class Node{
			private Node next;
			private T val;
			
			public Node(T val){
				this.val = val;
			}
			
			public void setNext(Node n){
				next = n;
			}
			
			public Node getNext(){
				return next;
			}
			
			public T getValue(){
				return val;
			}
		}
	}
	
	private class BinaryTree<T extends Comparable<T>>{
		private Node root;
		
		public BinaryTree(){
			
		}
		
		public void stackDisplay(){
			Stack<Node> s = new Stack<>();
			s.push(root);
			int x=0;
			int d=0;
			while(!s.empty()){
				Node n = s.pop();
				if(n!=null) s.push(n.lesser);
				if(n!=null) s.push(n.greater);
				System.out.print((n==null?0:n.val) + " ");
				x++;
			}
		}
		
		public int depth(){
			if(root==null) return 0;
			return root.depth();
		}
		
		public int sizeRecursive(){
			if(root==null) return 0;
			return 1+root.size();
		}
		
		public int numOfLeaves(){
			if(root==null)
				return 0;
			return root.numOfLeaves();
		}
		
		public int sizeRecursiveTree(){return sizeRecursiveTree(root);}
		
		private int sizeRecursiveTree(Node myRoot){
			if(myRoot==null) return 0;
			return 1+sizeRecursiveTree(myRoot.getLesser())+sizeRecursiveTree(myRoot.getGreater());
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
			
			public int depth(){
				int l,r;
				if(lesser==null)
					l = 0;
				else
					l = lesser.depth();
				if(greater==null)
					r = 0;
				else
					r = greater.depth();
				return (l>r?l:r)+1;
			}
			
			public int numOfLeaves(){
				return lesser==null?(greater==null?1:greater.numOfLeaves()):(greater==null?lesser.numOfLeaves():lesser.numOfLeaves()+greater.numOfLeaves());
			}
			
			public Node getLesser(){
				return lesser;
			}
			
			public Node getGreater(){
				return greater;
			}
			
			public int size(){
				return (lesser==null?0:1+lesser.size())+(greater==null?0:1+greater.size());
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
		//BT.add(10);BT.add(8);BT.add(12);BT.add(7);BT.add(9);BT.add(11);BT.add(13);
		BT.add(2);BT.add(3);BT.add(4);BT.add(5);BT.add(6);BT.add(1);
		/*for(int x=0;x<51;x++){
			System.out.println(x+": "+BT.contains(x));
		}
		for(int x=0;true;x++){
			int lvl = BT.lvlWidth(x);
			System.out.println(x+" lvl: "+lvl);
			if(lvl==0) break;
		}
		//System.out.println(BT.depth());*/
		BT.stackDisplay();
	}
	
	public void help(){}
}
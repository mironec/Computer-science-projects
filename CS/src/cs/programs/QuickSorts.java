package cs.programs;

import java.util.Arrays;

import cs.CSProgram;

public class QuickSorts extends CSProgram{
	
	public QuickSorts(int id) {
		super("quickSorts",id);
	}
	
	private class Stack{
		private class Node{
			int value;
			Node next;
			
			public Node(int value){
				this.value = value;
				next = null;
			}
			
			public void setNext(Node next){
				this.next = next;
			}
			
			public Node getNext(){
				return next;
			}
		}
		
		Node top;
		
		public Stack(){
			top = null;
		}
		
		public void push(int value){
			push(new Node(value));
		}
		
		private void push(Node n){
			n.setNext(top);
			top=n;
		}
		
		public int pop(){
			if(top==null) return -1;
			int r = top.value;
			top = top.getNext();
			return r;
		}
		
		public boolean empty(){
			return top==null;
		}
	}
	
	public void qckSortStack(int[] a){
		Stack st = new Stack();
		st.push(0); st.push(a.length-1);
		while(!st.empty()){
			int e = st.pop();
			int s = st.pop();
			if(s>=e){continue;}
			int l = s;
			int r = e-1;
			int temp;
			int piv=a[e];
			
			while(l<r){
				if(a[l]>piv){
					temp=a[r];
					a[r]=a[l];
					a[l]=temp;
					r--;
				}
				else l++;
			}
			
			if(a[l]>piv){
				a[e]=a[l];
				a[l]=piv;
			}
			else if(a[l]<=piv){
				a[e]=a[l+1];
				a[l+1]=piv;
			}
			
			st.push(l+1); st.push(e);
			st.push(s); st.push(l);
		}
	}
	
	private int doPivot(int s, int e, int p, int a[]){
		if(s>=e) return 0;
		int l = s;
		int r = e;
		int temp;
		int piv=a[p];
		temp=a[e];
		a[e]=a[p];
		a[p]=temp;
		r--;
		
		while(l<r){
			if(a[l]>piv){
				temp=a[r];
				a[r]=a[l];
				a[l]=temp;
				r--;
			}
			else l++;
		}
		if(a[l]>piv){
			a[e]=a[l];
			a[l]=piv;
		}
		else if(a[l]<=piv){
			a[e]=a[l+1];
			a[l+1]=piv;
		}
		
		doPivot(s, l, l, a);
		doPivot(l+1, e, e, a);
		
		return r;
	}
	
	public void qckSort(int a[], int len){
		doPivot(0,len-1,len-1,a);
	}
	
	public void start(){
		int a[] = new int[10];
		int b[] = new int[10];
		for(int i=0;i<10;i++){
			b[i] = a[i] = (int) (Math.random()*10);
		}
		System.out.println(Arrays.toString(a));
		qckSort(a,a.length);
		System.out.println(Arrays.toString(a));
		qckSortStack(b);
		System.out.println(Arrays.toString(b));
	}
	
	public void help(){}
}
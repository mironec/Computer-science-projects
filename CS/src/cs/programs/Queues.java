package cs.programs;

import java.util.AbstractList;
import java.util.ArrayList;

import cs.CSProgram;

public class Queues extends CSProgram{
	
	public Queues(int id) {
		super("queues",id);
	}
	
	private class StaticQueue<E> {
		private ArrayList<E> k;
		private int head;
		private int tail;
		private int size;
		private int realSize;
		
		public StaticQueue(int cap){
			head = 0;
			tail = -1;
			size = cap;
			realSize = 0;
			k = new ArrayList<E>(size);
			for(int i=0;i<cap;i++){
				k.add(null);
			}
		}
		
		/*public StaticQueue(){
			this(1);
		}*/
		
		public void add(E data){
			if(realSize>=size){return;}
			realSize++;
			tail++;
			if(tail>=size){tail=0;}
			k.set(tail, data);
		}
		
		public E pop(){
			if(realSize==0) return null;
			realSize--;
			E u = k.get(head);
			head++;
			if(head>=size){head=0;}
			return u;
		}
	}
	
	private class Queue<E> extends AbstractList<E> {

		private class Node {
			private E data;
			private Node next;
			
			public Node(E data){
				this.data=data;
			}
			
			public Node next(){
				return next;
			}
			
			public void setNext(Node next){
				this.next = next;
			}
			
			public E data(){
				return data;
			}
			
			/*public void setData(){
				this.data=data;
			}*/
			
		}
		
		Node head,tail;
		
		public boolean add(E data){
			if(tail!=null){
				tail.setNext(new Node(data));
				tail = tail.next();
			}
			else{tail = new Node(data);}
			if(head==null) head=tail;
			return true;
		}
		
		public E pop(){
			if(head==null) return null;
			E data = head.data();
			head=head.next();
			return data;
		}
		
		public E get(int index) {
			return null;
		}

		@Override
		public int size() {
			return 0;
		}
		
	}
	
	public void start(){
		Queue<Integer> q = new Queue<>();
		q.add(1);
		q.add(2);
		q.add(3);
		System.out.println(q.pop());
		System.out.println(q.pop());
		q.add(5);
		System.out.println(q.pop());
		System.out.println(q.pop());
		q.add(6);
		System.out.println(q.pop());
		System.out.println(q.pop());
		
		StaticQueue<Integer> q2 = new StaticQueue<>(5);
		q2.add(1);
		q2.add(2);
		q2.add(3);
		System.out.println(q2.pop());
		System.out.println(q2.pop());
		q2.add(5);
		System.out.println(q2.pop());
		System.out.println(q2.pop());
		q2.add(6);
		System.out.println(q2.pop());
		System.out.println(q2.pop());
		q2.add(7);
		q2.add(8);
		q2.add(9);
		q2.add(10);
		q2.add(11);
		q2.add(12);
		System.out.println(q2.pop());System.out.println(q2.pop());System.out.println(q2.pop());System.out.println(q2.pop());System.out.println(q2.pop());System.out.println(q2.pop());
	}
	
	public void help(){}
}
package cs.programs;

import cs.CSProgram;

public class MyLinkedLists extends CSProgram {

	public MyLinkedLists(int id) {
		super("myLinkedLists", id);
	}

	public void start() {
		MyLinkedList<Integer> ll = new MyLinkedList<Integer>();
		ll.add(5);
		ll.add(6);
		ll.add(7);
		ll.add(8);
		ll.add(9);
		ll.add(10);
		for(int i : ll){
			System.out.println(i);
		}
		ll.add(4, 0);
		ll.add(6, 2);
		for(int i : ll){
			System.out.println(i);
		}
		ll.set(7, 3);
		ll.set(8, 4);
		ll.set(9, 5);
		ll.set(10, 6);
		for(int i : ll){
			System.out.println(i);
		}
		ll.remove(ll.getLength()-1);
		for(int i : ll){
			System.out.println(i);
		}
	}

	public void help() {
		
	}

}

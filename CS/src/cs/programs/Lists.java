package cs.programs;

import java.util.ArrayList;
import java.util.LinkedList;

import cs.CSProgram;

public class Lists extends CSProgram {

	final int SIZE = 100*1000;
	final int OPERATION_SIZE = 10*1000;
	
	public Lists(int id) {
		super("lists", id);
	}
	
	public void start() {
		LinkedList<Integer> bigLL = new LinkedList<Integer>();
		ArrayList<Integer> bigAL = new ArrayList<Integer>();
		MyLinkedList<Integer> bigML = new MyLinkedList<Integer>();
		
		long startTime = System.nanoTime();
		for(int x = 0;x<SIZE;x++){
			bigLL.add(x);
			bigAL.add(x);
			bigML.add(x);
		}
		long timeTaken = (System.nanoTime()-startTime);
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigLL.add(x);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Linked List add "+OPERATION_SIZE+" to end of a "+bigLL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigML.add(x);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("My List add "+OPERATION_SIZE+" to end of a "+bigML.getLength()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigAL.add(x);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Array List add "+OPERATION_SIZE+" to end of a "+bigAL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigLL.add(0, x);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Linked List add "+OPERATION_SIZE+" to beginning of a "+bigLL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigML.add(0, x);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("My List add "+OPERATION_SIZE+" to beginning of a "+bigML.getLength()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigAL.add(0, x);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Array List add "+OPERATION_SIZE+" to beginning of a "+bigAL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigLL.add(bigLL.size()/2, x);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Linked List add "+OPERATION_SIZE+" to middle of a "+bigLL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigML.add(bigML.getLength()/2, x);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("My List add "+OPERATION_SIZE+" to middle of a "+bigML.getLength()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigAL.add(bigAL.size()/2, x);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Array List add "+OPERATION_SIZE+" to middle of a "+bigAL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigLL.remove(0);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Linked List remove "+OPERATION_SIZE+" from beginning of a "+bigLL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigML.remove(0);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("My List remove "+OPERATION_SIZE+" from beginning of a "+bigML.getLength()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigAL.remove(0);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Array List remove "+OPERATION_SIZE+" from beginning of a "+bigAL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigLL.remove(bigLL.size()/2);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Linked List remove "+OPERATION_SIZE+" from middle of a "+bigLL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigML.remove(bigML.getLength()/2);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("My List remove "+OPERATION_SIZE+" from middle of a "+bigML.getLength()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigAL.remove(bigAL.size()/2);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Array List remove "+OPERATION_SIZE+" from middle of a "+bigAL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigLL.remove(bigLL.size()-1);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Linked List remove "+OPERATION_SIZE+" from end of a "+bigLL.size()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigML.remove(bigML.getLength()-1);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("My List remove "+OPERATION_SIZE+" from end of a "+bigML.getLength()+" element array:");
		printTime(timeTaken);
		
		startTime = System.nanoTime();
		for(int x = 0;x<OPERATION_SIZE;x++){
			bigAL.remove(bigAL.size()-1);
		}
		timeTaken = (System.nanoTime()-startTime);
		System.out.println("Array List remove "+OPERATION_SIZE+" from end of a "+bigAL.size()+" element array:");
		printTime(timeTaken);
	}
	
	private void printTime(long time){
		System.out.print(time+"ns, ");
		System.out.print(time/1000+"micros, ");
		System.out.print(time/1000/1000+"ms, ");
		System.out.println(time/1000/1000/1000+"s");
	}

	public void help() {
		
	}

}

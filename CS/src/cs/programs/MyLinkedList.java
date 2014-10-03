package cs.programs;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T>{

	private Item<T> first;
	private Item<T> last;
	private int length;
	
	public MyLinkedList() {
		 first = null;
		 last = null;
		 length = 0;
	}
	
	public int getLength(){
		return length;
	}
	
	public T getFirst(){
		return first==null?null:first.getValue();
	}
	
	public T getLast(){
		return last==null?null:last.getValue();
	}
	
	private Item<T> getItem(int index){
		if(index>=length) return null;
		if(index<0) return null;
		Item<T> current = first;
		for(int x = 0; x < index; x++){
			current = current.getNext();
		}
		return current;
	}
	
	public T get(int index){
		return getItem(index)==null?null:getItem(index).getValue();
	}
	
	public void add(T value){
		Item<T> newItem = new Item<T>(value);
		if(last!=null)
			last.setNext(newItem);
		last = newItem;
		if(first==null)
			first = newItem;
		length++;
	}
	
	public boolean add(T value, int index){
		if(index==0) addFirst(value);
		if(index==length-1) add(value);
		Item<T> current = getItem(index-1);
		if(current==null) return false;
		Item<T> newItem = new Item<T>(value);
		newItem.setNext(current.getNext());
		current.setNext(newItem);
		length++;
		return true;
	}
	
	public void addFirst(T value){
		Item<T> currentItem = new Item<T>(value);
		currentItem.setNext(first);
		first = currentItem;
		if(last==null) last = first;
		length++;
	}
	
	public boolean set(T value, int index){
		Item<T> current = getItem(index);
		if(current == null) return false;
		current.setValue(value);
		return true;
	}
	
	public boolean remove(int index){
		if(index == 0){
			if(last == first)last = null;
			first = first.getNext();
			length--;
			return true;
		}
		if(index == length-1){
			last = getItem(length-2);
			last.setNext(null);
			length--;
			return true;
		}
		Item<T> current = getItem(index-1);
		if(current == null) return false;
		if(current.getNext()!=null){
			if(last == current.getNext()) last = current;
			current.setNext(current.getNext().getNext());
		}
		else{return false;}
		length--;
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index = 0;
			Item<T> current = MyLinkedList.this.first;
			
			@Override
			public boolean hasNext() {
				return current!=null;
			}

			@Override
			public T next() {
				Item<T> retur = current;
				current = current.getNext();
				return retur.getValue();
			}

			@Override
			public void remove() {
				MyLinkedList.this.remove(index);
			}
		};
	}

}

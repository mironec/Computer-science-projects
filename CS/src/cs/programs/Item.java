package cs.programs;

public class Item<T> {

	private T data;
	private Item<T> next;
	
	public Item() {
		data = null;
		next = null;
	}
	
	public Item(T data) {
		this();
		this.data = data;
	}
	
	public Item(Item<T> next) {
		this();
		this.next = next;
	}
	
	public Item(T data, Item<T> next) {
		this();
		this.data = data;
		this.next = next;
	}
	
	public Item<T> getNext(){
		return next;
	}
	
	public T getValue(){
		return data;
	}
	
	public void setNext(Item<T> next){
		this.next = next;
	}
	
	public void setValue(T data){
		this.data = data;
	}

}

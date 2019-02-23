package tools;
/*
 * Alejandra Casanova
 */

public class ArrayIndexList<E>{
	private static final int INITCAP = 100; 
	private static final int CAPTOAR = 1; 
	private static final int MAXEMPTYPOS = 2; 
	private E[] element; 
	private int size; 

	public ArrayIndexList()  { 
		element = (E[]) new Object[INITCAP]; 
		size = 0; 
	}
	/**
	 * 
	 * @param index position to add the element
	 * @param e element to be added to the list
	 * @throws IndexOutOfBoundsException if the index entered is not a valid index on the determined ArrayIndex instace
	 */

	public void add(int index, E e) throws IndexOutOfBoundsException {
		if(index>size|| index<0) {
			throw new IndexOutOfBoundsException("add: Invalid index: " + index); 
		}        
		if(size==element.length) {changeCapacity(CAPTOAR);}
		moveDataOnePositionTR(index,size-1);
		element[index]=e;
		size++;
	}
	/**
	 * 
	 * @param e add element to the next position of the list
	 */
	public void add(E e) {
		if(size==element.length) {changeCapacity(CAPTOAR);}
		element[size]=e;
		size++;

	}
	/**
	 * 
	 * @param index is the position to return the element
	 * @return the element on the position of index
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int index) throws IndexOutOfBoundsException {
		if(index>size-1|| index<0) {
			throw new IndexOutOfBoundsException("get: Invalid index: " + index);
		}
		return element[index]; 
	}
	/**
	 * 
	 * @return true if the list does not contain any element
	 */

	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * 
	 * @param index is the position selected to remove the element 
	 * @return the element on the erased position
	 * @throws IndexOutOfBoundsException
	 */
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index>size-1 || index<0) { throw new IndexOutOfBoundsException("remove: Invalid index: "+ index);}
		E temp = element[index];
		moveDataOnePositionTL(index+1,size-1);
		size--;
		element[size]=null;
		return temp;
	}
	/**
	 * 
	 * @param index position selected to set the element
	 * @param e element to be setted
	 * @return element saved on the position before setting the new element
	 * @throws IndexOutOfBoundsException
	 */

	public E set(int index, E e) throws IndexOutOfBoundsException {
		if(index>size-1|| index<0) { throw new IndexOutOfBoundsException("set: Invalid index: " + index);
		}    
		E temp = element[index];
		element[index]=e;
		return temp;
	}
	/**
	 * 
	 * @return number of elements saved on the list (size of it)
	 */

	public int size() {
		return size;
	} 
	/**
	 * 
	 * @return length of the list
	 */
	public int capacity() {
		return element.length;
	}
	/**
	 * 
	 * @param change:Changes the size of the list to the new size, which is the parameter change
	 */


	private void changeCapacity(int change) { 
		int newCapacity = element.length + change; 
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { 
			newElement[i] = element[i]; 
			element[i] = null; 
		} 
		element = newElement; 
	}
	/**
	 * Low meaning the position closer to 0 and high meaning the position closer to length of the array
	 * @param low the lowest index in the list
	 * @param sup the highest index in the list 
	 */

	private void moveDataOnePositionTR(int low, int sup) { 
		for (int pos = sup; pos >= low; pos--)
			element[pos+1] = element[pos]; 
	}
	/**
	 * Low meaning the position closer to 0 and high meaning the position closer to length of the array
	 * @param low the lowest index in the list
	 * @param sup highest index in the list
	 */
	private void moveDataOnePositionTL(int low, int sup) { 
		for (int pos = low; pos <= sup; pos++)
			element[pos-1] = element[pos]; 

	}
	/**
	 * 
	 * @return true if the current position of the list has a next element
	 */
	public boolean hasNext() {
		int counter=0;
		while(!element[counter].equals(null)) {
			counter++;
			return true;
		}
		return false;

	}
}
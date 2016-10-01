package csci204;

//John DiIorio
//Doubly linked list class
//There are no known bugs in the code.

class DLList<E> implements List<E> {
	private Link<E> head; // Pointer to list header
	// private Link<E> tail; // Pointer to last element
	private Link<E> curr; // Access to current element
	private int listSize; // Size of list

	// Constructors
	// The @SuppressWarnings("unused") generates a warning
	DLList(int size) {
		this();
	}

	DLList() {
		clear();
	}

	// Remove all elements
	public void clear() {
		//Create a new node and point head to it
		head = new Link<E>(null, null);
		//Link it to itself and set the list size to 0
		head.setprev(head);
		head.setnext(head);
		curr = head;
		listSize = 0;
	}

	// Insert "it" at current position
	public boolean insert(E it) throws DLListException {
		if (it == null) {
			throw new DLListException("Cannot add to list");
		}
		//Create a new node behind the current one and link it to the previous and current node
		curr.setprev(new Link<E>(curr.prev(), curr));
		//Make it doubly linked
		curr = curr.prev();
		curr.prev().setnext(curr);
		//Set the new node to element and increment list size
		curr.setelement(it);		
		listSize++;
		return true;
	}

	// Append "it" to list
	public boolean append(E it) throws DLListException {
		if (it == null)
			throw new DLListException("Cannot add null to list");
		//Move current to end of list
		moveToEnd();
		//Create a new node at the end and link it to curr and head
		curr.setnext(new Link<E>(curr, head));
		head.setprev(curr.next());
		//Step to the new node and set the element to the parameter, increment the list size
		curr = curr.next();
		curr.setelement(it);
		listSize++;
		return true;
	}

	// Remove and return current element
	public E remove() throws DLListException {
		if (curr == head)
			throw new DLListException("Cannot add to list");
		//Store the element to remove and the current location of curr
		E tmpElement = curr.element();
		int tmpPos = currPos();
		//Set the current node to the element of the node next to it
		curr.setelement(curr.next().element());
		//Jumper the links around the old node
		curr.setnext(curr.next().next());
		curr = curr.next();
		curr.setprev(curr.prev().prev());
		curr = curr.prev();
		//Set the head to curr in order to preserve the list otherwise move curr back
		if (curr.next() == head.next()) {
			head = curr;
			moveToStart();
		} else
			moveToPos(tmpPos);
		listSize--;
		return tmpElement;
	}

	// Set curr at list start
	public void moveToStart() {
		curr = head.next();
	}

	// Set curr at list end
	public void moveToEnd() {
		//While the curr doesn't equal the head, move curr
		while (curr != head.prev()) {
			curr = curr.next();
		}
	}

	// Move curr one step left; no change if now at front
	public void prev() {
		//If curr is at head, move it until it is one before head
		if (curr == head.next()) {
			while (curr != head.prev()) {
				curr = curr.next();
			}
		} else { //Move it until it is one before curr
			Link<E> temp = head;
			while (temp.next() != curr)
				temp = temp.next();
			curr = temp;
		}
	}

	// Move curr one step right; no change if now at end
	public void next() {
		//If curr.next() is head, jump it past the dummy header node
		if (curr.next() == head)
			curr = curr.next().next();
		else //Step the curr
			curr = curr.next();
	}

	// Return list length
	public int length() {
		return listSize;
	}

	// Return the position of the current element
	public int currPos() {
		//If head equals curr, return -1
		if (head == curr)
			return -1;
		//Step a temporary link until it reaches curr, and return the number of times it steped
		Link<E> temp = head.next();
		int i;
		for (i = 1; curr != temp; i++)
			temp = temp.next();
		return i;
	}

	// Move down list to "pos" position
	public boolean moveToPos(int pos) {
		//If the position is invalid, return false
		if ((pos < 0) || (pos >= listSize))
			return false;
		//Set curr to the beginning, then step curr until it reaches the input position
		curr = head;
		for (int i = 0; i < pos; i++)
			curr = curr.next();
		return true;
	}

	// Return true if current position is at end of the list
	public Boolean isAtEnd() {
		if (curr.next() == head.next())
			return true;
		else
			return false;
	}

	// Return current element value. Note that null gets returned if curr is at the tail
	public E getValue() {
		return curr.element();
	}
	
	public String toString() {
		//Initialize the string with brackets, keeping track of curr's position, and move curr to start
		String str = "<";
		int tmpPos = currPos();
		moveToStart();		
		//While curr is not at the end, step curr and record the current element
		while(!isAtEnd()) {
			str += curr.element() + ", ";
			curr = curr.next();
		}
		//Add the second bracket, move current back to the original location, and return the string
		str += ">";		
		moveToPos(tmpPos);
		return str;
	}
}
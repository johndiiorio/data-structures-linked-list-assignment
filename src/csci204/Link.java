package csci204;
class Link<E> { // Singly linked list node class
	private E e; // Value for this node
	private Link<E> p;
	private Link<E> n; // Point to next node in list

	// Constructors
	Link(E it, Link<E> prev, Link<E> inn) {
		e = it;
		n = inn;
		p = prev;
	}

	Link(Link<E> prev, Link<E> inn) {
		e = null;
		n = inn;
		p = prev;
	}

	E element() {
		return (E)e;
	} // Return the value

	void setelement(E it) {
		e = it;
	} // Set element value

	Link<E> next() {
		return n;
	} // Return next link
	
	Link<E> prev(){
		return p;
	} // Return the previous link

	void setnext(Link<E> inn) {
		n = inn;
	} // Set next link
	void setprev(Link<E> prev) {
		p = prev;
	}
}
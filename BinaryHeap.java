
public class BinaryHeap<T extends Comparable<? super T>> {
	T[] elements;
	int last;

	@SuppressWarnings("unchecked")
	public BinaryHeap() {

		this.elements = (T[]) new Comparable[2];

		this.last = 1;
	}

	/*
	 * starts at the end of the array, and percolates up the tree until it finds
	 * the correct spot to insert the element
	 */
	public boolean insert(T e) {
		int i = last;
		if (i + 1 == elements.length) {
			this.doubleArray();
		}
		while (elements[i / 2] != null && elements[i / 2].compareTo(e) > 0) {
			elements[i] = elements[i / 2];
			i = i / 2;
		}
		elements[i] = e;
		this.last++;

		return true;
	}

	/*
	 * doubles the length of the array
	 */
	private void doubleArray() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable[this.elements.length * 2];
		for (int i = 0; i < elements.length; i++) {
			temp[i] = elements[i];
		}
		this.elements = temp;
	}

	/*
	 * deletes the min at the root of the tree. moves the last element to the
	 * root and then percolates it down until it finds the correct spot.
	 */
	public T deleteMin() {
		if (this.last == 1) {
			return null;
		}
		T minItem = elements[1];
		this.last--;
		elements[1] = elements[this.last];

		percolateDown(1);

		elements[this.last] = null;

		return minItem;
	}

	/*
	 * takes the element at location hole and percolates it down using swaps
	 * with the smallest child until it reaches the place where it belongs
	 */
	private void percolateDown(int hole) {
		int child;
		T temp = elements[hole];

		while (hole * 2 <= this.last) {
			child = hole * 2;
			if (child != this.last
					&& elements[child + 1].compareTo(elements[child]) < 0)
				child++;
			if (elements[child].compareTo(temp) < 0) {
				elements[hole] = elements[child];
				elements[child] = temp;
			} else
				break;
			hole = child;
		}
		elements[hole] = temp;
	}

	/*
	 * sorts the array of elements inserting them all into the heap, and then
	 * deleting the minimum one until they are all gone.
	 */
	public void sort(T[] elements) {
		for (int i = 0; i < elements.length; i++) {
			this.insert(elements[i]);
		}
		T[] temp = (T[]) new Comparable[this.elements.length];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = this.deleteMin();
		}
	}

	/*
	 * goes through the list in level order and prints the contents as a string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int i = 0;
		for (i = 0; i < this.last - 1; i++) {
			sb.append(elements[i] + ", ");
		}
		sb.append(elements[i]);
		sb.append("]");
		return sb.toString();
	}
}

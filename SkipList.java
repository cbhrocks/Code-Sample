import java.util.ArrayList;
import java.util.Random;

/**
 * A SkipList is a randomized multi-level linked list
 * 
 * @param <T>
 *            The generic type of the list.
 */
public class SkipList<T extends Comparable<? super T>> {

	private Node root;
	private int numNodesVisited;
	private Random randomizer;

	private static int MAX_LINKS = 10;

	/**
	 * Creates a skip list with truly random numbers. We won't use this
	 * constructor for our tests.
	 */
	public SkipList() {
		this.randomizer = new Random();
		reset();
	}

	/**
	 * Creates a SkipList with a given fixed seed. This is better for testing.
	 * 
	 * @param seed
	 *            A random seed.
	 */
	public SkipList(int seed) {
		this.randomizer = new Random(seed);
		reset();
	}

	// Creates the skip list's root and end nodes and does any other
	// initialization needed.
	private void reset() {
		this.root = new Node(null);
		for (int i = 0; i < this.MAX_LINKS; i++) {
			this.root.links.add(i, new Node(null));
		}
	}

	/**
	 * Grabs the next random integer from the array
	 * 
	 * @return 0 or 1.
	 */
	public int getRand() {
		int temp = this.randomizer.nextInt(2);
		// Uncomment to see random values generated.
		// System.out.println("Random = " + temp);
		return temp;
	}

	/**
	 * @return the root node
	 */
	public Node getRoot() {
		return this.root;
	}

	/**
	 * Inserts the elements in the array in the SkipList in order, then iterates
	 * through the list, copying them back into the array, thus sorting the
	 * array.
	 * 
	 * @param array
	 */
	public void sort(T[] array) {		
		MAX_LINKS = (int) (Math.log(array.length)/Math.log(2));

		this.reset();

		for (T e : array){
			this.insert(e);
		}
		Node temp = this.root;
		for (int i = 0; i < array.length; i++){
			array[i] = temp.element; 
			temp = temp.links.get(0);
		}
	}

	/**
	 * Inserts the given element in the list
	 * 
	 * @param e
	 * @return true if successful; false otherwise
	 */
	public boolean insert(T e) {
		int height = 0;
		Node toAdd = new Node(e);
		Node before = SkipSearch(e);

		toAdd.links.add(before.links.get(0));
		before.links.set(0, toAdd);

		while (getRand() == 1) {
			height++;
			toAdd.links.add(height, new Node(null));
		}
		this.setLinks(toAdd, height);

		return true;
	}

	/**
	 * sets the links in the SkipList to point to the correct nodes.
	 * 
	 * @param n, height
	 */
	private void setLinks(Node n, int height) {
		Node current = this.root;
		int currentHeight = height;
		Node next = current.links.get(currentHeight);
		while (currentHeight > 0) {
			if ((next.element != null)
					&& (next.element.compareTo(n.element) < 0)) {
				current = next;
				next = current.links.get(currentHeight);
			} else {
				n.links.set(currentHeight, next);
				current.links.set(currentHeight, n);
				currentHeight--;
				next = current.links.get(currentHeight);
			}
		}
	}

	/**
	 * returns the node directly before the insertion point on the lowest level
	 * 
	 * @param e
	 * @return Node directly before insertion point
	 */
	private Node SkipSearch(T key) {
		Node current = this.root;
		int n = MAX_LINKS - 1;
		while (n > -1 && this.root.links.get(n).element == null) {
			n--;
		}
		while (n != -1) {
			Node next = current.links.get(n);
			if ((next.element != null) && (next.element.compareTo(key) < 0)) {
				current = next;
				this.numNodesVisited++;
			} else {
				n--;
			}
		}
		return current;
	}

	/**
	 * Removes the given element from the list
	 * 
	 * @param e
	 * @return true if successful; false otherwise
	 */
	public boolean remove(T e) {
		Node current = this.root;
		int currentHeight = this.MAX_LINKS - 1;

		while (currentHeight > -1) {
			if (current.links.get(currentHeight).element == e) {
				current.links.set(currentHeight, current.links
						.get(currentHeight).links.get(currentHeight));
				currentHeight--;
			} else if (currentHeight == 0
					&& current.links.get(currentHeight).element == null) {
				return false;
			} else if (current.links.get(currentHeight).element == null) {
				currentHeight--;
			} else {
				current = current.links.get(currentHeight);
				this.numNodesVisited++;
			}
		}

		return true;
	}

	/**
	 * @return the number of nodes visited during insertion and removal
	 */
	public int getNodesVisited() {
		return this.numNodesVisited;
	}

	/**
	 * A Node holds data and links to the next node on its levels.
	 * 
	 */
	public class Node {

		private T element;
		private ArrayList<Node> links;

		/**
		 * Instantiates this node with the given element and links
		 * 
		 * @param e
		 * @param size
		 */
		public Node(T e) {
			this.element = e;
			this.links = new ArrayList<Node>();
		}

		/**
		 * Returns the list of links this node is holding
		 * 
		 * @return a list of links
		 */
		public ArrayList<Node> getLinks() {
			return this.links;
		}

		/**
		 * @return this node's element
		 */
		public T getElement() {
			return this.element;
		}
	}
}
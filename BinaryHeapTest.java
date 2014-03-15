import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests binary heaps.
 * 
 * @author Matt Boutell. Created May 7, 2013.
 */
public class BinaryHeapTest {

	/**
	 * Simple test method for insert, delete, toString, and sort. Enforces the method signatures.
	 */
	@Test
	public void testSimple() {
		// TODO: Implement the BinaryHeap class according to the spec.
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		// deleteMin returns null if it has no elements.
		assertNull(heap.deleteMin());
		heap.insert(21); 
		assertEquals("[null, 21]", heap.toString());
		int min = heap.deleteMin();
		assertEquals(21, min);
		assertEquals("[null]", heap.toString());
		String[] csLegends = new String[] {"Edsger Dijkstra", "Grace Hopper", "Donald Knuth", "Ada Lovelace", "Claude Shannon", "Alan Turing"};
		
		BinaryHeap<String> csLegendsHeap = new BinaryHeap<String>();
		csLegendsHeap.sort(csLegends);
		assertEquals("[Ada Lovelace, Alan Turing, Claude Shannon, Donald Knuth, Edsger Dijkstra, Grace Hopper]", Arrays.toString(csLegends));
	}
	
	
	// TODO: Add unit tests for each method until you are satisfied your code is correct. Your unit tests will be submitted and graded as well.
	
}

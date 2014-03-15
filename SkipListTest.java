import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * Tests the SkipList class.
 * 
 * @author Claude Anderson. Revised by Matt Boutell on Nov 4, 2013. Now takes
 *         random seeds - better integration into a later assignment that uses
 *         SkipLists to sort.
 */
public class SkipListTest {
	private static int points = 0;

	/**
	 * Tests insert
	 */
	@Test
	public void testInsert() {
		SkipList<Integer> s = new SkipList<Integer>(3226867);
		// This seed gives the "random" int sequence given in the slides.
		// 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0,
		// 1
		s.insert(5);
		s.insert(7);
		s.insert(6);
		s.insert(6);
		s.insert(4);
		int nv1 = s.getNodesVisited();
		s.insert(8);
		int nv2 = s.getNodesVisited();
		assertEquals(2, nv2 - nv1);

		ArrayList<SkipList<Integer>.Node> links = s.getRoot().getLinks();
		ArrayList<Integer> vals = new ArrayList<Integer>();
		vals.add(4);
		vals.add(4);
		vals.add(4);
		vals.add(6);
		vals.add(null);
		vals.add(null);
		vals.add(null);
		vals.add(null);
		vals.add(null);
		vals.add(null);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			if (links.get(i) == null) {
				assertEquals(vals.get(i), null);
			} else {
				assertEquals(vals.get(i), links.get(i).getElement());
			}
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(5);
		vals.add(6);
		vals.add(6);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			assertEquals(vals.get(i), links.get(i).getElement());
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(6);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			assertEquals(vals.get(i), links.get(i).getElement());
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(6);
		vals.add(6);
		vals.add(7);
		vals.add(null);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			if (links.get(i) == null) {
				assertEquals(vals.get(i), null);
			} else {
				assertEquals(vals.get(i), links.get(i).getElement());
			}
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(7);
		vals.add(7);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			assertEquals(vals.get(i), links.get(i).getElement());
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(8);
		vals.add(8);
		vals.add(8);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			assertEquals(vals.get(i), links.get(i).getElement());
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(null);
		vals.add(null);
		vals.add(null);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			if (links.get(i) == null) {
				assertEquals(vals.get(i), null);
			} else {
				assertEquals(vals.get(i), links.get(i).getElement());
			}
		}
		points += 25;
	}

	/**
	 * Tests insert using a different set of random numbers.
	 */
	@Test
	public void testInsert2() {
		// This seed gives the sequence:
		// 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1
		SkipList<Integer> s = new SkipList<Integer>(11583);

		s.insert(5);
		s.insert(7);
		s.insert(6);
		s.insert(6);
		s.insert(4);
		int nv1 = s.getNodesVisited();
		s.insert(8);
		int nv2 = s.getNodesVisited();
		assertEquals(1, nv2 - nv1);

		points += 5;
	}

	/**
	 * Tests remove
	 */
	@Test
	public void testRemove() {
		// This seed gives the "random" int sequence given in the slides again.
		// 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0,
		// 1
		SkipList<Integer> s = new SkipList<Integer>(3226867);

		assertTrue(s.insert(5));
		assertTrue(s.insert(9));
		assertTrue(s.insert(8));
		assertTrue(s.insert(7));
		assertTrue(s.insert(4));
		assertTrue(s.insert(10));
		assertFalse(s.remove(11));
		assertFalse(s.remove(6));
		int nv1 = s.getNodesVisited();
		assertTrue(s.remove(7));

		int nv2 = s.getNodesVisited();
		assertEquals(2, nv2 - nv1);

		ArrayList<SkipList<Integer>.Node> links = s.getRoot().getLinks();
		ArrayList<Integer> vals = new ArrayList<Integer>();
		vals.add(4);
		vals.add(4);
		vals.add(4);
		vals.add(null);
		vals.add(null);
		vals.add(null);
		vals.add(null);
		vals.add(null);
		vals.add(null);
		vals.add(null);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			if (links.get(i) == null) {
				assertEquals(vals.get(i), null);
			} else {
				assertEquals(vals.get(i), links.get(i).getElement());
			}
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(5);
		vals.add(8);
		vals.add(9);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			assertEquals(vals.get(i), links.get(i).getElement());
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(8);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			assertEquals(vals.get(i), links.get(i).getElement());
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(9);
		vals.add(9);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			if (links.get(i) == null) {
				assertEquals(vals.get(i), null);
			} else {
				assertEquals(vals.get(i), links.get(i).getElement());
			}
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(10);
		vals.add(10);
		vals.add(10);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			assertEquals(vals.get(i), links.get(i).getElement());
		}

		links = links.get(0).getLinks();
		vals = new ArrayList<Integer>();
		vals.add(null);
		vals.add(null);
		vals.add(null);
		assertEquals(vals.size(), links.size());
		for (int i = 0; i < vals.size(); i++) {
			if (links.get(i) == null) {
				assertEquals(vals.get(i), null);
			} else {
				assertEquals(vals.get(i), links.get(i).getElement());
			}
		}

		points += 10;
	}

	/**
	 * Test case for insert with height = log(n)
	 */
	@Test
	public void testLog() {
		// This seed gives the "random" int sequence that is actually highly
		// structured. Notice it gives a binary index tree.
		// 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0,
		// 0, 0, 0, 0
		SkipList<Integer> s = new SkipList<Integer>(81191498);

		assertTrue(s.insert(8));

		assertTrue(s.insert(4));
		assertTrue(s.insert(12));

		assertTrue(s.insert(2));
		assertTrue(s.insert(6));
		assertTrue(s.insert(10));
		assertTrue(s.insert(14));

		assertTrue(s.insert(1));
		assertTrue(s.insert(3));
		assertTrue(s.insert(5));
		assertTrue(s.insert(7));
		assertTrue(s.insert(9));
		assertTrue(s.insert(11));
		assertTrue(s.insert(13));

		int nv1 = s.getNodesVisited();
		assertTrue(s.insert(15));
		int nv2 = s.getNodesVisited();
		assertEquals(3, nv2 - nv1);
		points += 10;
	}

	/**
	 * Report points
	 */
	@AfterClass
	public static void testNothing() {
		System.out.println("Points: " + points + "/50");

	}

}

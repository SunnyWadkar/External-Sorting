import student.TestCase;

/**
 * @author Pranav Chimote pchimote
 * @author Sunny Wadkar sunnywadkar
 * @version 2020-10-13
 *
 *          Test for the Link Class
 */
public class LinkTest extends TestCase {

    /**
     * test for all the methods of the link class
     */
    public void testLink() {
        Link<Integer> node = new Link<Integer>(2);
        assertEquals((int)node.getValue(), 2);
        assertNull(node.getNext());
        Link<Integer> node2 = new Link<Integer>(8);
        node.setNext(node2);
        assertNotNull(node.getNext());
        node.setValue(5);
        assertEquals((int)node.getValue(), 5);
        node = node.getNext();
        assertEquals((int)node.getValue(), 8);

    }

}

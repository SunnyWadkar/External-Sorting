/**
 * @author Pranav Chimote pchimote
 * @author Sunny Wadkar sunnywadkar
 * @version 2020-10-13
 * 
 * @param <T>
 *            data type of the value, set as generic initially
 */
public class LinkedList<T> {

    private Link<T> head;
    private Link<T> tail;

    /**
     * Constructer for Linked List class
     */
    public LinkedList() {
        head = new Link<T>(null);
        tail = head;
    }


    /**
     * Inserts data into the linked list
     * 
     * @param record
     *            record to be inserted
     */
    public void insert(T record) {
        Link<T> node = new Link<T>(record);
        Link<T> curr;
        if (head.getNext() == null) {
            head.setNext(node);
            tail = node;
        }
        else {
            curr = tail;
            curr.setNext(node);
            tail = node;
        }
    }


    /**
     * This method returns the number of nodes in the linked list. The count
     * does not include the head node.
     * 
     * @return int length of the linked list
     */
    public int getListLength() {
        int length = 0;
        Link<T> curr = head.getNext();
        while (curr != null) {
            length++;
            curr = curr.getNext();
        }
        return length;
    }


    /**
     * Remove the node from the linked list
     * 
     * @param record
     *            record to be removed
     */
    public void remove(T record) {
        Link<T> curr = head;
        Link<T> temp;
        while (curr.getNext() != null) {
            if (curr.getNext().getValue() == record) {
                if (curr.getNext() == tail) {
                    tail = curr;
                }
                temp = curr.getNext().getNext();
                curr.setNext(temp);
                break;
            }
            curr = curr.getNext();
        }
    }


    /**
     * Pop function for LL
     * 
     * @return poped node from LL
     */
    public T pop() {
        Link<T> first = head.getNext();
        Link<T> temp;
        if (first != null) {
            temp = first.getNext();
            head.setNext(temp);
            if (tail == first) {
                tail = head;
            }
            return first.getValue();
        }
        else {
            return null;
        }
    }


    /**
     * delete the complete list
     */
    public void deleteList() {
        head.setNext(null);
        tail = head;
    }


    /**
     * Check if the list is empty
     * 
     * @return boolean check for empty list
     */
    public boolean empty() {
        return (head == tail);
    }


    /**
     * Getter function for the head of the list
     * 
     * @return Link<T> returns first node of the Linked list
     */
    public Link<T> getHead() {
        return head;
    }


    /**
     * Getter function for the tail of the list
     * 
     * @return Link<T> returns the last node of Linked List
     */
    public Link<T> getTail() {
        return tail;
    }

}

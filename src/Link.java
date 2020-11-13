/**
 * @author Pranav Chimote pchimote
 * @author Sunny Wadkar sunnywadkar
 * @version 2020-10-13
 *
 * @param <T>
 *            data type of the value, set as generic initially
 */
public class Link<T> {
    private T value;
    private Link<T> next;

    /**
     * Constructor for Node class
     * 
     * @param record
     *            record to be inserted
     */
    public Link(T record) {
        this.value = record;
        this.next = null;
    }


    /**
     * Getter for Node Value
     * 
     * @return T retrieves record saved in the node
     */
    public T getValue() {
        return this.value;
    }


    /**
     * Setter for node value
     * 
     * @param value
     *            setter for record to be saved in the node
     */
    public void setValue(T value) {
        this.value = value;
    }


    /**
     * Getter for next field of node
     * 
     * @return Node<T> retreives the next node
     */
    public Link<T> getNext() {
        return this.next;
    }


    /**
     * Setter for next field of node
     * 
     * @param next
     *            setter for the next node
     */
    public void setNext(Link<T> next) {
        this.next = next;
    }
}

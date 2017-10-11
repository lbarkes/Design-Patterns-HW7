import java.util.NoSuchElementException;

/**
 * Created by logan on 10/3/2017.
 */
public class LinkedComposite extends Component {

    private Node head;
    private int length;
    private String indent;
    private int instanceID;

    public LinkedComposite(Component... components){
        this.indent = "";
        this.parent = null;
        this.length = components.length;
        if(components.length <= 0){
            head = null;
        }
        else{
            this.head = new Node(components[0]);
            this.head.component.setParent(this);
            Node next = this.head;

            for(int i = 1; i < length; i++){
                next.next = new Node(components[i]);
                next = next.next;
                next.component.setParent(this);
            }
        }
        this.instanceID = System.identityHashCode(this);
    }

    public String toString() {
        return ( parent == null ) ?
                instanceID + " is the root." :
                instanceID + " is the child of " + parent;
    }

    public void add(Component component){ //add a node to the end of the list
        Node curr = this.head;
        while(curr.next != null){
            curr = curr.next;
        }
        Node nextNode = new Node(component);
        nextNode.component.setParent(this);
        curr.next = nextNode;
    }

    public void add(int index, Component component){ //add a node at the specified index
        Node node = new Node(component);
        node.component.setParent(this);
        if(index == 0){
            node.next = this.head;
            head = node;
            return;
        }
        if(head == null){
            throw new IndexOutOfBoundsException();
        }

        Node temp = head;
        Node prev = null;
        int count = 0;
        while(temp != null){
            if(count == index){
                prev.next = node;
                node.next = temp;
                break;
            }
            prev = temp;
            temp = temp.next;
            count++;
        }

        if(temp == null && count == index){
            prev.next = node;
        }
        else{
            throw new IndexOutOfBoundsException();
        }

    }

    public void remove(Component component){ //remove the component specified
        Node temp = this.head;
        Node prev = null;
        if(head == null){
            return;
        }
        while (temp != null) {
            if (temp == component) {
                temp.component.setParent(null);
                if (temp == head) {
                    head = head.next;
                } else {
                    prev.next = temp.next;
                }
                break;
            } else {
                prev = temp;
            }
            temp = temp.next;
        }
    }

    public Component getParent(){
        return this.parent;
    }

    public void setParent(Component parent){
        this.parent = parent;
        Component tempParent = this.parent;
        if(this.parent == null){
            this.indent = "";
        }
        this.indent = "";
        while(tempParent != null){ //add indents until root is reached
            tempParent = tempParent.getParent();
            this.indent += "\t";
        }

        Node node = head;
        if(head != null){ //update all children indents
            while(node != null){
                node.setParent(this);
                node = node.next;
            }
        }
    }

    @Override
    public Iter<Component> makeIter() {
        return new LinkedCompositeIter(this.head);
    }

    private class LinkedCompositeIter implements Iter<Component>{
        private Node head;
        private Node curr;

        public LinkedCompositeIter(Node head){
            this.curr = head;
            this.head = head;
        }


        public Component currentItem(){
            return curr.component;
        }

        public boolean isValid(){
            return (curr.next == null) ? false : true;
        }

        public void next(){
            if(curr.next != null){
                curr = curr.next;
            }
            else{
                throw new NoSuchElementException("End of LinkedComposite");
            }
        }

        public void reset(){
            this.curr = head;
        }
    }
}

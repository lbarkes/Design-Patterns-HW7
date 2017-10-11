import java.util.NoSuchElementException;

/**
 * Created by logan on 10/3/2017.
 */
public class Node extends Component{

    public Node next;

    public Component component;

    private String instanceID;

    public Node(Component component){
        this.parent = null;
        this.component = component;
        this.next = null;
    }

    public String toString(){
        return component.toString();
    }

    public Component getParent(){
        return component.getParent();
    }

    public void setParent(Component parent){
        component.setParent(parent);
    }

    @Override
    public Iter<Component> makeIter() {
        return new NodeIter();
    }

    private class NodeIter implements Iter<Component> {
        public Component currentItem(){
            return component;
        }

        public boolean isValid(){
            return false;
        }

        public void next(){
            throw new NoSuchElementException("End of ArrayComposite");
        }

        public void reset(){}
    }


}


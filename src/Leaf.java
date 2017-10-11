import java.util.NoSuchElementException;

/**
 * Created by logan on 10/3/2017.
 */
public class Leaf extends Component{
    private String name;
    private String indent;
    private int instanceID;

    public Leaf(String name){
        this.name = name;
        this.parent = null;
        this.indent = "";
        this.instanceID = System.identityHashCode(this);
    }

    public String toString() {
        return ( parent == null ) ?
                instanceID + " is the root." :
                instanceID + " is the child of " + parent;
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
        while(tempParent != null){ //go up the tree and keep adding indents util top is reached
            tempParent = tempParent.getParent();
            this.indent += "\t";
        }
    }

    @Override
    public Iter<Component> makeIter() {
        return new LeafIter();
    }

    private class LeafIter implements Iter<Component>{
        public Component currentItem(){
            return null;
        }

        public boolean isValid(){
            return false;
        }

        public void next(){
            throw new NoSuchElementException("InstanceComposite only has one child");

        }

        public void reset(){}
    }

}

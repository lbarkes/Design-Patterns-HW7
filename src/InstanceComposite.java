import java.util.NoSuchElementException;

/**
 * Created by logan on 10/3/2017.
 */
public class InstanceComposite extends Component{
    public Component child;
    private String indent;
    private int instanceID;


    public InstanceComposite(){ //no children
        this.child = null;
        this.indent = "";
        this.parent = null;
        this.instanceID = System.identityHashCode(this);
    }

    public InstanceComposite(Component child){ //only one child
        this.parent = null;
        this.child = child;
        this.child.setParent(this);
        this.indent = "";
    }

    public void remove(){
        this.child.setParent(null); //update indent of child
        this.child = null;
    }

    public void add(Component child){
        this.child = child;
        this.child.setParent(this);//update indent of child
    }

    public String toString() {
        return ( parent == null ) ?
                instanceID + " is the root." :
                instanceID + " is the child of " + parent;
    }

    public Component getParent(){
        return this.parent;
    }

    public void setParent(Component parent){ //update indents when parent is set
        this.parent = parent;
        Component tempParent = this.parent;
        if(this.parent == null){
            this.indent = "";
        }
        this.indent = "";
        while(tempParent != null){
            tempParent = tempParent.getParent();
            this.indent += "\t";
        }
        if(this.child != null){
            child.setParent(this);
        }
    }

    @Override
    public Iter<Component> makeIter() {
        return new InstanceCompositeIter();
    }

    private class InstanceCompositeIter implements Iter<Component>{
        public Component currentItem(){
            return child;
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
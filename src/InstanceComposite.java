/**
 * Created by logan on 10/3/2017.
 */
public class InstanceComposite extends Component{
    private Component child;
    private String indent;

    public InstanceComposite(){ //no children
        this.child = null;
        this.indent = "";
        this.parent = null;
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

    public String toString(){
        String first = "\n" + indent + "InstanceComposite containing" ;
        first += child.toString();
        return first;
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
}
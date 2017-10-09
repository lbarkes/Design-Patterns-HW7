/**
 * Created by logan on 10/3/2017.
 */
public class Leaf extends Component{
    private String name;
    private String indent;

    public Leaf(String name){
        this.name = name;
        this.parent = null;
        this.indent = "";
    }

    public String toString(){ //add indent to toString
        return "\n" + indent + "Leaf " + this.name;
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

}

/**
 * Created by logan on 10/3/2017.
 */
public class Node extends Component{

    public Node next;

    public Component component;

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


}


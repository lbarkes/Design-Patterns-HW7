/**
 * Created by logan on 10/3/2017.
 */
public class ArrayComposite extends Component {

    private Component[] components;
    private int length;
    private String indent;

    public ArrayComposite(Component... components){
        this.indent = "";
        this.parent = null;
        this.length = components.length;
        this.components = new Component[components.length];
        for(int i = 0; i < length; i++){
            this.components[i] = components[i];
            this.components[i].setParent(this);
        }
    }

    public void add(int index, Component component){
        if(index >= 0 && index < length){
            this.components[index] = component;
            this.components[index].setParent(this);
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    public void remove(int index){
        if(index >= 0 && index < length){
            this.components[index].setParent(null);
            this.components[index] = null;
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    public String toString(){
        String first = "\n" + indent + "ArrayComposite containing" ;
        for(int i = 0; i < length; i++){
            if(components[i] != null) {
                first += components[i].toString();
            }
        }
        return first;
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
        while(tempParent != null){
            tempParent = tempParent.getParent();
            this.indent += "\t";
        }

        for(int i = 0; i < length; i++){
            if(components[i] != null){
                components[i].setParent(this);
            }
        }
    }
}


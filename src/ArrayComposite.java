import java.util.NoSuchElementException;

/**
 * Created by logan on 10/3/2017.
 */
public class ArrayComposite extends Component {

    public Component[] components;
    private int length;
    private String indent;
    private int instanceID;

    public ArrayComposite(Component... components){
        this.indent = "";
        this.parent = null;
        this.length = components.length;
        this.components = new Component[components.length];
        for(int i = 0; i < length; i++){
            this.components[i] = components[i];
            this.components[i].setParent(this);
        }
        this.instanceID = System.identityHashCode(this);
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

    @Override
    public Iter<Component> makeIter() {
        return new ArrayCompositeIter();
    }

    private class ArrayCompositeIter implements Iter<Component>{
        private int index;

        public Component currentItem(){
            return components[index];
        }

        public boolean isValid(){
            if(index < components.length){
                return true;
            }
            else{
                return false;
            }

        }

        public void next(){

            if(index < components.length){
                index++;
            }
            else{
                throw new NoSuchElementException("End of ArrayComposite");
            }
        }

        public void reset(){
            index = 0;
        }
    }
}


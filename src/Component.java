/**
 * Created by logan on 10/3/2017.
 */
public abstract class Component {
    public Component parent;
    public abstract String toString();
    public abstract Component getParent();
    public abstract void setParent(Component parent);
}

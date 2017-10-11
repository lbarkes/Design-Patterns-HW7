/**
 * Created by logan on 10/9/2017.
 */
public interface Iter<Component>{
    Component currentItem();
    boolean isValid();
    void next();
    void reset();
}

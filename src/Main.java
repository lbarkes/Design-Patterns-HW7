/**
 * Created by logan on 10/3/2017.
 */
public class Main {
    public static void main(String args[]){

        LinkedComposite lc = new LinkedComposite( new Leaf( "A" ), new Leaf( "B" ) );
        ArrayComposite ac = new ArrayComposite( new Leaf( "C" ), lc, new Leaf( "D" ) );
        System.out.println(ac.toString());

        Leaf a = new Leaf("A");
        LinkedComposite lc1 = new LinkedComposite( a, new Leaf( "B" ) );
        lc.remove(a);
        lc.add(new Leaf("D"));
        ArrayComposite ac1 = new ArrayComposite( new Leaf( "C" ), lc1, new Leaf( "D" ) );
        ac1.add(2,new InstanceComposite( new Leaf( "Inst" )));
        System.out.println(ac1.toString());

        LinkedComposite list1 = new LinkedComposite( new Leaf( "E" ), new Leaf( "F" ) );
        ArrayComposite array1 = new ArrayComposite( new Leaf( "C" ), new Leaf( "D" ) );
        array1.remove(1);
        InstanceComposite instance1 = new InstanceComposite( new Leaf( "Inst" ));
        LinkedComposite list2 = new LinkedComposite( new Leaf( "A" ), list1, new Leaf( "G" ) );
        ArrayComposite array2 = new ArrayComposite( array1, instance1, list2, new Leaf("Last") );

        System.out.println("Test Array");
        Iter<Component> array2Iter = array2.makeIter();
        while(array2Iter.isValid()){
            System.out.println(array2Iter.currentItem().toString());
            array2Iter.next();
        }

        System.out.println("Test List");
        Iter<Component> list2Iter = list2.makeIter();
        while(list2Iter.isValid()){
            System.out.println(list2Iter.currentItem().toString());
            list2Iter.next();
        }

        System.out.println("Test ArrayReset");
        array2Iter.reset();
        while(array2Iter.isValid()){
            System.out.println(array2Iter.currentItem().toString());
            array2Iter.next();
        }

    }

}

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 *
 * @author StutiP_000
 */
public class IntSet {
    private Vector els;

    public IntSet() {
        els = new Vector();
    }

    public void insert(int x) {
        if (getIndex(x) < 0)
            els.add(x);
    }

    public void remove(int x) {
        int i = getIndex(new Integer(x));
        if (i < 0)
            return;
        els.set(i, els.lastElement());
        els.remove(els.size() - 1);
    }

    public boolean isIn(int x) {
        return getIndex(x) >= 0;
    }

    private int getIndex(int x) {
        for (int i = 0; i < els.size(); i++) {
            int y = (Integer) els.elementAt(i);
            if (x == y)
                return i;
        }
        return -1;
    }

    public int size() {
        return els.size();
    }

    public int choose() throws IllegalStateException {
        if (els.size() == 0)
            throw new IllegalStateException();
        return (Integer) els.lastElement();
    }

    // ==========================================================
    // Do not modify anything above this comment
    // ==========================================================
    
    public Iterator elements() {
        return new SetGen(els);
    }
    
    private static class SetGen implements Iterator {        
        

//Abstraction Function
// AF(c)= { c.els[i].intvalue | 0<=i<c.els.size}
//Representation Invariant
//	c.currentPosition!= null &&
//	c.currentPosition is an Integer &&
//	for any value of currentPosition. 0<currentPosition<els.size &&
//	c.currentPosition holds the index value

        private int currentPosition;
        private final Vector els;

        private SetGen(Vector els) {
            this.els = els;
            this.currentPosition = 0;
        }
        
        @Override
        public boolean hasNext() {
            return currentPosition < els.size();
        }
        
        @Override
        public Object next() throws NoSuchElementException {
            if(hasNext()) {
                Object el = els.elementAt(currentPosition);
                currentPosition++; 
                return el;
            }
            return null;
        }
    }
}


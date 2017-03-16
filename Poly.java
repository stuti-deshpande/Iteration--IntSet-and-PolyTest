package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

// ==============================================================
// Starter code for Liskov Exercise 6.5
// ==============================================================

public class Poly {
	 private int[] trms;
	    private int deg;

	    public Poly() {
	        trms = new int[1];
	        deg = 0;
	    }

	    public Poly(int c, int n) throws IllegalArgumentException {
	        if (n < 0)
	            throw new IllegalArgumentException();
	        if (c == 0) {
	            trms = new int[1];
	            deg = 0;
	            return;
	        }
	        trms = new int[n + 1];
	        for (int i = 0; i < n; i++)
	            trms[i] = 0;
	        trms[n] = c;
	        deg = n;
	    }

	    private Poly(int n) {
	        trms = new int[n + 1];
	        deg = n;
	    }

	    public int degree() {
	        return deg;
	    }

	    public int coeff(int d) {
	        if (d < 0 || d > deg)
	            return 0;
	        else
	            return trms[d];
	    }

	    public Poly sub(Poly q) throws NullPointerException {
	        return add(q.minus());
	    }

	    public Poly minus() {
	        Poly r = new Poly(deg);
	        for (int i = 0; i < deg; i++)
	            r.trms[i] = -trms[i];
	        return r;
	    }

	    public Poly add(Poly q) throws NullPointerException {
	        Poly la, sm;
	        if (deg > q.deg) {
	            la = this;
	            sm = q;
	        } else {
	            la = q;
	            sm = this;
	        }
	        int newdeg = la.deg;
	        if (deg == q.deg)
	            for (int k = deg; k > 0; k--)
	                if (trms[k] + q.trms[k] != 0)
	                    break;
	                else
	                    newdeg--;
	        Poly r = new Poly(newdeg);
	        int i;
	        for (i = 0; i <= sm.deg && i <= newdeg; i++)
	            r.trms[i] = sm.trms[i] + la.trms[i];
	        for (int j = i; j <= newdeg; j++)
	            r.trms[j] = la.trms[j];
	        return r;
	    }

	    public Poly mul(Poly q) throws NullPointerException {
	        if ((q.deg == 0 && q.trms[0] == 0) || (deg == 0 && trms[0] == 0))
	            return new Poly();
	        Poly r = new Poly(deg + q.deg);
	        r.trms[deg + q.deg] = 0;
	        for (int i = 0; i <= deg; i++)
	            for (int j = 0; j <= q.deg; j++)
	                r.trms[i + j] = r.trms[i + j] + trms[i] * q.trms[j];
	        return r;
	    }
	    
	    // ==========================================================
	    // Do not modify anything above this comment
	    // ==========================================================
	    
	    public Iterator<Poly> units() {
	        //TODO Implement this method
	    	//REQUIRES: this must not be modified when the generator is in use
	    	//EFFECTS: returns a generator that will produce the elements of this 
	    	//         each exactly once.
	    	if(this !=  null)
	    		return new PolyUnitGen(this);
	        return null;
	    }
	    
	    private static class PolyUnitGen implements Iterator {        
	        //TODO Implement this class
	        //TODO Give the abstraction function
	        //TODO Give the representation invariant
	    	//AF(c)=c0+c1x+c2x^2+...
	    	//where ci=c.trms[i] if 0<=i<c.trms.size
	    	//where index i >= n 
	    	//c.poly.trms are only elements in the sequence 
	    	//cix^i > cjx^j where i>j>=1
	    	
	    	//Rep Invariant
	    	//c.poly!=null && (0 <= c.num <= c.poly.deg)
	    	//Informal Rep:
	    	//c.p!=null && 
	    	//for the iterator. 0<=c.num<c.poly.deg
	    	
	    	private Poly poly;
	    	private int num;
	    	
	    	PolyUnitGen(Poly it)
	    	{
	    	//REQUIRES: it!= null
	    		poly=it;
	    		num=0;
	    	}
	        
	        public boolean hasNext() {
	            //TODO Implement this method
	        	//EFFECTS: returns true if the next element exists
	        	return(num <= poly.deg);
	        }
	        
	        public Object next() throws NoSuchElementException {
	            //TODO Implement this method
	        	//EFFECTS: returns the next element in the array
	        		for(int e=num;e<=poly.deg;e++)
	        		{
	        			if(poly.trms[e]!= 0)
	        			{
	        				num=e+1;
	        				return new Poly(poly.trms[e],e);
	        			}
	        		}
	        		throw new NoSuchElementException("No elements");
	        }
	    }
	    
	    public static void main(String []args)
	    {
	    	Poly o1 = new Poly(2,4); 
	    	Poly o2 = new Poly(3,3);
	    	Poly o3 = o1.add(o2);
	    	//System.out.print(o3.deg);
	    	Iterator i = o3.units();     
	    	Poly a =(Poly) i.next();    //iterator returns the values 2x^3
	    	System.out.println(a.deg); 
	    	System.out.println(a.trms[a.deg]);
	        a =(Poly) i.next();    //iterator returns the values 3x^4
	    	System.out.println(a.deg); 
	    	System.out.println(a.trms[a.deg]);
	    }
}

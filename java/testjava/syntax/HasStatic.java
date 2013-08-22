class HasStatic {
    static int j = 100;
    public static void main(String[] args)
    {
    	Outer o = new Outer();
    	o.say();
    	// Outer.NestedButNotInner n = new Outer.NestedButNotInner();
    	// n.say();
    	// Outer.Inner i = o.new Inner();
    	// i.say();
    }
}
class Outer {
	private int x=0;
	private static int y=0;
    
    void say()
    {	
    	Inner i = this.new Inner();
    	i.say();
    	NestedButNotInner n = new NestedButNotInner();
    	n.say();
    	System.out.println("private say!");
    }
    
    private class Inner extends HasStatic {
        // private static final int x = 3;  // OK: compile-time constant
        // static int y = 4;  // Compile-time error: an inner class
        // public Inner(){};
        void say()
        {
        	System.out.println(x);
        	// System.out.println(Outer.x);//non-static variable x cannot be referenced from a static context
        	System.out.println(Outer.this.x);
        	System.out.println(y);
        	System.out.println(Outer.y);
        	System.out.println(Outer.this.y);
        }
    }

   private static class NestedButNotInner{
        private static int z = 5;    // OK: not an inner class
        // public NestedButNotInner(){};
        void say()
        {
        	Outer o = new Outer();
        	// System.out.println("o.x" + x);//non-static variable x cannot be referenced from a static context
        	System.out.println("o.x" + o.x);
        	// System.out.println("o.x" + Outer.x);//non-static variable x cannot be referenced from a static context
        	System.out.println("Outer.y" + y);
        	System.out.println("Outer.y" + o.y);
        	System.out.println("Outer.y" + Outer.y);
        }
        public static void main(String[] args)
        {
        	System.out.println("NestedButNotInner say");
        }
    }
    interface NeverInner {}  // Interfaces are never inner
}
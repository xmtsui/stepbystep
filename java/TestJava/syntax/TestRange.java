/**
 * byte 1 (-128 127)
 * short 2(-32768 32767)
 * int 4(-2147483648 2147483647)
 * long 8(-9223372036854775808, 9223372036854775807)
 * char 2()
 * boolean 1
 */
class TestRange{
	public static void main(String[] args)
	{
		/**
	     * The constant value of this field is the smallest value of type
	     * {@code char}, {@code '\u005Cu0000'}.
	     *
	     * @since   1.0.2
	     */
	    
	    char charMIN_VALUE = '\u0000';
	    System.out.println("char min: " + charMIN_VALUE);


	    /**
	     * The constant value of this field is the largest value of type
	     * {@code char}, {@code '\u005CuFFFF'}.
	     *
	     * @since   1.0.2
	     */
	    char charMAX_VALUE = '\uFFFF';
	    System.out.println("char max: " + charMAX_VALUE);
		
		/**
	     * A constant holding the minimum value a {@code short} can
	     * have, -2<sup>15</sup>.
	     */
		short   shortMIN_VALUE = -32768;
		System.out.println("short min: " + shortMIN_VALUE);

	    /**
	     * A constant holding the maximum value a {@code short} can
	     * have, 2<sup>15</sup>-1.
	     */
	    short   shortMAX_VALUE = 32767;
	    System.out.println("short max: " + shortMAX_VALUE);


    	/**
     	* A constant holding the minimum value an {@code int} can
     	* have, -2<sup>31</sup>.
     	*/
     	int   intMIN_VALUE = 0x80000000;
     	System.out.println("int min: " + intMIN_VALUE);

	    /**
	     * A constant holding the maximum value an {@code int} can
	     * have, 2<sup>31</sup>-1.
	     */
	    int   intMAX_VALUE = 0x7fffffff;
	    System.out.println("int max: " + intMAX_VALUE);

	    /**
	     * A constant holding the minimum value a {@code long} can
	     * have, -2<sup>63</sup>.
	     */
	    long longMIN_VALUE = 0x8000000000000000L;
	    System.out.println("long min: " + longMIN_VALUE);

	    /**
	     * A constant holding the maximum value a {@code long} can
	     * have, 2<sup>63</sup>-1.
	     */
	    long longMAX_VALUE = 0x7fffffffffffffffL;
	    System.out.println("long max: " + longMAX_VALUE);
	}
}
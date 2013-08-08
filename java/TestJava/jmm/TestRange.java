/**
 * 
 */
class TestRange{
	public static void main(String[] args)
	{
		/*1个字节*/
		byte 			byte_max1 	= 127; 			 System.out.printf("byte max\t%d\n", byte_max1);
		byte 			byte_min1 	= -128;			 System.out.printf("byte min\t%d\n", byte_min1);
		// byte 		byte_max2 	= 127+1; 		 System.out.printf("byte max+1\t%d\n", byte_max2);
		// byte 		byte_min2 	= -128-1; 		 System.out.printf("byte min-1\t%d\n\n", byte_min2);

		/*2个字节,相当于无符号int*/
		char 			char_max1 	= 65535; 		 System.out.printf("char max\t%d\n", (int)char_max1);
		char 			char_min1 	= 0;			 System.out.printf("char min\t%d\n", (int)char_min1);
		// char 			char_max2	= 65535+1;		 System.out.printf("char max+1\t%d\n", char_max2);
		// char 			char_min2	= 0-1;			 System.out.printf("char min-1\t%d\n\n", char_min2);

		/* 2个字节=1024*64 */
		short 			short_max1 	= 32767; 		 System.out.printf("short max\t%d\n", short_max1);	
		short 			short_min1 	= -32768; 		 System.out.printf("short min\t%d\n", short_min1);	
		// short 			short_max2 	= 32767+1;		 System.out.printf("short max+1\t%d\n", short_max2);
		// short 			short_min2 	= -32768-1;		 System.out.printf("short min-1\t%d\n\n", short_min2);	

		/*java无无符号类型*/

		/*4个字节=1024*1024*1024*2 */
		int 			int_max1	= 2147483647; 	 System.out.printf("int max \t%d\n", int_max1);	
		int 			int_min1	= -2147483648; 	 System.out.printf("int min \t%d\n", int_min1);	
		// int 			int_max2	= 2147483647+1;  System.out.printf("int max+1\t%d\n", int_max2);	
		// int 			int_min2	= -2147483648-1; System.out.printf("int min-1\t%d\n\n", int_min2);	

		/*8个字节=1024*1024*1024*1024*1024*1024*8-1 */
		System.out.println("!!!!!"+(1024*1024*1024*2));//溢出,要加上l
		System.out.println("!!!!!"+(1024*1024*1024*2l));
		System.out.println("!!!!!"+(1024l*1024l*1024l*1024l*1024l*1024l*8-1));
		long 			long_max1	= 9223372036854775807l; 	 System.out.printf("long max\t%d\n", long_max1);
		long 			long_min1	= -9223372036854775808l; 	 System.out.printf("long min\t%d\n", long_min1);
		// long 			long_max2	= 9223372036854775807+1;  System.out.printf("long max+1\t%d\n", long_max2); 
		// long 			long_min2	= -9223372036854775808-1; System.out.printf("long min-1\t%d\n\n", long_min2); 		

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
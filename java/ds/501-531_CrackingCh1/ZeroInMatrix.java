/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column is set to 0.
 *
 * Test:
 * 3 4
 * 1 0 3 4
 * 1 2 0 0
 * 1 2 3 4 
 * @author xmtsui
 */
import java.util.Scanner;
class ZeroInMatrix{
	static int[][] getInputMatrix()
	{
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int col = sc.nextInt();

		int[][] a = new int[row][col];
		for(int i=0; i<row; ++i)
		{
			for(int j=0; j<col; ++j)
			{
				a[i][j] = sc.nextInt();
			}
		}

		for(int i=0; i<row; ++i)
		{
			for(int j=0; j<col; ++j)
			{

				System.out.print(a[i][j]);
			}
			System.out.println("");
		}

		return a;
	}

	static void doSetZero(int[][] a)
	{
		System.out.println("do set zero...");
		int row = a.length;
		int col = a[0].length;
		System.out.println(row+" "+col);

		boolean[] row_record = new boolean[row];
		boolean[] col_record = new boolean[col];

		//get flag, cost o(m+n)
		for(int i=0; i<row; ++i)
		{
			for(int j=0; j<col; ++j)
			{
				if(a[i][j] == 0)
				{
					row_record[i] = true;
					col_record[j] = true;			
				}
			}
		}

		/*//set rows
		for(int i=0; i<row; ++i)
		{
			if(row_record[i] == true)
			{
				for(int j=0; j<col; ++j)
				{
					a[i][j] = 0;
				}
			}
		}

		//set cols
		for(int j=0; j<col; ++j)
		{
			if(col_record[j] == true)
			{
				for(int i=0; i<row; ++i)
				{
					a[i][j] = 0;
				}
			}
		}*/

		//set rows and cols
		for(int i=0; i<row; ++i)
		{
			for(int j=0; j<col; ++j)
			{
				if(row_record[i] || col_record[j])
				{
					a[i][j] = 0;
				}
			}
		}

		//print
		for(int i=0; i<row; ++i)

		{
			for(int j=0; j<col; ++j)
			{

				System.out.print(a[i][j]);
			}
			System.out.println("");
		}
	}

	public static void main(String[] args)
	{
		doSetZero(getInputMatrix());
	}
}
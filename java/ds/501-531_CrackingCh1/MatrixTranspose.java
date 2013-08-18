/**
 * Transpose matrix
 *
 * Test:
 * 3
 * 3 2 1
 * 2 3 4
 * 3 4 5
 * @author xmtsui
 */
import java.util.Scanner;
class doMatrixTranspose{
	static int[][] getInputMatrix(){
		Scanner sc = new Scanner(System.in);
		int row, column;
		row = column = sc.nextInt();
		int[][] a = new int[row][column];
		for(int i=0; i<row; i++)
		{
			for(int j=0; j<column; j++)
			{
				a[i][j] = sc.nextInt();
			}
		}

		for(int i=0; i<row; i++)
		{
			for(int j=0; j<column; j++)
			{
				System.out.print(a[i][j]);
			}
			System.out.println("");
		}
		return a;
	}

	static void doMatrixTranspose(int[][] a)
	{
		System.out.println("do transpose...");
		int size = a[0].length;
		for(int i=0; i<size; i++)
		{
			for(int j=i; j<size; j++)
			{
				if(i != j)
				{
					a[i][j] = a[i][j] ^ a[j][i];
					a[j][i] = a[i][j] ^ a[j][i];
					a[i][j] = a[i][j] ^ a[j][i];
					// int tmp = a[i][j];
					// a[i][j] = a[j][i];
					// a[j][i] = tmp;
				}
			}
		}

		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{
				System.out.print(a[i][j]);
			}
			System.out.println("");
		}
		return a;
	}

	public static void main(String[] args)
	{
		doMatrixTranspose(getInputMatrix());
	}
}
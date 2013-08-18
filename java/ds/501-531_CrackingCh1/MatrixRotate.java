/**
 * Given an image represented by an NxN matrix,
 * where each pixel in the image is 4 bytes, 
 * write a method to rotate the image by 90 degrees. 
 * Can you do this in place?
 *
 * Test:
 * 3
 * 3 2 1
 * 2 3 4
 * 3 4 5
 * @author xmtsui
 */
import java.util.Scanner;
class MatrixRotate{
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

	static int[][] doMatrixRotate(int[][] a)
	{
		System.out.println("do rotate...");
		int size = a.length;
		for(int layer=0; layer<size>>1; ++layer)
		{
			int start = layer;
			int end = size - 1 - layer;

			for(int i=start; i<end; ++i)//注意理解循环次数
			{
				System.out.println("now: " + i);
				//store top
				int top = a[start][i];
				//left to top
				a[start][i] = a[end-i][start];
				//bottom to left
				a[end-i][start] = a[end][end-i];
				//right to bottom
				a[end][end-i] = a[i][end];
				//top to right
				a[i][end] = top;
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
		doMatrixRotate(getInputMatrix());
	}
}
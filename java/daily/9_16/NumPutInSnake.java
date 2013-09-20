public class NumPutInSnake{
	public static int[][] doPut(int n)
	{
		if(n<=0)
			return null;
		int tmp = n*n;
		int layer=0;
		while(tmp>=0)
		{
			tmp-=layer;
			layer++;
		}
		layer--;
		System.out.println("layer: "+layer);
		int num=0,limit=n*n;
		int[][] matrix = new int[layer+1][layer+1];
		for(int i=0; i<=layer; ++i)
		{
			if(i%2==0)
			{
				for(int j=0; j<=i; ++j)
				{
					if(num<=limit)
						matrix[j][i-j]=num++;
					else
						return matrix;
				}
			}
			else
			{
				for(int j=0; j<=i; ++j)
					if(num<=limit)
						matrix[i-j][j]=num++;
					else
						return matrix;
			}
		}
		return matrix;
	}

	public static void main(String[] args)
	{
		int[][] a = doPut(10);
		for(int[] item : a)
		{
			for(int item1 : item)
				System.out.printf("%02d ", item1);
			System.out.println();
		}
	}
}
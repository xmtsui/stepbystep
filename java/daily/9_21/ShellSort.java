class ShellSort implements Sort{
	@Override
	public void doSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		int increment = 1;
		while(increment <= len/3)
			increment = increment * 3 + 1;
		int k=0;
		do{
			for(int i=increment; i<len; i++)
			{
				if(i>=increment && a[i]<a[i-increment])
				{
					int tmp = a[i];
					int index = i;
					while(index>=increment && tmp<a[index-increment])
					{
						a[index] = a[index-increment];
						index-=increment;
					}
					a[index] = tmp;
				}
			}
			System.out.print("第"+ (++k) + "趟排序结果: ");
			for(int item : a)
				System.out.print(item+" ");
			System.out.println();
			increment = (increment-1)/3;
		}while(increment>=1);
	}
}
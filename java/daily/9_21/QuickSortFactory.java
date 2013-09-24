class QuickSortFactory extends SortFactory{
	@Override
	public Sort createSort()
	{
		return new QuickSort();
	}
}
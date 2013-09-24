class HeapSortFactory extends SortFactory{
	@Override
	public Sort createSort()
	{
		return new HeapSort();
	}
}
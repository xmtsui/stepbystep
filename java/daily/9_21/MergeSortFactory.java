class MergeSortFactory extends SortFactory{
	@Override
	public Sort createSort()
	{
		return new MergeSort();
	}
}
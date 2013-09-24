class ShellSortFactory extends SortFactory{
	@Override
	public Sort createSort()
	{
		return new ShellSort();
	}
}
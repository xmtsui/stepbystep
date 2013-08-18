public class Sample {
	private Sample instance;
	
	public void setSample(Object instance) {
		this.instance = (Sample) instance;
	}
	
	public boolean testInstanceOf(Object instance){
		return instance instanceof Sample;
	}

	@Override
	public boolean equals(Object obj){
		return true;
	}

	@Override
	public int hashCode()
	{
		return 1;
	}
}

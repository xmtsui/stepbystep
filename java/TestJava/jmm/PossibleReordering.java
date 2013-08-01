class PossibleReordering{
	static int x=0, y=0;
	static int a=0, b=0;
	public static void main(String[] args) throws InterruptedException
	{
		Thread one = new Thread(new Runnable(){
			public void run(){
				a = 1;
				x = b;

				loop(0);
			}

			private void loop(int i)
			{
				if(i != 1000)
				{
					i++;
					loop(i);
				}
				else
				{
					return;
				}
			}
		});

		Thread other = new Thread(new Runnable(){
			public void run(){
				b = 1;
				y = a;
			}
		});

		one.start();
		other.start();
		// one.suspend();
		// one.wait(1);
		// one.join(1000);
		// other.join(1000);
		System.out.println("(" + x + "," + y + ")");

		// other.sleep(2000);
		// one.resume();
	}
}
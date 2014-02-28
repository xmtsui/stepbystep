class ExceptioninFor{
	public static void main(String[] args)
	{
		try{
			String[] str = new String[10];
			for(int i=0; i<10; ++i)
			{
				//if(i==1)
					//throw new RuntimeException();
				Exception e1 = new Exception();
				e1.initCause(new NullPointerException());
				if(i==2)
					throw e1;
				str[i] = Integer.toString(i);
			}
			/*
			try{
				if(i==1)
					throw new RuntimeException();
				str[i] = Integer.toString(i);
			}catch (RuntimeException e)
			{
				str[1] = "runtime exception";
			}
			catch (Exception e )
			{
				str[1] = "exception";
			}*/
			for(String item : str)
			{
				System.out.println(item);
			}
		}catch(Exception e)
		{
			System.out.println(e.getCause().getCause());
		}
	}
}
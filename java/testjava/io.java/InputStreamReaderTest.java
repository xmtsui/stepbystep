class InputStreamReaderTest{
	public static void main(String[] args)
	{
		System.out.println(System.getProperty("line.separator"));
		InputStreamReader isr = new InputStreamReader(System.in);
		try{
			char[] buffer = new char[1024];
			int readed = isr.read(buffer, 0, 1024);
			if(readed > 0)
				System.out.print(new String(buffer));
			System.out.println(isr.getEncoding());
			BigInteger bi = new BigInteger("1");
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
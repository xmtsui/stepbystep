class HashMap_Test{
	public static void main(String[] args)
	{
		System.out.println(hash(0));
		System.out.println(hash(1));
		System.out.println(hash(8));
		System.out.println(hash(1024));


		Map<String,String> valueMap = new HashMap<String,String>
		for(String valName :valueMap.keySet())  
		{  
			String key =valName;  
			Object value = valueMap.get(valName);        
		}  

		Set<Map.Entry<String, String>> keyEntrySet = valueMap.entrySet();  
		for(Map.Entry<String, String> entry:keyEntrySet)  
		{  
			String key = entry.getKey();  
			Object value = entry.getValue();  

		}

		Map<String,String> questionsMap= new HashMap<String,String>
		Iterator<Entry<String,String>> questions = questionsMap.entrySet().iterator();
		while (questions.hasNext()) {
			Entry<String, String> question = questions.next();
			question.getValue()
			question.getKey()

		}
	}

	static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	static int indexFor(int h, int length) {
		return h & (length-1);
	}
}


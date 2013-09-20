def fab(a : Int) : Int = {
	if(a<=0) 0; else if(a==1) 1; else if(a==2) 1; else fab(a-2) + fab(a-1)
}

for(i <- 1 to 6)
	printf("%d ", fab(i))
printf("\n")
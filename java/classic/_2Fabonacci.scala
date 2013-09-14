def fab(a : Int) : Int = {
	if(a<=0) 0; else if(a==1) 1; else if(a==2) 1; else fab(a-2) + fab(a-1)
}

for(i <- 1 to 6)
	printf("%d", fab(i))
printf("\n")


//scala尾递归 http://en.literateprograms.org/Fibonacci_numbers_(Scala)
def fab_tail(a : Int) : Int = tail(a, 1, 1)
def tail(a:Int, i:Int, j:Int) : Int = {
	if(a==1)
		j
	else
		tail(a-1, i+j, i)
}
for(i <- 1 to 6)
	printf("%d", fab_tail(i))
printf("\n")
// def sum(a : Int) : Int = { if(a==0) 0; else a + sum(a-1)}
def sum(a : Int) = {var tmp = 0 ; for(i <- 0 to a) {tmp+=i}; tmp}
def sum(a : Int, codeBlock: Int => Int) = {var res=0; for(i<-0 to a) res+=codeBlock(i);res}
println(sum(10))
println(sum(10, i=>if(i%2 == 0) 1 else 0))//统计偶数个数
println(sum(10, i=>if(i%2 != 0) 1 else 0))//统计奇数个数
println(sum(10, i=>if(i%2 == 0) i else 0))//只计算偶数和


def check1() = true
def check2() : Boolean = true
// def check3() = return true //ERROR, Must declare return type
def check3() : Boolean = return true
println(check1)
println(check2)
println(check3)
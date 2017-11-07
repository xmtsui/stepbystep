/**
 * scalac Sample.scala
 * scala Sample
 * java -cp /Users/saixiaomin/Downloads/opensource/scala-2.10.2/lib/scala-library.jar:. Sample
 */
object Sample {
  def main(args : Array[String]) = {
  	def add(i : Int, j : Int) : Int = i + j
  	println("Hello Scala" + add(2,3))
  	var h = new hehe(1)
  	println(h.dd)
  }
}

private[this] case class hehe(dd: Int)

// object HelloWorld {
	// def main(args:Array[String]) = println("Hello world, Welcome to Scala: 崔晓旻 " + args(0))
// }

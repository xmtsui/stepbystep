[ -f "$1.class" ] && rm $1.class
for file in $1.java
do
echo "Compiling $file........"
javac $file
done
if [ -f "$1.class" ]
then
echo "-----------OUTPUT-----------"
java $1
else
echo " "
fi
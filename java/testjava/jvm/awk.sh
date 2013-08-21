#/usr/bin/awk

#打印行号
#awk '{ print NR}' test.txt

#打印字段总数
#awk '{ print NF}' test.txt

# if(NR>=4) {print  $1 "\t" $2 "\t" $3-1 "\t" $4}}' test.txt
# echo \`jps\` | awk '{if($2=="Jps") print $1}'
# echo $jps

#jmap -histo 15602

awk '{ if(NR>=4) {print $1 "\t\t\t\t" $2 "\t\t\t\t" $3/1024 "kb" "\t\t\t\t" $4}}' test.txt > analysis.txt
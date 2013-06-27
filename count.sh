#!/bin/bash
echo counting...
echo ========
find . | grep ".*\.\(java\|c\|h\|cpp\|py\|txt\)$" | xargs wc -l | grep total | awk '{ print $1; }'
# find . ".*\.\(java\|c\|h\|cpp\|py\|txt\)$" | xargs wc -l | grep total | awk '{ print $1; }'

#jc=`find . -name "*.java"  | xargs wc -l | grep total | awk '{ print $1; }'`
#cc=`find . -name "*.c" | xargs wc -l | grep total | awk '{ print $1; }'`
#cppc=`find . -name "*.cpp" | xargs wc -l | grep total | awk '{ print $1; }'`
# result=$[$jc+$cc+$cppc]
# if [ -n "$result" ]; then
# 	echo "total: $result"
# fi
#
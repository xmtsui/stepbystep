#/usr/bin/awk
awk '{if($1=="public") print $0 }' 1.txt
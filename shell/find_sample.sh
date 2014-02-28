#!bin/bash
find . -path "*test*" -a -prune -a -type f -o -name "*.java" -a -type f|xargs grep "createSession" | more
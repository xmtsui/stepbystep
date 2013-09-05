#!/bin/bash
#查找包含某些关键词的类名
find . -name *.java | xargs grep -i "contextClassLoader" --exclude "*awt*" | awk '{print $1}'
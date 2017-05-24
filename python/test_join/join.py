import os
import commands
file1 = open("excel1.txt")
file2 = open("excel2.txt")

try:
  lines = file1.readlines()
  a=""
  for line in lines:
    a = a+line
    print "%s" % (a)
finally:
  file1.close()
  file2.close()

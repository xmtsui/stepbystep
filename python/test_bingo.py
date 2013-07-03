#! /usr/bin/python
# Filename: test_bingo.py
# Author: xmtsui
# Date: 2013/07/02
from random import randint
num = randint(1,10)
print 'guess the number'
bingo = False
while bingo == False:
	answer = input()
	if answer > num:
		print "larger"
	elif answer < num:
		print "smaller"
	else:
		print "bingo"
		bingo = True

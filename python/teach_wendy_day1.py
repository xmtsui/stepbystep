#!/usr/bin/python
# coding=utf-8
# Filename : crawl.py
import urllib2
from sgmllib import SGMLParser

class ListTD(SGMLParser):
	def __init__(self):
		SGMLParser.__init__(self)
		self.is_td = ""
		self.name = []
	def start_td(self, attrs):
		self.is_td=1
		#if(len(attrs)>=2 and attrs[0][1] == 'middle'):
		#	self.is_td=1
	def end_td(self):
		self.is_td = ""
	def handle_data(self, text):
		if self.is_td == 1:
			self.name.append(text.strip().replace("\r\n", ''))

url = 'http://www1.sac.net.cn/newcn/inquire/inquire_object.jsp?type=TJ'
content = urllib2.urlopen(url).read()
listtd = ListTD()
listtd.feed(content)
for item in listtd.name:
	print item
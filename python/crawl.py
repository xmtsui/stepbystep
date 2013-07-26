#!/usr/bin/python
# coding=utf-8
# Filename : crawl.py
import urllib
import urllib2
import time
from sgmllib import SGMLParser

class ListTD(SGMLParser):
	def __init__(self):
		SGMLParser.__init__(self)
		self.is_td = ""
		self.name = []
	def start_td(self, attrs):
		# self.is_td=1
		if(len(attrs)>=2 and attrs[0][1] == 'middle'):
			self.is_td=1
	def end_td(self):
		self.is_td = ""
	def handle_data(self, text):
		if self.is_td == 1:
			self.name.append(text.strip().replace("\r\n", ''))

for page in range(0,46):
	# 连续请求会失败，sleep越久效果越好
	# time.sleep()
	url = 'http://www1.sac.net.cn/newcn/inquire/inquire_object.jsp?'
	values = { 
		'orderBy' : 'asc',
		'page' : page,
		'name' : '',
		'type' : 'TJ',
		'turnPage' : 'true'
	} 
	postdata = urllib.urlencode(values)
	# print postdata
	headers = {
		'Host' : 'www1.sac.net.cn',
		'Connection' : 'keep-alive',
		'Content-Length' : '44',
		'Cache-Control' : 'max-age=0',
		'Accept' : 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
		'Origin' : 'http://www1.sac.net.cn',
		'User-Agent' : 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36',
		'Content-Type' : 'application/x-www-form-urlencoded',
		'Referer' : 'http://www1.sac.net.cn/newcn/inquire/inquire_object.jsp?type=TJ',
		'Accept-Encoding' : 'gzip,deflate,sdch',
		'Accept-Language' : 'zh-CN,zh;q=0.8',
		'Cookie' : 'JSESSIONID=0000AdD4jEmEpchUDNOxhRDSPFL:-1'
	}

	req = urllib2.Request(url,postdata,headers) 
	try:
		response = urllib2.urlopen(req)
	except URLError as e:
		print e.reason
	except urllib2.HTTPError as e:
		print e.code
		print e.read()

	# print postdata
	# content = urllib2.urlopen(url).read()
	# print content
	
	content = response.read()
	listtd = ListTD()
	listtd.feed(content)
	i = 0
	for item in listtd.name:
		#print "len", len(item)
		#print "item",item
		if item.isdigit() and len(item) != 0:
			print item,
			print listtd.name[i+1].strip().replace("\r\n", ''),
			print listtd.name[i+2].strip().replace("\r\n", ''),
			print listtd.name[i+3].strip().replace("\r\n", ''),
			print listtd.name[i+4].strip().replace("\r\n", ''),
			print listtd.name[i+5].strip().replace("\r\n", '')
		i+=1
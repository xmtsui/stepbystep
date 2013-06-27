#!/usr/bin/python
# Filename : crawl.py
import urllib2
import socket

class RedirectHandler(urllib2.HTTPRedirectHandler):
    def http_error_301(self, req, fp, code, msg, headers):
        pass
    def http_error_302(self, req, fp, code, msg, headers):
        pass

print '----'
try:
	request = urllib2.Request('http://gkcx.eol.cn/schoolhtm/schoolAreaPoint/31/li/schoolAreaPoint31_sichuan_li.htm')
	request.add_header('User-Agent', 'fake-client')
	response = urllib2.urlopen(request, timeout=10)
	print response.read()
except urllib2.HTTPError, e:
    print e.code
print '----'


'''
import urllib2
from sgmllib import SGMLParser
class ListTD(SGMLParser):
	def __init__(self):
		SGMLParser.__init__(self)
		self.is_td = ""
		self.name = []
	def start_td(self, attrs):
		self.is_td= 1
	def end_td(self):
		self.is_td = ""
	def handle_data(self, text):
		if self.is_td == 1:
			self.name.append(text)
# url='http://edu.sina.com.cn/gaokao/2013-06-04/1044382259.shtml'
# url='http://www.nasdaq.com/symbol/qlys/short-interest'
url='http://gkcx.eol.cn/schoolhtm/schoolAreaPoint/31/li/schoolAreaPoint31_sichuan_li.htm'
try:
	response=urllib2.urlopen(url).read()
	print response
except urllib2.HTTPError,e:
	print e.code
except urllib2.URLError,e:
	print e.reason
print '---'
listtd = ListTD()
listtd.feed(response)
for item in listtd.name:
	print item
'''
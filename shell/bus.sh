#!/bin/bash -e
if [ "$#" -eq "2" ]; then
	curl "http://www.10628106.com/ADSB/BusLocation.aspx?busNum=$1&GpsSite=$2" | grep '开往'
# | xargs python /Users/saixiaomin/ws/gitws/python/html2text/html2text.py utf8 | sed -e 's/[\t ]//g'
else
	echo "Usage: $0 BusNumber Station"
	echo "Example:"
	echo "$0 1 昭觉横路"
fi
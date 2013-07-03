#! /usr/bin/python
# Filename: test_input.py
yourname = raw_input("please input your name: ")
hint = "Name: %s " % yourname
print hint

contact_list = []
contact1 = {}
contact1['name'] = 'tsui'
contact1['phone'] = 15882402022

contact_list.append(contact1)

contact2 = {}
contact2['name1'] = 'wendy'
contact2['phone'] = 15882402021

contact_list.append(contact2)

print contact_list
use crashcourse;

#################第四章 检索数据 SELECT语句#######################
SELECT * FROM products;

#检索不同的行
SELECT DISTINCT vend_id FROM products;

#限制结果
SELECT prod_name FROM products LIMIT 5;#限制不多于五行
SELECT prod_name FROM products LIMIT 5,5;#从第五行开始

SELECT prod_name FROM products LIMIT 0,1;#第0行开始的1行
SELECT prod_name FROM products LIMIT 1 offset 0;#第0行开始的1行

SELECT prod_name FROM products LIMIT 1,1;#第1行开始的1行
SELECT prod_name FROM products LIMIT 1 offset 1;#第1行开始的1行

##################第五章 排序检索数据#######################
SELECT prod_name FROM products order by prod_name;
SELECT prod_id, prod_price, prod_name FROM products ORDER BY prod_name DESC;#降序
SELECT prod_id, prod_price, prod_name FROM products ORDER BY prod_name, prod_price ASC;#升序,默认
SELECT prod_id, prod_price, prod_name FROM products ORDER BY prod_price, prod_name;

SELECT prod_price FROM products ORDER BY prod_price DESC LIMIT 1;#求最贵的，注意orderby子句位置，from之后

##################第六章 过滤数据 WHERE#######################
SELECT prod_name, prod_price FROM products WHERE prod_price = 2.50;#同时使用where 和orderby的话，order by位置在where之后
#where 操作符 = <> != < <= > >= BETWEEN
SELECT prod_name, prod_price FROM products WHERE prod_price <= 10;
SELECT vend_id, prod_name FROM products WHERE vend_id <> 1003;
SELECT prod_name, prod_price FROM products WHERE prod_price BETWEEN 5 AND 10;#包括边界值
SELECT prod_name FROM products WHERE prod_price IS NULL;
SELECT cust_id FROM customers WHERE cust_email IS NULL;#检索文件中顾客电子邮件地址为空的id

##################第七章 数据过滤 组合WHERE子句################
#AND
#检索1003制造的小于等于10块钱的行
SELECT prod_id, prod_price, prod_name FROM products WHERE vend_id = 1003 AND prod_price <= 10;
#OR
SELECT prod_name, prod_price FROM products WHERE vend_id = 1002 OR vend_id = 1003;
#and 的优先级高于or
SELECT prod_name, prod_price FROM products WHERE vend_id = 1002 OR vend_id = 1003 AND prod_price >= 10;
SELECT prod_name, prod_price FROM products WHERE (vend_id = 1002 OR vend_id = 1003) AND prod_price >= 10;

#IN(功能与or一样，指定要匹配值的清单的关键字)更直观，执行更快，可以包含其他select语句
SELECT prod_name, prod_price FROM products WHERE vend_id IN (1002, 1003) ORDER BY prod_name;
#NOT（否定之后的任何条件）IN, BETWEEN, EXISTS
SELECT prod_name, prod_price FROM products WHERE vend_id NOT IN (1002, 1003) ORDER BY prod_name;

###################第八章 通配符进行过滤######################
#LIKE 匹配多个字符，0个或者1个，或者多个
SELECT prod_name, prod_price FROM products WHERE prod_name LIKE 'jet%';#注意尾空格，最后加一个％或者使用函数解决
SELECT prod_name, prod_price FROM products WHERE prod_name LIKE '%anvil%';
SELECT prod_name, prod_price FROM products WHERE prod_name LIKE 's%e';
#下划线_ 匹配单个字符，0个或者1个
SELECT prod_name, prod_price FROM products WHERE prod_name LIKE '_ ton anvil';
SELECT prod_name, prod_price FROM products WHERE prod_name LIKE '% ton anvil';#%放在开始出，搜索起来是最慢的，应该避免

##################第九章 用正则表达式进行搜索#################
#mysql仅支持一个小的子集
#基本字符匹配
SELECT prod_name FROM products WHERE prod_name REGEXP '1000' ORDER BY prod_name;
SELECT prod_name FROM products WHERE prod_name LIKE '%000' ORDER BY prod_name;#LIKE只能匹配整个列，REGEXP可以通过^$达到相同的效果
SELECT prod_name FROM products WHERE prod_name REGEXP '.000' ORDER BY prod_name;
SELECT prod_name FROM products WHERE prod_name REGEXP BINARY 'jetPack .000' ORDER BY prod_name;#BINARY区分大小写
SELECT prod_name FROM products WHERE prod_name REGEXP BINARY 'jetPack .000' ORDER BY prod_name;
#进行OR匹配
SELECT prod_name FROM products WHERE prod_name REGEXP '1000|2000' ORDER BY prod_name;
#匹配几个字符之一
SELECT prod_name FROM products WHERE prod_name REGEXP '[123] Ton' ORDER BY prod_name;
SELECT prod_name FROM products WHERE prod_name REGEXP '[1|2|3] Ton' ORDER BY prod_name;
SELECT prod_name FROM products WHERE prod_name REGEXP '1|2|3 Ton' ORDER BY prod_name;#注意结合规律，此处为1或2或3 Ton
SELECT prod_name FROM products WHERE prod_name REGEXP '[^123] Ton' ORDER BY prod_name;#匹配除这些字符之外的
#匹配范围 ［0-9］［a－z］
SELECT prod_name FROM products WHERE prod_name REGEXP '[1-5] Ton' ORDER BY prod_name;
#匹配特殊字符 .匹配任意字符,若匹配. 则需要转义
#注意大多正则表达式使用单个反斜杠转义特殊字符。mysql需要两个，mysql自己解释一个，正则表达式库解释一个
SELECT vend_name FROM vendors WHERE vend_name REGEXP '.' ORDER BY vend_name;
SELECT vend_name FROM vendors WHERE vend_name REGEXP '\\.' ORDER BY vend_name;
SELECT vend_name FROM vendors WHERE vend_name REGEXP '[:blank:]' ORDER BY vend_name;
#匹配字符类
#[:alnum:]任意字母和数字
#[:alpha:]任意字符
#[:blank:]空格和制表符[\\t]
#[:cntrl:]ascii控制字符（0～31和127）
#[:digit:]任意数字\d
#[:graph:]与[:print:]相同，但不包括空格
#[:print:]任意可打印字符
#[:lower:]任意小写[a-z]
#[:upper:]任意大写[A-Z]
#[:punct:]既不在alnum又不在cntrl中的任意字符
#[:space:]包括空格在内的任意空白[\\f\\n\\r\\t\\v] \s
#[:xdigi:]任意16进制数字同[a-fA-F0-9]
#[::]#[::]#[::]

#匹配多个实例
# * 0个或多个
# + 1个或多个 {1,}
# ? 0个或1个 {0,}
# {n} 指定数目的匹配
# {n,} 不少于指定数目的匹配，大于等于
# {n,m} 匹配数目的范围（m不超过255）
SELECT prod_name FROM products WHERE prod_name REGEXP '\\([0-9] sticks?\\)' ORDER BY prod_name;#匹配sticks中的s0次或1次
SELECT prod_name FROM products WHERE prod_name REGEXP '[[:digit:]]{4}' ORDER BY prod_name;#匹配任意数字4次
SELECT prod_name FROM products WHERE prod_name REGEXP '[0-9][0-9][0-9][0-9]' ORDER BY prod_name;#功能同上

#定位符
# ^ 文本的开始
# $ 文本的结尾
# [[:<:]] 词的开始
# [[:>:]] 词的结尾
SELECT prod_name FROM products WHERE prod_name REGEXP '^[0-9\\.]' ORDER BY prod_name;#以一个数，包括以小数点开始的数开始的所有产品

# 简单的正则表达式测试
SELECT 'HELLO WORLD' REGEXP '^[a-z0-9]';

####################第十章 创建计算字段####################
SELECT Concat(vend_name, ' (', vend_country, ')') FROM vendors ORDER BY vend_name;#其他数据库一般用＋或者｜｜来实现拼接
SELECT Concat(vend_name, ' (', RTrim(vend_country), ')') FROM vendors ORDER BY vend_name;#RTrim去掉串右边的空格，LTrim去掉左边的空格 Trim去掉左右两边的空格
SELECT Concat(RTrim(vend_name), ' (', Rtrim(vend_country), ')') AS vend_title FROM vendors ORDER BY vend_name;#使用别名
SELECT prod_id, quantity, item_price, quantity*item_price AS expanded_price FROM orderitems WHERE order_num = 20005;#算数计算 +-*/

#测试计算
SELECT 3*2;
SELECT Trim('  abc  ');
SELECT Now();

####################第十一章 数据处理函数####################
#1)常用文本处理函数 http://hi.baidu.com/bcpxqz/item/c68d8435b5500c20b2c0c5b3
#Left()串左边的字符
SELECT cust_name, Left(cust_contact,3) FROM customers WHERE cust_contact = 'Y Lee';
#Length() 串的长度
#LOCATE(substr,str) 找出串的一个串
SELECT cust_name, Locate('Lee', cust_contact) FROM customers WHERE cust_contact = 'Y Lee';
#Lower()
#LTrim()
#Right()
#Rtrim
#Soundex
SELECT cust_name, cust_contact FROM customers WHERE Soundex(cust_contact) = Soundex('Y Lie');
#SubString()
#Upper()
#2)日期处理函数
#CurDate()
#Date()
#Day()
#Hour()
#Minute()
#Month()
#Now()
#Second()
#Time()
#Year()
SELECT cust_id, order_num FROM orders WHERE order_date = '2005-09-01';
SELECT cust_id, order_num FROM orders WHERE Date(order_date) = '2005-09-01';
SELECT cust_id, order_num FROM orders WHERE Year(order_date) = '2005' AND Month(order_date) = 9;
#3) 数值处理函数
#Abs()
#Cos()
#Exp()
SELECT Exp(2);
#Mod()
SELECT Mod(2,3);
#Pi()
SELECT Pi();
#Rand()
SELECT Rand();
#Sin()
#Sqrt()
SELECT Sqrt(4);
#Tan()

####################第十二章 聚集函数####################
#汇总数据但不需要检索出来。确定表中的行数（或者满足某个条件或包含某个特定值的行数）
#获得表中行组的和
#找出表列的最大值，最小值和平均值
#聚集函数
#AVG()返回某列的平均值
#COUNT()行数
#MAX()
#MIN()
#SUM()某列之和
SELECT AVG(prod_price) AS avg_price FROM products;#所有产品价格的平均值
SELECT AVG(prod_price) AS avg_price FROM products WHERE vend_id = 1003;#1003产品的平均值

#count()
#1）COUNT(*)对表中行的数目进行技术，不管表列中包含的是空值（NULL）还是非空值
#2）COUNT(column)对特定列中具有值的行进行计数，忽略NULL值
SELECT COUNT(*) AS num_cust FROM customers;#客户总数
SELECT COUNT(cust_email) AS num_cust FROM customers;#统计有电子邮件地址的客户总数

#MAX()忽略NULL
SELECT MAX(prod_price) FROM products;
SELECT MAX(prod_price) AS max_price FROM products;
SELECT prod_name AS max_name FROM products;
SELECT MAX(prod_name) AS max_name FROM products;

#MIN()同MAX()
#SUM()
SELECT SUM(quantity) AS items_ordered FROM orderitems WHERE order_num = 20005;

#聚集不同值 distinct只能用于列名
SELECT AVG(DISTINCT prod_price) AS avg_price FROM products WHERE vend_id = 1003;
#组合聚集函数
SELECT COUNT(*) AS num_items, MIN(prod_price) AS price_min, MAX(prod_price) AS price_max, AVG(prod_price) AS price_avg FROM products;

####################第十三章 分组数据####################
#分组把数据分为多个逻辑组，以便能对每个组进行聚集计算
SELECT COUNT(*) AS num_prods FROM products WHERE vend_id = 1003;
SELECT COUNT(*), prod_id FROM products GROUP BY vend_id;
SELECT prod_id, COUNT(*) AS num_prods FROM products GROUP BY vend_id WITH ROLLUP;
#过滤分组
#两个以上的订单
SELECT cust_id, COUNT(*) AS orders FROM orders GROUP BY cust_id HAVING COUNT(*) >= 2;
#列出两个以上，价格10以上的产品的供应商, WHERE在分组前，HAVING在分组之后
SELECT vend_id, COUNT(*) AS num_prods FROM products WHERE prod_price >= 10 GROUP BY vend_id HAVING COUNT(*) >= 2;
#分组和排序
SELECT * FROM orderitems;
SELECT order_num, SUM(quantity*item_price) AS ordertotal FROM orderitems GROUP BY order_num HAVING SUM(quantity*item_price) >= 50;

#SELECT 顺序
#SELECT 
#WHERE
#GROUP BY
#ORDER BY
#LIMIT

####################第十四章 子查询####################
#利用子查询进行过滤
#列必须匹配，机WHERE子句中的列具有与WHERE子句中相同数目的列
SELECT cust_id FROM orders WHERE order_num IN (SELECT order_num FROM orderitems
	 WHERE prod_id = 'TNT2');
#作为计算字段使用子查询
SELECT cust_name, cust_state, (SELECT COUNT(*) FROM 
	orders WHERE orders.cust_id = customers.cust_id) AS orders
FROM customers
ORDER BY cust_name;

#使用联结操作的话
SELECT cust_id FROM orders INNER JOIN orderitems
ON orders.order_num = orderitems.order_num
AND orderitems.prod_id = 'TNT2';
#WHERE orderitems.prod_id = 'TNT2';#也可以

####################第十五章 创建联结####################
#指定两个表，表明需要做联结
SELECT vend_name, prod_name, prod_price FROM vendors, products WHERE
	vendors.vend_id = products.vend_id
	ORDER BY vend_name, prod_name;
#若没有联结，返回笛卡尔积
SELECT vend_name, prod_name, prod_price FROM vendors, products
	ORDER BY vend_name, prod_name;

#内部联结实现上面相同功能
#ANSI SQL 推荐内部联结语法（等值联结equijoin, 基于两个表之间的相等测试）
SELECT vend_name, prod_name, prod_price FROM vendors INNER JOIN products
	ON vendors.vend_id = products.vend_id
	ORDER BY vend_name, prod_name;

#联结多个表
SELECT prod_name, vend_name, prod_price, quantity
FROM orderitems, products, vendors
WHERE products.vend_id = vendors.vend_id
	AND orderitems.prod_id = products.prod_id
	AND order_num = 20005;
#注意，运行时指定表处理联结是非常耗资源的，因此应该仔细，不要联结不必要的表
#联结的表越多，性能下降越厉害

####################第十六章 创建高级联结####################
#别名出了用于列名和计算字段外，还可以用于表
#表别名
SELECT cust_name, cust_contact FROM customers AS c, orders AS o, orderitems AS oi
WHERE c.cust_id = o.cust_id
	AND oi.order_num = o.order_num
	AND prod_id = 'TNT2';

#自联结
#1)
SELECT prod_id, prod_name FROM products
WHERE vend_id = (SELECT vend_id
				 FROM products
				 WHERE prod_id = 'DTNTR');
#2)(一般情况，联结比处理第一种子查询要快的多)
SELECT p1.prod_id, p1.prod_name FROM products AS p1, products AS p2
WHERE p1.vend_id = p2.vend_id
	AND p2.prod_id = 'DTNTR';
SELECT p1.prod_id, p1.prod_name FROM products AS p1 INNER JOIN products AS p2
ON p1.vend_id = p2.vend_id
	AND p2.prod_id = 'DTNTR';

#自然联结(所有内部联结都是自然联结)
#

#外部联结
#许多联结将一个表的行与另一个表中的行相关联
#但有时候需要包含没有关联行的那些行，此时要用外部联结
SELECT customers.cust_id, orders.order_num
FROM customers INNER JOIN orders
	ON customers.cust_id = orders.cust_id;

#为了检索客户，包括那些没有订单的客户
SELECT customers.cust_id FROM customers;
SELECT orders.order_num  FROM orders;

#外部联结需指定left, right
#left表示左边表选择所有行
#right表示右边表选择所有行
SELECT customers.cust_id, orders.order_num
FROM customers LEFT OUTER JOIN orders
	ON customers.cust_id = orders.cust_id;

SELECT customers.cust_id, orders.order_num
FROM customers RIGHT OUTER JOIN orders
	ON customers.cust_id = orders.cust_id;

SELECT customers.cust_id, orders.order_num
FROM customers RIGHT OUTER JOIN orders
	ON orders.cust_id = customers.cust_id;

#带聚集函数的联结
SELECT customers.cust_name, customers.cust_id, COUNT(orders.order_num) AS num_ord
FROM customers INNER JOIN orders
	ON customers.cust_id = orders.cust_id
	GROUP BY customers.cust_id;

#保证使用正确的联结条件，否则将返回不正确的数据
#应该总是提供联结条件，否则会得出笛卡尔积
#在一个联结中可以包含多个表，甚至对于每个联结可以采用不同的联结类型
#一起测试之前，分别测试每一个联结，可降低故障


####################第十七章 组合查询####################
#mysql允许执行多个查询，并将结果作为单个查询结果返回
#union并,或叫组合查询compund query
#1）单个查询中从不同的表返回类似结构的数据
#2）对单个表执行多个查询，按单个查询返回数据

SELECT vend_id, prod_id, prod_price
FROM products
WHERE prod_price <= 5;

SELECT vend_id, prod_id, prod_price
FROM products
WHERE vend_id IN (1001, 1002);

#union 由两个或者两个以上select语句组成，语句之间用关键字union分割
#每个查询必须包含相同的列，表达式或聚集函数，但不必以相同的次序出现
#列数据类型必须兼容，类型不必完全相同，但必须是DBMS可以隐含地转换的类型（如数值或者日期类型）
SELECT vend_id, prod_id, prod_price
FROM products
WHERE prod_price <= 5
union
SELECT vend_id, prod_id, prod_price
FROM products
WHERE vend_id IN (1001, 1002)
ORDER BY vend_id;
#对组合查询结果排序，需要放在最后一个select语句之后

SELECT vend_id, prod_id, prod_price
FROM products
WHERE prod_price <= 5
	OR vend_id IN (1001, 1002)
	ORDER BY vend_id;

#包含或取消重复的行
#UNION ALL 不取消重复的行
SELECT vend_id, prod_id, prod_price
FROM products
WHERE prod_price <= 5
UNION ALL
SELECT vend_id, prod_id, prod_price
FROM products
WHERE vend_id IN (1001,1002);





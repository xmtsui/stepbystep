#命令别名
alias go='python /Users/saixiaomin/Downloads/goagent/local/proxy.py'
alias mysql='/usr/local/mysql/bin/mysql'
alias mysqldump='/usr/local/mysql/bin/mysqldump'
alias subl='/Applications/Sublime\ Text\ 2.app/Contents/SharedSupport/bin/subl'
alias ha='/Users/saixiaomin/Downloads/opensource/hadoop-1.0.4/bin/hadoop'
alias ma='/Users/saixiaomin/Documents/git_workspace/mahout/trunk/bin/mahout'
export JAVA_HOME

#配置Gnuplot的终端类型
export GNUTERM=x11

#配置输出的编码格式，如javac,date
export LC_ALL=en_US.UTF-8  
export LANG=en_US.UTF-8

#解决javac乱码
export JAVA_TOOL_OPTIONS=-Dfile.encoding=utf-8
#PATH=$PATH:/Users/saixiaomin/Documents/jee_workspace/nutch/apache-nutch-1.6/bin/
#brew安装，配置颜色用到的
if brew list | grep coreutils > /dev/null ; then
  PATH="$(brew --prefix coreutils)/libexec/gnubin:$PATH"
  alias ls='ls -F --show-control-chars --color=auto'
  eval `gdircolors -b $HOME/.dir_colors`
fi

#自定义行编辑模式
set +o emacs
set -o vi

#定制提示符变量
#export PS1='\h:\s:\W \u\$'
export PS1='{\A}:\W tsui\$ '

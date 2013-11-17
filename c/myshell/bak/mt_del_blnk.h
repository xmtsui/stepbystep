#ifndef _MT_DEL_BLNK_H
#define _MT_DEL_BLNK_H
#endif

char *mt_del_blnk(char *buf_cnv)
{
	int i = 0; //counter
	char *tmp;
	while(*buf_cnv)
	{
	if((strcmp(" ",buf_cnv[i])) != 0)
		tmp[i] = buf_cnv[i];
	i++;
	buf_cnv++;
	}	
	printf("%s",tmp);
	buf_cnv = strcpy(buf_cnv,tmp);
	return buf_cnv;
}

#include "network.h"

void sig_alarm(int singo){
	printf("pid: %d stop\n", getpid());
	exit(0);
}

int main()
{
	signal(SIGALRM, sig_alarm);
	if(fork() == 0)
	{
		printf("hello child: %d\n", getpid());
		kill(0, SIGALRM);
	}
	printf("hello parent: %d\n", getpid());
	kill(0, SIGALARM);
	sleep(1);
	return 0;
}
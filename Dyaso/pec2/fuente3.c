#include <stdio.h>
#include <fcntl.h>
#include <stdbool.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/stat.h>

int createSharedMemoryId(key_t key, int size);
int getKeyForFile();

#define MAX_BUF 1024

static const char * FIFO_FILE_NAME = "fichero1";

int main() {
	key_t key;
	char message[MAX_BUF];
	int sharedMemoryId;
	char* vc1;

	printf("el 33 \n");

    key = getKeyForFile();
	    if(key != -1){
		sharedMemoryId = createSharedMemoryId(key, sizeof(message));
		sleep(3);
		vc1 = (char*)shmat(sharedMemoryId, (void *)0, 0);
		if (vc1 == (char *)(-1)) {
	        perror("shmat");
	        exit(1);
    	}
	   	printf("Readed %s\n", vc1);

    } else {
    	printf("Error getting key for file: %s\n", strerror(errno));
    }
	
}

int createSharedMemoryId(key_t key, int size){
  return shmget(key, size, IPC_CREAT | 0600);
}

key_t getKeyForFile(){
  char filePath[1024];
  if (getcwd(filePath, sizeof(filePath)) != NULL){
        strcat(filePath, "/");
        strcat(filePath, FIFO_FILE_NAME);
        return ftok(filePath, 0777);
  } else {
    return (key_t) -1;
  }
}
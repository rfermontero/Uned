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

#define MAX_BUF 1024 * 4

static const char * FILE_NAME_PROCESS_1 = "Ej1";
static const char * FIFO_FILE_NAME = "fichero1";
static const int MESSAGE_TYPE = 1;

//Struct for shared message over MessageQueue
struct Message {
    long id;
    pid_t pid;
};

//Allocate a shared memory id
int createSharedMemoryId(key_t key, int size);
//Get key_t for file
key_t getKeyForFile(const char * file);
//Perform a P operation over operations struct and applies to semaphoreid
int P(int semaphoreId, struct sembuf *operations);
//Perform a V operation over operations struct and applies to semaphoreid
int V(int semaphoreId, struct sembuf *operations);
//Perform a operations over semaphoreId
int applySemaphoreOperation(int semaphoreId, struct sembuf *operations);
//Create a semaphore attached to key
int createSemaphoreId(key_t key);
//Get a message queue id for key
int getMessageQueueId(key_t key);

int main() {

	key_t key;
	key_t messageQueueKey;
	char message[MAX_BUF];
	char* vc1;
	int sharedMemoryId;
	int semaphoreId;
	int semop;
	int messageQueueId;
	int sendop;
  struct sembuf operations[1];
  struct Message messagebuf;

  key = getKeyForFile(FIFO_FILE_NAME);
	if(key != -1){
			sharedMemoryId = createSharedMemoryId(key, sizeof(message));
			semaphoreId = createSemaphoreId(key);
		    semop = P(semaphoreId, operations);
    		vc1 = (char*) shmat(sharedMemoryId, (void *)0 , 0);
			if (vc1 != (char *)(-1)) {
			      printf("Leida variable de memoria compartida ->  %s\n", vc1);
			      shmdt(vc1);
			      messageQueueId = getMessageQueueId(messageQueueKey);
			      if (messageQueueId != -1) {
			      	messagebuf.id = MESSAGE_TYPE;
			      	messagebuf.pid = getpid();
		            sendop = msgsnd(messageQueueId, 
		                (struct msgbuf *) &messagebuf,
		                sizeof(pid_t),
		                IPC_NOWAIT);
		            if(sendop==0){
		                printf("El proceso P3 (PID=%d, Ej3) transmite un mensaje al proceso P1 por una cola de mensajes\n", getpid());
		                pause();
		            } else {
		            	printf("Error enviando mensaje a cola de mensajes: %s\n", strerror(errno));
		            }
				  } else {
            printf("Error abriendo cola de mensajes: %s\n", strerror(errno));
          }
			} else {
				printf("Error leyendo variable de memoria compartida: %s\n", strerror(errno));
			}
    } else {
    	printf("Error obteniendo key para el fichero: %s\n", strerror(errno));
    }
	
}

int createSharedMemoryId(key_t key, int size){
  return shmget(key, size, IPC_CREAT | 0600);
}

key_t getKeyForFile(const char * file) {
  return ftok(file, 0777);
}

int createSemaphoreId(key_t key) {
  return semget(key, 3, IPC_CREAT| 0600);
}

int applySemaphoreOperation(int semaphoreId, struct sembuf *operations){
  return semop(semaphoreId, operations, 1);
}

int P(int semaphoreId, struct sembuf *operations){
  operations[0].sem_num=0;
  operations[0].sem_op=-1;
  operations[0].sem_flg=0;

  return applySemaphoreOperation(semaphoreId, operations);
}

int V(int semaphoreId, struct sembuf *operations){
  operations[0].sem_num=0;
  operations[0].sem_op=+1;
  operations[0].sem_flg=0;

  return applySemaphoreOperation(semaphoreId, operations);
}

int getMessageQueueId(key_t key){
    int messageQueueId;
    key = ftok(FILE_NAME_PROCESS_1, 0777);
    if (key != (key_t)-1) {
       return msgget(key, 0600 | IPC_CREAT);
    } else {
      return -1;
  }
}
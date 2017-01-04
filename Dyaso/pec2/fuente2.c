#include <stdio.h>
#include <fcntl.h>
#include <stdbool.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/ipc.h>

#define MAX_BUF 1024

static const char * FIFO_FILE_NAME = "fichero1";

bool readMessageFromFifo();
int shareContentInMemoryId(int memoryId);
int createSharedMemoryId(int size);
int getKeyForFile(char file);
int createSemaphoreId(key_t key);

int main() {

    char message[MAX_BUF];
    key_t key;
    int sharedMemoryId;
    int semaphoreId;
    char vc1[MAX_BUF];
    struct sembuf Operacion;
    union semun arg;
    int i=0;

    message = readMessageFromFifo();

    if(message==null){
      return -1;
    }

    key = getKeyForFile(FIFO_FILE_NAME);

    if(key != -1){

      sharedMemoryId = createSharedMemoryId(sizeof(message));
      semaphoreId = createSemaphoreId(key);
      arg.val = 0;
      semctl(semaphoreId, 0, SETVAL, &arg);

      if(sharedMemoryId!=-1 && semaphoreId!=-1){
        vc1 = shareContentInMemoryId(sharedMemoryId);
      }

      execl("./Ej3", "Ej3", NULL);
    }

    return 0;
}

int createSharedMemoryId(key_t key, int size){
  return shmget(key, size, IPC_CREAT | 0600);
}

int shareContentInMemoryId(int memoryId){
  return shmat(memoryId, 0,0);
}

int createSemaphoreId(key_t key){
  return semget(key, 1, IPC_CREAT| 0600);
}

int getKeyForFile(char file){
  if (getcwd(filePath, sizeof(filePath)) != NULL){
        strcat(filePath, file);
        return ftok(filePath, 0777);
  } else {
    return -1;
  }
}

bool readMessageFromFifo(){
  int fifo;
  char message[MAX_BUF];
  fifo = open(FIFO_FILE_NAME, O_RDONLY);
  if(fifo < 0) {
    printf("Error opening fifo: %s\n", strerror(errno));
    return null;
  }
  if(fifo == 0) {
    printf("Error opening fifo: %s\n", strerror(errno));
    return null;
  }
  int readResult;
  readResult = read(fifo, message, sizeof(message));
  if(readResult<0){
    printf("Error reading fifo: %s\n", strerror(errno));
    return null;
  }
  if(readResult==0){
    printf("Error reading fifo null: %s\n", strerror(errno));
    return null;
  }
  printf("Mensaje es %s\n", message);
  close(fifo);
  return message;
}
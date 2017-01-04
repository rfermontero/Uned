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

#define MAX_BUF 1024
#define SECONDS 1

static const char * FIFO_FILE_NAME = "fichero1";

void saveMesageInBuffer(char message[]);
char* shareContentInMemoryId(int memoryId);
int createSharedMemoryId(key_t key);
int getKeyForFile();
int createSemaphoreId(key_t key);
void handleFork(pid_t pid);
void writeMessageInSharedVariable(char* dest, char* message);
void closeSemaphore(int semaphoreId, struct sembuf *sops);
void applySemaphoreOperation(int semaphoreId, struct sembuf *sops);
void openSemaphore(int semaphoreId, struct sembuf *operations);

int main() {

    char message[MAX_BUF];
    key_t key;
    int sharedMemoryId;
    int semaphoreId;
    char *vc1;
    char *data;
    pid_t p3;
    struct sembuf operations[1];

    printf("start p2\n");

    saveMesageInBuffer(message);

    if(message==NULL){
      return -1;
    }

    key = getKeyForFile();

    if(key != -1){

      sharedMemoryId = createSharedMemoryId(key);
      semaphoreId = createSemaphoreId(key);
      closeSemaphore(semaphoreId, operations);

      if(sharedMemoryId!=-1 && semaphoreId!=-1){
        vc1 = shareContentInMemoryId(sharedMemoryId);
      }

      switch(p3 = fork()){
        case -1: 
            printf("Error");
            break;
        case 0:
            printf("run\n");
            execl("./Ej3", "Ej3", NULL);
            break;
        default:
            sleep(SECONDS);
            writeMessageInSharedVariable(vc1, message);
            openSemaphore(semaphoreId, operations);
            pause();
            break;
    }

    } else {
       printf("Error getting key for file: %s\n", strerror(errno));
    }

    return 0;
}

void applySemaphoreOperation(int semaphoreId, struct sembuf *operations){
  semop(semaphoreId, operations, 1);
}

void closeSemaphore(int semaphoreId, struct sembuf *operations){
  operations[0].sem_num=1;
  operations[0].sem_op=-1;
  operations[0].sem_flg=0;

  applySemaphoreOperation(semaphoreId, operations);
}

void openSemaphore(int semaphoreId, struct sembuf *operations){
  operations[0].sem_num=1;
  operations[0].sem_op=1;
  operations[0].sem_flg=0;

  applySemaphoreOperation(semaphoreId, operations);
}

void writeMessageInSharedVariable(char *dest, char *message){
  printf("El proceso P2 (PID=%d, Ej2) transmite un mensaje al proceso P3 a traves de una variable en memoria compartida\n", getpid());
  strcat(message, '\0');
  strncpy(dest, message, MAX_BUF);
}

int createSharedMemoryId(key_t key){
  return shmget(key, MAX_BUF, IPC_CREAT | 0600);
}

char* shareContentInMemoryId(int memoryId){
  return shmat(memoryId, (void *)0, 0);
}

int createSemaphoreId(key_t key){
  return semget(key, 1, IPC_CREAT| 0600);
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

void saveMesageInBuffer(char message[]){
  int fifo;
  message[MAX_BUF];
  fifo = open(FIFO_FILE_NAME, O_RDONLY);
  if(fifo < 0) {
    printf("Error opening fifo: %s\n", strerror(errno));
    return;
  }
  if(fifo == 0) {
    printf("Error opening fifo: %s\n", strerror(errno));
    return;
  }
  int readResult;
  readResult = read(fifo, message, sizeof(message));
  if(readResult<0){
    printf("Error reading fifo: %s\n", strerror(errno));
    return;
  }
  if(readResult==0){
    printf("Error reading fifo null: %s\n", strerror(errno));
    return;
  }
  close(fifo);
}
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
#define SECONDS 1

static const char * FIFO_FILE_NAME = "fichero1";

//Save message in buffer defined as a constant FIFO_FILE_NAME
void saveMesageInBuffer(char message[]);
//Write message in shared variable defined as dest
void writeMessageInSharedVariable(char* dest, char* message);
//Attach a shared memory base on memoryId
char* shareContentInMemoryId(int memoryId);
//Allocate a shared memory id
int createSharedMemoryId(key_t key);
//Get key_t for file defined as a constant FIFO_FILE_NAME
key_t getKeyForFile(const char * file);
//Create a semaphore attached to key
int createSemaphoreId(key_t key);
//Perform a P operation over operations struct and applies to semaphoreid
int P(int semaphoreId, struct sembuf *operations);
//Perform a V operation over operations struct and applies to semaphoreid
int V(int semaphoreId, struct sembuf *operations);
//Perform a operations over semaphoreId
int applySemaphoreOperation(int semaphoreId, struct sembuf *operations);

int main() {
    key_t key;
    pid_t p3;
    int sharedMemoryId;
    int semaphoreId;
    int semop;
    char message[MAX_BUF];
    char *vc1;
    char *data;
    struct sembuf operations[1];

    saveMesageInBuffer(message);

    if(message==NULL){
      return -1;
    }

    key = getKeyForFile(FIFO_FILE_NAME);

    if(key != -1){
      sharedMemoryId = createSharedMemoryId(key);
      semaphoreId = createSemaphoreId(key);
      if(sharedMemoryId!=-1 && semaphoreId!=-1 && semop==0){
        vc1 = shareContentInMemoryId(sharedMemoryId);
        switch(p3 = fork()){
          case -1: 
              printf("Error haciendo fork al proceso");
              break;
          case 0:
              execl("./Ej3", "Ej3", NULL);
              break;
          default:
              sleep(SECONDS);
              writeMessageInSharedVariable(vc1, message);
              semop = V(semaphoreId, operations);
              if(semop != 0){
                printf("Error abriendo el semaforo: %s\n", strerror(errno));
              }
              pause();
              break;
        }
      } else {
        printf("Error: %s\n", strerror(errno));
      }

    } else {
       printf("Error: %s\n", strerror(errno));
    }

    return 0;
}

int applySemaphoreOperation(int semaphoreId, struct sembuf *operations){
  int result;
  result = semop(semaphoreId, operations, 1);
  return result;
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

  applySemaphoreOperation(semaphoreId, operations);
}

void writeMessageInSharedVariable(char *dest, char *message){
  printf("El proceso P2 (PID=%d, Ej2) transmite un mensaje al proceso P3 a traves de una variable en memoria compartida\n", getpid());
  strncpy(dest, message, MAX_BUF);
  dest[MAX_BUF-1] = '\0';//strncopy Add a null as final message because strncpy didn't
}

int createSharedMemoryId(key_t key){
  return shmget(key, MAX_BUF, IPC_CREAT | 0600);
}

char* shareContentInMemoryId(int memoryId){
  return shmat(memoryId, (void *)0, 0);
}

int createSemaphoreId(key_t key){
  return semget(key, 3, IPC_CREAT | 0600);
}

key_t getKeyForFile(const char * file){
  return ftok(file, 0777);
}

void saveMesageInBuffer(char message[]){
  int fifo;
  message[MAX_BUF];
  fifo = open(FIFO_FILE_NAME, O_RDONLY);
  if(fifo < 0 || fifo == 0) {
    printf("Error abriendo FIFO: %s\n", strerror(errno));
    return;
  }
  int readResult;
  readResult = read(fifo, message, sizeof(message));
  if(readResult<0 || readResult==0){
    printf("Error leyendo FIFO: %s\n", strerror(errno));
    return;
  }
  close(fifo);
}
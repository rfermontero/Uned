#include <stdio.h>
#include <fcntl.h>
#include <stdbool.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>

#define MAX_BUF 1024

static const char * FIFO_FILE_NAME = "fichero1";

bool readMessageFromFifo();

int main() {
    readMessageFromFifo();
    return 0;
}

bool readMessageFromFifo(){
  int fifo;
  char message[MAX_BUF];
  fifo = open(FIFO_FILE_NAME, O_RDONLY);
  if(fifo < 0) {
    printf("Error opening fifo: %s\n", strerror(errno));
    return false;
  }
  if(fifo == 0) {
    printf("Error opening fifo: %s\n", strerror(errno));
    return false;
  }
  int readResult;
  readResult = read(fifo, message, sizeof(message));
  if(readResult<0){
    printf("Error reading fifo: %s\n", strerror(errno));
    return false;
  }
  if(readResult==0){
    printf("Error reading fifo null: %s\n", strerror(errno));
    return false;
  }
  printf("Mensaje es %s\n", message);
  close(fifo);
  return true;
}
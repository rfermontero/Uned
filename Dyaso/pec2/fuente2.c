#include <stdio.h>
#include <fcntl.h>

static const char FIFO_FILE_NAME[] = "./fichero1";

void readMessageFromFifo();

int main() {

    return 0;
}

void readMessageFromFifo(){
    if (mkfifo(FIFO_FILE_NAME, 0600) < 0) {
        printf("Error creating FIFO");
    } else {
        int pipe;
        char buffer[1024];
        pipe = open(FIFO_FILE_NAME, O_WRONLY);
        while (!feof(pipe)){
      	if (fgets(buffer, 128, pipe)){
      			printf ("RECIBIDO: %s", buffer);
      		}
    	}
  		fclose(pipe);
    }
}
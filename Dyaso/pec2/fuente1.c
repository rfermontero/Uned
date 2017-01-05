#include <stdio.h>
#include <errno.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <sys/ioctl.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdbool.h>

static const char * FILE_NAME_PROCESS_1 = "/Ej1";
static const char * FIFO_FILE_NAME = "fichero1";
static const int MESSAGE_TYPE = 1;

//Struct for shared message over MessageQueue
struct Message {
    long id;
    pid_t pid;
};

//Function to handle process 2 in orderto read message from fifo and exec Ej2
void handleProcess2(int fd[]);
//Save C string from keyboard on message
void getMessage(char *message);
//Wait until message it's received via MessageQueue attached to key
void waitForMessageInQueue(key_t key);
//Send message over pipe as fd
void sendMessageOverPipe(int fd[], char message[]);
//Handle message received in order to kill process arg, p2 and remove unused files
void onMessageReceived(pid_t pidToKill);
//Send a SIGKILL sinnal to pid
void killProcess(pid_t pid);
//Write message in FIFO attached to constant FIFO_FILE_NAME
bool writeMessageInFifo(char message[]);
//Get a message queue id for key
int getMessageQueueId(key_t key);

pid_t p2; //Global variable to keep p2 pid

int main() {

    int fd[2];
    char message[1024];
    key_t key;
            
    pipe(fd); //Create a pipe in FD

    switch(p2 = fork()){
        case -1: 
            printf("Error haciendo fork al proceso");
            break;
        case 0:
            handleProcess2(fd);
            break;
        default:
            getMessage(message);
            sendMessageOverPipe(fd, message);
            waitForMessageInQueue(key);
            break;
    }

    return 0;
}

void handleProcess2(int fd[]){
    char *buffer = NULL;
    char byte = 0;
    int count = 0;
    close(fd[1]);
    while (read(fd[0], &byte, 1) == 1){
            if (ioctl(fd[0], FIONREAD, &count) != -1){
                buffer = malloc(count+1);
                buffer[0] = byte;
                if (read(fd[0], buffer+1, count) == count){
                    if(writeMessageInFifo(buffer)){
                        free(buffer);
                        execl("./Ej2", "Ej2", NULL);
                    }
                }
            }
    }    
}

bool writeMessageInFifo(char message[]){
    if (mkfifo(FIFO_FILE_NAME, S_IRWXU) == 0) {
        int fifo;
        fifo = open(FIFO_FILE_NAME, O_RDWR);
        if(fifo < 0) {
            printf("Error abriendo FIFO: %s\n", strerror(errno));
            return false;
        }
        int writeResult;
        writeResult = write(fifo, message, strlen(message));
        if(writeResult<0){
           printf("Error escribiendo FIFO: %s\n", strerror(errno));
           return false;
        }
        printf("El proceso P2 (PID=%d, Ej1) transmite un mensaje al proceso P2 por una tubería FIFO\n", getpid());
        return true;
    } else {
        printf("Error creando FIFO %s\n", strerror(errno));
        return false;
    }
}

void getMessage(char *message){
    printf("Introduce un mensaje: ");
    scanf("%[^\n]", message);
}

void sendMessageOverPipe(int fd[], char message[]){
    close(fd[0]);
    printf("El proceso P1 (PID=%d, Ej1) transmite un mensaje al proceso P2 por una tubería anonima\n", getpid());
    write(fd[1], message, (strlen(message)+1));
}

void waitForMessageInQueue(key_t key){
    struct Message message;
    int messageQueueId = getMessageQueueId(key);
    if (messageQueueId != -1) {
            msgrcv(messageQueueId, 
                (struct msgbuf *) &message,
                sizeof(pid_t),
                MESSAGE_TYPE,
                0/*Wait for message flag*/);

             pid_t pidReceived = message.pid;
             onMessageReceived(pidReceived);

    } else {
        printf("Error creando cola de mensajes: %s\n", strerror(errno));
    }
}

void onMessageReceived(pid_t pidToKill){
    char filePath[1024];
    killProcess(pidToKill);
    killProcess(p2);
    if (getcwd(filePath, sizeof(filePath)) != NULL){
        strcat(filePath, FIFO_FILE_NAME);
        remove(filePath);
    }
}

int getMessageQueueId(key_t key){
    char filePath[1024];
    int messageQueueId;
    if (getcwd(filePath, sizeof(filePath)) != NULL){
        strcat(filePath, FILE_NAME_PROCESS_1);
        key = ftok(filePath, 0777);
        if (key != (key_t)-1) {
            return msgget(key, 0600 | IPC_CREAT);
        } else {
            return -1;
        }
    }
}

void killProcess(pid_t pid){
    kill(pid, SIGKILL);
}
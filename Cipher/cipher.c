// Qihan Wu 260907547
#include <stdio.h>
#include<math.h>
#include <stdlib.h>
#include <string.h>


void errorMsg(char *msg){
    printf("%s\n",msg);
    printf("Program syntax： ./cipher SWITCH KEY LENGTH < FILENAME\n");
    printf("Legal usage examples: ./cipher -e 10 100 < filename.txt\n");
    printf("Legal usage examples: ./cipher -d 10 200 < filename.e\n");
}

void encrypt(int key, int length){

    int row = length/key+1;

    char inputData[500];
    char out[1000];

    for(int i=0;i<length;i++){
        inputData[i]=getc(stdin);
    }
    char matrix[500][500];

    int pos=0;

    for(int i=0;i<row;i++){
        for(int j=0;j<key;j++){
            matrix[i][j]=inputData[pos];
            pos++;
        }
    }

    pos=0;

    for(int i=0;i<length%key;i++){
        for(int j=0;j<row;j++){
            out[pos]=matrix[j][i];
            pos++;
        }
    }
    for(int i=length%key;i<key;i++){
        for(int j=0;j<length/key;j++){
            out[pos]=matrix[j][i];
            pos++;
        }
    }

    for(int i=0;i<length;i++){
        printf("%c",out[i]);
    }

}

void decrypt(int key, int length){

    int row = length/key+1;

    char inputData[500];
    char out[1000];

    for(int i=0;i<length;i++){
        inputData[i]=getc(stdin);
    }

    char matrix[500][500];

    int pos=0;

    for(int i=0;i<length%key;i++){
        for(int j=0;j<=length/key;j++){
            matrix[j][i]=inputData[pos];
            pos++;
        }
    }
    for(int i=length%key;i<key;i++){
        for(int j=0;j<length/key;j++){
            matrix[j][i]=inputData[pos];
            pos++;
        }
    }
    pos=0;

    for(int i=0;i<row;i++){
        for(int j=0;j<key;j++){
            out[pos] = matrix[i][j];
            pos++;
        }
    }


    for(int i=0;i<length;i++){
        printf("%c",out[i]);
    }

}

int main(int  argc,  char*  argv[]) {
   if (argc != 4){
       errorMsg("Error: Wrong number of arguments");
       return 1;
   }

   if (strcmp(argv[1],"-e")!=0 && strcmp(argv[1],"-d")!=0){
       errorMsg("Error: Wrong switch");
       return 2;
   }

   int key = atoi(argv[2]);
   int length = atoi(argv[3]);

   if (length > 500){
       errorMsg("Error: Wrong LENGTH value, LENGTH is too large");
       return 3;
   }
   if (length <= 0){
       errorMsg("Error: Wrong LENGTH value, LENGTH is too small");
       return 3;
   }

   if (key > length){
       errorMsg("Error: KEY > LENGTH");
       return 4;
   }

   if (key == length){
       errorMsg("Error: KEY = LENGTH");
       return 4;
   }
   if (strcmp(argv[1],"-e")==0){
       encrypt(key,length);
   } else {
       decrypt(key,length);
   }
   return 0;
}
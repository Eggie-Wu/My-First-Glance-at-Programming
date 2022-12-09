//Qihan Wu 260907547
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>


void errorMsg(){
    printf("Program syntax: ./tv STATE TRANSACTIONS\n");
    printf("Legal usage examples: ./tv state.csv transaction.csv\n");
    printf("Legal usage examples: ./tv ../state.csv /data/log/transaction.csv\n");
}

struct NODE{
    int accountNumber;
    double startingBalance;
    double endingBalance;
    struct NODE *next;
};

void printNodes(struct NODE *ptr) {
    while (ptr != NULL) {
        printf("check %d %lf %lf\n",ptr->accountNumber,ptr->startingBalance,ptr->endingBalance);
        ptr = ptr->next;
    }
}

struct NODE *addNode(struct NODE *acclist,int ac,double sb,double eb){
    struct NODE *newacc = (struct NODE *) malloc(sizeof(struct NODE)); 
    newacc->accountNumber=ac;
    newacc->startingBalance=sb;
    newacc->endingBalance=eb;
    newacc->next=NULL;

    if (acclist==NULL){
        return newacc;
    }
    if(acclist->accountNumber>ac){
        newacc->next=acclist;
        return newacc;
    }
    struct NODE *temp=acclist;
    while(temp!=NULL){
        if(temp->accountNumber==ac){
            printf("Duplicate account number [account, start, end] : %d %.2f %.2f\n",ac,sb,eb);
            free(newacc);
            return acclist;
        }
        if(temp->accountNumber<ac){
            if(temp->next==NULL){
                temp->next=newacc;
                return acclist;
            }
            if(temp->next->accountNumber>ac){
                newacc->next=temp->next;
                temp->next=newacc;
                return acclist;
            }
            if(temp->next->accountNumber<=ac){
                temp=temp->next;
            }
        }
    }
    return acclist;
}



int main(int  argc,  char*  argv[]) {

   if (argc != 3){
       printf("Error: Wrong number of arguments\n");
       errorMsg();
       exit(1);
   }

   FILE *state = fopen(argv[1],"rt"),*tr = fopen(argv[2],"rt");

   if(state==NULL){
       if(tr==NULL){
           printf("Unable to openfilename %s and %s\n",argv[1],argv[2]);
           errorMsg();
           exit(2);
       }
       printf("Unable to openfilename %s\n",argv[1]);
       errorMsg();
       exit(2);
   }
   if(tr==NULL){
       printf("Unable to openfilename %s\n",argv[2]);
       errorMsg();
       exit(2);
   }
   
   fgetc(state);
   fgetc(tr);
   if(feof(state)){
       if(!feof(tr)){
           printf("File state.csv is empty. Unable to validate transaction.csv.\n");
           exit(3);
       }
   }


   char test1[1024];

   struct NODE *acclist = NULL;
   
   int ac;
   double sb;
   double eb;

   while(1){
       fgets(test1,1024,state);
       if(sscanf(test1,"%d,%lf,%lf",&ac,&sb,&eb)==3){
           acclist = addNode(acclist,ac,sb,eb);
       }
       if (feof(state)) break;
   }
   
   fclose(state);

   struct NODE *head1=acclist;
   //printNodes(head1);

   
   char test2[1024];
   char mode;
   double amount;

   while(1){
       fgets(test2,1024,tr);
       if(sscanf(test2,"%d,%c,%lf",&ac,&mode,&amount)==3){
           struct NODE *temp1=acclist;
           while(temp1!=NULL){
               if(temp1->accountNumber==ac){
                    if(mode=='d'){
                        temp1->startingBalance=temp1->startingBalance+amount;
                    }
                    if(mode=='w'){
                        if(temp1->startingBalance-amount<0){
                            printf("Balance below zero error (account, mode, transaction, startingBalance before): %d %c %.2f %.2f\n",ac,mode,amount,temp1->startingBalance);
                            temp1->startingBalance=0;
                        }else{
                            temp1->startingBalance=temp1->startingBalance-amount;
                        }
                    }
                    break;
                }
                if(temp1->next==NULL){
                    printf("Account not found (account, mode, amount): %d %c %.2f\n",ac,mode,amount);
                }
                temp1=temp1->next;
            }
       }
       if(feof(tr)){
           break;
       }
   }

   fclose(tr);
   struct NODE *head=acclist;
   //printNodes(head);

   struct NODE *temp=acclist;
   while(temp!=NULL){
       if(temp->startingBalance!=temp->endingBalance){
           printf("End of day balances do not agree (account, starting, ending): %d %.2f %.2f\n",temp->accountNumber,temp->startingBalance,temp->endingBalance);
       }
       temp=temp->next;
   }

   struct NODE *temp1=acclist;
   while(temp1!=NULL){
       temp1=temp1->next;
       free(acclist);
       acclist=temp1;
   }

   
   exit(0);
}
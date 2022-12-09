#include <iostream>
using namespace std;

#define ARRSIZE 50
int datastore[ARRSIZE] = {};

//each elements represent [id,size]
int record[ARRSIZE][2]={};


//total number of used spaces
int occupied=0;

// Feel free to add any useful variables that you might need

// Question 1
int newstore(int ssize)
{
    // provide your implementation
    
    //check if ssize is valid;
    if (ssize<1||ssize+occupied>50){
        return -1;

    //check if there's enough space
    }else{

        //assign ssize to the next empty position of record[][]
        for(int i=0;i<ARRSIZE;i++){
            if(record[i][1]==0){
                record[i][1]=ssize;

                //update the num of occupied spaces
                occupied+=ssize;

                //assign the id
                if(i==0){
                    record[i][0]=0;
                }else{
                    record[i][0]=record[i-1][0]+1;
                }
                //return the id
                return record[i][0];
            }
        }
        return -1;
    }
}

// Question 2
int add_element_to_store(int id, int val, int idx=-1)
{
    // provide your implementation
    if(val==0){
        //input val not valid
        return -1;
    }

    int startIndex=0;
    //search for the id
    for(int ptr=0;ptr<50;ptr++){
        if(record[ptr][0]==id&&record[ptr][1]>0){
            if(idx==-1){
                //startindex and endindex represents the position of that datastore
                int endIndex = startIndex+record[ptr][1];
                for(int pos=startIndex;pos<endIndex;pos++){
                    if(datastore[pos]==0){
                        datastore[pos]=val;
                        return 0;
                    }
                }
                //comming out means the the datastore of this id is full;
                return -1;
            }else if(idx>=0){
                if(idx<record[ptr][1]){
                    //calculate the position;
                    int pos=startIndex+idx;
                    datastore[pos]=val;
                    return 0;
                }else{
                    //idx out of range
                    return -1;
                }
            }else{
                //the case idx<-1
                return -1;
            }  
        }
        //update the start index
        startIndex+=record[ptr][1];
    }
    //comming out means the id doesn't exist
    return -1;
}

// Question 3
void print_debug()
{
    // provide your implementation
    cout << "available elements in the data store: "<<(ARRSIZE-occupied) << endl;
    cout << "datastore: ";
    //iterate through datastore[]
    for(int i=0;i<ARRSIZE;i++){
        cout<<datastore[i]<<" ";
    }
    cout << endl;

    int pos=0;
    //iterate through all id
    for(int i=0;i<ARRSIZE;i++){
        if(record[i][1]!=0){
            cout << "store "<<record[i][0]<<" : ";
            for( int j = 0; j < record[i][1];j++){
                cout<<datastore[pos]<<" ";
                pos+=1;
            }
            cout << endl;
        }
    }
    cout << "#####################################################"<< endl;

}

// Question 4
void delete_element_from_store_by_value(int id, int val)
{
    // provide your implementation

    int startIndex=0;
    //search for the id
    for(int ptr=0;ptr<50;ptr++){
        if(record[ptr][0]==id&&record[ptr][1]>0){
            int endIndex = startIndex+record[ptr][1];
            //find the value
            for(int pos=startIndex;pos<endIndex;pos++){
                if(datastore[pos]==val){
                    datastore[pos]=0;
                    return;
                }
            }
            //val doesn't exist
            return;
        }
        startIndex+=record[ptr][1];
    }
    //id doesn't exist
    return;
}

void delete_element_from_store_by_index(int id, int idx)
{
    // provide your implementation

    int startIndex=0;
    //search for the id
    for(int ptr=0;ptr<50;ptr++){
        if(record[ptr][0]==id&&record[ptr][1]>0){
            if(idx>=record[ptr][1]||idx<0){
                return;
            }else{
                //calculate the position;
                int pos=startIndex+idx;

                //change it to 0;
                datastore[pos]=0;
                return;
            }
        }
        startIndex+=record[ptr][1];
    }
    //id doesn't exist
    return;
}

// Question 5
void which_stores_have_element(int val)
{
    // provide your implementation
    //sanity check value shouldn't be 0
    if(val==0){
        cout << "Element 0 is not available in any store"<<endl;
        return;
    }

    int startIndex=0;
    cout << "Element "<<val<<" is ";
    bool found=false;
    //iterate through all id
    for(int ptr=0;ptr<ARRSIZE;ptr++){
        if(record[ptr][1]>0){
            int endIndex=startIndex+record[ptr][1]-1;
            for(int i=startIndex;i<endIndex+1;i++){
                if(datastore[i]==val){
                    if(!found){
                        cout << "in store IDs: ";
                        found=true;
                    }
                    cout << record[ptr][0] << ", ";
                    break;
                }
            }
            startIndex+=record[ptr][1];
        }
    }
    if(!found){
        cout << "not available in any store"<<endl;
    }else{
        cout<<endl;
    }
    return;
}

// Question 6
void delete_store(int id)
{
    // provide your implementation

    int startIndex=0;
    //search for the id
    for(int ptr=0;ptr<ARRSIZE;ptr++){
        if(record[ptr][0]==id&&record[ptr][1]>0){
            occupied-=record[ptr][1];
            int endIndex=startIndex+record[ptr][1]-1;
            //shift all the left over elements to the left
            for(int ptr2=endIndex+1;ptr2<ARRSIZE;ptr2++){
                datastore[ptr2-record[ptr][1]]=datastore[ptr2];
            }

            //shift all the left over clients to the left
            for(int i=ptr;i<ARRSIZE;i++){
                if(i==ARRSIZE-1){
                    record[i][0]=0;
                    record[i][1]=0;
                    return;
                }else if(record[i+1][1]>0){
                    record[i][0]=record[i+1][0];
                    record[i][1]=record[i+1][1];
                    
                }else{
                    record[i][0]=0;
                    record[i][1]=0;
                    
                    return;
                }
            }
            return;
        }
        startIndex+=record[ptr][1];
    }
    //id doesn't exist
    return;   
}

// Question 7
int resize_store(int id, int newsize)
{
    // provide your implementation
    //sanity checks
    if(newsize<1){
        return -1;
    }

    int startIndex=0;
    //search for the id
    for(int ptr=0;ptr<50;ptr++){
        if(record[ptr][0]==id&&record[ptr][1]>0){
            //change nothing if newsize==original size
            if(newsize==record[ptr][1]){
                return 0;
            
            //similar to delete store if newsize < original size
            }else if(newsize<record[ptr][1]){
                occupied+=newsize-record[ptr][1];
                int endIndex=startIndex+record[ptr][1]-1;
                int diff=record[ptr][1]-newsize;
                for(int ptr2=endIndex+1;ptr2<ARRSIZE;ptr2++){
                    datastore[ptr2-diff]=datastore[ptr2];
                }
                record[ptr][1]=newsize;
                return 0;
            
            //if newsize > original size, shift left over elements and clients to the right
             
            }else{
                if(occupied+newsize-record[ptr][1]>ARRSIZE){
                    return -1;
                }
                occupied+=newsize-record[ptr][1];
                int endIndex=startIndex+record[ptr][1]-1;
                int diff=newsize-record[ptr][1];
                for(int ptr2=ARRSIZE-1;ptr2>startIndex+newsize-1;ptr2--){
                    datastore[ptr2]=datastore[ptr2-diff];
                }
                //initialize all new spaces to 0
                for(int ptr3=endIndex+1;ptr3<startIndex+newsize-1;ptr3++){
                    datastore[ptr3]=0;
                }
                record[ptr][1]=newsize;
                return 0;
            }
        }
        startIndex+=record[ptr][1];
    }
    //id doesn't exist
    return -1;
}


// DO NOT ADD ANY LOGIC TO THE MAIN FUNCTION.
// YOU MAY MODIFY FOR YOUR OWN TESTS ONLY BUT THE ORIGINAL MAIN
// FUNCTION SHOULD BE SUBMITTED AS IS
int main()
{
    int s1 = newstore(3); // create new empty data store of size 3
    int s2 = newstore(5); // create new empty data store of size 5

    if (s1 != -1)
    {
        add_element_to_store(s1, 13);
        add_element_to_store(s1, 15);
        add_element_to_store(s1, 21);
        add_element_to_store(s1, 42); // this should return -1
    }
    print_debug();
    
    if (s2 != -1)
    {
        add_element_to_store(s2, 7, 2);
        add_element_to_store(s2, 15, 0);
        add_element_to_store(s2, 22, 1);
    }
    print_debug();

    delete_element_from_store_by_value(s1, 13);
    delete_element_from_store_by_value(s1, 71);
    delete_element_from_store_by_index(s2, 2);
    delete_element_from_store_by_index(s1, 5);
    print_debug();


    which_stores_have_element(15);
    which_stores_have_element(29);

    delete_store(s1);
    print_debug();
    

    resize_store(s2, 20);
    int s3 = newstore(40);
    print_debug();

    s3 = newstore(30);
    add_element_to_store(s3, 7, 29);
    print_debug();

    
}
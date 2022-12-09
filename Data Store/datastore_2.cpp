#include <iostream>
using namespace std;

//A Node
struct Store {
    int id;
    int ssize;
	int* store_data;
	Store* next;
};

Store* head=NULL;

// Question 1
int newstore(int ssize)
{
    //create the new store
    Store* newstore=  new Store;
    newstore->ssize=ssize;
    newstore->store_data=new int[ssize];
    newstore->next=NULL;

    if(head!=NULL){
        //datastore is not empty
        Store* ptr=head;
        //traverse to the end
        while(ptr->next){
            ptr=ptr->next;
        }
        newstore->id=ptr->id+1;
        ptr->next=newstore;
    }else{
        //datastore is empty
        newstore->id=0;
        head=newstore;
    }
    return newstore->id;
}

// Question 2
int add_element_to_store(int id, int val, int idx=-1)
{
    if(val==0){
        //input val not valid
        return -1;
    }

    //find the store
    Store* ptr=head;
    while(ptr){
        if(ptr->id==id){
            if(idx==-1){
                for(int i=0;i<ptr->ssize;i++){
                    if(ptr->store_data[i]==0){
                        ptr->store_data[i]=val;
                        return 0;
                    }
                }
                //comming out means the the datastore of this id is full;
                return -1;
            }else if(idx>=0){
                if(idx<ptr->ssize){
                    ptr->store_data[idx]=val;
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
        ptr=ptr->next;
    }
    //comming out means the id doesn't exist
    return -1;
}

// Question 3
void print_debug()
{
    Store* ptr0=head;
    Store* ptr1=head;
    Store* ptr2=head;

    //find total used
    int totalused =0;
    while(ptr0){
        totalused+=ptr0->ssize;
        ptr0=ptr0->next;
    }

    //print entire datastore
    cout << "number of total used store elements: " << totalused<<endl;
    cout << "datastore: ";
    while(ptr1){
        for(int i=0;i<ptr1->ssize;i++){
            cout<<ptr1->store_data[i]<<" ";
        }
        ptr1=ptr1->next;
    }
    cout << endl;

    //print each store
    while(ptr2){
        cout << "store "<<ptr2->id<<" : ";
        for(int i=0;i<ptr2->ssize;i++){
            cout<<ptr2->store_data[i]<<" ";
        }
        cout << endl;
        ptr2=ptr2->next;
    }
    cout << "#####################################################"<< endl;
}

// Question 4
void delete_element_from_store_by_value(int id, int val)
{
    if(val==0){
        //input val not valid
        return;
    }

    //find the store
    Store* ptr=head;
    while(ptr){
        if(ptr->id==id){
            for(int i=0;i<ptr->ssize;i++){
                if(ptr->store_data[i]==val){
                    ptr->store_data[i]=0;
                    return;
                }
            }
            //val doesn't exist
            return;
        }
        ptr=ptr->next;
    }
    //comming out means the id doesn't exist
    return;
}

void delete_element_from_store_by_index(int id, int idx)
{
    //find the store
    Store* ptr=head;
    while(ptr){
        if(ptr->id==id){
            if(ptr->id==id){
                if(idx>=ptr->ssize||idx<0){
                    return;
                }else{
                    ptr->store_data[idx]=0;
                }
            }
            return;
        }
        ptr=ptr->next;
    }
    //comming out means the id doesn't exist
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

    cout << "Element "<<val<<" is ";
    //iterate through all stores
    bool found=false;
    Store* ptr=head;
    while(ptr){
        for(int i=0;i<ptr->ssize;i++){
            if(ptr->store_data[i]==val){
                if(!found){
                    cout << "in store IDs: ";
                    found=true;
                }
                cout << ptr->id << ", ";
                break;
            }
        }
        ptr=ptr->next;
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
    //find the store
    if(!head){
        return;
    }
    Store* ptr=head;
    if(ptr->id==id){
        //free the memory
        delete [] ptr->store_data;
        head=ptr->next;
        return;
    }
    while(ptr->next){
        if(ptr->next->id==id){
            //free the memory
            delete [] ptr->next->store_data;
            ptr->next=ptr->next->next;
            return;
        }
        ptr=ptr->next;
    }
    //comming out means the id doesn't exist
    return;  
}

// Question 7
int resize_store(int id, int newsize)
{
    //sanity checks
    if(newsize<1){
        return -1;
    }

    Store* ptr=head;
    //find the store
    while(ptr){
        if(ptr->id==id){
            int* new_store_data=new int[newsize];

            if(newsize<=ptr->ssize){
                for(int i=0;i<newsize;i++){
                    new_store_data[i]=ptr->store_data[i];
                }
            }else{
                for(int i=0;i<ptr->ssize;i++){
                    new_store_data[i]=ptr->store_data[i];
                }
            }
            //free the memory and change the pointer
            delete [] ptr->store_data;
            ptr->store_data=new_store_data;
            ptr->ssize=newsize;
            return 0;
        }
        ptr=ptr->next;
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
    //int s3 = newstore(40);
    print_debug();

    int s3 = newstore(30);
    add_element_to_store(s3, 7, 29);
    print_debug();
}


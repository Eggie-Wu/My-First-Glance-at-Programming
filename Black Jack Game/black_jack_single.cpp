#include <iostream>
#include <vector>
#include <algorithm>
#include <random>
using namespace std;

//Rank
enum Rank{ACE=1,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING};

//Type
enum Type{CLUBS=1, DIAMONDS, HEARTS, SPADES};

class Card{
    public:
    Card(Rank r,Type t){
        this->rank=r;
        this->type=t;
    }
    
    int getValue(){
        int value=this->rank;
        if(value>10){
            return 10;
        }
        return value;
    }

    void displayCard(){
        if(this->rank<=10){
            cout<<this->getValue();
        }else{
            switch (rank) {
                case JACK: cout << "J";break;
                case QUEEN: cout << "Q";break;
                case KING: cout << "K";break;
            } 
        }
        switch (type) {
            case CLUBS: cout << "C"; return;
            case DIAMONDS: cout << "D"; return;
            case HEARTS: cout << "H"; return;
            case SPADES: cout << "S"; return;
        }
        return;
    }

    private:
    Rank rank;
    Type type;
};

class Hand{
    public:

    //maintain two record of total-point, one with the first Ace=1, one with the first Ace=11,
    //if the higher one busts, change it to be equal to the lower one.
    void add(Card c){
        this->handCards.push_back(c);
        totalPointlow+=c.getValue();
        totalPointhigh+=c.getValue();
        if(c.getValue()==1&&totalPointhigh<=11){
            totalPointhigh+=10;
        }
        if(totalPointhigh>21){
            totalPointhigh=totalPointlow;
        }
    }
    
    void clear(){
        handCards.clear();
        totalPointhigh=0;
        totalPointlow=0;
    }

    //return the higher total
    int getTotal() const{
        return this->totalPointhigh;
    }

    void displayHand(){
        for(int i=0;i<handCards.size();i++){
            handCards[i].displayCard();
            cout<<" ";
        }
    }

    private:
    vector<Card> handCards;
    int totalPointhigh;
    int totalPointlow;
};

class Deck{
    public:
    void populate(){
        this->deckCards.clear();
        for(int i=1;i<=4;i++){
            for(int j=1;j<=13;j++){
                Card c((Rank)j,(Type)i);
                this->deckCards.push_back(c);
            }
        }
    }

    //uses c++ std::shuffle
    void shuffle(){
        std::shuffle(deckCards.begin(), deckCards.end(),random_device());
    }

    Card deal(){
        Card c = deckCards.at(deckCards.size()-1);
        deckCards.pop_back();
        return c;
    }

    //let computer player cheat deal,
    //find a card in the deck to make casino win
    //if not possible, perform a normal deal
    Card cheatDeal(int casinoPoints,int playerPoints){
        for(int i=0;i<deckCards.size();i++){
            int resultPoint= casinoPoints + deckCards[i].getValue();
            if(resultPoint <= 21){
                if(resultPoint > playerPoints){
                    Card c=deckCards[i];
                    deckCards.erase(deckCards.begin()+i);
                    return c;
                }
            }
        }
        Card c = deckCards.at(deckCards.size()-1);
        deckCards.pop_back();
        return c;
    }

    private:
    vector<Card> deckCards;
};

class AbstractPlayer{
    public:
    virtual bool isDrawing() const = 0;

    bool isBusted(){return this->hand.getTotal()>21;}

    void draw(Card c){this->hand.add(c);}

    //help display the existing card and total point
    void displayPlayer(){
        this->hand.displayHand();
        cout<<"["<<this->hand.getTotal()<<"]"<<endl;
    }

    //return total points of a player
    int getTotalPoints(){return hand.getTotal();}

    void reset(){this->hand.clear();}

    protected:
    Hand hand;
};

class HumanPlayer : public AbstractPlayer{
    public:
    //only "y" indecate yes
    bool isDrawing() const{
        cout<<"Do you want to draw? (y/n):";
        string answer;
        cin>>answer;
        return answer=="y";
    }

    //return whether the casino win or not
    bool announce(int casinoPoints){
        if(casinoPoints>21){
            cout<<"Casino busts."<<endl;
            cout<<"Player wins."<<endl;
            return false;
        }
        if(this->hand.getTotal()>casinoPoints){
            cout<<"Player wins."<<endl;
            return false;
        }else if(this->hand.getTotal()<casinoPoints){
            cout<<"Casino wins."<<endl;
            return true;
        }else{
            cout<<"Push: No one wins."<<endl;
            return false;
        }
    }

    private:
};

class ComputerPlayer : public AbstractPlayer{
    public:
    bool isDrawing() const{
        return !(this->hand.getTotal()>16);
    }

    private:
};

class BlackJackGame{
    public:
    BlackJackGame(){
        this->round=0;
        this->casinoWin=0;
    }

    ~BlackJackGame(){
        m_player.reset();
        m_casino.reset();
    }

    void play(){
        //initialization and update number of round
        round+=1;
        m_player.reset();
        m_casino.reset();
        m_deck.populate();
        m_deck.shuffle();
        
        //draw at the beginning of each round
        m_casino.draw(m_deck.deal());
        cout<<"Casino : ";
        m_casino.displayPlayer();
        m_player.draw(m_deck.deal());
        m_player.draw(m_deck.deal());
        cout<<"Player : ";
        m_player.displayPlayer();

        //player turn
        if(m_player.getTotalPoints()!=21){
            while(m_player.isDrawing()){
                m_player.draw(m_deck.deal());
                cout<<"Player : ";
                m_player.displayPlayer();

                //stop asking when points=21
                if(m_player.getTotalPoints()==21){break;}

                if(m_player.isBusted()){
                    cout<<"Player busts"<<endl;
                    cout<<"Casino wins"<<endl;
                    casinoWin+=1;
                    return;
                }
            }
        }

        
        //casino turn
        while(m_casino.isDrawing()){
            //check winRate and decide whether to cheat or not
            //if number of round is bigger than 3, win rate < 55%, then cheat
            //cheating is not always successful, but will cheat again in the next round as well
            if(round>3 && winRate()<0.55){
                m_casino.draw(m_deck.cheatDeal(m_casino.getTotalPoints(),m_player.getTotalPoints()));
            }else{
                m_casino.draw(m_deck.deal());
            }
            cout<<"Casino : ";
            m_casino.displayPlayer();

            if(m_casino.isBusted()){
                break;
            }
        }

        int casinoPoints=m_casino.getTotalPoints();

        //announce and update the number of casino's win
        if(m_player.announce(casinoPoints)){casinoWin+=1;};
    }

    private:
    Deck m_deck;
    ComputerPlayer m_casino;
    HumanPlayer m_player;
    int round;
    int casinoWin;

    //return a win rate of the casino
    double winRate(){
        double winrate=((double)casinoWin)/((double)round);
        return winrate;
    }
};

int main()
{
    cout << "\tWelcome to the Comp322 Blackjack game!" << endl << endl;

    BlackJackGame game;

    // The main loop of the game
    bool playAgain = true;
    char answer = 'y';
    while (playAgain)
    {
        game.play();

        // Check whether the player would like to play another round
        cout << "Would you like another round? (y/n): ";
        cin >> answer;
        cout << endl << endl;
        playAgain = (answer == 'y' ? true : false);
    }

    cout <<"Gave over!";
    return 0;
}
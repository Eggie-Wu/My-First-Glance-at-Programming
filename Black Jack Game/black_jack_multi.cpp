#include <iostream>
#include <vector>
#include <algorithm>
#include <random>
#include <string>
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

    //maintain two totalpoint, one with first Ace=1, one with first Ace=11,
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

    //use c++ std::shuffle
    void shuffle(){
        std::shuffle(deckCards.begin(), deckCards.end(),random_device());
    }

    Card deal(){
        Card c = deckCards.at(deckCards.size()-1);
        deckCards.pop_back();
        return c;
    }

    //let computer player cheat deal, 
    //find a card in the deck to make casino win at least two hands of the player
    //if not possible, perform a normal deal
    Card cheatDeal(int casinoPoints,vector<int> playerPoints){
        for(int i=0;i<deckCards.size();i++){
            //resulting point of casino after dealing this card
            int resultpoint=casinoPoints+deckCards[i].getValue();
            if(resultpoint<=21){
                //count indecate number of the potential winning
                int count=0;
                for(int i=0;i<playerPoints.size();i++){
                    if(resultpoint>playerPoints[i]){
                        count++;
                    }
                }
                //winning one hands of a one hand player is sufficient to deviate winRate.
                //winning two hands of a two/three hand player is sufficient to deviate winRate.
                if(count>=2||count>=playerPoints.size()){
                    Card c=deckCards[i];
                    deckCards.erase(deckCards.begin()+i);
                    return c;
                }
            }
        }
        return this->deal();
    }

    private:
    vector<Card> deckCards;
};

class AbstractPlayer{
    public:
    virtual bool isDrawing() const = 0;

    bool isBusted(int i){return this->hands[i].getTotal()>21;}

    void draw(Card c,int i){this->hands[i].add(c);}

    //help display the existing card and total point
    void displayPlayer(int i){
        this->hands[i].displayHand();
        cout<<"["<<this->hands[i].getTotal()<<"]"<<endl;
    }

    //return total points of a hand of a player
    int getTotalPoints(int i){return hands[i].getTotal();}

    void reset(){
        for(int i=0;i<hands.size();i++){
            hands[i].clear();
        }
        hands.clear();
    }

    //add n hands to the player
    void addHand(int n){
        for(int i=0;i<n;i++){
            Hand h;
            h.clear();
            hands.push_back(h);
        }
    }

    protected:
    vector<Hand> hands;
};

class HumanPlayer : public AbstractPlayer{
    public:
    bool isDrawing() const{
        //only "y" indecate yes
        cout<<"Do you want to draw? (y/n):";
        string answer;
        cin >> answer;
        return answer=="y";
    }

    //return number of casino's win
    int announce(int casinoPoints){
        int count=0;
        if(casinoPoints>21){
            cout<<"Casino busts"<<endl;
            for(int i=0;i<hands.size();i++){
                cout<<"Player "<<"hand "<<i+1<<" wins, Casino loses"<<endl;
            }
            return count;
        }

        for(int i=0;i<hands.size();i++){
            if(this->hands[i].getTotal()>casinoPoints){
                if(this->hands[i].getTotal()>21){
                    cout<<"Player Hand "<<i+1<<": busts and lose, Casino wins."<<endl;
                    count++;
                }else{
                    cout<<"Player Hand "<<i+1<<": wins, Casino loses."<<endl;
                }
            }else if(this->hands[i].getTotal()<casinoPoints){
                cout<<"Player Hand "<<i+1<<": loses, Casino wins."<<endl;
                count++;
            }else{
                cout<<"Player Hand "<<i+1<<": push, no one wins."<<endl;
            }
        }
        return count;
    }

    private:
};

class ComputerPlayer : public AbstractPlayer{
    public:

    bool isDrawing() const{
        return !(this->hands[0].getTotal()>16);
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
        //input num of hand
        string k;
        cout<<"How many hands do you want to play? 1, 2, or 3:";
        cin >> k;
        int n;
        if(k=="1"||k=="2"||k=="3"){n=stoi(k);}
        else{
            cin.clear();
            cout<<"You have entered wrong input"<<endl;
            return;
        }

        //initialization and update number of round
        round+=n;
        m_player.reset();
        m_casino.reset();
        m_deck.populate();
        m_deck.shuffle();

        //add hands for both player and draw cards at the beginning of each round
        m_casino.addHand(1);
        m_player.addHand(n);
        m_casino.draw(m_deck.deal(),0);
        cout<<"Casino : ";
        m_casino.displayPlayer(0);
        for(int i=0;i<n;i++){
            m_player.draw(m_deck.deal(),i);
            m_player.draw(m_deck.deal(),i);
            cout<<"Player "<<"hand "<<i+1<<": ";
            m_player.displayPlayer(i);
        }

        //player turn
        //maintain a int to represent number of play's busts.
        int bustCount=0;
        for(int i=0;i<n;i++){
            cout<<"Player turn, hand "<<i+1<<":"<<endl;
            m_player.displayPlayer(i);
            while(m_player.isDrawing()){
                m_player.draw(m_deck.deal(),i);
                cout<<"Player "<<"hand "<<i+1<<": ";
                m_player.displayPlayer(i);
                if(m_player.getTotalPoints(i)==21){break;}

                if(m_player.isBusted(i)){
                    cout<<"Player "<<"hand "<<i+1<<" busts"<<endl;
                    bustCount++;
                    break;
                }
            }
        }
        //if all hands of the player busts, annouce and update record of casinoWin
        if(bustCount==n){
            casinoWin+=m_player.announce(m_casino.getTotalPoints(0));
            return;
        }

        //casino turn
        while(m_casino.isDrawing()){
            //check winRate and decide whether to cheat or not
            //if number of round is bigger than 3, win rate < 55%, then cheat
            //cheating is not always successful, but will cheat again in the next round as well
            if(round>3 && this->winRate()<0.55){
                //store totalpoints of all hands of the player into a vector
                vector<int> playerPoints;
                for(int i=0;i<n;i++){
                    if(m_player.getTotalPoints(i)>21){
                        playerPoints.push_back(0);
                    }else{
                        playerPoints.push_back(i);
                    }
                }
                //pass in totalpoints of all hands of the player
                m_casino.draw(m_deck.cheatDeal(m_casino.getTotalPoints(0),playerPoints),0);
            }else{
                m_casino.draw(m_deck.deal(),0);
            }

            cout<<"Casino : ";
            m_casino.displayPlayer(0);

            if(m_casino.isBusted(0)){
                break;
            }
        }

        //if two hands of player busts && one hand of player doesn't && casino busts, 
        //I count as player win all three hands
        casinoWin+=m_player.announce(m_casino.getTotalPoints(0));
    }

    private:
    Deck m_deck;
    ComputerPlayer m_casino;
    HumanPlayer m_player;
    int round;
    int casinoWin;

    double winRate(){
        double winrate=casinoWin/((double)round);
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
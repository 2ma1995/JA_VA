package com.txtlearn.blackjackdemo02;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		
		//Welcome Msg
		System.out.println("Welcome to Blackjack!");
		
		//Create our Playing deck
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		//Create a deck for the player
		Deck playerDeck = new Deck();
		
		Deck dealerDeck = new Deck();
		
		double playerMoney = 100.00;
		
		Scanner userInput = new Scanner(System.in);
		
		//Game loop
		while(playerMoney > 0) {
			//play on!
			//Take the Players bet
			System.out.println("You have $"+playerMoney+", 얼마를 걸겠습니까?");
			double playerBet = userInput.nextDouble();
			if (playerBet > playerMoney) {
				System.out.println("자본금이 모자릅니다. 수고링~");
				break;
			}
			
			boolean endRound = false;
			
			//Start Dealing
			//Player get two cards
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			
			//dealer get two card
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			while(true) {
				System.out.println("your hand:");
				System.out.print(playerDeck.toString());
				System.out.println("당신의 덱의 값 : " + playerDeck.cardsValue());
			//display Dealer Hand
				System.out.println("dealer hand : " + dealerDeck.getCard(0).toString() + "and [Hidden]");
				
				//what does the player want to do?
				System.out.println("Would you like to (1) Hit or (2) Stand?");
				int response = userInput.nextInt();
				
				//they hit
				if(response == 1) {
					playerDeck.draw(playingDeck);
					System.out.println("you draw a:" +playerDeck.getCard(playerDeck.deckSize()-1).toString());
					//bust of >21
					if (playerDeck.cardsValue()>21) {
						System.out.println("BUST! Currently valued at: "+ playerDeck.cardsValue());
						playerMoney-= playerBet;
						endRound = true;
						break;
					}
				}
			if(response == 2) {
				break;
			}
		}
			//reveal dealer cards
			System.out.println("dealer Cards: "+dealerDeck.toString());
			//see if dealer has more points than player
			if((dealerDeck.cardsValue()>playerDeck.cardsValue())&&endRound == false) {
				System.out.println("dealer beats you!");
				playerMoney -= playerBet;
				endRound=true;
			}
			//Dealer draws at 16, stand at 17
			while((dealerDeck.cardsValue()<17)&& endRound == false) {
				dealerDeck.draw(playingDeck);
				System.out.println("Dealer Draws:" + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			}
			//display total value for dealer 
			System.out.println("dealer's hand is valued at : "+ dealerDeck.cardsValue());
			//determine if dealer busted
			if((dealerDeck.cardsValue()>21)&& endRound == false) {
				System.out.println("Dealer busts! you win.");
				playerMoney +=playerBet;
				endRound = true;
			}
			
			//determine if push
			if((playerDeck.cardsValue() == dealerDeck.cardsValue())&& endRound == false) {
				System.out.println("Push");
				endRound = true;
			}
			
			if((playerDeck.cardsValue()>dealerDeck.cardsValue())&& endRound == false) {
				System.out.println("you win the hand!");
				playerMoney += playerBet;
				endRound = true;
			}
			else if(endRound == false) {
				System.out.println("you lose the hand.");
				playerMoney -= playerBet;
				endRound = true;
			}
			playerDeck.moveALLtoDeck(playingDeck);
			dealerDeck.moveALLtoDeck(playingDeck);
			System.out.println("End of hand.");
	}
		System.out.println("Game over! 거지가 되었습니다! 도박중독 상담 02-740-9000 :(");
	}

}

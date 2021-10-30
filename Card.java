package com.txtlearn.blackjackdemo02;

public class Card {

	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value calue, Value value) {
		this.value = value;
		this.suit = suit;
	}
	
	public String toString() {
		return this.suit.toString()+"-"+this.value.toString();
	}
	
	public Value getValue(){
		return this.value;
	}
}

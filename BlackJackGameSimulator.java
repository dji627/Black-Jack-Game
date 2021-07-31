/* 
 * Dengcheng Ji
 * Black Jack Card Game Simulator
 */

import java.util.Scanner;

/**
 * This class computes and displays the attributes of the participants in this
 * game, using various methods.
 * 
 * @author Dengcheng Ji
 * @version 1.0
 * @param participant the names for ones that participate in this game
 */
class Participant {
	private String participant = null; // The type of participant, dealer or player
	private int newDraw = 0; // A integer value that represents the index of the card to be drawn from the
								// card deck
	private String[] cardsInHand = new String[26]; // The string array that stores the cards the participants received
													// through out a round of the game
	private int[] pointsReceived = new int[26]; // The integer array that store the points of the corresponding cards in
												// the cardInHand[] array
	private int cardIndex = 0; // A value to represent the index of the cardsInHand[] array

	Participant(String participant) { // Constructor of the class
		this.participant = participant;
	}

	/**
	 * The method is used to represent the participant being dealt with a card
	 * 
	 * @param randomNumber a random integer to represent the card to be dealt,
	 * @param cardUsed     the string array that keeps track of which cards have
	 *                     already been dealt from the card deck.
	 * @param cardDeck     the string array that stores all 52 cards with type and
	 *                     suit
	 * @param cardPoints   the integer array that store the points each card
	 *                     represents, the values in the array corresponds to the
	 *                     values in the cardDeck array
	 */
	public void gettingCard(int randomNumber, String[] cardUsed, String[] cardDeck, int[] cardPoints) {
		do {
			newDraw = randomNumber; // Assigns a random integer, which will be used to select the card to be dealt
			if (cardUsed[newDraw] == "not used") { // Checks to see if the card has been used in the previous dealings,
													// if there were any
			}
		} while (cardUsed[newDraw] == "used"); // If the card is already used, the do-while loop will cycle to find
												// another card that is not used
		cardUsed[newDraw] = "used"; // When an unused card is found, it will be reassigned to "used" so that it
									// won't be dealt again
		this.cardsInHand[cardIndex] = cardDeck[newDraw]; // Assigns the dealt card from the cardDeck[] array to the
															// cardsInHand[] array, this represents that the participant
															// has received that card from the card deck
		this.pointsReceived[cardIndex] = cardPoints[newDraw]; // Assigns the corresponding card point to the
																// pointsReceived[] array
		cardIndex += 1; // Once the card is dealt, the cardIndex moves to the next index for the next
						// round of dealing
	}

	/**
	 * The method creates the actual poker card figure with the number and the suit
	 * 
	 * @param cardNum the card number of the poker card
	 * @param suit    the suit of the poker card
	 */
	public void cardFig(String cardNum, String suit) {
		if (cardNum.equals("10")) { // Since the number 10 has two digit, the figure has to be arranged different,
									// all other numbers are arranged the same
			System.out.println(" ------------- ");
			System.out.println("|  " + cardNum + "         |");
			System.out.println("|  " + suit + "          |");
			System.out.println("|             |");
			System.out.println("|             |");
			System.out.println("|             |");
			System.out.println("|          " + cardNum + " |");
			System.out.println("|          " + suit + "  |");
			System.out.println(" ------------- ");
		} else {
			System.out.println(" ------------- ");
			System.out.println("|  " + cardNum + "          |");
			System.out.println("|  " + suit + "          |");
			System.out.println("|             |");
			System.out.println("|             |");
			System.out.println("|             |");
			System.out.println("|          " + cardNum + "  |");
			System.out.println("|          " + suit + "  |");
			System.out.println(" ------------- ");

		}
	}

	/**
	 * The method is to display the all the cards the participant currently have
	 */
	public void showingCard() {
		System.out.println();
		System.out.println(participant + "'s cards: "); // Displaying the participant
		for (int i = 0; i < cardsInHand.length; i++) { // Use the for loop to account for all the indexes in the array
			if (cardsInHand[i] != null) { // If not value is assign the the index it will be skipped
				String cardNum = cardsInHand[i].substring(0, cardsInHand[i].length() - 1); // extract the number on the
																							// card and store it to the
																							// string variable
				String suit = cardsInHand[i].substring(cardsInHand[i].length() - 1); // extract the suit and store it to
																						// the string variable
				cardFig(cardNum, suit); // Invoke the carFig method and display the card figure of the card, the cardNum
										// and suit variables are used
			}
		}
	}

	/**
	 * The method is to display certain messages when the participant gets a
	 * BlackJack or is Busted, the messages are also different if the participant is
	 * different
	 * 
	 * @param playerOrDealer the name of the participant, player or dealer
	 */
	public void blackJackOrBust(Participant playerOrDealer) {
		if (playerOrDealer.calculatingPoints() == 21) {
			if (playerOrDealer.participant == "Player 1") {
				System.out.println("Black Jack! good for you!");
			} else if (playerOrDealer.participant == "Dealer") {
				System.out.println("Black Jack for Dealer.");
			}
		} else if (playerOrDealer.calculatingPoints() > 21) {
			if (playerOrDealer.participant == "Player 1") {
				System.out.println("You Busted!");
			} else if (playerOrDealer.participant == "Dealer") {
				System.out.println("Dealer Busted.");
			}
		}
	}

	/**
	 * The method sums the pointReceived[] array
	 * 
	 * @return the integer value that represent the total points the participant
	 *         currently have
	 */
	public int calculatingPoints() {
		int sum = 0;
		for (int i = 0; i < pointsReceived.length; i++) { // The total point is calculated by summing up all the integer
															// in the pointsReceived[] array
			sum += pointsReceived[i];
		}

		if (sum > 21) { // If the point exceeds 21, the program searches for Aces, which is worth 11
						// points and bring it down to 1 point
			for (int i = 0; i < pointsReceived.length; i++) { // Loop to search for Aces in the pointsReceived[] array
				if (pointsReceived[i] == 11) { // Only ace will worth 11 points
					pointsReceived[i] = 1; // Convert the found Ace to 1 point
					sum = 0; // Since the sum needs to be recalculated, it needs to be reset to 0
					for (int j = 0; j < pointsReceived.length; j++) { // Recalculating the total point after the Ace
																		// conversion happened above
						sum += pointsReceived[j];
					}
					if (sum < 21 | sum == 21) { // The for loop will break once the total points gets below 21 or equal
												// to 21, if not it will search for additional Aces in the array
						break;
					}
				}
			}
		}
		return sum;
	}

	/**
	 * The method displays the total points the participant currently have
	 */
	public void showingPoints() {
		System.out.println(participant + "'s Points: " + calculatingPoints());
	}
}

/**
 * This class extends the Participant class, representing the player and
 * contains the unique attributes and methods for players
 * 
 * @author Dengcheng Ji
 * @param player the name of the player to be called
 */
class Player extends Participant {

	private double bet;

	Player(String player) { // Constructor of the class
		super(player);
	}

	/**
	 * The setter method for the bet
	 * 
	 * @param bet the amount of the bet
	 */
	public void setBet(double bet) {
		this.bet = bet;
	}

	/**
	 * The getter method of the bet
	 * 
	 * @return bet the amount of the bet
	 */
	public double getBet() {
		return bet;
	}
}

/**
 * The is class extends the Participant class, representing the dealer and
 * contains the unique attributes and methods for the dealer
 * 
 * @author Dengcheng Ji
 * @param dealer the name of the dealer to be called
 */
class Dealer extends Participant {

	Dealer(String dealer) { // Constructor of the class
		super(dealer);
	}

	/**
	 * This method displays the blind card figure representing the second card the
	 * dealer receives, which is upside down
	 */
	public void blindCard() {
		System.out.println(" ------------- ");
		System.out.println("|  +          |");
		System.out.println("|  +          |");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println("|          +  |");
		System.out.println("|          +  |");
		System.out.println(" ------------- ");
	}
}

/**
 * The class that simulated the Black Jack game, contains the main program
 * structures
 * 
 * @author Dengcheng Ji
 *
 */
public class BlackJackGameSimulator {
	public static void main(String[] args) {

		// A String array stores the string representation of the cards, beginning with
		// the number,
		// then the suits; S = Spades, C = Clubs, D = Diamonds, and H = Hearts
		String cardDeck[] = { "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS", "AS", "2C", "3C",
				"4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC", "AC", "2H", "3H", "4H", "5H", "6H", "7H",
				"8H", "9H", "10H", "JH", "QH", "KH", "AH", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD",
				"QD", "KD", "AD" };

		// An integer array stores the points of the each cards in correspondence with
		// the the cardDeck[] array,
		// note the Ace is worth 11 points initially
		int cardPoints[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2,
				3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

		// A string array corresponding to the cardDeck[] array to determine if a card
		// has been dealt from the card deck
		String cardUsed[] = new String[52];

		double playerMoney = 0; // The total money the player has for the game
		String anotherRound = null; // String variable that stores the player input whether to go for another round
									// or not
		Scanner input = new Scanner(System.in);
		Scanner strInput = new Scanner(System.in);

		// Initialize the amount of money the player will get to play the game
		System.out.println("How much money did you bring to the table?");
		playerMoney = input.nextDouble();

		// Set up a do-while loop that contains the algorithm for 1 round of game, the
		// loop end if the player do not want to continue
		do {
			// Instantiate a Player and a Dealer object, these objects need to be
			// instantiated within the do-while loop since all the attributes of the objects
			// need to be re-initiated when a new round of the game starts.
			Player player1 = new Player("Player 1");
			Dealer dealer = new Dealer("Dealer");
			int hitOrStay = 0; // Variable to store user input value to determine whether to hit or to stay

			// Reassigning all the cards to be "not used", this is like getting all the
			// cards from the previous rounds from the participants
			for (int i = 0; i < cardUsed.length; i++) {
				cardUsed[i] = "not used";
			}

			// Ask player's input for the bet of the round, only values lager than 0 can be
			// entered
			do {
				if (player1.getBet() > playerMoney) {
					System.out.println("You don't have this much money dude");
				}
				System.out.println("Place your bet:");
				player1.setBet(input.nextDouble());
			} while (player1.getBet() < 0 | player1.getBet() > playerMoney);

			gameStart(); // Invoke the gameStart() method

			// Player's first 2 cards
			for (int i = 1; i <= 2; i++) {
				player1.gettingCard(randomNumber(), cardUsed, cardDeck, cardPoints);
			}

			// Invoke the Player class methods to display the cards and show the points of
			// the player
			player1.showingCard();
			player1.showingPoints();
			input.nextLine();

			// If the player's points exceeds 21, invoke a method to display the appropriate
			// message, whether it is a Black Jack or a Bust
			if (player1.calculatingPoints() > 20) {
				player1.blackJackOrBust(player1);
			}

			else {
				input.nextLine();
				// Dealer's first 2 cards
				System.out.println("\nDealer gets 2 cards, 1 facing down");
				for (int i = 1; i <= 2; i++) {
					dealer.gettingCard(randomNumber(), cardUsed, cardDeck, cardPoints);
					if (i == 1) {
						dealer.showingCard(); // Dealer's first card will be shown
					} else {
						dealer.blindCard(); // Dealer's second is upside down
					}
				}
				input.nextLine();

				// Player start to get cards
				System.out.println();
				System.out.println("Player 1's turn");

				do {
					System.out.println("Press 1 to Hit or 2 to Stay"); // Prompts the player to hit or stay
					hitOrStay = input.nextInt();
				} while (hitOrStay != 1 & hitOrStay != 2);

				if (hitOrStay == 1) { // If the player choose to hit after his/her first 2 cards
					do { // The do-while loop is to repeat the dealing process until the player stays

						// Invoke the Player class methods to display the cards and show the points of
						// the player
						player1.gettingCard(randomNumber(), cardUsed, cardDeck, cardPoints);
						player1.showingCard();
						player1.showingPoints();

						// If the player's points exceeds 21, invoke a method to display the appropriate
						// message, whether it is a Black Jack or a Bust
						if (player1.calculatingPoints() > 20) {
							player1.blackJackOrBust(player1);
							break; // the do-while loop breaks if player's points exceeds 20, whether it is a Black
									// Jack or Bust
						}
						do {
							System.out.println("Press 1 to Hit or 2 to Stay");
							hitOrStay = input.nextInt();
						} while (hitOrStay != 1 & hitOrStay != 2);

					} while (hitOrStay == 1); // Loop will end if the player enters 2 to stay
				}

				if (hitOrStay == 2) { // If the player choose to stay after his/her first 2 cards
					System.out.println("Dealer's turn, and reveals the blind card");

					dealer.showingCard(); // Invoke the Dealer class method to display the 2 cards the dealer received
											// earlier
					dealer.showingPoints();
					input.nextLine();

					// Set up if statements to determine the next step after the blind card is
					// unfold
					if (dealer.calculatingPoints() < 17) { // Dealer will continue get cards if the dealer point is less
															// than 17
						do {
							System.out.println("\nDealer gets another card");
							dealer.gettingCard(randomNumber(), cardUsed, cardDeck, cardPoints);
							dealer.showingCard();
							dealer.showingPoints();
							if (dealer.calculatingPoints() > 20) {
								dealer.blackJackOrBust(dealer); // If the dealer point is gets larger than 20, the
																// method is invoked to display the appropriate message
							}
							input.nextLine();
						} while (dealer.calculatingPoints() < 17);
					} else if (dealer.calculatingPoints() > 20) { // If the dealer's points is larger than 20 right
																	// after the blind card is unfold
						dealer.blackJackOrBust(dealer);
					}
				}
			}

			System.out.println();
			System.out.println("          Announce Winner");
			System.out.println("----------------------------------------");
			System.out.println();

			// Invoke the methods to display the points of the player and the dealer
			player1.showingPoints();
			dealer.showingPoints();

			//displays the result of the round with appropriate message and
			//return value of the money the player has left after the result
			if (player1.calculatingPoints() > 21) {
				System.out.println("You lose your bet of $" + player1.getBet() + " and now you have $" + (playerMoney -= player1.getBet()));
				
			} else if (dealer.calculatingPoints() > 21) {
				System.out.println("You won $" + 2 * player1.getBet() + " and now you have $" + (playerMoney += player1.getBet()));
				
			} else if (player1.calculatingPoints() == 21 | player1.calculatingPoints() > dealer.calculatingPoints()) {
				System.out.println("You won $" + 2 * player1.getBet() + " and now you have $" + (playerMoney += player1.getBet()));
				
			} else if (player1.calculatingPoints() == dealer.calculatingPoints()) {
				System.out.println("Tie, you get your bet back and now you have $" + playerMoney);
				
			} else {
				System.out.println("You lose your bet of $" + player1.getBet() + " and now you have $" + (playerMoney -= player1.getBet()));
			}

			anotherRound = anotherRound(strInput);

		} while (playerMoney > 0 & anotherRound.equalsIgnoreCase("y")); // The round will only repeat if the player's
																		// money is not 0 and the player wants another
																		// round

		// Display message when the game ends, whether it is because the player chose to
		// or the player is out of cash
		if (playerMoney <= 0 & anotherRound.equalsIgnoreCase("y")) {
			System.out.println("Sorry you are out of cash....");
		} else if (playerMoney > 0 & anotherRound.equalsIgnoreCase("n")) {
			System.out.println("Thank you for playing");
		}
	}

	/**
	 * The method displays the graphic when a new round of game starts
	 */
	public static void gameStart() {
		System.out.println();
		System.out.println("          Game Start");
		System.out.println("---------------------------------");
	}

	/**
	 * The method generates a random number to be used to draw a random card from a
	 * deck of cards
	 * 
	 * @return a random number between 0 and 51
	 */
	public static int randomNumber() {
		return (int) (51 * Math.random());
	}

	/**
	 * This method gathers information from the player whether a new round of game
	 * is to be played
	 * 
	 * @param strInput the string input from the player
	 * @return anotherRound the string of "Y" or "N"
	 */
	public static String anotherRound(Scanner strInput) {
		String anotherRound = null;
		do {
			System.out.println();
			System.out.println("Play again?(Y/N)");
			anotherRound = strInput.nextLine();
		} while (anotherRound.equalsIgnoreCase("y") == false & anotherRound.equalsIgnoreCase("n") == false);

		return anotherRound;
	}

}

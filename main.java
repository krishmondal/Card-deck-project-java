import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


enum Suit {
    SPADES, CLUBS, HEARTS, DIAMONDS
}


enum Rank {
    A(14), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), J(11), Q(12), K(13);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}


class Deck {
    private List<Card> cards;
    private static final int TOTAL_CARDS = 52;

    public Deck() {
        cards = new ArrayList<>(TOTAL_CARDS);
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null; 
        }
        return cards.remove(cards.size() - 1);
    }

    public int getDeckSize() {
        return cards.size();
    }

    public List<Card> drawRandomCards(int numberOfCards) {
        List<Card> drawnCards = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfCards; i++) {
            if (cards.isEmpty()) {
                break; 
            }
            int index = random.nextInt(cards.size());
            drawnCards.add(cards.remove(index));
        }
        return drawnCards;
    }
}


class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
       
        int colorComparison = getColor(c1).compareTo(getColor(c2));
        if (colorComparison != 0) {
            return colorComparison;
        }
    
        int suitComparison = c1.getSuit().compareTo(c2.getSuit());
        if (suitComparison != 0) {
            return suitComparison;
        }
       
        return Integer.compare(c1.getRank().getValue(), c2.getRank().getValue());
    }

    private Color getColor(Card card) {
        if (card.getSuit() == Suit.HEARTS || card.getSuit() == Suit.DIAMONDS) {
            return Color.RED;
        } else {
            return Color.BLACK;
        }
    }

    private enum Color {
        RED, BLACK
    }
}


public class main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle(); 

        
        List<Card> drawnCards = deck.drawRandomCards(20);
        System.out.println("Drawn Cards:");
        for (Card card : drawnCards) {
            System.out.println(card);
        }

        
        Collections.sort(drawnCards, new CardComparator());
        System.out.println("\nSorted Cards:");
        for (Card card : drawnCards) {
            System.out.println(card);
        }
    }
}
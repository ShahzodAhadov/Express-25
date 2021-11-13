package uz.pdp.service;

import uz.pdp.model.Cafe;
import uz.pdp.model.Card;
import uz.pdp.model.User;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardsService implements BaseService<Card, List<Card>, User, Long, UUID> {
    private List<Card> cards = new ArrayList<>();

    @Override
    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public int sendSMS() {
        return (int) (Math.random() * 90000 + 10000);
    }

    @Override
    public List<Card> getList(User user) {
        UUID userID = user.getId();
        List<Card> myCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.getUserID() == userID && card.isActive()) {
                myCards.add(card);
            }
        }
        return myCards;
    }

    @Override
    public List<Card> getList() {
        return null;
    }

    @Override
    public boolean check(Long cardNumber) {
        for (Card card : cards) {
            if (card.getCardNumber() == cardNumber)
                return false;
        }
        return true;
    }

    @Override
    public Card get(Long aLong) {
        return null;
    }

    @Override
    public Card get(Long d1, Long d2) {
        return null;
    }

    @Override
    public Card getByID(UUID id) {
        for (Card card : cards) {
            if (card.getId().equals(id))
                return card;
        }
        return null;
    }
}

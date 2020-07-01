package com.memo.api.service;

import com.memo.api.domain.Card;
import com.memo.api.domain.CardList;
import com.memo.api.domain.CardSelector;
import com.memo.api.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public CardList find(CardSelector cardSelector) {
        CardList cardList = new CardList();
        cardList.setCards(cardRepository.findList(cardSelector));
        return cardList;
    }

    @Override
    public Card get(Long cardId) {
        return cardRepository.findOne(cardId);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void add(Card card){
        cardRepository.insert(card);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void set(Card card){
        cardRepository.update(card);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void remove(Long cardId){
        Card card = cardRepository.findOne(cardId);
        cardRepository.delete(card);
    }


}

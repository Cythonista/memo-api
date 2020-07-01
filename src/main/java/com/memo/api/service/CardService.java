package com.memo.api.service;

import com.memo.api.domain.Card;
import com.memo.api.domain.CardList;
import com.memo.api.domain.CardSelector;

public interface CardService {
    CardList find(CardSelector cardSelector);
    //CardList find(@Param("cardId") Long cardId);
    Card get(Long cardId);
    void add(Card card);
    void set(Card card);
    void remove(Long cardId);
}

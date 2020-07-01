package com.memo.api.repository;

import com.memo.api.domain.Card;
import com.memo.api.domain.CardSelector;
import java.util.List;

public interface CardRepository {
    List<Card> findList(CardSelector selector);

    Card findOne(Long cardId);

    void insert(Card card);

    void update(Card card);

    void delete(Card card);
}

package com.memo.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.memo.api.domain.Card;
import com.memo.api.domain.CardList;
import com.memo.api.domain.CardSelector;
import com.memo.api.repository.CardRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CardServiceImplTest {
    @Nested public class UseMockitoTest {
        @Mock
        private CardRepository cardRepository;

        @BeforeEach
        public void before() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testFind() {
            CardSelector input = new CardSelector();
            List<Card> findResult = new ArrayList<>();
            Mockito.doReturn(findResult).when(cardRepository).findList(input);
            CardServiceImpl target = new CardServiceImpl(cardRepository);

            CardList cardList = target.find(input);

            assertEquals(findResult, cardList.getCards());
            Mockito.verify(cardRepository, Mockito.times(1)).findList(input);
        }

        @Test
        public void testGet() {
            CardSelector cardSelector = new CardSelector();
            cardSelector.setCardId(100L);
            Card findResult = new Card();
            Mockito.doReturn(findResult).when(cardRepository).findOne(cardSelector.getCardId());
            CardServiceImpl target = new CardServiceImpl(cardRepository);
            Card result = target.get(cardSelector);

            assertEquals(findResult, result);
            Mockito.verify(cardRepository, Mockito.times(1)).findOne(cardSelector.getCardId());
        }

        @Test
        public void testAdd() {
            Card card = new Card();
            CardServiceImpl target = new CardServiceImpl(cardRepository);

            target.add(card);
            Mockito.verify(cardRepository, Mockito.times(1)).insert(card);
        }

        @Test
        public void testSet() {
            Card card = new Card();
            card.setCardId(100L);
            Mockito.doNothing().when(cardRepository).update(card);
            CardServiceImpl target = new CardServiceImpl(cardRepository);

            target.set(card);

            Mockito.verify(cardRepository, Mockito.times(1)).update(card);
        }

        @Test
        public void testRemove() {
            CardSelector cardSelector = new CardSelector();
            cardSelector.setCardId(100L);
            Mockito.doNothing().when(cardRepository).delete(cardSelector);
            CardServiceImpl target = new CardServiceImpl(cardRepository);

            target.remove(cardSelector);

            Mockito.verify(cardRepository, Mockito.times(1)).delete(cardSelector);
        }
    }
}

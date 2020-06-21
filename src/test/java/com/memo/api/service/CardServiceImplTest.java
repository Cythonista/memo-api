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
    }
}

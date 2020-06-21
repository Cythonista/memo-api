package com.memo.api.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.memo.api.ApiApplication;
import com.memo.api.domain.Card;
import com.memo.api.domain.CardSelector;
import com.memo.api.repository.util.DbTestExecutionListener;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

public class CardRepositoryImplDbUnitTest {
    @SpringBootTest(classes = ApiApplication.class)
    @TestExecutionListeners( {DependencyInjectionTestExecutionListener.class, DbTestExecutionListener.class} )
    @Nested public class FindDbTest {

        @Autowired
        private CardRepository target;

        @Test
        public void testFindAll() {
            List<Card> cards = target.findList(new CardSelector());
            assertEquals(5, cards.size());
            assertEquals(1L, cards.get(0).getCardId());
            assertEquals(2L, cards.get(1).getCardId());
            assertEquals(3L, cards.get(2).getCardId());
            assertEquals(4L, cards.get(3).getCardId());
            assertEquals(5L, cards.get(4).getCardId());
        }
    }
}

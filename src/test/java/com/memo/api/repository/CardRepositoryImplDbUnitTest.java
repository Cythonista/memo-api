package com.memo.api.repository;

import static com.memo.api.repository.util.DbTestExecutionListener.DATA_DIR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.memo.api.ApiApplication;
import com.memo.api.domain.Card;
import com.memo.api.domain.CardSelector;
import com.memo.api.repository.util.DbTestExecutionListener;
import com.memo.api.util.DbUnitUtil;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
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

        @Test
        public void testFindOne() {
            Card card = target.findOne(1L);
            assertNotNull(card);
            assertEquals(1L, card.getCardId());
            assertEquals("カード1", card.getCardName());
            assertEquals("概要1", card.getOverview());
        }


        @Test
        public void testGetException() {assertThrows(ResourceNotFoundException.class, () -> target.findOne(6L));}
    }

    @SpringBootTest(classes = ApiApplication.class)
    @TestExecutionListeners( {DependencyInjectionTestExecutionListener.class, DbTestExecutionListener.class} )
    @Nested public class InsertDbTest {
        private final File expectedData = new File(DATA_DIR + "card_insert_expected.xlsx");

        @Autowired
        private CardRepository target;

        @Autowired
        private DataSource dataSource;

        @Test
        public void testInsert() {
            Card card = new Card();
            card.setCardName("カード6");
            card.setOverview("概要6");
            target.insert(card);
            DbUnitUtil.assertMutateResult(
                    dataSource,
                    "CARD",
                    expectedData,
                    Arrays.asList("CARD_ID", "UPDATED_AT"));
        }
    }

    @SpringBootTest(classes = ApiApplication.class)
    @TestExecutionListeners( {DependencyInjectionTestExecutionListener.class, DbTestExecutionListener.class} )
    @Nested public class UpdateDbTest {
        private final File expectedData = new File(DATA_DIR + "card_update_expected.xlsx");

        @Autowired
        private CardRepository target;

        @Autowired
        private DataSource dataSource;

        @Test
        public void testUpdate() {
            Card card = new Card();
            card.setCardId(5L);
            card.setCardName("カード5");
            card.setOverview("概要5");
            target.update(card);
            DbUnitUtil.assertMutateResult(
                    dataSource,
                    "CARD",
                    expectedData,
                    Arrays.asList("UPDATED_AT"));
        }
    }

    @SpringBootTest(classes = ApiApplication.class)
    @TestExecutionListeners( {DependencyInjectionTestExecutionListener.class, DbTestExecutionListener.class} )
    @Nested public class DeleteDbTest {
        private final File expectedData = new File(DATA_DIR + "card_remove_expected.xlsx");

        @Autowired
        private CardRepository target;

        @Autowired
        private DataSource dataSource;

        @Test
        public void testDelete() {
            CardSelector cardSelector = new CardSelector();
            cardSelector.setCardId(5L);
            target.delete(cardSelector);
            DbUnitUtil.assertMutateResult(
                    dataSource,
                    "CARD",
                    expectedData,
                    Arrays.asList("UPDATED_AT"));
        }
    }


}

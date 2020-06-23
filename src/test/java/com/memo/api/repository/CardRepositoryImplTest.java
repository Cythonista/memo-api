package com.memo.api.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.memo.api.domain.Card;
import com.memo.api.domain.CardSelector;
import com.memo.api.repository.mybatis.CardMapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CardRepositoryImplTest {

    @Mock
    private SqlSession sqlSession;

    @Mock
    private CardMapper mapper;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(mapper).when(sqlSession).getMapper(CardMapper.class);
    }

    @AfterEach
    public void after() {
        Mockito.verify(sqlSession, Mockito.times(1)).getMapper(CardMapper.class);
    }

    @Test
    public void testFindList() {
        CardSelector selector = new CardSelector();
        List<Card> findResult = new ArrayList<>();
        Card card = new Card();
        findResult.add(card);
        Mockito.doReturn(findResult).when(mapper).find(selector);
        List<Card> result = new CardRepositoryImpl(this.sqlSession).findList(selector);
        assertEquals(findResult, result);
        Mockito.verify(mapper, Mockito.times(1)).find(selector);
    }
}
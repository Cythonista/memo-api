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

    @Test
    public void testGet(){
        Long input = 100L;
        Card findResult = new Card();
        Mockito.doReturn(findResult).when(mapper).get(input);

        Card result = new CardRepositoryImpl(this.sqlSession).findOne(input);
        assertEquals(findResult, result);
        Mockito.verify(mapper, Mockito.times(1)).get(input);
    }

    @Test
    public void testAdd() {
        Card input = new Card();
        Mockito.doReturn(1).when(mapper).add(input);
        new CardRepositoryImpl(this.sqlSession).insert(input);
        Mockito.verify(mapper, Mockito.times(1)).add(input);
    }

    @Test
    public void testSet() {
        Card input = new Card();
        Mockito.doReturn(1).when(mapper).set(input);
        new CardRepositoryImpl(this.sqlSession).update(input);
        Mockito.verify(mapper, Mockito.times(1)).set(input);
    }

    @Test
    public void testRemove() {
        Card input = new Card();
        Mockito.doReturn(1).when(mapper).remove(input);
        new CardRepositoryImpl(this.sqlSession).delete(input);
        Mockito.verify(mapper, Mockito.times(1)).remove(input);
    }

}
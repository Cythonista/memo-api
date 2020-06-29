package com.memo.api.repository;

import com.memo.api.domain.Card;
import com.memo.api.domain.CardSelector;
import com.memo.api.repository.mybatis.CardMapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepositoryImpl implements CardRepository {
    private static final Logger logger = LoggerFactory.getLogger(CardRepositoryImpl.class);
    private final SqlSession sqlSession;

    public CardRepositoryImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Card> findList(CardSelector cardSelector) {
        return this.sqlSession.getMapper(CardMapper.class).find(cardSelector);
    }

    @Override
    public Card findOne(Long cardId) {
        Card card = this.sqlSession.getMapper(CardMapper.class).get(cardId);
        if (card == null) {
            logger.info("Card not found. id = {}", cardId);
            throw new ResourceNotFoundException("Card not found.");
        }
        return card;
    }
    @Override
    public void insert(Card card) {
        this.sqlSession.getMapper(CardMapper.class).add(card);
    }

    @Override
    public void update(Card card) {
        int affected = this.sqlSession.getMapper(CardMapper.class).set(card);
        if(affected != 1) {
            logger.info("Card not found. id={}", card.getCardId());
            throw new ResourceNotFoundException("Card not found.");
        }
    }

    @Override
    public void delete(CardSelector cardSelector) {
        this.sqlSession.getMapper(CardMapper.class).remove(cardSelector);
    }
}
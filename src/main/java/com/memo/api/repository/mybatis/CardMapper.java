package com.memo.api.repository.mybatis;

import com.memo.api.domain.Card;
import com.memo.api.domain.CardSelector;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CardMapper {
    List<Card> find(CardSelector cardSelector);
    Card get(@Param("cardId") Long cardId);
    int add(Card card);
    int set(Card card);
    int remove(CardSelector selector);
}
package com.memo.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) //エラー回避 JSONには存在してマッピング対象のクラスには存在しないフィールドがあるとき
@JsonInclude(JsonInclude.Include.NON_NULL) //nullのフィールドをレスポンスさせない
public class CardList {
    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}
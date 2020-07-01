package com.memo.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true) //エラー回避 JSONには存在してマッピング対象のクラスには存在しないフィールドがあるとき
@JsonInclude(JsonInclude.Include.NON_NULL) //nullのフィールドをレスポンスさせない
public class Card {
    private Long cardId;
    private String cardName;
    private String overview;
    private LocalDateTime updatedAt;
    private Boolean deleteFlag;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
package com.memo.api.controller;

import com.memo.api.domain.Card;
import com.memo.api.domain.CardList;
import com.memo.api.domain.CardSelector;
import com.memo.api.service.CardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "services/v1/cards")
public class CardRestController {

    private final CardService cardService;

    public CardRestController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public CardList find(CardSelector cardSelector) {
        return this.cardService.find(cardSelector);
    }

    @GetMapping(path = "/{cardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Card get(@PathVariable Long cardId) {
        return this.cardService.get(cardId);
    }
}

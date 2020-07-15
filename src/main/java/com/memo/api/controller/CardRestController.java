package com.memo.api.controller;

import com.memo.api.domain.Card;
import com.memo.api.domain.CardList;
import com.memo.api.domain.CardSelector;
import com.memo.api.service.CardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/card")
public class CardRestController {

    private final CardService cardService;

    public CardRestController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public CardList find(CardSelector cardSelector) {
        return this.cardService.find(cardSelector);
    }

    @GetMapping(path="/{cardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Card get(@PathVariable Long cardId) {
        return this.cardService.get(cardId);
    }

    @PostMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Card card) {
        this.cardService.add(card);
    }

    @PatchMapping(path="/{cardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void set(@PathVariable Long cardId, @RequestBody Card card) {
        card.setCardId(cardId);
        this.cardService.set(card);
    }

    @DeleteMapping(path="/{cardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void remove(@PathVariable Long cardId){
        this.cardService.remove(cardId);
    }
}

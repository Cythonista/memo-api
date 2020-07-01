package com.memo.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.memo.api.domain.Card;
import com.memo.api.domain.CardList;
import com.memo.api.domain.CardSelector;
import com.memo.api.service.CardService;
import com.memo.api.util.UnitTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ChannelRestControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardRestController target;

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void testFindWhenAllParameters() throws Exception {
        CardList findResult = new CardList();
        ArgumentMatcher<CardSelector> matcher = argument -> {
            assertEquals(1L, argument.getCardId());
            return true;
        };
        Mockito.doReturn(findResult).when(cardService).find(Mockito.argThat(matcher));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/services/v1/list")
                .param("cardId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(UnitTestUtil.entity2JsonText(findResult), result.getResponse().getContentAsString());
        Mockito.verify(cardService, Mockito.times(1)).find(Mockito.argThat(matcher));
    }

    @Test
    public void testFindWhenNullParameters() throws Exception {
        CardList findResult = new CardList();
        ArgumentMatcher<CardSelector> matcher = (argument) -> {
            assertNull(argument.getCardId());
            return true;
        };
        Mockito.doReturn(findResult).when(cardService).find(Mockito.argThat(matcher));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/services/v1/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(cardService, Mockito.times(1)).find(Mockito.argThat(matcher));
        assertEquals(UnitTestUtil.entity2JsonText(findResult), result.getResponse().getContentAsString());
    }

    @Test
    public void testGet() throws Exception {
        Long input = 100L;
        Card getResult = new Card();
        Mockito.doReturn(getResult).when(cardService).get(input);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/services/v1/"+input.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals(UnitTestUtil.entity2JsonText(getResult), result.getResponse().getContentAsString());
        Mockito.verify(cardService, Mockito.times(1)).get(input);
    }

    @Test
    public void testAdd() throws Exception {
        // setup: 事前準備
        Mockito.doNothing().when(cardService).add(ArgumentMatchers.any(Card.class));
        // when: 対象のAPIリクエストを実行
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/services/v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UnitTestUtil.entity2JsonText(new Card())));
        // then: テスト結果の検証。戻り値、Mockの呼び出し方法、回数など
        Mockito.verify(cardService, Mockito.times(1)).add(ArgumentMatchers.any(Card.class));
    }

    @Test
    public void testSet() throws Exception {
        // setup: 事前準備
        Long id = 10L;
        ArgumentMatcher<Card> matcher = argument -> {
            assertEquals(argument.getCardId(), id);
            return true;
        };
        Mockito.doNothing().when(cardService).set(Mockito.argThat(matcher));
        // when: 対象のAPIリクエストを実行
        mockMvc.perform(MockMvcRequestBuilders
                .patch("/services/v1/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UnitTestUtil.entity2JsonText(new Card())));
        // then: テスト結果の検証。戻り値、Mockの呼び出し方法、回数など
        Mockito.verify(cardService, Mockito.times(1)).set(Mockito.argThat(matcher));
    }

    /**
     * ユーザ削除による検証
     */
    @Test
    public void testRemove() throws Exception {
        // setup: 事前準備
        Long id = 10L;
        Mockito.doNothing().when(cardService).remove(id);
        // when: 対象のAPIリクエストを実行
        mockMvc.perform(MockMvcRequestBuilders.delete("/services/v1/10"));
        // then: テスト結果の検証。戻り値、Mockの呼び出し方法、回数など
        Mockito.verify(cardService, Mockito.times(1)).remove(id);
    }

}
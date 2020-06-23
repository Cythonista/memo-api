package com.memo.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.memo.api.domain.CardList;
import com.memo.api.domain.CardSelector;
import com.memo.api.service.CardService;
import com.memo.api.util.UnitTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
                .get("/services/v1/cards")
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
                .get("/services/v1/cards"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(cardService, Mockito.times(1)).find(Mockito.argThat(matcher));
        assertEquals(UnitTestUtil.entity2JsonText(findResult), result.getResponse().getContentAsString());
    }

}
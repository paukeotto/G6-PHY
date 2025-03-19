package org.g06.heartbridge.tool;

import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ConsultantSuggestTest {
    @Autowired
    private ApplicationContext applicationContext;
    @Test
    void test() {
        ConsultantSuggest consultantSuggest = applicationContext.getBean(ConsultantSuggest.class);
        ConsultantSuggest consultantSuggest1=applicationContext.getBean(ConsultantSuggest.class);
        assertNotSame(consultantSuggest1, consultantSuggest);
    }
}
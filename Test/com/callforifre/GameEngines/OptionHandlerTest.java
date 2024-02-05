package com.callforifre.GameEngines;

import com.callforfire.GameEngines.OptionHandler;
import org.junit.Before;
import org.junit.Test;

public class OptionHandlerTest {
    private OptionHandler optionHandler;

    @Before
    public void init() {
        optionHandler = new OptionHandler();
    }

    @Test
    public void handleTalkWithNpcShouldPrintErrorMessage() {
        optionHandler.handleTalkWithNpc("jimmy");
    }

    @Test
    public void handleTalkWithNpcShouldPrintNpcDialogue() {
        optionHandler.handleTalkWithNpc("cookie");
    }
}
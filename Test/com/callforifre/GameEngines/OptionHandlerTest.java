package com.callforifre.GameEngines;

import com.callforfire.GameEngines.OptionHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OptionHandlerTest {
    private OptionHandler optionHandler;
    List<String> actionNoun = new ArrayList<>();

    @Before
    public void init() {
        optionHandler = new OptionHandler();
        actionNoun.add("look");
        actionNoun.add("cheese");
    }

    @Test
    public void handleTalkWithNpcShouldPrintErrorMessage() {
        optionHandler.handleTalkWithNpc("jimmy");
    }

    @Test
    public void handleTalkWithNpcShouldPrintNpcDialogue() {
        optionHandler.handleTalkWithNpc("cookie");
    }

    @Test
    public void handleItemDescriptionShouldPrintItemsDescriptionAndShouldNotBeNull() {
        optionHandler.handleLook(actionNoun);
    }
}
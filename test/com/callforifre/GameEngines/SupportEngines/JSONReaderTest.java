package com.callforifre.GameEngines.SupportEngines;

import com.callForFire.gameEngines.supportEngines.JsonReader;
import com.callForFire.models.Item;
import com.callForFire.models.Location;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JSONReaderTest {

    @Before
    public void init() {
    }

    @Test
    public void jsonReaderShouldReturnSpecificNPCData() {
        // This should print the data for private snuffy
        assertNotNull(JsonReader.readNpcDialogue("private-snuffy"));
    }

    @Test
    public void readNpcDialogueShouldReturnNull() {
        assertNull(JsonReader.readNpcDialogue("PRIVateSnuffy"));
    }


    @Test
    public void readLocationInformationShouldPrintTheNewLocationsNameAndNotBeNull() {
        Location location = JsonReader.returnLocationInformationForDirectionToMove("Firing Point", "west");
        System.out.println(location.getName());
        assertNotNull(location);
    }

    @Test
    public void readLocationInformationShouldBeNull() {
        // To the north is hesco barriers, there is no location for there so this should be null
        Location location = JsonReader.returnLocationInformationForDirectionToMove("Firing Point", "north");
        assertNull(location);
    }

    @Test
    public void readLocationInformationShouldBeNullWithInvalidInput() {
        // BlahBlah is not a vaild direction, this should return null
        Location location = JsonReader.returnLocationInformationForDirectionToMove("Firing Point", "blahblah");
        assertNull(location);
    }

    @Test
    public void readItemDescriptionShouldNotBeNullAndPrintItemDescription() {
        Item item = JsonReader.readItemDescription("cheese");
        System.out.println("Item: " + item.getName() + ", description: " + item.getDescription());
        assertNotNull(item);
    }

    @Test
    public void verbReader_shouldReturnGo_ifWalk_passed() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("I");
        userInput.add("walk");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("north");

        String verb = JsonReader.readVerbJson(userInput);

        assertEquals("go", verb);
    }

    @Test
    public void nounReader_shouldReturn_northIfNorthIsPassed() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("I");
        userInput.add("walk");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("to");
        userInput.add("the");
        userInput.add("north");

        String noun = JsonReader.readNounJson(userInput);

        assertEquals("north", noun);
    }

    @Test
    public void nounReader_shouldReturn_brokenMortarTube_ifPassed() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("get");
        userInput.add("Broken-Mortar-tube");

        String noun = JsonReader.readNounJson(userInput);

        assertEquals("Broken-Mortar-tube", noun);
    }

    @Test
    public void verbReader_shouldReturn_get() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("get");
        userInput.add("Broken-Mortar-tube");

        String verb = JsonReader.readVerbJson(userInput);

        assertEquals("get", verb);
    }
}
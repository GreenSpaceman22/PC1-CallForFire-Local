package com.callforifre.GameEngines.SupportEngines;

import com.callforfire.GameEngines.SupportEngines.JSON_Reader;
import com.callforfire.Models.Item;
import com.callforfire.Models.Location;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

public class JSONReaderTest {

    @Before
    public void init() {
    }

    @Test
    public void jsonReaderShouldReturnSpecificNPCData() {
        // This should print the data for private snuffy
        assertNotNull(JSON_Reader.readNpcDialogue("private-snuffy"));
    }

    @Test
    public void readNpcDialogueShouldReturnNull() {
        assertNull(JSON_Reader.readNpcDialogue("PRIVateSnuffy"));
    }


    @Test
    public void readLocationInformationShouldPrintTheNewLocationsNameAndNotBeNull() {
        Location location = JSON_Reader.returnLocationInformationForDirectionToMove("Firing Point", "west");
        System.out.println(location.getName());
        assertNotNull(location);
    }

    @Test
    public void readLocationInformationShouldBeNull() {
        // To the north is hesco barriers, there is no location for there so this should be null
        Location location = JSON_Reader.returnLocationInformationForDirectionToMove("Firing Point", "north");
        assertNull(location);
    }

    @Test
    public void readLocationInformationShouldBeNullWithInvalidInput() {
        // BlahBlah is not a vaild direction, this should return null
        Location location = JSON_Reader.returnLocationInformationForDirectionToMove("Firing Point", "blahblah");
        assertNull(location);
    }

    @Test
    public void readItemDescriptionShouldNotBeNullAndPrintItemDescription() {
        Item item = JSON_Reader.readItemDescription("cheese");
        System.out.println("Item: " + item.getName() + ", description: " + item.getDescription());
        assertNotNull(item);
    }

    @Test
    public void verbReader_shouldReturnGo_ifWalk_passed() {
        List<String> userInput = new ArrayList<String>();
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

        String verb = JSON_Reader.readVerbJson(userInput);

        assertEquals("go", verb);
    }

    @Test
    public void nounReader_shouldReturn_northIfNorthIsPassed() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("walk");
        userInput.add("north");

        String noun = JSON_Reader.readNounJson(userInput);

        assertEquals("north", noun);
    }
}
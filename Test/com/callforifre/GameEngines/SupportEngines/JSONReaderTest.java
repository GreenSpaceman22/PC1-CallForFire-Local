package com.callforifre.GameEngines.SupportEngines;

import com.callforfire.GameEngines.SupportEngines.JSON_Reader;
import org.junit.Before;
import org.junit.Test;

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
}
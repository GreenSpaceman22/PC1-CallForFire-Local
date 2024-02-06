package com.callforifre.GameEngines.SupportEngines;

import com.callforfire.Utils.OptionChecker;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptionCheckerTest {

    @Test
    public void checkItemInLocationShouldReturnTrue() {
        assertTrue(OptionChecker.checkItemIsPresentInLocation("Chow Hall", "cheese"));
    }

    @Test
    public void checkItemInLocationShouldReturnFalse() {
        assertFalse(OptionChecker.checkItemIsPresentInLocation("Chow Hall", "milk"));
    }
}

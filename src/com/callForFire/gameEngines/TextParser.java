package com.callForFire.gameEngines;

import com.apps.util.Prompter;
import com.callForFire.gameEngines.supportEngines.JsonReader;
import com.callForFire.utils.UtilFunctions;

import java.util.*;

public class TextParser {
    private final List<String> parsedWords = new ArrayList<>();
    private final List<String> actionNoun = new ArrayList<>();
    private String userInput;
    private final Prompter prompter = new Prompter(new Scanner(System.in));

    // METHODS
    // Get the action and noun of the users input, this will be the main method of this class
    public List<String> getUserString(OptionHandler optionHandler, PlayerEngine playerEngine) {
        promptUser();

        parseUserInput();

        parseActionAndNoun(optionHandler, playerEngine);

        return actionNoun;
    }

    public void promptUser() {
        String userInput = prompter.prompt(">").toLowerCase();
        setUserInput(userInput);
    }

    public void parseUserInput() {
        // Split the user input into words based on whitespace
        String[] words = getUserInput().split("\\s+");

        // Add the words to the parsedWords list
        parsedWords.addAll(Arrays.asList(words));
    }

    public void parseActionAndNoun(OptionHandler optionHandler, PlayerEngine playerEngine) {

        actionNoun.clear(); // Ensure the action noun is clear before we set it again

        String action = JsonReader.readVerbJson(getParsedWords());
        String noun = JsonReader.readNounJson(getParsedWords());

        actionNoun.add(action);
        actionNoun.add(noun);

        parsedWords.clear();

        // move, get, fire, talk, look, inventory, drop, help, quit
        if("go".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setMove(true);
        } else if("get".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setGet(true);
        } else if ("fire".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setFire(true);
        } else if("talk".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setTalk(true);
        } else if ("look".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setLook(true);
        }  else if ("inventory".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setInventory(true);
        } else if ("drop".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setDrop(true);
        } else if ("help".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setHelp(true);
        } else if ("quit".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setQuit(true);
        } else if ("cheat".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setCheat(true);
        }else {
            UtilFunctions.showInvalidCommand(userInput);
            getUserString(optionHandler, playerEngine);
        }
    }

    // Getters/Setters
    public List<String> getParsedWords() {
        return parsedWords;
    }

    public List<String> getActionNoun() {
        return actionNoun;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
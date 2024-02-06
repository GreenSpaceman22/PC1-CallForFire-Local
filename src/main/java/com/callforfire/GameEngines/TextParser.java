package com.callforfire.GameEngines;

import com.apps.util.Prompter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextParser {
    private final List<String> parsedWords = new ArrayList<>();
    private final List<String> actionNoun = new ArrayList<>();
    private String userInput;
    private final Prompter prompter = new Prompter(new Scanner(System.in));


    // METHODS
    // Get the action and noun of the users input, this will be the main method of this class
    public List<String> getUserString(OptionHandler optionHandler) {
        promptUser();

        parseUserInput();

        parseActionAndNoun(optionHandler);

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

    public void parseActionAndNoun(OptionHandler optionHandler) {
        // These are temporary to keep the game circular until we incorporate the actual parser
        actionNoun.clear();
        actionNoun.addAll(this.getParsedWords());
        parsedWords.clear();

        if("go".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setMove(true);
        } else if("talk".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setTalk(true);
        } else if ("look".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setLook(true);
        } else if("fire".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setFire(true);
        } else if("get".equalsIgnoreCase(actionNoun.get(0))) {
            optionHandler.setGet(true);
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
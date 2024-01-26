package com.callforfire.GameEngines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextParser {
    private List<String> parsedWords;
    private List<String> actionNoun;
    private String userInput;
    private final Scanner scanner = new Scanner(System.in);

    // Methods

    // Get the action and noun of the users input, this will be the main method of this class
    public List<String> getUserString() {
        promptUser();
        parseUserInput();

        return actionNoun;
    }

    public void promptUser() {
        String userInput = scanner.next();
        setUserInput(userInput);
    }

    public void parseUserInput() {
        parsedWords = new ArrayList<>();

        // Split the user input into words based on whitespace
        String[] words = userInput.split("\\s+");

        // Add the words to the parsedWords list
        parsedWords.addAll(Arrays.asList(words));
    }

    // Getters/Setters
    public List<String> getParsedWords() {
        return parsedWords;
    }

    public void setParsedWords(List<String> parsedWords) {
        this.parsedWords = parsedWords;
    }

    public List<String> getActionNoun() {
        return actionNoun;
    }

    public void setActionNoun(List<String> actionNoun) {
        this.actionNoun = actionNoun;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}

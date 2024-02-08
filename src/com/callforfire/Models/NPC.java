package com.callforfire.Models;

import com.callforfire.GameEngines.SupportEngines.JSON_Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NPC {
    private String name;
    private List<String> dialogue = new ArrayList<>();

    public NPC() {}

    public NPC(String name, List<String> dialogue) {
        this.name = name;
        this.dialogue = dialogue;
    }

    public String getRandomDialogue(String npcName) {
        NPC npc = JSON_Reader.readNpcDialogue(npcName);
        if (npc== null || npc.getDialogue().isEmpty()) {
            return null; // Return null if the dialogue list is empty or null
        } else  {
            setDialogue(npc.getDialogue());
        }

        // Generate a random index within the range of the dialogue list
        Random random = new Random();
        int index = random.nextInt(dialogue.size());

        // Return the dialogue at the randomly generated index
        return dialogue.get(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDialogue() {
        return dialogue;
    }

    public void setDialogue(List<String> dialogue) {
        this.dialogue = dialogue;
    }
}
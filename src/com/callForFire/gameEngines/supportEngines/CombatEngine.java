package com.callForFire.gameEngines.supportEngines;

import com.callForFire.models.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatEngine {
    private Enemy enemy = new Enemy();

    public CombatEngine() {
        List<Enemy> enemies = JsonReader.returnListOfEnemies();
        if (enemies != null && !enemies.isEmpty()) {
            // Generate a random index within the bounds of the list
            Random random = new Random();
            int randomIndex = random.nextInt(enemies.size());

            // Retrieve the enemy object at the random index
            Enemy randomEnemy = enemies.get(randomIndex);

            // Set the retrieved enemy object as the current enemy
            setEnemy(randomEnemy);
        } else {
            // Handle the case when the list of enemies is empty or null
            System.out.println("No enemies found!");
        }
    }

    public List<String> attackEnemy(String x, String y) {
        List<String> result = new ArrayList<>();

        // Map letters A-J to numerical values 1-10
        int xCoord = x.toUpperCase().charAt(0) - 'A' + 1;

        // Calculate the relative position of the attack compared to the enemy's position
        int xDifference = xCoord - Integer.parseInt(enemy.getGridX());
        int yDifference = Integer.parseInt(y) - Integer.parseInt(enemy.getGridY());

        // Determine if the attack is high or low
        if (yDifference < 0) {
            result.add("High");
        } else if (yDifference > 0) {
            result.add("Low");
        } else {
            result.add("Same");
        }

        // Determine if the attack is left or right
        if (xDifference < 0) {
            result.add("Left");
        } else if (xDifference > 0) {
            result.add("Right");
        } else {
            result.add("Same");
        }

        return result;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
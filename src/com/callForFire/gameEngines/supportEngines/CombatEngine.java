package com.callForFire.gameEngines.supportEngines;

import com.callForFire.gameEngines.PlayerEngine;
import com.callForFire.models.Enemy;

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

    public boolean attackEnemy(boolean hasCopenhagen) {
        Random random = new Random();

        // If hasCopenhagen is true, user has 1-2 chance of hitting the enemy
        if (hasCopenhagen) {
            // Generate a random number between 1 and 3
            int randomNumber = random.nextInt(3) + 1;
            // Return true if the random number is 1 or 2, indicating a hit
            return randomNumber <= 2;
        } else {
            // If hasCopenhagen is false, user has 1-3 chance of hitting the enemy
            // Generate a random number between 1 and 4
            int randomNumber = random.nextInt(4) + 1;
            // Return true if the random number is 1, 2, or 3, indicating a hit
            return randomNumber <= 3;
        }
    }

    public boolean enemyAttacksPlayer() {
        Random random = new Random();
        // Generate a random number between 1 and 4
        int randomNumber = random.nextInt(5) + 1;
        // Return true if the random number is 1, 2, or 3, indicating a hit
        return randomNumber <= 2;
    }

    public int calculateBattleDamage(boolean isPlayerAttacking, PlayerEngine playerEngine) {
        if(isPlayerAttacking) {
            enemy.setEnemyHealth(enemy.getEnemyHealth() - 25);
            return enemy.getEnemyHealth();
        } else {
            playerEngine.setHealth(playerEngine.getHealth() - 25);
            return playerEngine.getHealth();
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
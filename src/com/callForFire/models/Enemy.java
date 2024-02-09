package com.callForFire.models;

public class Enemy {
    private int enemyHealth;

    private String enemyType;

    public Enemy() {}

    // Getters And Setters
    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public String getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(String enemyType) {
        this.enemyType = enemyType;
    }
}
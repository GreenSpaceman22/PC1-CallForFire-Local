package com.callForFire.models;

import java.util.ArrayList;
import java.util.List;

public class Enemy {
    private String gridX;

    private String gridY;

    private int enemyHealth;

    private String enemyType;

    List<String> enemyLocation = new ArrayList<>();

    public Enemy() {
        enemyLocation.add(gridX);
        enemyLocation.add(gridY);
    }

    // Getters And Setters
    public String getGridX() {
        return gridX;
    }

    public void setGridX(String gridX) {
        this.gridX = gridX;
    }

    public String getGridY() {
        return gridY;
    }

    public void setGridY(String gridY) {
        this.gridY = gridY;
    }

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

    public List<String> getEnemyLocation() {
        return enemyLocation;
    }

    public void setEnemyLocation(List<String> enemyLocation) {
        this.enemyLocation = enemyLocation;
    }
}

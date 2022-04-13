package com.company;


/***
 * parent class for the all player classes. It contains common attributes of the player classes.
 */
abstract class Player extends Thread implements PotencyCalculator
{
    private String role;
    private int entityID;
    private int healthPoints;
    private int baseDamage;


    public String getRole() { return role; }
    public int getEntityID() { return entityID; }
    public int getHealthPoints() { return healthPoints; }
    public int getBaseDamage() { return baseDamage; }

    public void setRole(String role) { this.role = role; }
    public void setEntityID(int entityID) { this.entityID = entityID; }
    public void setHealthPoints(int healthPoints)
    {
        this.healthPoints = healthPoints;
        if(this.getHealthPoints() < 0)
        {
            this.setHealthPoints(0);
        }
    }
    public void setBaseDamage(int baseDamage) { this.baseDamage = baseDamage; }

    /***
     *   a default constructor for Player class, values are assigned as stated in the assignment paper
     */
    public Player()
    {
        this.setRole("-");
        this.setEntityID(0);
        this.setHealthPoints(0);
        this.setBaseDamage(0);
    }

    public Player(String role, int entityID, int healthPoints, int baseDamage)
    {
        this.role = role;
        this.entityID = entityID;
        this.healthPoints = healthPoints;
        this.baseDamage = baseDamage;
    }
}
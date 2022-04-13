package com.company;

/***
 *  is a player class that contains statistics and functions specific to the tank class of the game.
 */
public class Tank extends Player
{
    private int defense;

    /***
     *  a default constructor for Tank class, values are assigned as stated in the assignment paper
     */
    public Tank()
    {
        this.setEntityID(1);
        this.setHealthPoints(100);
        this.setBaseDamage(10);
        this.setRole("Tank");
        this.defense = 6;
    }

    /***
     * Constructor that takes argument for tank
     * @param role is the role of the player entity
     * @param entityID is the id of the player entity
     * @param healthPoints is the health point of the player entity
     * @param baseDamage is the base damage to the player entity
     * @param defense is the defense of the tank
     */
    public Tank(String role, int entityID, int healthPoints, int baseDamage, int defense)
    {
        this.setEntityID(entityID);
        this.setHealthPoints(healthPoints);
        this.setBaseDamage(baseDamage);
        this.setRole(role);
        this.defense = defense;
    }
    /***
     * getter method for defense variable.
     * @return defense
     */
    public int getDefense() { return defense; }
    /***
     * setter method for intelligence variable.
     */
    public void setDefense(int defense) { this.defense = defense; }

    /***
     * deals damage to opponent.
     * @return damage value
     */
    public int dealDamage()  { return getBaseDamage();  }
    /***
     * calculates damage received by the opponent.
     * @param damageReceived is the value that received from opponents attack.
     */
    public void takeDamage(int damageReceived) {setHealthPoints(getHealthPoints() - (damageReceived - defense));}

    /***
     * run is the main thread executable
     */
    @Override
    public void run()
    {
        EncounterManager encounterManager = EncounterManager.getInstance();
        while(( encounterManager.enemyIsAlive() == 1) && (encounterManager.playersAreAlive() == 1))
        {
            try {
                encounterManager.playerAttack(this);
                encounterManager.healthTracker.put(this.getEntityID(), this.getHealthPoints());

                encounterManager.loopNumber += 1;

                String attackAmount = this.dealDamage() + " damage attack";

                encounterManager.scoreBoard(this.getRole(), "attacked the", "enemy", attackAmount);

                Thread.sleep(1000);


            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
}

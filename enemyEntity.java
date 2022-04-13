package com.company;

/***
 *  is the enemy class that contains statistics and functions specific to the enemy class.
 */
public class enemyEntity extends Thread implements PotencyCalculator
{
    private int entityID;
    private int healthPoints;
    private int baseDamage;

    /***
     *  a default constructor for enemy entity, values are assigned as stated in the assignment paper
     */
    public enemyEntity()
    {
        entityID = 4;
        healthPoints = 100;
        baseDamage = 10;
    }

    /***
     * constructor that takes arguments for enemyEntity
     * @param entityID is id of enemy
     * @param healthPoints is the health point of enemy
     * @param baseDamage is the base damage to the enemy
     */
    public enemyEntity(int entityID, int healthPoints, int baseDamage)
    {
        setEntityID(entityID);
        setHealthPoints(healthPoints);
        setBaseDamage(baseDamage);
    }


    public int getEntityID() { return entityID; }
    public int getHealthPoints() { return healthPoints; }
    public int getBaseDamage() { return baseDamage; }

    /***
     * setter method for entityID variable.
     */
    public void setEntityID(int entityID) { this.entityID = entityID; }
    /***
     * setter method for healthPoints variable.
     */
    public void setHealthPoints(int healthPoints) { this.healthPoints = healthPoints; }
    /***
     * setter method for baseDamage variable.
     */
    public void setBaseDamage(int baseDamage) { this.baseDamage = baseDamage;}

    /***
     * deals damage to opponent.
     * @return damage value
     */
    public int dealDamage() { return getBaseDamage(); }
    /***
     * calculates damage received by the opponent.
     * @param damageReceived is the value that received from opponents attack.
     */
    public void takeDamage(int damageReceived)
    {
        setHealthPoints(getHealthPoints() - damageReceived);
        if(this.getHealthPoints() < 0)
        {
            this.setHealthPoints(0);
        }

    }
    private int counter = 1;

    /***
     * run is the main thread executable
     */
    @Override
    public void run()
    {
        EncounterManager encounterManager = EncounterManager.getInstance();
        while(( encounterManager.enemyIsAlive() == 1) && (encounterManager.playersAreAlive() == 1))
        {
            try
            {

                encounterManager.loopNumber += 1;
                if (counter != 4)
                {
                    String target = encounterManager.enemyAttack();

                    String attackAmount = this.dealDamage() + " damage attack";
                    encounterManager.scoreBoard("Enemy", "attacked the", target, attackAmount);

                    counter++;
                }
                else
                {
                    encounterManager.groupWideAttack();

                    String attackAmount = this.dealDamage() + " damage attack";
                    encounterManager.scoreBoard("Enemy", "attacked", "all players", attackAmount);
                    counter = 1;
                }


                Thread.sleep(1000);


            }
            catch (Exception e) {
                System.out.println("Exception is caught");
            }
        }

        if(encounterManager.enemyIsAlive() == 0)
            System.out.println("The enemy is dead. The encounter has ended.");
        else if (encounterManager.playersAreAlive() == 0)
            System.out.println("All players are dead. The encounter has ended.");

    }


}

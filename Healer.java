package com.company;

/***
 *  is a player class that contains statistics and functions specific to the healer class of the game.
 */
public class Healer extends Player
{
    private int mind;

    /***
     *  a default constructor for Healer class, values are assigned as stated in the assignment paper
     */
    public Healer()
    {
        this.setEntityID(3);
        this.setHealthPoints(100);
        this.setBaseDamage(10);
        this.setRole("Healer");
        this.mind = 7;
    }

    /***
     * Constructor that takes argument for healer
     * @param role is the role of the player entity
     * @param entityID is the id of the player entity
     * @param healthPoints is the health point of the player entity
     * @param baseDamage is the base damage to the player entity
     * @param mind is the mind of the healer
     */
    public Healer(String role, int entityID, int healthPoints, int baseDamage, int mind)
    {
        this.setEntityID(entityID);
        this.setHealthPoints(healthPoints);
        this.setBaseDamage(baseDamage);
        this.setRole(role);
        this.mind = mind;
    }

    /***
     * getter method for mind variable.
     * @return mind
     */
    public int getMind() { return mind; }
    /***
     * setter method for mind variable.
     */
    public void setMind(int mind) { this.mind = mind; }

    /***
     * deals damage to opponent.
     * @return damage value
     */
    public int dealDamage(){ return getBaseDamage(); }

    /***
     * calculates damage received by the opponent.
     * @param damageReceived is the value that received from opponents attack.
     */
    public void takeDamage(int damageReceived) {setHealthPoints(getHealthPoints() - damageReceived);}

    /***
     *  heal method for heal attribute of heal
     */
    public int heal() { return (getMind()  + 10); }

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
                String healedPlayer = encounterManager.healPlayer();
                encounterManager.healthTracker.put(this.getEntityID(), this.getHealthPoints());

                encounterManager.loopNumber += 1;

                String healAmount = this.heal() + " HP";
                encounterManager.scoreBoard(healedPlayer,"healed by","", healAmount);
                Thread.sleep(1000);

            }
            catch (Exception e) {
                System.out.println("Exception is caught");
            }
        }


    }


}
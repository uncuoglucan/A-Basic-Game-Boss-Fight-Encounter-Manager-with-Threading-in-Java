package com.company;

/***
 *  is a player class that contains statistics and functions specific to the damage dealer class of the game.
 */
public class DamageDealer extends Player
{
    private int intelligence;

    /***
     *  a default constructor for DamageDealer class, values are assigned as stated in the assignment paper
     */
    public DamageDealer()
    {
        this.setEntityID(2);
        this.setHealthPoints(100);
        this.setBaseDamage(10);
        this.setRole("DamageDealer");
        this.intelligence = 7;
    }

    /***
     * Constructor that takes argument for damage dealer
     * @param role is the role of the player entity
     * @param entityID is the id of the player entity
     * @param healthPoints is the health point of the player entity
     * @param baseDamage is the base damage to the player entity
     * @param intelligence is the intelligence of the damage dealer
     */
    public DamageDealer(String role, int entityID, int healthPoints, int baseDamage, int intelligence)
    {
        this.setEntityID(entityID);
        this.setHealthPoints(healthPoints);
        this.setBaseDamage(baseDamage);
        this.setRole(role);
        this.intelligence = intelligence;
    }


    /***
     * getter method for intelligence variable.
     * @return intelligence
     */
    public int getIntelligence() { return intelligence; }
    /***
     * setter method for intelligence variable.
     */
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }

    /***
     * deals damage to opponent.
     * @return damage value
     */
    public int dealDamage() {  return getBaseDamage() + getIntelligence(); }
    /***
     * calculates damage received by the opponent.
     * @param damageReceived is the value that received from opponents attack.
     */
    public void takeDamage(int damageReceived){ setHealthPoints(getHealthPoints() - damageReceived); }

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
                encounterManager.playerAttack(this);
                encounterManager.healthTracker.put(this.getEntityID(), this.getHealthPoints());

                encounterManager.loopNumber += 1;

                String attackAmount = this.dealDamage() + " damage attack";
                encounterManager.scoreBoard(this.getRole(), "attacked", "the enemy", attackAmount);

                Thread.sleep(500);

            }
            catch (Exception e)
            {
                System.out.println("Exception is caught");
            }
        }


    }

}
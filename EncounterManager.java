package com.company;//package com.company;
import java.util.*;

/***
 * Encounter Manager is the main class that initiates the program.
 */
public class EncounterManager
{
    /***
     * singleton class  and getInstance of it is initialized here.
     */
    static EncounterManager encounterManager = new EncounterManager();
    public static EncounterManager getInstance(){ return encounterManager;}

    int loopNumber = 1;

    Tank tank = new Tank();
    DamageDealer damageDealer = new DamageDealer();
    Healer healer = new Healer();
    enemyEntity enemy = new enemyEntity();

    /***
     * a Hash map that keep track of the health of the player
     */
    public Map<Integer, Integer> healthTracker = new HashMap<>();

    /***
     * is the main class that run the program.
     * @param args is the input argument of the main class of the program.
     */
    public static void main(String[] args)
    {
        encounterManager.menu();
    }

    /***
     * is the method that contains the both encounter menu where the threads for the encounter manager started.
     */
    public void menu()
    {
        System.out.println("The Encounter has started!");
        System.out.println("\tEntities' HP");
        System.out.println("\tTank: " + encounterManager.tank.getHealthPoints());
        System.out.println("\tDamage Dealer: " + encounterManager.damageDealer.getHealthPoints());
        System.out.println("\tHealer: " + encounterManager.healer.getHealthPoints());
        System.out.println("\tEnemy: " + encounterManager.enemy.getHealthPoints() + "\n");

        /***
         * where threads started.
         */
        damageDealer.start();
        tank.start();
        healer.start();
        enemy.start();
    }

    /***
     * is a synchronized function for showing health points of the entities in the encounter
     * @param role is the parameter which executes the action
     * @param action is the parameter which action executed
     * @param target is the parameter that action is targeted.
     * @param amount is the amount of damage or heal value of the entity.
     */
    synchronized public void scoreBoard(String role, String action, String target, String amount)
    {
        System.out.println(encounterManager.loopNumber + ")" + " " + role +  " " + action + " " + target + " (" + amount + ")");
        System.out.println("\tEntities' HP");
        System.out.println("\tTank: " + encounterManager.tank.getHealthPoints());
        System.out.println("\tDamage Dealer: " + encounterManager.damageDealer.getHealthPoints());
        System.out.println("\tHealer: " + encounterManager.healer.getHealthPoints());
        System.out.println("\tEnemy: " + encounterManager.enemy.getHealthPoints() + "\n");


    }


    /***
     * checks whether enemy is alive or not.
     * @return a value that will check whether enemy is alive or not.
     */
    synchronized public int enemyIsAlive()
    {
        if(enemy.getHealthPoints() <= 0)
            return 0;
        else
            return 1;
    }

    /***
     * checks whether all player are alive or not
     * @return a value that check whether all player are alive or not.
     */
    synchronized public int playersAreAlive()
    {
        if(tank.getHealthPoints() <= 0 && healer.getHealthPoints() <= 0 && damageDealer.getHealthPoints() <= 0)
            return 0;
        else
            return 1;
    }

    /***
     * initiates an attack that deal damage to enemy.
     */
    synchronized public void playerAttack(Player PC)
    {
        enemy.takeDamage(PC.dealDamage());
    }

    /***
     * is a method that heals the player who has the least health.
     */
    synchronized public String healPlayer()
    {
        Integer damagedPlayer = Collections.min(healthTracker.entrySet(), Map.Entry.comparingByValue()).getKey();

        if(damagedPlayer == tank.getEntityID())
        {
            tank.setHealthPoints(tank.getHealthPoints() + healer.heal());

            if (tank.getHealthPoints() > 100)
                tank.setHealthPoints(100);
            if(isTankAlive() == 0)
                tank.setHealthPoints(0);

            return tank.getRole();
        }
        else if (damagedPlayer == damageDealer.getEntityID())
        {
            damageDealer.setHealthPoints(damageDealer.getHealthPoints() + healer.heal());

            if (damageDealer.getHealthPoints() > 100)
                damageDealer.setHealthPoints(100);
            if(isDamageDealerAlive() == 0)
                damageDealer.setHealthPoints(0);

            return damageDealer.getRole();
        }
        else if (damagedPlayer == healer.getEntityID())
        {
            healer.setHealthPoints(healer.getHealthPoints() + healer.heal());

            if (healer.getHealthPoints() > 100)
                healer.setHealthPoints(100);
            if(isHealerAlive() == 0)
                healer.setHealthPoints(0);

            return healer.getRole();
        }
        else
            throw new IllegalStateException("Unexpected value: " + damagedPlayer);
    }

    /***
     * initiates an attack that deal damage to player.
     * It first starts tank if tank is that, attack continues on damage dealer and healer respectively.
     */
    synchronized String enemyAttack()
    {
        if (isTankAlive() == 1)
        {
            tank.takeDamage(enemy.dealDamage());
            return  "Tank";
        }

        else if (isDamageDealerAlive() == 1 && isTankAlive() == 0)
        {
            damageDealer.takeDamage(enemy.dealDamage());
            return "damage Dealer";
        }

        else if (isHealerAlive() == 1 && isDamageDealerAlive() == 0 && isTankAlive() == 0)
        {
            healer.takeDamage(enemy.dealDamage());
            return "healer";
        }
            return " ";
    }

    /***
     * initiates a group attack that deal damage to all member of the group.
     */
    synchronized public void groupWideAttack()
    {
        tank.takeDamage(enemy.dealDamage());
        damageDealer.takeDamage(enemy.dealDamage());
        healer.takeDamage(enemy.dealDamage());
    }

    /***
     *  Method that checks whether tank is alive or not
     * @return a signal that shows whether player is alive or not
     */
    public int isTankAlive()
    {
        if(tank.getHealthPoints() <= 0)
            return 0;
        else
            return 1;
    }

    /***
     *  Method that checks whether damage dealer is alive or not
     * @return a signal that shows whether player is alive or not
     */
    public int isDamageDealerAlive()
    {
        if(damageDealer.getHealthPoints() <= 0)
            return 0;
        else
            return 1;
    }

    /***
     *  Method that checks whether healer is alive or not
     * @return a signal that shows whether player is alive or not
     */
    public int isHealerAlive()
    {
        if(healer.getHealthPoints() <= 0)
            return 0;
        else
            return 1;
    }
}

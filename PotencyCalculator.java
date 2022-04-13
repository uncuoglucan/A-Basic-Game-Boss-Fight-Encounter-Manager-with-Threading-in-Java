package com.company;

/***
 * is the interface for the dealDamage and takeDamage methods that vary between enemy class and player classes.
 */
public interface PotencyCalculator
{
    int dealDamage();
    void takeDamage(int damageReceived);
}
package com.costr.tailklicker.Logik;

/**
 * KlickZähler.java
 * 
 * This class is responsible for counting the clicks in the game.
 * It keeps track of the number of clicks and provides methods to increment and reset the count.
 * * @author Costr
 */
    
 public interface KlickZähler {

    /**
     * Increments the click count by one.
     */
    void increment();

    /**
     * Resets the click count to zero.
     */
    void reset();
    /**
     * Returns the current click count.
     * 
     * @return the current click count
     */
    static int getCount(){
        return 0; // Default implementation, should be overridden in the implementing class
    }
 }
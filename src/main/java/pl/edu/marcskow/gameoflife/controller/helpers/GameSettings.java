package pl.edu.marcskow.gameoflife.controller.helpers;

/**
 * Sincerely it's probably not the best idea to storage this here, but it's needed to set new cell states in
 * automatons, in next version it will be rebuild to set this when the automaton is starting and transfered to
 * the automaton classes
 */
public class GameSettings {
    public static int[] bornRule;
    public static int[] surviveRule;
    public static int elementaryRule;
}

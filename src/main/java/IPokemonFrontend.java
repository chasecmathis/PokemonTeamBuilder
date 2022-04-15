import java.util.List;

/**
 * Interface handling the frontend side of the Pokemon Team Builder app
 */
public interface IPokemonFrontend {

  /**
   * This method drives the entire read, eval, print loop (repl) for the
   * Pokemon Team Builder app.  This loop will continue to run until the user
   * explicitly enters the quit command.
   */
  public void runCommandLoop();

  /**
   * Displays a list of commands for the user to choose from.
   * i.e.
   * 1) Display [P]okemon
   * 2) Filter [t]ypes
   * 3) Filter [g]enerations
   * 4) Display [c]urrent team
   * 5) [Q]uit
   */
  public void displayCommandMenu();

  /**
   * Accept different user input once a list of Pokemon has been displayed
   */
  public void pokemonInput(int page);

  /**
   * Accept different user input once the user wants to change filters
   */
  public void typeInput();

  /**
   * Accept different user input once the user wants to change filters
   */
  public void generationInput();

  /**
   * Display the user's currently built pokemon team
   *
   * @param pokemon list of user's current pokemon team
   */
  public void displayPokemonTeam(List<IPokemon> pokemon);

}

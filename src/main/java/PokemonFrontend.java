import java.util.List;
import java.util.Scanner;

/**
 * Interface handling the frontend side of the Pokemon Team Builder app
 */
public class PokemonFrontend implements IPokemonFrontend {

  final private Scanner INPUT = new Scanner(System.in);
  final private IPokemonBackend BACKEND;

  public PokemonFrontend(IPokemonBackend backend) {
    this.BACKEND = backend;
  }

  /**
   * This method drives the entire read, eval, print loop (repl) for the
   * Pokemon Team Builder app.  This loop will continue to run until the user
   * explicitly enters the quit command.
   */
  @Override public void runCommandLoop() {
    System.out.print("Welcome to the Pokemon Team Builder!\n=================================");
    displayCommandMenu();
  }

  /**
   * Displays a list of commands for the user to choose from.
   * i.e.
   * 1) Display [P]okemon
   * 2) Filter [t]ypes
   * 3) Filter [g]enerations
   * 4) Display [c]urrent team
   * 5) [Q]uit
   */
  @Override public void displayCommandMenu() {
    // prints out a simply command menu for the user to choose from
    System.out.print("\n\t1) Display [P]okemon\n\t2) Filter [t]ypes\n\t3) Filter "
        + "[g]enerations\n\t4) Display [c]urrent team\n\t5) [Q]uit\nChoose a command from the "
        + "menu above: ");

    String response = INPUT.next().trim().toUpperCase();
    // opens a new menu depending on what the user chooses
    switch (response) {
      case "1":
      case "P":
        // allow the user to display all pokemon with current filters
        pokemonInput(0);
      case "2":
      case "T":
        // allow the user to filter by type
        typeInput();
      case "3":
      case "G":
        // allow the user to filter by generation
        generationInput();
      case "4":
      case "C":
        // display the current pokemon team
        displayPokemonTeam(BACKEND.getTeam());
      case "5":
      case "Q":
        // close program
        INPUT.close();
        System.exit(0);
      default:
        // check for invalid input
        System.out.println("Invalid response provided");
        displayCommandMenu();
    }
  }

  /**
   * Accept different user input once a list of Pokemon has been displayed
   */
  @Override public void pokemonInput(int page) {
    // display the current page
    System.out.println(
        "Showing page " + (page + 1) + " out of " + ((BACKEND.searchPokemon().size()) / 10
            + 1));

    // prints out all pokemon on this page
    for (int i = 1; i <= Math.min(10, BACKEND.searchPokemon().size() - page * 10); ++i)
      System.out.println(
          "================" + (i + page * 10) + "================\n" + BACKEND.searchPokemon()
              .get((i - 1) + page * 10));

    // allow the user to choose from input...
    // next page, previous page, quit, or add a pokemon to their team
    System.out.print(
        "\n\t1) [N]ext page\n\t2) [P]revious page\n\t3) [Q]uit\n\t4) Pokemon number to add to "
            + "your team\nChoose command from menu above: ");
    String response = INPUT.next().toUpperCase().trim();

    // check what their response is
    try {
      // checks if there is a next page
      if (response.equals("N") && (page != BACKEND.searchPokemon().size() / 10)) {
        ++page;
        pokemonInput(page);
      } else if (response.equals("P") && (page > 0)) {
        // checks if there is a previous page
        --page;
        pokemonInput(page);
      } else if (response.equals("Q")) {
        // quit back to main menu
        page = 0;
        displayCommandMenu();
      } else if (Integer.parseInt(response) > page * 10 && Integer.parseInt(response) <= Math.min(
          BACKEND.searchPokemon().size(), (page + 1) * 10)) {
        // if the number provided is within range displayed on current screen, add that pokemon
        // to the current team
        BACKEND.addToTeam(BACKEND.searchPokemon().get(Integer.parseInt(response) - 1));
        pokemonInput(page);
      } else {
        // if invalid input, throw an exception to display error message
        throw new NumberFormatException();
      }
    } catch (NumberFormatException nfe) {
      // display error message...
      System.out.println("Invalid response provided");
      pokemonInput(page);
    }

  }

  /**
   * Accept different user input once the user wants to change filters
   */
  @Override public void typeInput() {
    // display all types and their filters
    // if a filters has an 'X' by it... it is active
    for (PokemonTypes type : PokemonTypes.values()) {
      char active = BACKEND.getTypeFilter(type) ? 'X' : '_';
      System.out.println("\t" + type + " _" + active + "_");
    }

    // allow the user to select a type to toggle, reset the type filter, or the quit the menu
    System.out.print("\tSelect type, [q]uit, or [r]eset: ");
    String response = INPUT.next().toUpperCase().trim();
    // handle different responses
    try {
      BACKEND.toggleTypeFilter(PokemonTypes.valueOf(response));
      typeInput();
    } catch (IllegalArgumentException iae) {
      switch (response) {
        // quit the menu
        case "Q":
          displayCommandMenu();
          // reset all the filters to be not active
        case "R":
          BACKEND.resetTypeFilter();
          typeInput();
        default:
          // an invalid response was provided, go back to the menu
          System.out.println("Invalid response provided");
          typeInput();
      }
    }
  }

  /**
   * Accept different user input once the user wants to change filters
   */
  @Override public void generationInput() {
    // display all generations and their filter status
    // if a filter has an 'X' by it, it is active
    for (int i = 1; i <= 7; ++i) {
      char active = BACKEND.getGenerationFilter(i) ? 'X' : '_';
      System.out.println("\tGeneration " + i + " _" + active + "_");
    }

    // Prompt the user for input...
    System.out.print("\tSelect generation, [q]uit, or [r]eset: ");
    String response = INPUT.next().toUpperCase().trim();
    try {
      // handle response, checks if the number given was within the generation range
      if (Integer.parseInt(response) >= 0 && Integer.parseInt(response) <= 7) {
        BACKEND.toggleGenerationFilter(Integer.parseInt(response));
        generationInput();
      }
      // throw an exception the if number provided was outuside of the generation range
      throw new NumberFormatException();
    } catch (NumberFormatException nfe) {
      switch (response) {
        // quit the menu
        case "Q":
          displayCommandMenu();
          // reset the filters
        case "R":
          BACKEND.resetGenerationFilter();
          generationInput();
        default:
          // display an error message for the user
          System.out.println("Invalid response provided");
          generationInput();
      }
    }
  }

  /**
   * Display the user's currently built pokemon team
   *
   * @param pokemon list of user's current pokemon team
   */
  @Override public void displayPokemonTeam(List<IPokemon> pokemon) {
    // if the size of the team is already maxed (6)
    if (pokemon.size() > 6) {
      System.out.println("Max team size reached");
      pokemon.remove(6);
      displayCommandMenu();
    }

    // if the pokemon team is empty thus far
    if (pokemon.size() == 0) {
      System.out.println("No Pokemon have been added to the team");
      displayCommandMenu();
    }

    // display all the pokemon in the current team
    System.out.println("Current Pokemon Team: ");
    int i = 1;
    // display all pokemon
    for (IPokemon pokes : pokemon)
      System.out.println("================" + i++ + "================\n" + pokes + "\n");

    // take input from the user
    System.out.print("Remove pokemon number from team or [q]uit: ");
    String response = INPUT.next().trim().toUpperCase();

    try {
      // check if the response is a valid pokemon team number
      if (Integer.parseInt(response) > 0 && Integer.parseInt(response) <= BACKEND.getTeam()
          .size()) {
        pokemon.remove(Integer.parseInt(response) - 1);
        displayCommandMenu();
      }
      // number was outside of range -> thrown an exception
      throw new NumberFormatException();
    } catch (NumberFormatException nfe) {
      // quit the menu
      if (response.equals("Q"))
        displayCommandMenu();
      else {
        // invalid response was provided
        System.out.println("Invalid response provided");
        displayPokemonTeam(BACKEND.getTeam());
      }
    }
  }
}

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class representing the backend for the Pokemon Team Builder
 */
public class PokemonBackend implements IPokemonBackend {

  // maps pokemon types to their filter status
  private final HashMap<PokemonTypes, Boolean> TYPES_FILTER = new HashMap<>();
  // maps pokemon generations to their filter status
  private final HashMap<Integer, Boolean> GENERATION_FILTER = new HashMap<>();
  // list of the user's pokemon team
  private final List<IPokemon> POKEMON_TEAM = new ArrayList<>();
  // list of all pokemon in the dataset
  private final List<IPokemon> ALL_POKEMON;

  /**
   * Constructor that loads pokemon and initializes filters
   *
   * @param loader PokemonLoader to load pokemon
   * @throws FileNotFoundException if pokemon.csv is not found
   */
  public PokemonBackend(IPokemonLoader loader) throws IOException {
    // loads Pokemon from the csv
    this.ALL_POKEMON = loader.loadPokemon("pokemon.csv");
    // initialize types
    for (int i = 0; i < PokemonTypes.values().length; ++i)
      this.TYPES_FILTER.put(PokemonTypes.values()[i], true);
    // initialize generations
    for (int i = 1; i <= 7; ++i)
      this.GENERATION_FILTER.put(i, true);
  }

  /**
   * Returns a list of current pokemon with filters
   */
  @Override public List<IPokemon> searchPokemon() {
    ArrayList<IPokemon> pokemon = new ArrayList<>();
    // filter out pokemon
    // if the pokemon is within the current filters, add it to the list of pokemon being searched
    for (IPokemon Pokemon : this.ALL_POKEMON)
      if (isValidPokemon(Pokemon))
        pokemon.add(Pokemon);
    return pokemon;
  }

  /**
   * Checks to see if pokemon fits current filter criteria
   *
   * @param pokemon the pokemon being checked
   * @return true if it is valid, false otherwise
   */
  private boolean isValidPokemon(IPokemon pokemon) {
    // Checks to see if the Pokemon's type and generation is within the current filter
    return (this.TYPES_FILTER.get(pokemon.getTypeOne()) || (pokemon.getTypeTwo() != null
        && this.TYPES_FILTER.get(pokemon.getTypeTwo()))) && this.GENERATION_FILTER.get(
        pokemon.getGeneration());
  }

  /**
   * Check if the current filter is active or not for a certain type
   *
   * @param type the type to be checked
   * @return true if the filter is active, false otherwise
   */
  @Override public boolean getTypeFilter(PokemonTypes type) {
    return this.TYPES_FILTER.get(type);
  }

  /**
   * Toggles the current filter of the given type. Alll filters are on by default
   *
   * @param type the pokemon type to toggle
   */
  @Override public void toggleTypeFilter(PokemonTypes type) {
    // toggle filter
    this.TYPES_FILTER.replace(type, !this.TYPES_FILTER.get(type));
  }

  /**
   * Resets the current type filter
   */
  @Override public void resetTypeFilter() {
    for (int i = 0; i < PokemonTypes.values().length; ++i)
      this.TYPES_FILTER.replace(PokemonTypes.values()[i], false);
  }

  /**
   * Check if the current filter is active for a certain generation
   *
   * @param generation the generation to be checked
   * @return true if the current filter is active
   */
  @Override public boolean getGenerationFilter(int generation) {
    return this.GENERATION_FILTER.get(generation);
  }

  /**
   * Toggles the current filter of the given generation. All filters are on by default
   *
   * @param generation the pokemon generation to toggle
   */
  @Override public void toggleGenerationFilter(int generation) {
    // toggle filter
    this.GENERATION_FILTER.replace(generation, !this.GENERATION_FILTER.get(generation));
  }

  /**
   * Resets the current generation filter
   */
  @Override public void resetGenerationFilter() {
    for (int i = 1; i <= 7; ++i)
      this.GENERATION_FILTER.replace(i, false);
  }

  /**
   * Add a pokemon to the user's current team
   *
   * @param pokemon the pokemon to be added
   */
  @Override public void addToTeam(IPokemon pokemon) {
    // adds pokemon to the given team
    this.POKEMON_TEAM.add(pokemon);
  }

  /**
   * Gets the user's current team
   *
   * @return the user's current team
   */
  @Override public List<IPokemon> getTeam() {
    return POKEMON_TEAM;
  }
}

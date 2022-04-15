import java.util.List;

/**
 * Interface representing backend for a Pokemon Team Builder
 */
public interface IPokemonBackend {

  /**
   * Returns a list of current pokemon with filters
   */
  public List<IPokemon> searchPokemon();

  /**
   * Check if the current filter is active or not for a certain type
   * @param type the type to be checked
   * @return true if the filter is active, false otherwise
   */
  public boolean getTypeFilter(PokemonTypes type);

  /**
   * Toggles the current filter of the given type. All filters are on by default
   * @param type the pokemon type to toggle
   */
  public void toggleTypeFilter(PokemonTypes type);

  /**
   * Resets the current type filter
   */
  public void resetTypeFilter();

  /**
   * Check if the current filter is active for a certain generation
   * @param generation the generation to be checked
   * @return true if the current filter is active
   */
  public boolean getGenerationFilter(int generation);

  /**
   * Toggles the current filter of the given generation. All filters are on by default
   * @param generation the pokemon generation to toggle
   */
  public void toggleGenerationFilter(int generation);

  /**
   * Resets the current generation filter
   */
  public void resetGenerationFilter();

  /**
   * Add a pokemon to the user's current team
   * @param pokemon the pokemon to be added
   */
  public void addToTeam(IPokemon pokemon);

  /**
   * Gets the user's current team
   * @return the user's current team
   */
  public List<IPokemon> getTeam();

}

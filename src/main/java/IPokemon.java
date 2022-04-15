import java.awt.*;

/**
 * Class representing a Pokémon object
 */
public interface IPokemon {

  /**
   * Gets the name of the Pokémon
   *
   * @return the name of the Pokémon
   */
  public String getName();

  /**
   * Sets the name of the pokemon
   */
  public void setName(String name);

  /**
   * Gets the first type of the Pokémon
   *
   * @return the first type of the Pokémon
   */
  public PokemonTypes getTypeOne();

  /**
   * Sets the first type of the Pokemon
   */
  public void setTypeOne(PokemonTypes typeOne);

  /**
   * Gets the second type of the Pokémon (return null if Pokémon has only one type)
   *
   * @return the second type of the Pokémon
   */
  public PokemonTypes getTypeTwo();

  /**
   * Sets the second type of the Pokemon
   */
  public void setTypeTwo(PokemonTypes typeTwo);

  /**
   * Gets the generation number of the Pokémon
   *
   * @return the generation number of the Pokémon
   */
  public int getGeneration();

  /**
   * Sets the generation of the Pokemon
   */
  public void setGeneration(int generation);

  /**
   * Gets the Pokédex number of the Pokémon
   *
   * @return the Pokédex number of the Pokémon
   */
  public int getPokedex();

  /**
   * Sets the Pokedex number
   */
  public void setPokedex(int pokedex);

  public Image getImage();

  public void setImage(Image picture);

}

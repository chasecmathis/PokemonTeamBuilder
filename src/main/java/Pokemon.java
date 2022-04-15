import com.opencsv.bean.CsvBindByName;

import java.awt.*;

/**
 * Class representing a Pokémon object
 */
public class Pokemon implements IPokemon {

  @CsvBindByName(column = "name") String name;

  @CsvBindByName(column = "type1") PokemonTypes typeOne;

  @CsvBindByName(column = "type2") PokemonTypes typeTwo;

  @CsvBindByName(column = "generation") int generation;

  @CsvBindByName(column = "pokedex_number") int pokedex;

  Image picture;

  /**
   * Gets the name of the Pokémon
   *
   * @return the name of the Pokémon
   */
  @Override public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the pokemon
   *
   * @param name the name of the pokemon
   */
  @Override public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the first type of the Pokémon
   *
   * @return the first type of the Pokémon
   */
  @Override public PokemonTypes getTypeOne() {
    return this.typeOne;
  }

  /**
   * Sets the first type of the Pokemon
   *
   * @param typeOne the first type of the pokemon
   */
  @Override public void setTypeOne(PokemonTypes typeOne) {
    this.typeOne = typeOne;
  }

  /**
   * Gets the second types of the Pokémon
   *
   * @return the second type of the Pokémon
   */
  @Override public PokemonTypes getTypeTwo() {
    return this.typeTwo;
  }

  /**
   * Sets the second type of the Pokemon
   *
   * @param typeTwo the second type of the pokemon
   */
  @Override public void setTypeTwo(PokemonTypes typeTwo) {
    this.typeTwo = typeTwo;
  }


  /**
   * Gets the generation number of the Pokémon
   *
   * @return the generation number of the Pokémon
   */
  @Override public int getGeneration() {
    return this.generation;
  }

  /**
   * Sets the generation of the Pokemon
   *
   * @param generation the generation to set
   */
  @Override public void setGeneration(int generation) {
    this.generation = generation;
  }


  /**
   * Gets the Pokédex number of the Pokémon
   *
   * @return the Pokédex number of the Pokémon
   */
  @Override public int getPokedex() {
    return this.pokedex;
  }

  /**
   * Sets the Pokedex number
   *
   * @param pokedex the pokedex number to set
   */
  @Override public void setPokedex(int pokedex) {
    this.pokedex = pokedex;
  }

  @Override public Image getImage() {
    return this.picture;
  }

  @Override public void setImage(Image picture) {
    this.picture = picture;
  }

  /**
   * Forms a string of the Pokemon's metadata
   *
   * @return A nicely formatted string containing the Pokemons metadata
   */
  @Override public String toString() {
    String pokemon = "\tDex Number: " + pokedex + "\n\tPokemon: " + name + "\n\tType: " + typeOne;
    if (typeTwo != null)
      pokemon += ", " + typeTwo;
    pokemon += "\n\tGeneration: " + generation;
    return pokemon;
  }
}

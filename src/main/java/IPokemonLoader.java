import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IPokemonLoader {

  /**
   * Parses through a given csv file of Pokemon data
   * @param filename the path of the file to parse through
   * @return a list of all Pokemon objects found in the file
   * @throws FileNotFoundException if the filepath was incorrect/file not found
   */
  public List<IPokemon> loadPokemon(String filename) throws IOException;
}

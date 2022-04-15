import com.opencsv.bean.CsvToBeanBuilder;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Class that loads all Pokemon into a list
 */
public class PokemonLoader implements IPokemonLoader {

  /**
   * Parses through a given csv file of Pokemon data
   *
   * @param filename the path of the file to parse through
   * @return a list of all Pokemon objects found in the file
   * @throws FileNotFoundException if the filepath was incorrect/file not found
   */
  @Override public List<IPokemon> loadPokemon(String filename) throws IOException {
    // uses OpenCSV to read csv file and build pokemon objects off of each line
    List<IPokemon> pokemon =
        (new CsvToBeanBuilder<IPokemon>(new FileReader(filename)).withType(Pokemon.class).build()
            .parse());


    // for future updates to frontend where image feature might be useful
    /*for (IPokemon pokes : pokemon)
      pokes.setImage(ImageIO.read(new File("pokemon/" + pokes.getPokedex() + ".png")));*/


    return pokemon;
  }
}

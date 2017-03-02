package tbp.picturelocator;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

  public static final Path TEST_FILE_RELATIVE_PATH = Paths.get("resources/gpx/st_petersburg_May_1,_2016_09_49_41.gpx").toAbsolutePath();

  public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
//    Gpx workingGPX;
//
//    try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(TEST_FILE_RELATIVE_PATH))) {
//      GpxParserOptions gpxParserOptions = GpxParserOptions.PARSE_ALL;
//      gpxParserOptions.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"));
//      workingGPX = new GpxParser().parseGpx(bis, gpxParserOptions);
//    }
//    for (Track t : workingGPX.getTracks()) {
//      System.out.println("t.getTrackPoints()" + t.getTrackPoints());
//      t.getTrackPoints().forEach(System.out::println);
//    }
    System.out.println("fdlkhjsfkjsdhlfkhasdlkfjhaslkdfjhsaflksdjhflasdkjfhasldkjfhlkjh");
  }
}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlantUMLRunner {
    private static String jarPath;
    public static void setJarPath(String path) { jarPath = path; }
    public static void generateSchema(String data, String outputDir, String outputFilename) throws Exception {
        File dir = new File(outputDir);
        dir.mkdir();
        File file = new File(dir, outputFilename + ".puml");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(data);
        }
    }
}

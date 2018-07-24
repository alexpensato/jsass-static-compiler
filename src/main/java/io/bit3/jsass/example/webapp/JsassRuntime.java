package io.bit3.jsass.example.webapp;

import static java.lang.System.err;
import static java.lang.System.out;

import io.bit3.jsass.CompilationException;
import io.bit3.jsass.Compiler;
import io.bit3.jsass.Options;
import io.bit3.jsass.Output;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class JsassRuntime {
    public static void main(String[] args) {
        String path = "C:\\Workspace\\my-ux-project";

        String inputPath = path + "\\style.scss";
        String outputPath = path + "\\stylesheet.css";

        URI inputFile = new File(inputPath).toURI();
        URI outputFile = new File(outputPath).toURI();

        Compiler compiler = new Compiler();
        Options options = new Options();

        try {
            Output output = compiler.compileFile(inputFile, outputFile, options);

            out.println("Compiled successfully");
            out.println(output.getCss());

            List<String> lines = Arrays.asList(output.getCss().split("\r\n"));
            Path file = Paths.get(outputPath);
            Files.write(file, lines, Charset.forName("UTF-8"));

        } catch (CompilationException e) {
            err.println("Compiling failed");
            err.println(e.getErrorText());

        } catch (IOException e) {
            err.println("Writing file failed");
            err.println(e.getMessage());
        }
    }
}

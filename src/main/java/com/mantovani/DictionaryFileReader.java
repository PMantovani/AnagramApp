package com.mantovani;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DictionaryFileReader {

    private String filename;

    DictionaryFileReader(String filename) {
        this.filename = filename;
    }

    List<String> read() throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) {
            throw new FileNotFoundException("File " + filename + " not found");
        } else {
            File wordsFile = new File(resource.getFile());
            Scanner fileScanner = new Scanner(wordsFile);

            while (fileScanner.hasNextLine()) {
                words.add(fileScanner.nextLine());
            }
            return words;
        }
    }
}

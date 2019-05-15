import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<String> text = new ArrayList<>();

        //Loetakse sõnad sisse
        BufferedReader input = new BufferedReader(new FileReader("input.txt"));
        String line = input.readLine();
        while (line != null) {
            if (line.contains(" ")) {
                String[] words = line.split(" ");
                for (int i = 0; i <= words.length - 1; i++) {
                    String part = words[i];
                    String lowerPart = part.toLowerCase();
                    text.add(lowerPart);
                }
            } else {
                String lowerLine = line.toLowerCase();
                text.add(lowerLine);
            }
            line = input.readLine();
        }
        input.close();
        System.out.println("Sõnu kokku: " + text.size());
        //Korduvus kontroll ja sorteerimine
        Map<String, Long> frequencyMap;
        ArrayList<String> wordFrequency = new ArrayList<>();

        frequencyMap = text.stream().collect(groupingBy(Function.identity(), counting()));
        for (Map.Entry<String, Long> entry : frequencyMap.entrySet()) {
            wordFrequency.add(entry.getKey() + " " + entry.getValue());
        }
        List<String> sorted = wordFrequency.stream().sorted(Comparator.comparing(String::toString)).collect(Collectors.toList());

        //Faili kirjutamine
        int sortedListSize = sorted.size();
        System.out.println("Unikaalseid sõnu: " + sortedListSize);
        System.out.println("Sõnade list asub failis: output.txt");
        FileWriter output = new FileWriter("output.txt");
        for (int g = 0; g <= sortedListSize - 1; g++) {
            output.write(sorted.get(g));
            output.write("\n");
        }
        output.close();
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String path1 = "SampleText.txt";
        String path2 = "SampleFilter.txt";
        String path3 = "SampleResult.txt";

        ArrayList<String> textlines = new ArrayList<>();

        try (BufferedReader buffread1 = new BufferedReader(new FileReader(path1))) {
            String line = buffread1.readLine().toLowerCase().trim();
            while (line != null) {
                textlines.add(line.toLowerCase());
                line = buffread1.readLine();
            }
        }
        catch (IOException e1) {
            System.out.println("Erro: Arquivo não encontrado." + e1);
        }

        Hashtable<Character, ArrayList<Word>> wordIndex = new Hashtable<>();

        for (int i = 0; i < textlines.size(); i++) {
            String[] words = textlines.get(i).replaceAll("[^a-zA-Z0-9-]", " ").replaceAll("\\s+", " ").split("\\s+");

            for (int j = 0; j < words.length; j++) {

                if (words[j].isEmpty()) continue;

                char hashkey = words[j].charAt(0);

                if (!wordIndex.containsKey(hashkey)) {
                    wordIndex.put(hashkey, new ArrayList<>());
                }

                ArrayList<Word> wordListKey = wordIndex.get(hashkey);

                boolean dupeFlag = false;

                for (Word arrayWord: wordListKey) {
                    if (arrayWord.word.equals(words[j])) {
                        arrayWord.LineCount(i+1);
                        dupeFlag = true;
                        break;
                    }
                }

                if (!dupeFlag) {
                    Word wordN = new Word(words[j],new LinkedList<>());
                    wordIndex.get(hashkey).add(wordN);
                    wordN.LineCount(i+1);
                }
            }
        }

        ArrayList<String> textfilter = new ArrayList<>();

        try (BufferedReader buffread2 = new BufferedReader(new FileReader(path2))) {
            String line = buffread2.readLine().toLowerCase().trim();
            while (line != null) {
                textfilter.add(line.toLowerCase());
                line = buffread2.readLine();
            }
        }
        catch (IOException e2) {
            System.out.println("Erro: Arquivo não encontrado." + e2);
        }

        String wholeString = String.join(", ",textfilter);
        String[] keywords = wholeString.toLowerCase().replaceAll("[^a-zA-Z0-9-]", " ").replaceAll("\\s+", " ").split("\\s+");
        Arrays.parallelSort(keywords);

        try (BufferedWriter typewriter = new BufferedWriter(new FileWriter(path3))) {
            for (String word:keywords) {
                char keyfinder = word.charAt(0);
                if (wordIndex.containsKey(keyfinder)) {
                    ArrayList<Word> wordList = wordIndex.get(keyfinder);
                    for (Word arrayWord: wordList) {
                        if (arrayWord.word.equals(word)) {
                            typewriter.write(arrayWord.toString());
                            typewriter.newLine();
                        }
                    }
                }
            }
        }
        catch (IOException e3) {
            System.out.println("Arquivo não gerado!" + e3);
        }
    }
}



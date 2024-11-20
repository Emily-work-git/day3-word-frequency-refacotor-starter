import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String ERROR_MESSAGE = "Calculate Error";

    public String getResult(String sentences) {
        if (sentences.split(SPACE).length == 1) {
            return sentences + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentences.split(SPACE);

                List<WordFrequency> wordFrequencies = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencies.add(wordFrequency);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequencyMap = getListMap(wordFrequencies);

                List<WordFrequency> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequencyMap.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    list.add(wordFrequency);
                }
                wordFrequencies = list;

                wordFrequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_BREAK);
                for (WordFrequency word : wordFrequencies) {
                    String s = word.getValue() + " " + word.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return ERROR_MESSAGE;
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequency.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                map.put(wordFrequency.getValue(), arr);
            } else {
                map.get(wordFrequency.getValue()).add(wordFrequency);
            }
        }
        return map;
    }

}

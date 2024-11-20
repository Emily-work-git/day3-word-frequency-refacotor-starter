import java.util.*;
import java.util.stream.Collectors;

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

                List<WordFrequency> wordFrequencies = Arrays.stream(words).map(word -> new WordFrequency(word, 1)).toList();

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequencyMap = getListMap(wordFrequencies);

                List<WordFrequency> list = new ArrayList<>();
                list = wordToWordFrequencyMap.entrySet()
                        .stream()
                        .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                        .collect(Collectors.toList());

                wordFrequencies = list;

                wordFrequencies.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_BREAK);
                wordFrequencies.stream().map(word -> word.getValue()+" "+word.getWordCount()).forEach(joiner::add);
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


    //convert for loop to stream
    //extract method to:
        //getWordFrequencies
        //getWordFrequencyList
}

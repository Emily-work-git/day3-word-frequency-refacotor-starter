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
                String[] words = sentences.split(SPACE);
                List<WordFrequency> wordFrequencies = Arrays.stream(words).map(word -> new WordFrequency(word, 1)).toList();
                wordFrequencies = getWordFrequencies(wordFrequencies);
                return formatWordFrequencyResponse(wordFrequencies);
            } catch (Exception e) {
                return ERROR_MESSAGE;
            }
        }
    }

    private static String formatWordFrequencyResponse(List<WordFrequency> wordFrequencies) {
        StringJoiner joiner = new StringJoiner(LINE_BREAK);
        wordFrequencies.stream().map(word -> word.getValue()+" "+word.getWordCount()).forEach(joiner::add);
        return joiner.toString();
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencies) {
        Map<String, List<WordFrequency>> wordToWordFrequencyMap = getListMap(wordFrequencies);

        wordFrequencies =  wordToWordFrequencyMap.entrySet()
                .stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size())).
                sorted((word1, word2) -> word2.getWordCount() - word1.getWordCount())
                .collect(Collectors.toList());
        return wordFrequencies;
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getValue));
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public String getResult(String sentences) {
        if (sentences.split("\\s+").length == 1) {
            return sentences + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentences.split("\\s+");

                List<Input> wordFrequencies = new ArrayList<>();
                for (String word : words) {
                    Input input = new Input(word, 1);
                    wordFrequencies.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map = getListMap(wordFrequencies);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                wordFrequencies = list;

                wordFrequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : wordFrequencies) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }

}

import java.util.LinkedList;

public class Word {

    String word;
    LinkedList<Integer> lineOccur;

    public Word(String word, LinkedList<Integer> lineOccur) {
        this.word = word;
        this.lineOccur = new LinkedList<>();
    }

    public void LineCount(int line) {
        if(!lineOccur.contains(line)) {
            lineOccur.add(line);
        }
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public LinkedList<Integer> getLineOccur() {
        return lineOccur;
    }

    public void setLineOccur(LinkedList<Integer> lineOccur) {
        this.lineOccur = lineOccur;
    }

    @Override
    public String toString() {
        StringBuilder tostring = new StringBuilder();
        tostring.append(word);
        tostring.append(" ");

        for (int i = 0; i < lineOccur.size(); i++) {
            tostring.append(lineOccur.get(i));
            tostring.append(" ");
        }
        return tostring.toString();
    }
}


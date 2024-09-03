package usdf.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Feats {
    private HashMap<Character, Boolean> featMap = new HashMap<>();
    private final String featStack;

    public Feats(String featStack) {
        List<Character> fullFeatList = TrainingUtility.getFeatList();
        fullFeatList.forEach(c -> this.featMap.put(c, false));

        List<Character> feats = TrainingUtility.returnFeatsWithoutCombinations(featStack);
        feats.forEach(feat -> this.featMap.put(feat, true));

        this.featStack = featStack;
    }

    public String getFeatStack() {
        return featStack;
    }

    public List<Character> getObtainedFeatsList() {
        List<Character> featsList = new ArrayList<>();
        for (Character feat : this.featMap.keySet()) if (this.featMap.get(feat)) featsList.add(feat);
        return featsList;
    }

    public List<Character> getUnobtainedFeatsList() {
        List<Character> featsList = new ArrayList<>();
        for (Character feat : this.featMap.keySet()) if (!this.featMap.get(feat)) featsList.add(feat);
        return featsList;
    }

    @Override
    public String toString() {
        return featStack;
    }
}
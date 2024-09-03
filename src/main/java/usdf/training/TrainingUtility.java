package usdf.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrainingUtility {
    private static HashMap<Character, String> featMap = new HashMap<>(){{
        put('A', "Action Request Forms");
        put('B', "Expert Trainer");
        put('C', "Cooking Specialist Course");
        put('D', "Diplomacy Skills");
        put('E', "Combat Medic Program");
        put('F', "Basic Marksmanship Training");
        put('G', "Being A Good Communicator");
        put('H', "Basic Drill Training");
        put('I', "Drill Instructor");
        put('J', "Online Security");
        put('K', "Knowledge of Legislation and Standing Orders");
        put('L', "SNCO Leadership Course");
        put('M', "HQ Duty Officer");
        put('N', "HQ Management, Trolls & Raids");
        put('O', "Officer Leadership Course");
        put('P', "Performance Pay & Rewards");
        put('Q', "Advanced Security Training");
        put('R', "Recruitment Supervisor");
        put('S', "HQ Sentry");
        put('T', "Knowledge of Transferring Personnel");
        put('U', "Introduction to Offices");
        put('W', "WO Leadership Course");
        put('X', "Background Check Completed");
        put('Y', "Advanced Marksmanship Training");
        put('Z', "Professional Military Education (PME)");
        put('£', "Marksmanship and Medical Instructor");
        put('§', "Global PTP Instructor");
    }};

    private static HashMap<Character, String> featCombinationMap = new HashMap<>(){{
        put('&', "RGJTH");
        put('=', "FY");
        put('+', "EFY");
        put('%', "BS");
        put('@', "MBS");
        put('#', "LW");
        put('$', "LWO");
        put('!', "UKP");
        put('?', "ADN");
        put('1', "ABCDEFGHJPRSTUVY");
        put('2', "ABCDEFGHJPRSTUVYLM");
        put('3', "ABCDEFGHJPRSTUVYLWMZ");
        put('4', "ABCDEFGHJPRSTUVYLWIMZ");
        put('5', "ABCDEFGHJPRSTUVYLWIOMZ");
    }};

    public static List<Character> returnFeatsWithoutCombinations(String feats) {
        List<Character> featList = new ArrayList<>();
        for (char combinationCharacter : feats.toCharArray()) {
            if (TrainingUtility.featCombinationMap.containsKey(combinationCharacter)) {
                for (Character character : TrainingUtility.featCombinationMap.get(combinationCharacter).toCharArray()) {
                    featList.add(character);
                }
            }
        }
        return featList;
    }

    public static HashMap<Character, String> getAllFeats() {
        return TrainingUtility.featMap;
    }

    public static List<Character> getFeatList() {
        return new ArrayList<Character>(featMap.keySet());
    }

//    public List<String> returnFeatsFromStack(String feats) {
//        List<Character> featList = new ArrayList<>();
//        for (char c : feats.toCharArray()) {
//            if (TrainingUtility.featCombinationMap.containsKey(c + "") || featMap.containsKey(c + "")) {
//                featList.add(TrainingUtility.featMap.get(c + ""));
//            }
//        }
//        return featList;
//    }
}

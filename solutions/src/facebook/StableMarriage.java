package facebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StableMarriage {
    // Stable Marriage Problem - Gale–Shapley Algorithm (Deferred Acceptance algorithm)
    // Given N men and N women and the preference marriage order for each man and woman write an
    // algorithm to marry them in a manner so that their marriages are stable.

    // Given n men and n women, where each person has ranked all members of the opposite sex in order of preference,
    // marry the men and women together such that there are no two people of the opposite sex who would both rather
    // have each other than their current partners. When there are no such pairs of people,
    // the set of marriages is deemed stable

    //There are 2 men (m1 and m2) and 2 women (w1 and w2). Marriage preference order is given below -

    //Preference order of m1 - [w1, w2]
    //Preference order of m2 - [w1, w2]
    //Preference order of w1 - [m1, m2]
    //Preference order of w2 - [m1, m2]

    //Possible couples for marriage (m1, w1) and (m2, w2) (m1, w2) and (m2, w1)

    //Couples (m1, w2) and (m2, w1) are not stable since m1 would prefer w1 over w2 (assigned to m1) similarly w1
    // would prefer m1 over m2 (assigned to w1).
    // On the other hand couples (m1, w1) and (m2, w2) are stable since there are no two people of the opposite sex
    // who would both rather have each other than their current partners. Let's see what are other two people of
    // the opposite sex (m1, w2) and (m2, w1). In (m1, w2), w2 would prefer m1 but m1 would not prefer w2 over w1
    // (assigned to m1) and similarly in (m2, w1), m2 would prefer w1 but w1 would not prefer m2 over m1
    // (assigned to w1) so (m1, w1) and (m2, w2) are stable couple.

    public static void main(String[] args) {
        int men[][] = {
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3}
        };

        // Preference order for 3 women
        int women[][] = {
                {1, 0, 2, 3},
                {1, 2, 3, 0},
                {0, 1, 3, 2},
                {0, 1, 3, 2}
        };
        StableMarriage sm = new StableMarriage();
        sm.matchMaking(men, women);
    }

    void matchMaking(int [][] men, int [][] women){
        HashMap<Integer, Integer> couples = findCouples(men, women);
        System.out.println("\n------------------Final Matching----------------------------");
        for(Map.Entry<Integer, Integer> entrySet: couples.entrySet()) {
            System.out.println("Woman: " + entrySet.getKey() + " is engaged with man: " + entrySet.getValue());
        }
    }

    HashMap<Integer, Integer> findCouples(int[][] men, int[][] women) {
        HashMap<Integer, Integer> couples = new HashMap<>();
        for (int i = 0; i < women.length; i++) {
            couples.put(i, null);
        }

        Set<Integer> bachelors = new HashSet<>();
        for (int i = 0; i < men.length; i++) {
            bachelors.add(i);
        }
        while (!bachelors.isEmpty()) {
            int currentBachelor = bachelors.iterator().next();
            System.out.println("\nMan " + currentBachelor + " is looking for a woman now-");
            // check for all the women preferences of current bachelor in preference order
            for (int w = 0; w < men[currentBachelor].length; w++) {
                if (couples.get(w) == null) {
                    couples.put(w, currentBachelor);
                    System.out.println("Women " + w + " has ACCEPTED the man: " + currentBachelor);
                    bachelors.remove(currentBachelor);
                    break;
                } else {
                    //current woman had already accepted the proposal from some other man
                    //check if women is interested accepting current bachelor
                    // and dumping the man which she had accepted earlier
                    int alreadyAcceptedMan = couples.get(w);
                    if (willChangePartner(currentBachelor, alreadyAcceptedMan, w, women)) {
                        couples.put(w, currentBachelor);
                        bachelors.add(alreadyAcceptedMan);
                        bachelors.remove(currentBachelor);
                        System.out.println("Women " + w + " has DUMPED the man: " + alreadyAcceptedMan);
                        System.out.println("Women " + w + " has ACCEPTED the man: " + currentBachelor);
                        break; //
                    }
                }
            }
        }
        return couples;
    }

    boolean willChangePartner(int currentBachelor, int alreadyAcceptMan, int currentWomen, int[][] women) {
        int pref_currentBachelor = -1;
        int pref_alreadyAcceptedMan = -1;
        for (int i = 0; i < women[currentWomen].length; i++) {
            if (women[currentWomen][i] == currentBachelor)
                pref_currentBachelor = i;
            if (women[currentWomen][i] == alreadyAcceptMan)
                pref_currentBachelor = i;
        }
        if (pref_currentBachelor < pref_alreadyAcceptedMan)
            return true;
        else
            return false;
    }
}

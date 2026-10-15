import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Week5Homework {
    // A1. Update only the captain and vice-captain positions in the original array.
    public static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        playerScores[captainIndex] *= 2.0;
        playerScores[viceCaptainIndex] *= 1.5;
    }

    // A2. Return the first duplicate in scan order using nested loops only.
    public static String findDuplicatePick(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    // A3. Find min and max together in one pass; no sorting.
    public static String findMinMaxSpread(int[] scores) {
        int min = scores[0];
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) min = scores[i];
            if (scores[i] > max) max = scores[i];
        }
        return "Min: " + min + " | Max: " + max + " | Spread: " + (max - min);
    }

    // A4. The helper computes one match's average; classification happens once per row.
    private static double rowAverage(int[] row) {
        if (row.length == 0) return 0.0;
        long total = 0;
        for (int runs : row) total += runs;
        return (double) total / row.length;
    }

    public static String classifyMatches(int[][] runsPerOver, int threshold) {
        StringBuilder result = new StringBuilder();
        for (int match = 0; match < runsPerOver.length; match++) {
            double average = rowAverage(runsPerOver[match]);
            if (match > 0) result.append(" | ");
            result.append("Match ").append(match).append(": ")
                  .append(average >= threshold ? "Power Surge" : "Normal");
        }
        return result.toString();
    }

    // Thresholds: established at 10+ matches; otherwise 5+ matches and not injured.
    // Batting average is the performance measure available for ranking in this prompt.
    public static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    public static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    public static String draftAndRank(Player[] players) {
        List<Player> selected = new ArrayList<>();
        for (Player player : players) {
            if (isDraftable(player.matchesPlayed)
                    || isDraftable(player.matchesPlayed, player.injured)) {
                selected.add(player);
            }
        }
        Player[] ranked = selected.toArray(new Player[0]);
        Arrays.sort(ranked);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ranked.length; i++) {
            if (i > 0) result.append(" | ");
            result.append(i + 1).append(". ").append(ranked[i].name);
        }
        return result.toString();
    }

    public static class Player implements Comparable<Player> {
        private final String name;
        private final int matchesPlayed;
        private final double battingAverage;
        private final boolean injured;

        public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        @Override
        public int compareTo(Player other) {
            return Double.compare(other.battingAverage, battingAverage);
        }
    }

    public static void main(String[] args) {
        double[] scores = {40, 55, 30, 62};
        applyMultipliers(scores, 1, 3);
        System.out.println(Arrays.toString(scores));
        System.out.println(findDuplicatePick(new String[] {"Kohli", "Bumrah", "Kohli", "Rohit"}));
        System.out.println(findMinMaxSpread(new int[] {45, 82, 79, 90, 33, 90, 61}));
        System.out.println(classifyMatches(
                new int[][] {{4, 6, 8}, {10, 12, 14}, {2, 3, 1}}, 8));
        Player[] players = {
                new Player("Virat", 15, 48.0, false), new Player("Rahul", 7, 55.0, false),
                new Player("Sameer", 3, 60.0, false), new Player("Dev", 12, 20.0, true)
        };
        System.out.println(draftAndRank(players));
    }
}

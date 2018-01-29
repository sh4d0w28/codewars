package kuy_4;

/** https://www.codewars.com/kata/51fda2d95d6efda45e00004e */

import java.util.Arrays;
import java.util.List;

public class CodewarsRanks {

    public static void main(String[] args) {
        User user = new User();
        for(int i = 0; i< 1000; i++) {
            user.addProgress(2);
            System.out.println(user);
        }
    }
}

class User {

    private static List<Integer> ranks = Arrays.asList(-8,-7,-6,-5,-4,-3,-2,-1,1,2,3,4,5,6,7,8);

    private int rankIndex;
    private int rankProgress;

    public int rank;
    public int progress;

    public User() {
        rankIndex = 0;
        rankProgress = 0;
        updatePublic();
    }

    public void addProgress(int percentage) {
        rankProgress += percentage;
        if (rankProgress >= 100) {
            rankIndex += rankProgress / 100;
            rankProgress = rankProgress % 100;
        }
        if(rankIndex >= 15) {
            rankIndex = 15;
            rankProgress = 0;
        }
        updatePublic();
    }

    public void incProgress(int taskRank) {
        if(!ranks.contains(taskRank)) {
            throw new IllegalArgumentException();
        }
        int realRank = ranks.indexOf(taskRank);

        if (realRank == rankIndex) {
            addProgress(3);
        } else if (rankIndex - realRank == 1) {
            addProgress(1);
        } else if (rankIndex - realRank> 1) {
            return;
        } else {
            int padd = rankIndex - realRank;
            addProgress(10 * padd * padd);
        }
    }

    private void updatePublic(){
        rank = ranks.get(rankIndex);
        progress = rankProgress;
    }

    @Override
    public String toString() {
        return rank + "(" + progress + "/100)";
    }
}
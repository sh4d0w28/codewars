package codewars.kuy_5;
/*
* Story
A freak power outage at the zoo has caused all of the electric cage doors to malfunction and swing open...

All the animals are out and some of them are eating each other!

It's a Zoo Disaster!
Here is a list of zoo animals, and what they can eat

antelope eats grass
big-fish eats little-fish
bug eats leaves
bear eats big-fish
bear eats bug
bear eats chicken
bear eats cow
bear eats leaves
bear eats sheep
chicken eats bug
cow eats grass
fox eats chicken
fox eats sheep
giraffe eats leaves
lion eats antelope
lion eats cow
panda eats leaves
sheep eats grass

Kata Task
INPUT
A comma-separated string representing all the things at the zoo

TASK
Figure out who eats who until no more eating is possible.

OUTPUT
A list of strings (refer to the example below) where:

The first element is the initial zoo (same as INPUT)
The last element is a comma-separated string of what the zoo looks like when all the eating has finished
All other elements (2nd to last-1) are of the form X eats Y describing what happened
Notes
Animals can only eat things beside them

Animals always eat to their LEFT before eating to their RIGHT

Always the LEFTMOST animal capable of eating will eat before any others

Any other things you may find at the zoo (which are not listed above) do not eat anything and are not edible

*/

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ZooDisaster {

    private static Map<String, List<String>> eatrels = new HashMap<>();
    private static void init(){
        eatrels.put("antelope", Arrays.asList("grass"));
        eatrels.put("big-fish", Arrays.asList("little-fish"));
        eatrels.put("bug",      Arrays.asList("leaves"));
        eatrels.put("bear",     Arrays.asList("big-fish", "bug", "chicken", "cow", "leaves", "sheep"));
        eatrels.put("chicken",  Arrays.asList("bug"));
        eatrels.put("cow",      Arrays.asList("grass"));
        eatrels.put("fox",      Arrays.asList("chicken", "sheep"));
        eatrels.put("giraffe",  Arrays.asList("leaves"));
        eatrels.put("lion",     Arrays.asList("antelope", "cow"));
        eatrels.put("panda",    Arrays.asList("leaves"));
        eatrels.put("sheep",    Arrays.asList("grass"));
    }
    private static boolean isEats(String predator, String victim){
        return eatrels.containsKey(predator) && eatrels.get(predator).contains(victim);
    }

    public static String[] whoEatsWho(final String zoo) {
        init();
        List<String> results = new ArrayList<>();
        results.add(zoo);

        List<String> initZoo = new ArrayList<>();
        initZoo.addAll(Arrays.asList(zoo.split(",")));

        initZoo.add("BRICK");
        initZoo.add(0, "BRICK");
        boolean ate = false;
        for (int i = 1; i<initZoo.size()-1; i++) {
            if (isEats(initZoo.get(i), initZoo.get(i-1))) {
                results.add(String.format("%s eats %s", initZoo.get(i), initZoo.get(i-1)));
                initZoo.remove(i-1);
                i = 0;
            } else if (isEats(initZoo.get(i), initZoo.get(i+1))) {
                results.add(String.format("%s eats %s", initZoo.get(i), initZoo.get(i+1)));
                initZoo.remove(i+1);
                i = 0;
            }
        }
        initZoo.remove(0);
        initZoo.remove(initZoo.size()-1);

        results.add(String.join(",", initZoo));

        return results.toArray(new String[results.size()]);
    }

    @Test
    public void ZooDisasterTest() {
        final String input = "fox,bug,chicken,grass,sheep";
        final String[] expected = 	{
                "fox,bug,chicken,grass,sheep",
                "chicken eats bug",
                "fox eats chicken",
                "sheep eats grass",
                "fox eats sheep",
                "fox"};
        Assert.assertArrayEquals(expected, whoEatsWho(input));
    }
}
package codewars.kuy_5;

class Preloaded {
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ?!@#&()|<>.:=-+*/0123456789";
}

class Dinglemouse {

    private static final String ALPHABET = Preloaded.ALPHABET;

    public static String[] flapDisplay(final String[] lines, final int[][] rotors) {

        String[] result = new String[lines.length];

        for(int i = 0; i< lines.length; i++) {
            result[i] = "";
            int acc = 0;
            for(int j = 0; j < rotors[i].length; j++) {
                acc += rotors[i][j];
                Character newChar = ALPHABET.charAt(
                        (ALPHABET.indexOf(lines[i].charAt(j)) + acc) % ALPHABET.length()
                );
                result[i] = result[i].concat(newChar.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] lines = {"CAT"};
        int[][] rotors = {{1,13,27}};
        String[] expected = {"DOG"};
        String[] result = flapDisplay(lines, rotors);

        System.out.println(expected);
        System.out.println(result);
    }
}
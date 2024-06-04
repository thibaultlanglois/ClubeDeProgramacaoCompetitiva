import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new TreeMap<>();
        List<String> duplicates = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("#")) break;

            String[] words = line.split("\\s+");
            for (String word : words) {
                char[] chars = word.toLowerCase().toCharArray();
                Arrays.sort(chars);
                String sorted = new String(chars);

                if (map.containsKey(sorted)) {
                    duplicates.add(map.get(sorted));
                    duplicates.add(word);
                } else {
                    map.put(sorted, word);
                }
            }
        }

        List<String> ananagrams = new ArrayList<>();
        for (String value : map.values()) {
            if (!duplicates.contains(value)) {
                ananagrams.add(value);
            }
        }

        Collections.sort(ananagrams);
        for (String ananagram : ananagrams) {
            System.out.println(ananagram);
        }
        scanner.close();
    }
}

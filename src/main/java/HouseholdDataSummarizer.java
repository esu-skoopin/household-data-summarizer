/**
 * Summarizes resident data from .csv files or .txt files formatted into CSV format
 */

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.File;
import java.util.*;

public class HouseholdDataSummarizer {

    /**
     * Takes a file of resident data as input and outputs a summary of said data where residents are grouped by address.
     *
     * For each group of residents, the following information is outputted:
     * - "Household of n" where n is the number of people in living at the address in question
     * - The first name, last name, address and age of every resident over 18, sorted by last name, then first name
     * - A line of placeholder text (i.e., "An individual that is under 19 years old") for residents 18 and under
     *
     * @param data - the file to be summarized
     */
    public static void summarizeHouseholdData(File data) throws Exception {
        try {
            CSVReader reader = new CSVReader(new FileReader(data));
            HashMap<String, TreeSet<Person>> householdMap = new HashMap<String, TreeSet<Person>>();
            String[] row;

            while ((row = reader.readNext()) != null) {
                Person person = new Person(
                        row[0],
                        row[1],
                        format("street", row[2]),
                        format("city", row[3]),
                        row[4].toUpperCase(),
                        Integer.parseInt(row[5])
                );
                String address = person.getAddress();
                if (!householdMap.containsKey(address)) {
                    TreeSet<Person> temp = new TreeSet<Person>(
                            Comparator.comparing((Person p) -> p.getLastName())
                                      .thenComparing(p -> p.getFirstName())
                    );
                    temp.add(person);
                    householdMap.put(address, temp);
                }
                else {
                    householdMap.get(address).add(person);
                }
            }
            reader.close();
            householdMap.forEach((address, residents) -> {
                System.out.println("Household of " + residents.size() + ":");
                residents.forEach(resident -> {
                    if (resident.getAge() > 18) {
                        System.out.println(String.join(
                                ", ",
                                resident.getFirstName(),
                                resident.getLastName(),
                                resident.getAddress(),
                                String.valueOf(resident.getAge())
                        ));
                    }
                    else {
                        System.out.println("An individual that is under 19 years old");
                    }
                });
                System.out.println();
            });
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * Sanitizes and formats every passed street address and city
     * @param subject - the type of value to be sanitized and formatted (i.e., "street" or "city")
     * @param value - the actual street address or city
     * @return the sanitized and formatted street address or city
     */
    public static String format(String subject, String value) {
        String regex = subject.equals("street") ? "[^a-zA-Z0-9'\\- ]" : "[^a-zA-Z'\\- ]";
        String[] words = value.replaceAll(regex, "").toLowerCase().split("\\s+");
        StringBuilder retVal = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                retVal.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1))
                      .append(" ");
            }
        }
        return retVal.toString().trim();
    }
}
public class Person {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int age;

    public Person(String firstName, String lastName, String street, String city, String state, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return String.join(", ", street, city, state);
    }

    public int getAge() {
        return age;
    }
}
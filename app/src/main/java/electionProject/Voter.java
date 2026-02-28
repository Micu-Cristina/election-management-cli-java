package electionProject;

// Represents a voter registered in a constituency.
public class Voter {
    private String nationalId; // voter national id
    private int age; // voter age
    private boolean needsAssistance; // indicates if voter needs assistance
    private String name; // voter name

    // constructor initializes all voter fields
    public Voter(String nationalId, int age, boolean needsAssistance, String name) {
        this.nationalId = nationalId;
        this.age = age;
        this.needsAssistance = needsAssistance;
        this.name = name;
    }

    // getter for voter national id
    public String getNationalId() {
        return nationalId;
    }

    // getter for voter age
    public int getAge() {
        return age;
    }

    // getter for assistance requirement
    public boolean needsAssistance() {
        return needsAssistance;
    }

    // getter for voter name
    public String getName() {
        return name;
    }



}

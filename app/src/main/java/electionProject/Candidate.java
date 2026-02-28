package electionProject;

// Candidate class implements Comparable for ordering
// Represents a person running in the election.

public class Candidate implements Comparable<Candidate> {
    private String nationalId; // candidate national id
    private int age; // candidate age
    private String name; // candidate name
    private int voteCount; // number of votes received


    // constructor to initialize candidate fields
    public Candidate(String nationalId, int age, String name) {
        this.nationalId = nationalId;
        this.age = age;
        this.name = name;
        this.voteCount = 0;
    }

    // getter for candidate national id
    public String getNationalId() {
        return nationalId;
    }

    // getter for candidate age
    public int getAge() {
        return age;
    }

    // getter for candidate name
    public String getName() {
        return name;
    }

    // getter for vote count
    public int getVoteCount() {
        return voteCount;
    }

    // setter for vote count
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    // increments vote count by 1
    public void addVote() {
        this.voteCount++;
    }

    // compares candidates by vote count, then national id
    @Override
    public int compareTo(Candidate other) {
        if (other.voteCount > this.voteCount) {
            return -1;
        } else if (other.voteCount < this.voteCount) {
            return 1;
        }
        if (other.nationalId.compareTo(this.nationalId) < 0) {
            return -1;
        } else if (other.nationalId.compareTo(this.nationalId) > 0) {
            return 1;
        }

        return 0;
    }

}


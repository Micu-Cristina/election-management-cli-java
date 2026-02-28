package electionProject;

// Records a single fraud attempt and its context.
public class Fraud {
    private String voterNationalId; // voter national id involved in fraud
    private String fraudType; // fraud type
    private String voterName; // voter name involved in fraud
    private String constituencyName; // constituency name where fraud happened

    // constructor initializes all fields
    public Fraud(String voterNationalId, String fraudType, String voterName, String constituencyName) {
        this.voterNationalId = voterNationalId;
        this.fraudType = fraudType;
        this.voterName = voterName;
        this.constituencyName = constituencyName;
    }

    // getter for voter national id
    public String getVoterNationalId() {
        return voterNationalId;
    }

    // getter for fraud type
    public String getFraudType() {
        return fraudType;
    }

    // getter for voter name
    public String getVoterName() {
        return voterName;
    }

    // getter for constituency name
    public String getConstituencyName() {
        return constituencyName;
    }
}

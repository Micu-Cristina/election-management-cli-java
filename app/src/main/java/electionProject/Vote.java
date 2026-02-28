package electionProject;

// Links a voter to the candidate they selected.
public class Vote {
    private String voterNationalId; // voter who cast the vote
    private String candidateNationalId; // candidate who received the vote

    // constructor initializes voter and candidate ids
    public Vote(String voterNationalId, String candidateNationalId) {
        this.voterNationalId = voterNationalId;
        this.candidateNationalId = candidateNationalId;
    }

    // getter for voter national id
    public String getVoterNationalId() {
        return voterNationalId;
    }

    // getter for candidate national id
    public String getCandidateNationalId() {
        return candidateNationalId;
    }
}

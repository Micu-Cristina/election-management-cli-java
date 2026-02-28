package electionProject;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

// Local container that tracks voters and votes within a region.
public class Constituency {
    private String name; // constituency name
    private String region; // constituency region
    private Map<String, Voter> voters; // voters by national id
    private Map<String, Candidate> candidates; // candidates by national id
    private Map<String, Integer> votes; // votes by candidate national id

    // constructor initializes name, region, and maps
    public Constituency(String name, String region) {
        this.name = name;
        this.region = region;
        this.voters = new HashMap<>();
        this.candidates = new HashMap<>();
        this.votes = new HashMap<>();
    }

    // getter for constituency name
    public String getName() {
        return name;
    }

    // getter for constituency region
    public String getRegion() {
        return region;
    }

    // checks if a voter exists by national id
    public boolean hasVoterByNationalId(String nationalId) {
        return voters.containsKey(nationalId); // true if voter exists
    }

    // adds a voter to the constituency
    public void addVoter(Voter voter) {
        if (!voters.containsKey(voter.getNationalId())) { // check duplicate national id
            voters.put(voter.getNationalId(), voter); // add voter
            System.out.println("Voter " + voter.getName() + " was added");
        } else {
            System.out.println("ERROR: Voter " + voter.getName() + " already has the same national ID");
        }
    }

    // getter for a voter by national id
    public Voter getVoter(String nationalId) {
        return voters.get(nationalId);
    }

    // getter for voters map
    public Map<String, Voter> getVoters() {
        return voters;
    }

    // alternative lookup for a voter by national id
    public Voter findVoterByNationalId(String nationalId) {
        for (Map.Entry<String, Voter> entry : voters.entrySet()) { // iterate through voters
            if (entry.getKey().equals(nationalId)) {
                return entry.getValue(); // return voter
            }
        }
        return null; // return null if not found
    }

    // adds a candidate to the constituency after validation
    public boolean addCandidate(Candidate candidate) {
        if (candidate.getNationalId().length() != 13) { // validate national id
            System.out.println("ERROR: Invalid national ID");
            return false;
        }
        if (candidate.getAge() < 35) { // validate age (min 35)
            System.out.println("ERROR: Invalid age");
            return false;
        }
        if (candidates.containsKey(candidate.getNationalId())) { // check duplicate
            System.out.println("ERROR: Candidate " + name + " already has the same national ID");
            return false;
        }
        candidates.put(candidate.getNationalId(), candidate); // add candidate
        System.out.println("Success. Candidate " + candidate.getName() + " was added");
        return true;
    }

    // returns a list of candidates who received votes
    public List<Candidate> getVotedCandidates() {
        List<Candidate> votedCandidates = new ArrayList<>(); // list for voted candidates
        System.out.println("Candidates in map: " + candidates.size());
        for (Candidate candidate : candidates.values()) {  // iterate candidates
            votedCandidates.add(candidate);
        }

        return votedCandidates;
    }

    // getter for candidates map
    public Map<String, Candidate> getCandidates() {
        return candidates;
    }

    // records a vote for a candidate
    public void recordVote(String candidateNationalId) {
        if (candidates.containsKey(candidateNationalId)) { // check candidate exists
            votes.putIfAbsent(candidateNationalId, 0); // init if needed

            votes.put(candidateNationalId, votes.get(candidateNationalId) + 1); // add vote

            System.out.println("Vote recorded for candidate: " + candidates.get(candidateNationalId).getName());
        } else {
            System.out.println("ERROR: Candidate does not exist in this constituency.");
        }
    }

    // getter for votes map
    public Map<String, Integer> getVotes() {
        return votes;
    }

    // returns total votes in the constituency
    public int getTotalVotes() {
        int totalVotes = 0;
        for (int candidateVotes : votes.values()) { // iterate vote counts
            totalVotes += candidateVotes;
        }
        return totalVotes;
    }

    // returns number of votes for a candidate by national id
    public int getVotesForCandidate(String candidateNationalId) {
        if (votes.containsKey(candidateNationalId)) {
            return votes.get(candidateNationalId);
        } else {
            System.out.println("ERROR: Candidate with national ID " + candidateNationalId + " does not exist in this constituency.");
            return 0;
        }
    }

}

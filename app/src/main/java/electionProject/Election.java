package electionProject;

import java.util.*;


// Aggregates constituencies, candidates, votes, and frauds for one election.
public class Election {
    private String id; // election id
    private String name; // election name
    private boolean started; // election state (started or not)
    private Map<String, Constituency> constituencies; // existing constituencies
    private Map<String, Candidate> candidates; // candidates registered in the election
    private Set<String> recordedVotes; // keeps track of voters who already voted
    private Stack<Fraud> frauds; // fraud stack
    private Map<String, Integer> globalVotes; // global votes for each candidate

    // constructor
    public Election(String id, String name) {
        this.id = id;
        this.name = name;
        this.started = false;
        this.constituencies = new HashMap<>();
        this.candidates = new HashMap<>();
        this.recordedVotes = new HashSet<>();
        this.frauds = new Stack<>();
        this.globalVotes = new HashMap<>();
    }

    // checks if the election has started
    public boolean isStarted() {
        return started;
    }

    // starts the election
    public void start() {
        this.started = true;
    }

    // getter for the election name
    public String getName() {
        return name;
    }

    // adds a constituency to the election
    public boolean addConstituency(String name, String region) {
        if (constituencies.containsKey(name)) {  // check if it already exists
            return false;
        }
        constituencies.put(name, new Constituency(name, region));  // add constituency
        return true;
    }

    // checks if a constituency exists
    public boolean hasConstituency(String name) {
        return constituencies.containsKey(name);
    }

    // removes a constituency by name
    public boolean removeConstituency(String name) {
        if (constituencies.containsKey(name)) {
            constituencies.remove(name); // remove constituency
            return true;
        }
        return false;
    }

    // adds a candidate after validating national id and age
    public boolean addCandidateWithValidation(Candidate candidate) {
        if (candidate.getNationalId().length() != 13) {  // validate national id
            System.out.println("ERROR: Invalid national ID");
            return false;
        }
        if (candidate.getAge() < 35) { // validate age
            System.out.println("ERROR: Invalid age");
            return false;
        }
        if (candidates.containsKey(candidate.getNationalId())) {  // check duplicate
            System.out.println("ERROR: Candidate " + name + " already has the same national ID");
            return false;
        }
        candidates.put(candidate.getNationalId(), candidate); // add candidate
        System.out.println("Success. Candidate " + candidate.getName() + " was added");
        return true;
    }

    // adds a candidate to the election
    public boolean addCandidate(Candidate candidate) {
        candidates.put(candidate.getNationalId(), candidate);
        return true;
    }

    // checks if a candidate with a national id exists
    public boolean hasCandidateByNationalId(String nationalId) {
        return candidates.containsKey(nationalId);
    }

    // removes a candidate by national id
    public void removeCandidate(String nationalId) {
        Candidate candidate = candidates.get(nationalId);
        if (candidate != null) {
            candidates.remove(nationalId); // remove candidate
            System.out.println("Candidate " + candidate.getName() + " was removed");
        } else {
            System.out.println("ERROR: No candidate with national ID " + nationalId);
        }
    }

    // returns a candidate by national id
    public Candidate getCandidateByNationalId(String nationalId) {
        return candidates.get(nationalId);
    }

    // getter for a constituency by name
    public Constituency getConstituency(String constituencyName) {
        return constituencies.get(constituencyName);
    }

    // getter for constituencies
    public Map<String, Constituency> getConstituencies() {
        return constituencies;
    }

    // getter for candidates
    public Map<String, Candidate> getCandidates() {
        return candidates;
    }

    // checks if a voter already voted
    public boolean hasVoted(String voterNationalId) {
        return recordedVotes.contains(voterNationalId);
    }

    // records a vote for a voter
    public void recordVote(String voterNationalId) {
        recordedVotes.add(voterNationalId);  // add voter national id
    }

    // records fraud
    public void recordFraud(Fraud fraud) {
        frauds.push(fraud);  // push fraud to stack
    }

    // finds a voter by national id
    public Voter findVoterByNationalId(String nationalId) {
        for (Constituency constituency : constituencies.values()) {
            Voter voter = constituency.getVoter(nationalId);
            if (voter != null) {
                return voter; // return voter if found
            }
        }
        return null;  // return null if not found
    }

    // getter for fraud stack
    public Stack<Fraud> getFrauds() {
        return frauds;
    }

    // stops the election
    public void stop() {
        this.started = false;
    }

    // adds a global vote for a candidate
    public void addGlobalVote(String candidateNationalId) {
        int currentVotes = globalVotes.getOrDefault(candidateNationalId, 0); // get current count
        globalVotes.put(candidateNationalId, currentVotes + 1); // increment
        System.out.println("Vote added for candidate " + candidateNationalId + ". Global votes now: " + (currentVotes + 1));
    }

    // getter for a candidate's global votes
    public int getGlobalVotesFor(String candidateNationalId) {
        return globalVotes.getOrDefault(candidateNationalId, 0); // return global votes for candidate
    }

    // getter for all global votes
    public Map<String, Integer> getGlobalVotes() {
        return globalVotes;
    }

}

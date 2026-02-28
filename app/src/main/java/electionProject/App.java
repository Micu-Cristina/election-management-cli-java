package electionProject;

import java.io.InputStream;
import java.util.*;



public class App {
    private Scanner scanner;
    private Map<String, Election> electionsById;  // stores elections by id

    /**
     * Creates the CLI app with a provided input stream.
     * This makes the class easy to test with custom input.
     */
    public App(InputStream input) {
        this.scanner = new Scanner(input);
        this.electionsById = new HashMap<>();  // initialize election map
    }

    /**
     * Runs the main CLI loop.
     * Prints the menu, reads a command, and dispatches it.
     */
    public void run() {
        while (true) {
            // display available commands
            System.out.println("Choose a command:");
            System.out.println("0. Create election");
            System.out.println("1. Start election");
            System.out.println("2. Add constituency");
            System.out.println("3. Remove constituency");
            System.out.println("4. Add candidate to election");
            System.out.println("5. Remove candidate from election");
            System.out.println("6. Add voter to constituency");
            System.out.println("7. List election candidates");
            System.out.println("8. List constituency voters");
            System.out.println("9. Vote");
            System.out.println("10. Stop election");
            System.out.println("11. Create constituency vote report");
            System.out.println("12. Create national vote report");
            System.out.println("13. Detailed constituency analysis");
            System.out.println("14. Detailed national analysis");
            System.out.println("15. Report frauds");
            System.out.println("16. Delete election");
            System.out.println("17. List elections");
            System.out.println("18. Exit");

            // read user option
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline after number

            // route to the correct command handler
            switch (option) {
                case 0:
                    createElection();
                    break;
                case 1:
                    startElection();
                    break;
                case 2:
                    addConstituency();
                    break;
                case 3:
                    removeConstituency();
                    break;
                case 4:
                    addCandidateToElection();
                    break;
                case 5:
                    removeCandidateFromElection();
                    break;
                case 6:
                    addVoterToConstituency();
                    break;
                case 7:
                    listElectionCandidates();
                    break;
                case 8:
                    listConstituencyVoters();
                    break;
                case 9:
                    vote();
                    break;
                case 10:
                    stopElection();
                    break;
                case 11:
                    createConstituencyVoteReport();
                    break;
                case 12:
                    createNationalVoteReport();
                    break;
                case 13:
                    detailedConstituencyAnalysis();
                    break;
                case 14:
                    detailedNationalAnalysis();
                    break;
                case 15:
                    reportFrauds();
                    break;
                case 16:
                    deleteElection();
                    break;
                case 17:
                    listElections();
                    break;
                case 18:
                    exitApp();
                    return;
                default:
                    System.out.println("Invalid option.");
            }

        }
    }

    /**
     * Command 0: creates a new election by id and name.
     * Rejects duplicates and invalid input format.
     */
    private void createElection() {
        // command 0
        System.out.println("Option 0 selected. Create election.");
        System.out.println("Enter election ID and name:");

        String inputCase0 = scanner.nextLine();
        String[] partsCase0 = inputCase0.split(" ", 2);

        if (partsCase0.length < 2) {
            System.out.println("ERROR: Invalid format.");
            return;
        }

        String electionId = partsCase0[0].trim();
        String electionName = partsCase0[1].trim();

        if (electionsById.containsKey(electionId)) {
            System.out.println("ERROR: An election with id " + electionId + " already exists");
        } else {
            Election election = new Election(electionId, electionName);
            electionsById.put(electionId, election);
            System.out.println("Success. Election " + electionName + " was created");
        }
    }

    /**
     * Command 1: starts an existing election.
     * Ensures the election exists and is not already started.
     */
    private void startElection() {
        // command 1
        System.out.println("Option 1 selected. Start election.");

        System.out.println("Enter election ID to start:");
        String inputCase1 = scanner.nextLine().trim();

        Election election = electionsById.get(inputCase1);

        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (election.isStarted()) {
            System.out.println("ERROR: Election already started");
            return;
        }

        election.start();
        System.out.println("Success. Election " + election.getName() + " has started");
    }

    /**
     * Command 2: adds a constituency to an active election.
     * Validates election state and unique constituency name.
     */
    private void addConstituency() {
        // command 2
        System.out.println("Option 2 selected. Add constituency.");

        System.out.println("Enter election ID, constituency name, and region:");

        String inputCase2 = scanner.nextLine();
        String[] partsCase2 = inputCase2.split(" ", 3);

        if (partsCase2.length < 3) {
            System.out.println("ERROR: Invalid format.");
            return;
        }

        String electionId = partsCase2[0];
        String constituencyName = partsCase2[1];
        String region = partsCase2[2];

        Election election = electionsById.get(electionId);

        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (!election.isStarted()) {
            System.out.println("ERROR: Voting period has not started");
            return;
        }

        if (election.hasConstituency(constituencyName)) {
            System.out.println("ERROR: A constituency named " + constituencyName + " already exists");
        } else {
            election.addConstituency(constituencyName, region);
            System.out.println("Success. Constituency " + constituencyName + " was added");
        }
    }

    /**
     * Command 3: removes a constituency from an active election.
     * Requires a valid election and existing constituency.
     */
    private void removeConstituency() {
        // command 3
        System.out.println("Option 3 selected. Remove constituency.");

        System.out.println("Enter election ID and constituency name:");

        String inputCase3 = scanner.nextLine();
        String[] partsCase3 = inputCase3.split(" ", 2);

        if (partsCase3.length < 2) {
            System.out.println("ERROR: Invalid format");
            return;
        }

        String electionId = partsCase3[0].trim();
        String constituencyName = partsCase3[1].trim();

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (!election.isStarted()) {
            System.out.println("ERROR: Voting period has not started");
            return;
        }

        if (election.removeConstituency(constituencyName)) {
            System.out.println("Success. Constituency " + constituencyName + " was removed");
        } else {
            System.out.println("ERROR: No constituency named " + constituencyName);
        }
    }

    /**
     * Command 4: registers a candidate in an active election.
     * Validates age, national ID length, and duplicate IDs.
     */
    private void addCandidateToElection() {
        // command 4
        System.out.println("Option 4 selected. Add candidate to election.");

        System.out.println("Enter election ID, national ID, age, and candidate name:");
        String inputCase4 = scanner.nextLine().trim();

        String[] partsCase4 = inputCase4.split(" ", 4);

        if (partsCase4.length < 4) {
            System.out.println("ERROR: Invalid format. Use <election_id> <national_id> <age> <name>.");
            return;
        }

        String electionId = partsCase4[0].trim();
        String nationalId = partsCase4[1].trim();
        String ageString = partsCase4[2].trim();
        String candidateName = partsCase4[3].trim();

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (!election.isStarted()) {
            System.out.println("ERROR: Voting period has not started");
            return;
        }

        if (nationalId.length() != 13) {
            System.out.println("ERROR: Invalid national ID");
            return;
        }

        int age = Integer.parseInt(ageString);

        if (age < 35) {
            System.out.println("ERROR: Invalid age");
            return;
        }

        if (election.hasCandidateByNationalId(nationalId)) {
            System.out.println("ERROR: Candidate " + candidateName + " already has the same national ID");
            return;
        }

        Candidate candidate = new Candidate(nationalId, age, candidateName);

        boolean success = election.addCandidate(candidate);
        if (success) {
            System.out.println("Candidate " + candidateName + " was added");
        } else {
            System.out.println("Error adding candidate.");
        }
    }

    /**
     * Command 5: removes a candidate by national ID.
     * Requires an active election and an existing candidate.
     */
    private void removeCandidateFromElection() {
        // command 5
        System.out.println("Option 5 selected. Remove candidate from election.");

        String inputCase5 = scanner.nextLine();
        String[] partsCase5 = inputCase5.split(" ", 2);

        if (partsCase5.length < 2) {
            System.out.println("ERROR: Invalid format.");
            return;
        }

        String electionId = partsCase5[0];
        String nationalId = partsCase5[1];

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (!election.isStarted()) {
            System.out.println("ERROR: Voting period has not started");
            return;
        }

        Candidate candidate = election.getCandidateByNationalId(nationalId);
        if (candidate == null) {
            System.out.println("ERROR: No candidate with national ID " + nationalId);
            return;
        }

        election.removeCandidate(nationalId);

        System.out.println("Candidate " + candidate.getName() + " was removed");
    }


    /**
     * Command 6: registers a voter in a constituency.
     * Validates election state, age, and national ID length.
     */
    private void addVoterToConstituency() {
        // command 6
        System.out.println("Option 6 selected. Add voter to constituency.");

        String[] inputCase6 = scanner.nextLine().split(" ");

        String electionId = inputCase6[0];
        String constituencyName = inputCase6[1];
        String nationalId = inputCase6[2];
        int age = Integer.parseInt(inputCase6[3]);
        boolean needsAssistance = inputCase6[4].equalsIgnoreCase("yes");

        String voterName = "";
        if (inputCase6.length > 5) {
            voterName = "";
            for (int i = 5; i < inputCase6.length; i++) {
                if (i > 5) {
                    voterName += " ";
                }
                voterName += inputCase6[i];
            }
        } else {
            System.out.println("ERROR: Not enough data for voter name.");
        }

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (!election.isStarted()) {
            System.out.println("ERROR: Voting period has not started");
            return;
        }

        if (!election.hasConstituency(constituencyName)) {
            System.out.println("ERROR: No constituency named " + constituencyName);
            return;
        }

        if (nationalId.length() != 13) {
            System.out.println("ERROR: Invalid national ID");
            return;
        }

        if (age < 18) {
            System.out.println("ERROR: Invalid age");
            return;
        }

        Constituency constituency = election.getConstituency(constituencyName);
        if (constituency.hasVoterByNationalId(nationalId)) {
            System.out.println("ERROR: Voter " + voterName + " already has the same national ID");
            return;
        }

        Voter voter = new Voter(nationalId, age, needsAssistance, voterName);
        constituency.addVoter(voter);

        System.out.println("Voter " + voterName + " was added");
    }

    /**
     * Command 7: lists election candidates.
     * Orders by vote count, then by national ID.
     */
    private void listElectionCandidates() {
        // command 7
        System.out.println("Option 7 selected. List election candidates.");

        String electionId = scanner.nextLine().trim();

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (!election.isStarted()) {
            System.out.println("ERROR: Election has not started yet");
            return;
        }

        Map<String, Candidate> candidates = election.getCandidates();

        if (candidates.isEmpty()) {
            System.out.println("EMPTY: No candidates");
            return;
        }

        List<Candidate> candidateList = new ArrayList<>(candidates.values());

        for (int i = 0; i < candidateList.size() - 1; i++) {
            for (int j = i + 1; j < candidateList.size(); j++) {
                Candidate candidate1 = candidateList.get(i);
                Candidate candidate2 = candidateList.get(j);

                if (candidate1.compareTo(candidate2) < 0) {
                    candidateList.set(i, candidate2);
                    candidateList.set(j, candidate1);
                }
            }
        }

        System.out.println("Candidates:");
        for (Candidate candidate : candidateList) {
            System.out.println(candidate.getName() + " " + candidate.getNationalId() + " " + candidate.getAge());
        }
    }

    /**
     * Command 8: lists voters from a constituency.
     * Sorts by national ID for consistent output.
     */
    private void listConstituencyVoters() {
        // command 8
        System.out.println("Option 8 selected. List constituency voters.");

        String inputCase8 = scanner.nextLine();
        String[] partsCase8 = inputCase8.split(" ", 2);

        if (partsCase8.length < 2) {
            System.out.println("ERROR: Invalid format.");
            return;
        }

        String electionId = partsCase8[0];
        String constituencyName = partsCase8[1];

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (!election.isStarted()) {
            System.out.println("ERROR: Election has not started yet");
            return;
        }

        Constituency constituency = election.getConstituency(constituencyName);
        if (constituency == null) {
            System.out.println("ERROR: No constituency named " + constituencyName);
            return;
        }

        Map<String, Voter> voters = constituency.getVoters();

        if (voters.isEmpty()) {
            System.out.println("EMPTY: No voters in " + constituencyName);
            return;
        }

        List<Voter> voterList = new ArrayList<>(voters.values());
        voterList.sort(Comparator.comparing(Voter::getNationalId));

        System.out.println("Voters in " + constituencyName + ":");
        for (Voter voter : voterList) {
            System.out.println(voter.getName() + " " + voter.getNationalId() + " " + voter.getAge());
        }
    }


    /**
     * Command 9: records a vote.
     * Detects fraud for unregistered or repeat voters.
     */
    private void vote() {
        // command 9
        System.out.println("Option 9 selected. Vote.");

        System.out.println("Enter election_id, constituency_name, voter_national_id, candidate_national_id");

        String inputCase9 = scanner.nextLine();
        String[] partsCase9 = inputCase9.split(" ", 4);

        if (partsCase9.length < 4) {
            System.out.println("ERROR: Invalid format.");
            return;
        }

        String electionId = partsCase9[0];
        String constituencyName = partsCase9[1];
        String voterNationalId = partsCase9[2];
        String candidateNationalId = partsCase9[3];

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (!election.isStarted()) {
            System.out.println("ERROR: Voting period has not started");
            return;
        }

        Constituency constituency = election.getConstituency(constituencyName);
        if (constituency == null) {
            System.out.println("ERROR: No constituency named " + constituencyName);
            return;
        }

        Voter voterInConstituency = constituency.findVoterByNationalId(voterNationalId);
        if (voterInConstituency == null) {
            System.out.println("FRAUD: Voter with national ID " + voterNationalId + " attempted fraud. Vote was canceled");
            Fraud fraud = new Fraud(voterNationalId, "Unregistered voter", "", constituencyName);
            election.recordFraud(fraud);
            return;
        }

        if (election.hasVoted(voterNationalId)) {
            System.out.println("FRAUD: Voter with national ID " + voterNationalId + " attempted fraud. Vote was canceled");
            Fraud fraudCase9 = new Fraud(voterNationalId, "Multiple voting", voterInConstituency.getName(), constituencyName);
            election.recordFraud(fraudCase9);
            return;
        }

        Candidate candidate = election.getCandidateByNationalId(candidateNationalId);
        if (candidate == null) {
            System.out.println("ERROR: No candidate with national ID " + candidateNationalId);
            return;
        }

        Vote vote = new Vote(voterNationalId, candidateNationalId);
        election.recordVote(voterNationalId);
        election.addGlobalVote(candidateNationalId);
        boolean candidateAdded = constituency.addCandidate(candidate);
        candidate.addVote();
        constituency.recordVote(candidateNationalId);
        System.out.println(voterInConstituency.getName() + " voted for " + candidate.getName());
    }

    /**
     * Command 10: stops an ongoing election.
     * Prevents further voting once ended.
     */
    private void stopElection() {
        // command 10
        System.out.println("Option 10 selected. Stop election.");

        String electionId = scanner.nextLine().trim();

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (!election.isStarted()) {
            System.out.println("ERROR: Voting period has not started");
            return;
        }

        election.stop();
        System.out.println("Success. Election " + election.getName() + " has ended");
    }

    /**
     * Command 11: prints a constituency vote report.
     * Requires the election to be finished.
     */
    private void createConstituencyVoteReport() {
        // command 11
        System.out.println("Option 11 selected. Create constituency vote report.");

        String inputCase11 = scanner.nextLine();

        String[] partsCase11 = inputCase11.split(" ", 2);

        if (partsCase11.length < 2) {
            System.out.println("ERROR: Invalid format.");
            return;
        }

        String electionId = partsCase11[0];
        String constituencyName = partsCase11[1];

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (election.isStarted()) {
            System.out.println("ERROR: Voting has not ended yet");
            return;
        }

        Constituency constituency = election.getConstituency(constituencyName);
        if (constituency == null) {
            System.out.println("ERROR: No constituency named " + constituencyName);
            return;
        }

        List<Candidate> votedCandidates = constituency.getVotedCandidates();

        if (votedCandidates.isEmpty()) {
            System.out.println("EMPTY: No votes cast in " + constituencyName);
            return;
        }

        for (int i = 0; i < votedCandidates.size() - 1; i++) {
            for (int j = i + 1; j < votedCandidates.size(); j++) {
                Candidate candidate1 = votedCandidates.get(i);
                Candidate candidate2 = votedCandidates.get(j);

                if (candidate1.compareTo(candidate2) < 0) {
                    votedCandidates.set(i, candidate2);
                    votedCandidates.set(j, candidate1);
                }
            }
        }

        System.out.println("Vote report for " + constituencyName + ":");
        for (Candidate candidate : votedCandidates) {
            System.out.println(candidate.getName() + " " + candidate.getNationalId() + " - " + constituency.getVotesForCandidate(candidate.getNationalId()));
        }
    }

    /**
     * Command 12: prints the national vote report.
     * Aggregates totals across constituencies.
     */
    private void createNationalVoteReport() {
        // command 12
        System.out.println("Option 12 selected. Create national vote report.");

        String electionId = scanner.nextLine();

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (election.isStarted()) {
            System.out.println("ERROR: Voting has not ended yet");
            return;
        }

        Map<String, Integer> globalResults = election.getGlobalVotes();

        if (globalResults.isEmpty()) {
            System.out.println("EMPTY: No votes cast nationwide");
            return;
        }

        System.out.println("Global results:");
        for (Map.Entry<String, Integer> entry : globalResults.entrySet()) {
            String candidateNationalId = entry.getKey();
            int voteCount = entry.getValue();
            System.out.println("Candidate ID: " + candidateNationalId + " | Votes: " + voteCount);
        }

        List<Map.Entry<String, Integer>> sortedResults = new ArrayList<>(globalResults.entrySet());

        for (int i = 0; i < sortedResults.size() - 1; i++) {
            for (int j = i + 1; j < sortedResults.size(); j++) {
                Map.Entry<String, Integer> entry1 = sortedResults.get(i);
                Map.Entry<String, Integer> entry2 = sortedResults.get(j);

                Candidate candidate1 = election.getCandidates().get(entry1.getKey());
                Candidate candidate2 = election.getCandidates().get(entry2.getKey());

                if (candidate1 != null && candidate2 != null) {
                    int voteCompare = Integer.compare(entry2.getValue(), entry1.getValue());
                    if (voteCompare != 0) {
                        if (voteCompare < 0) {
                            sortedResults.set(i, entry2);
                            sortedResults.set(j, entry1);
                        }
                    } else {
                        if (candidate1.compareTo(candidate2) > 0) {
                            sortedResults.set(i, entry2);
                            sortedResults.set(j, entry1);
                        }
                    }
                }
            }
        }

        System.out.println("National vote report:");
        for (Map.Entry<String, Integer> entry : sortedResults) {
            String candidateNationalId = entry.getKey();
            int voteCount = entry.getValue();

            Candidate candidate = election.getCandidates().get(candidateNationalId);
            if (candidate != null) {
                System.out.println(candidate.getName() + " " + candidateNationalId + " - " + voteCount);
            }
        }
    }


    /**
     * Command 13: prints detailed constituency analytics.
     * Includes turnout share and top candidate share.
     */
    private void detailedConstituencyAnalysis() {
        // command 13
        System.out.println("Option 13 selected. Detailed constituency analysis.");

        String[] inputCase13 = scanner.nextLine().split(" ");
        String electionId = inputCase13[0];
        String constituencyName = inputCase13[1];

        Election election = electionsById.get(electionId);

        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }
        if (election.isStarted()) {
            System.out.println("ERROR: Voting has not ended yet");
            return;
        }

        Constituency constituency = election.getConstituency(constituencyName);
        if (constituency == null) {
            System.out.println("ERROR: No constituency named " + constituencyName);
            return;
        }

        int constituencyVotes = constituency.getTotalVotes();
        if (constituencyVotes == 0) {
            System.out.println("EMPTY: No votes cast in " + constituencyName);
            return;
        }

        int nationalVotes = 0;
        for (Map.Entry<String, Integer> entry : election.getGlobalVotes().entrySet()) {
            nationalVotes += entry.getValue();
        }

        int constituencyVotePercent = (int) ((double) constituencyVotes / nationalVotes * 100);

        String topCandidateNationalId = null;
        int maxVotes = 0;
        for (Map.Entry<String, Integer> entry : constituency.getVotes().entrySet()) {
            if (entry.getValue() >= maxVotes) {
                maxVotes = entry.getValue();
                topCandidateNationalId = entry.getKey();
            }
        }

        Candidate topCandidate = election.getCandidates().get(topCandidateNationalId);

        int topCandidatePercent = (int) ((double) maxVotes / constituencyVotes * 100);

        System.out.println("In " + constituencyName + " there were " + constituencyVotes + " votes out of " + nationalVotes + ". That's " + constituencyVotePercent + "%. Most votes were received by " + topCandidateNationalId + " " + topCandidate.getName() + ". This represents " + topCandidatePercent + "% of the constituency votes.");

    }


    /**
     * Command 14: prints national analytics by region.
     * Summarizes turnout and top candidate per region.
     */
    private void detailedNationalAnalysis() {
        // command 14
        System.out.println("Option 14 selected. Detailed national analysis.");

        System.out.println("Enter election_id:");
        String electionId = scanner.nextLine();

        Election election = electionsById.get(electionId);
        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (election.isStarted()) {
            System.out.println("ERROR: Voting has not ended yet");
            return;
        }

        int totalNationalVotes = 0;
        for (Constituency constituency : election.getConstituencies().values()) {
            totalNationalVotes += constituency.getTotalVotes();
        }

        if (totalNationalVotes == 0) {
            System.out.println("EMPTY: No votes cast nationwide.");
            return;
        }

        Map<String, Integer> votesByRegion = new HashMap<>();
        Map<String, Map<String, Integer>> votesByCandidateByRegion = new HashMap<>();

        for (Constituency constituency : election.getConstituencies().values()) {
            String region = constituency.getRegion();
            int constituencyTotalVotes = constituency.getTotalVotes();

            votesByRegion.put(region, votesByRegion.getOrDefault(region, 0) + constituencyTotalVotes);

            Map<String, Integer> constituencyVotes = constituency.getVotes();

            for (Map.Entry<String, Integer> entry : constituencyVotes.entrySet()) {
                String candidateNationalId = entry.getKey();
                int candidateVotes = entry.getValue();

                votesByCandidateByRegion.putIfAbsent(region, new HashMap<>());
                Map<String, Integer> regionVotes = votesByCandidateByRegion.get(region);

                if (regionVotes.containsKey(candidateNationalId)) {
                    regionVotes.put(candidateNationalId, regionVotes.get(candidateNationalId) + candidateVotes);
                } else {
                    regionVotes.put(candidateNationalId, candidateVotes);
                }
            }
        }

        Map<String, Candidate> topCandidatesByRegion = new HashMap<>();
        Map<String, Integer> maxVotesByRegion = new HashMap<>();

        for (String region : votesByCandidateByRegion.keySet()) {
            Map<String, Integer> votesPerCandidateInRegion = votesByCandidateByRegion.get(region);
            int maxVotes = 0;
            String topCandidateNationalId = null;

            for (Map.Entry<String, Integer> entry : votesPerCandidateInRegion.entrySet()) {
                String candidateNationalId = entry.getKey();
                int candidateVotes = entry.getValue();

                if (candidateVotes >= maxVotes) {
                    maxVotes = candidateVotes;
                    topCandidateNationalId = candidateNationalId;
                }
            }

            if (topCandidateNationalId != null) {
                topCandidatesByRegion.put(region, election.getCandidates().get(topCandidateNationalId));
                maxVotesByRegion.put(region, maxVotes);
            }
        }

        List<String> sortedRegions = new ArrayList<>(votesByRegion.keySet());

        System.out.println("There were " + totalNationalVotes + " votes nationwide.");

        for (String region : sortedRegions) {
            int regionVotes = votesByRegion.get(region);

            int regionPercent = (int) ((double) regionVotes / totalNationalVotes * 100);

            Candidate topCandidate = topCandidatesByRegion.get(region);
            int maxVotes = maxVotesByRegion.get(region);

            int candidatePercent = (int) ((double) maxVotes / regionVotes * 100);

            System.out.println("In " + region + " there were " + regionVotes + " votes out of " + totalNationalVotes + ". That's " + regionPercent + "%. Most votes were received by " + topCandidate.getNationalId() + " " + topCandidate.getName() + ". This represents " + candidatePercent + "% of the region's votes.");
        }
    }


    /**
     * Command 15: reports fraud attempts.
     * Outputs entries in LIFO order.
     */
    private void reportFrauds() {
        // command 15
        System.out.println("Option 15 selected. Report frauds.");

        String electionId = scanner.nextLine().trim();
        Election election = electionsById.get(electionId);

        if (election == null) {
            System.out.println("ERROR: No election with this id");
            return;
        }

        if (election.isStarted()) {
            System.out.println("ERROR: Voting has not ended yet");
            return;
        }

        if (election.getFrauds().isEmpty()) {
            System.out.println("EMPTY: No frauds reported");
            return;
        }

        System.out.println("Reported frauds:");

        Stack<Fraud> frauds = election.getFrauds();
        while (!frauds.isEmpty()) {
            Fraud fraud = frauds.pop();
            System.out.println("In " + fraud.getConstituencyName() + ": " + fraud.getVoterNationalId() + " " + fraud.getVoterName());
        }
    }

    /**
     * Command 16: deletes an election and its data.
     * Removes it from the in-memory registry.
     */
    private void deleteElection() {
        // command 16
        System.out.println("Option 16 selected. Delete election.");

        System.out.println("Enter election_id to delete:");
        String electionId = scanner.nextLine();

        Election election = electionsById.get(electionId);

        if (election == null) {
            System.out.println("ERROR: No election with this id");
        } else {
            electionsById.remove(electionId);
            System.out.println("Success. Election " + election.getName() + " was deleted.");
        }
    }

    /**
     * Command 17: lists all stored elections.
     * Shows id and name for each entry.
     */
    private void listElections() {
        // command 17
        System.out.println("Option 17 selected. List elections.");

        if (electionsById.isEmpty()) {
            System.out.println("EMPTY: No elections");
            return;
        }

        System.out.println("Elections:");

        for (Map.Entry<String, Election> entry : electionsById.entrySet()) {
            String electionId = entry.getKey();
            Election election = entry.getValue();
            System.out.println(electionId + " " + election.getName());
        }
    }

    /**
     * Command 18: exits the application loop.
     */
    private void exitApp() {
        // command 18
        System.out.println("Option 18 selected. Exit application.");
    }

    /**
     * Program entry point.
     */
    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}
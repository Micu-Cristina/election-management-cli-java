# Election Management System (CLI)

## Overview
This project is a console-based election management system that models the full lifecycle
of an election: setup, voter registration, candidate enrollment, voting, fraud detection,
and multi-level reporting. It is built in Java using clean object-oriented design and
standard collections (maps, sets, lists, stacks) to ensure fast lookups and consistent
state transitions during the voting process.

## Project goal
Provide a clear, testable CLI simulation of a real-world election process where data
integrity is enforced through validations, and post-election analysis is available at
both local (constituency) and national (region) levels.

## How it works (high level)
An election is created, opened for voting, populated with constituencies, candidates,
and voters, then closed and analyzed. The workflow is driven by a command menu that
routes each action to a dedicated handler and prints deterministic output for testing.

## Key capabilities
- Create and manage multiple elections in memory.
- Start and stop elections to enforce a strict voting window.
- Register constituencies with regional grouping.
- Enroll candidates and voters with validation rules.
- Record votes with fraud detection (unregistered voters, double voting).
- Generate constituency-level and national-level reports and analytics.
- Provide detailed regional analysis after voting concludes.

## Architecture and data model
- App: interactive CLI that routes commands and orchestrates the workflow.
- Election: aggregate root that owns constituencies, candidates, global votes, and frauds.
- Constituency: local container for voters and vote counts per candidate.
- Candidate: candidate identity and vote tally; comparable for sorting.
- Voter: voter identity and assistance requirement.
- Vote: simple value object linking voter and candidate identifiers.
- Fraud: records fraud attempts with context for auditing.

## Class-by-class reference

### `App` (CLI controller)
The `App` class is the interactive shell. It prints the menu, reads input, validates
format, and dispatches commands to domain objects. It keeps an in-memory map of all
elections keyed by id and relies on `Scanner` for input parsing.

**Responsibilities**
- Menu rendering and command routing.
- Input parsing and format validation.
- Enforcing the correct election state before actions.
- Printing all user-visible output.

**Function-by-function behavior**
- `run()` displays the menu, reads a numeric command, and routes to the handler.
- `createElection()` parses id + name, rejects duplicates, and stores the election.
- `startElection()` validates existence and starts the voting window.
- `addConstituency()` validates election state and uniqueness, then adds a constituency.
- `removeConstituency()` validates state and existence, then deletes the constituency.
- `addCandidateToElection()` validates national ID and age, then registers the candidate.
- `removeCandidateFromElection()` deletes a candidate by national ID.
- `addVoterToConstituency()` validates input, age, and ID, then registers the voter.
- `listElectionCandidates()` sorts candidates by vote count, then national ID.
- `listConstituencyVoters()` sorts voters by national ID and prints them.
- `vote()` validates election state, voter registration, and candidate; records fraud
  for invalid attempts; records votes otherwise.
- `stopElection()` ends the voting window.
- `createConstituencyVoteReport()` prints per-candidate totals for one constituency.
- `createNationalVoteReport()` aggregates and sorts totals across all constituencies.
- `detailedConstituencyAnalysis()` prints turnout share and top candidate share.
- `detailedNationalAnalysis()` prints regional turnout share and top candidate per region.
- `reportFrauds()` prints fraud attempts using LIFO order.
- `deleteElection()` removes an election and all related data.
- `listElections()` prints the id and name for all stored elections.
- `exitApp()` terminates the loop.

### `Election` (aggregate root)
`Election` owns the full state for a single election: constituencies, candidates,
recorded voters, frauds, and national vote totals.

**Key fields and structures**
- `Map<String, Constituency> constituencies` stores constituencies by name.
- `Map<String, Candidate> candidates` stores candidates by national ID.
- `Set<String> recordedVotes` tracks voter IDs to prevent multiple votes.
- `Stack<Fraud> frauds` stores fraud attempts in LIFO order.
- `Map<String, Integer> globalVotes` aggregates votes per candidate nationwide.

**Behavior**
- Starts/stops the election and exposes current status.
- Adds/removes constituencies and candidates.
- Records votes and fraud attempts.
- Exposes aggregated data for reporting and analysis.

### `Constituency` (local registry)
`Constituency` models one voting area inside a region. It keeps local voters and
tracks local vote totals per candidate.

**Key fields and structures**
- `Map<String, Voter> voters` stores voters by national ID.
- `Map<String, Candidate> candidates` stores candidates that received votes locally.
- `Map<String, Integer> votes` stores vote counts per candidate ID.

**Behavior**
- Registers voters and candidates locally.
- Records votes and returns per-candidate totals.
- Computes the local total vote count.

### `Candidate`
Represents a person running in the election.

**Key fields**
- `nationalId`, `age`, `name`, `voteCount`.

**Behavior**
- `addVote()` increments the candidate’s vote count.
- `compareTo()` enables deterministic ranking by votes, then national ID.

### `Voter`
Represents a registered voter with basic identity and eligibility data.

**Key fields**
- `nationalId`, `age`, `needsAssistance`, `name`.

**Behavior**
- Read-only getters used by CLI output and validation.

### `Vote`
Simple value object that links voter ID to candidate ID.

### `Fraud`
Records a fraud attempt with enough context for reporting.

## Core business rules
- An election must be started before adding constituencies, candidates, or voters.
- Voting is allowed only while the election is active.
- Candidates must be at least 35 years old and have a 13-character national ID.
- Voters must be at least 18 years old and have a 13-character national ID.
- Duplicate national IDs are rejected for both voters and candidates.
- Fraud is recorded when a voter is not registered or tries to vote multiple times.

## Command reference (interactive CLI)
Each command is selected by number. Inputs are space-separated.

| Command | Action | Required input |
| --- | --- | --- |
| 0 | Create election | election_id election_name |
| 1 | Start election | election_id |
| 2 | Add constituency | election_id constituency_name region |
| 3 | Remove constituency | election_id constituency_name |
| 4 | Add candidate | election_id national_id age candidate_name |
| 5 | Remove candidate | election_id national_id |
| 6 | Add voter | election_id constituency_name national_id age needs_assistance name |
| 7 | List election candidates | election_id |
| 8 | List constituency voters | election_id constituency_name |
| 9 | Vote | election_id constituency_name voter_national_id candidate_national_id |
| 10 | Stop election | election_id |
| 11 | Constituency vote report | election_id constituency_name |
| 12 | National vote report | election_id |
| 13 | Detailed constituency analysis | election_id constituency_name |
| 14 | Detailed national analysis | election_id |
| 15 | Report frauds | election_id |
| 16 | Delete election | election_id |
| 17 | List elections | none |
| 18 | Exit | none |

## Reporting and analytics
- Constituency vote report: lists candidates and their vote counts within a constituency.
- National vote report: aggregates votes across all constituencies and sorts results.
- Detailed constituency analysis: turnout share vs national total and top candidate share.
- Detailed national analysis: per-region turnout share and top candidate by region.

## Validation and error handling
- All commands validate input format before operating on data.
- Invalid IDs or missing entities return clear error messages.
- Voting rejects unregistered voters and repeat voters, and logs fraud.

## Fraud handling
- Unregistered voter: the attempt is rejected and recorded.
- Multiple voting: repeated voting attempts are rejected and recorded.
- Fraud cases are stored in a stack and displayed on request after voting ends.

## Data structures and complexity
- `Map` and `Set` provide O(1) average lookups by national ID.
- `List` sorting provides deterministic ranking for reports.
- Fraud reporting is O(k) where k is the number of fraud entries.
- Current report sorting is O(n^2) due to simple nested loops.


## How to run
- `./gradlew run`

## How to test
- `./gradlew test`

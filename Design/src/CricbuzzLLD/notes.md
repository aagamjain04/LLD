# Cricbuzz Low Level Design - Interview Notes

## System Overview
A cricket match simulation system that models T20/ODI matches with complete scoring, player management, and match flow.



## Core Components

### 1. Match Management

**Match.java**
- Orchestrates entire match flow (2 innings)
- Handles toss logic
- Manages innings creation and execution
- Determines winner based on total runs
- Prints scorecards after each inning

**Key Flow:**
```
Toss → Inning 1 (bat first) → Inning 2 (chase) → Determine Winner
```

---

### 2. Match Types (Strategy Pattern)

**MatchType Interface**
```java
- noOfOvers(): int
- maxOverCountBowlers(): int
```

**Implementations:**
- **T20MatchType**: 20 overs, 4 max overs per bowler
- **OneDayMatchType**: 50 overs, 10 max overs per bowler

**Why This Design?**
- Easy to add new formats (Test, The Hundred, etc.)
- Encapsulates format-specific rules
- Makes Match class format-agnostic

---

### 3. Innings Management

**InningDetails.java**
- Manages one complete inning
- Controls over-by-over execution
- Handles batsman selection
- Tracks runs to win (for 2nd innings)
- Swaps striker/non-striker after each over

**Key Logic:**
```java
for each over:
    - Choose bowler
    - Execute over (6 balls)
    - Check win condition
    - Swap batsmen
```

---

### 4. Over Management

**OverDetails.java**
- Executes 6 legal balls
- Handles extra balls (wide, no-ball)
- Manages wicket scenarios
- Checks win conditions after each ball

**Important Details:**
- Only NORMAL balls count toward the 6-ball limit
- Wide/No balls increment `extraBalls` counter
- Calls `chooseNextBatsman()` on wicket

---

### 5. Ball Delivery (Observer Pattern)

**BallDetails.java**
- Simulates individual ball delivery
- Determines ball outcome (runs/wicket)
- Manages striker rotation on odd runs (1, 3)
- **Notifies observers** for score updates

**Observer List:**
1. BattingScoreUpdater
2. BowlingScoreUpdater

**Why Observer Pattern?**
- Decouples ball delivery from score updates
- Easy to add new observers (e.g., commentary, analytics)
- Single source of truth (BallDetails)

---

### 6. Team Management

**Team.java**
- Maintains playing XI queue
- Controls batting order via `PlayerBattingController`
- Controls bowling rotation via `PlayerBowlingController`
- Tracks striker/non-striker
- Calculates total runs
- Prints scorecards

**Controllers:**

**PlayerBattingController:**
- Queue of yet-to-play batsmen
- Manages striker/non-striker pair
- Throws exception when all players are out

**PlayerBowlingController:**
- Deque for bowler rotation
- Tracks overs bowled per bowler
- Enforces max overs limit
- Uses map to store bowler stats

---

### 7. Player & Scoring

**PlayerDetails.java**
- Combines Person + PlayerType
- Maintains BattingScoreCard
- Maintains BowlingScoreCard
- Provides print methods for scorecards

**BattingScoreCard:**
- Total runs, balls played
- Fours, sixes
- Strike rate
- Wicket details (how out)

**BowlingScoreCard:**
- Total overs, runs conceded
- Wickets taken
- No balls, wide balls
- Economy rate

---

### 8. Score Updaters (Observer Implementation)

**BattingScoreUpdater:**
- Updates batsman's runs, balls faced
- Increments fours/sixes
- Records wicket details

**BowlingScoreUpdater:**
- Updates bowler's overs (on 6th ball)
- Adds runs conceded
- Increments wickets taken
- Tracks extras (wide, no-ball)

---

## Design Patterns Used

### 1. **Observer Pattern**
- **Where**: BallDetails → ScoreUpdateObservers
- **Why**: Decouples ball delivery from multiple score updates
- **Benefit**: Easy to add new observers (commentary, statistics)

### 2. **Strategy Pattern**
- **Where**: MatchType interface
- **Why**: Different match formats have different rules
- **Benefit**: Open for extension, closed for modification

### 3. **Controller Pattern**
- **Where**: PlayerBattingController, PlayerBowlingController
- **Why**: Separates team logic from player management
- **Benefit**: Single responsibility, easier testing

---

## Key Enums

**BallType**: NORMAL, WIDEBALL, NOBALL  
**RunType**: ZERO, ONE, TWO, THREE, FOUR, SIX  
**WicketType**: RUNOUT, BOLD, CATCH  
**PlayerType**: BATSMAN, BOWLER, WICKETKEEPER, CAPTAIN, ALLROUNDER

---

## Class Relationships

```
Match
├── Team (x2)
│   ├── PlayerDetails (x11)
│   │   ├── BattingScoreCard
│   │   └── BowlingScoreCard
│   ├── PlayerBattingController
│   └── PlayerBowlingController
├── InningDetails (x2)
│   └── OverDetails (xN)
│       └── BallDetails (x6 per over)
│           └── Wicket (optional)
└── MatchType (Strategy)
```

---

## Time Complexity Notes

- **Ball delivery**: O(1) - constant observers
- **Score calculation**: O(N) - iterate all 11 players
- **Bowler selection**: O(1) - deque operations
- **Batsman selection**: O(1) - queue operations

---

## Memory Considerations

- Stores complete ball-by-ball history
- For T20: 20 overs × 6 balls × 2 innings = 240 ball objects
- For ODI: 50 overs × 6 balls × 2 innings = 600 ball objects
- Could optimize by storing only summary stats

---

## Time Complexity Notes

- **Ball delivery**: O(1) - constant observers
- **Score calculation**: O(N) - iterate all 11 players
- **Bowler selection**: O(1) - deque operations
- **Batsman selection**: O(1) - queue operations

## Memory Considerations

- Stores complete ball-by-ball history
- For T20: 20 overs × 6 balls × 2 innings = 240 ball objects
- For ODI: 50 overs × 6 balls × 2 innings = 600 ball objects
- Could optimize by storing only summary stats

---

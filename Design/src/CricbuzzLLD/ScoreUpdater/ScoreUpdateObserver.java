package CricbuzzLLD.ScoreUpdater;

import CricbuzzLLD.Innings.BallDetails;

public interface ScoreUpdateObserver {

    public void update(BallDetails ballDetails);
}

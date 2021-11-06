
package server;

public class Turn {
    private int playerId;
    private int turnResult;
    
    public Turn(int playerId, int turnResult){
        this.playerId = playerId;
        this.turnResult = turnResult;
    }
    
    // getters and setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getTurnResult() {
        return turnResult;
    }

    public void setTurnResult(int turnResult) {
        this.turnResult = turnResult;
    }
    
}

package by.bsu.zenko.model;

/**
 * Created by 123 on 05.10.2016.
 */
public class Player {
    private int playerId;
    private String name;
    private int clubId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("ID: "+playerId+"\n");
        tmp.append("NAME: "+name+"\n");
        tmp.append("ID_CLUB: "+clubId+"\n");
        return tmp.toString();
    }
}

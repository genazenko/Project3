package by.bsu.zenko.model;

/**
 * Created by 123 on 05.10.2016.
 */
public class FootballClub {
    private int clubId;
    private String name;
    private String country;

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("ID_CLUB: "+clubId+"\n");
        str.append("NAME: "+name+"\n");
        str.append("COUNTRY: "+country+"\n");
        return str.toString();
    }
}

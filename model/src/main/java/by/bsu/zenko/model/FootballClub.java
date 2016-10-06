package by.bsu.zenko.model;

/**
 * Created by 123 on 05.10.2016.
 */
public class FootballClub {
    private int id_Club;
    private String name;
    private String country;

    public int getId_Club() {
        return id_Club;
    }

    public void setId_Club(int id_Club) {
        this.id_Club = id_Club;
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
        str.append("ID_CLUB: "+id_Club+"\n");
        str.append("NAME: "+name+"\n");
        str.append("COUNTRY: "+country+"\n");
        return str.toString();
    }
}

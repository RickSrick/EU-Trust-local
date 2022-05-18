package Creation.Data;

public class Country {

    private String name;
    private String countryID;
    private String flagURL;

    public Country (String n, String c, String f) {
        name = n;
        countryID = c;
        flagURL = f;
    }

    public String getCountryID() { return countryID; }
    public String getName() { return name; }
    public String getFlagURL() { return flagURL; }
}

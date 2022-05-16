package Creation.Data;

public class Country {

    private String name;
    private String countryId;
    private String flagURL;

    public Country (String n, String c, String f) {
        name = n;
        countryId = c;
        flagURL = f;
    }

    public String getCountryId() { return countryId; }
    public String getName() { return name; }
    public String getFlagURL() { return flagURL; }
}

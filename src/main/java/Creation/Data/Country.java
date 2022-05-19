package Creation.Data;

public class Country {

    private String countryName;
    private String countryID;
    private String flagLink;

    public Country(String _name, String _ID, String _flagLink) {

        countryName = _name;
        countryID = _ID;
        flagLink = _flagLink;

    }

    public String getName() { return countryName; }

    public String getID() { return countryID; }

    public String getFlagLink() { return flagLink; }
    
}

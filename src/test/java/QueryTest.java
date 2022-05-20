import Creation.BadResponseException;
import Query.Query;
import java.util.Vector;

public class QueryTest {

    public static void main(String[] args) throws BadResponseException {

        Query query = new Query();

        String[] countries1 = {"IT"};
        String[] providers1 = {"Actalis S.p.A."};
        String[] types1 = {};
        String[] statuses1 = {"granted"};

        executeRequest(query, countries1 , providers1, types1, statuses1);

    }

    public static void executeRequest(Query _query, String[] _countries, String[] _providers, String[] _types, String[] _statuses) throws BadResponseException {

        System.out.println("-------------------------------");
        System.out.println("------ STARTING REQUESTS ------");
        System.out.println("-------------------------------");

        for (String country : _countries)
            _query.editFilterParameter(Query.CRITERIA_FILTERS[0], country);

        for (String s : _providers)
            _query.editFilterParameter(Query.CRITERIA_FILTERS[1], s);

        for (String s : _types)
            _query.editFilterParameter(Query.CRITERIA_FILTERS[2], s);

        for (String s : _statuses)
            _query.editFilterParameter(Query.CRITERIA_FILTERS[3], s);

        System.out.println("SEARCH CRITERIA:\n"+_query.getCriteria()+"\n");

        Vector<String> types = _query.getValidServiceTypes();
        Vector<String> statuses = _query.getValidServiceStatuses();
        Vector<String> providers = _query.getValidProviders();
        Vector<String> services = _query.getValidServices();

        System.out.println("\n-------- RESULTS: --------");

        System.out.println("-- SERVICE TYPES: --");
        for (String type : types)
            System.out.println(type);

        System.out.println("-- SERVICE STATUSES: --");
        for (String status : statuses)
            System.out.println(status);

        System.out.println("-- PROVIDERS: --");
        for (String provider : providers)
            System.out.println(provider);

        System.out.println("-- SERVICES: --");
        for (String service : services)
            System.out.println(service);


        System.out.println("-------------------------------");
        System.out.println("------- ENDING REQUESTS -------");
        System.out.println("-------------------------------");

    }
}

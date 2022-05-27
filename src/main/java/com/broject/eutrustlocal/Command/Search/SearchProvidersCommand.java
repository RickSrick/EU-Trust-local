package com.broject.eutrustlocal.Command.Search;
import com.broject.eutrustlocal.Command.Command;
import com.broject.eutrustlocal.Query.Query;

public class SearchProvidersCommand extends VirtualSearch implements Command {

    public SearchProvidersCommand(Query mainQuery){
        super(mainQuery);
    }

    @Override
    public void execute() {

    }
}

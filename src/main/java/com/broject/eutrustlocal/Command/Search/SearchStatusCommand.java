package com.broject.eutrustlocal.Command.Search;
import com.broject.eutrustlocal.Command.Command;
import com.broject.eutrustlocal.Query.Query;

public class SearchStatusCommand extends VirtualSearch implements Command {

    public SearchStatusCommand(Query mainQuery) {
        super(mainQuery);
    }

    @Override
    public void execute() {

    }
}

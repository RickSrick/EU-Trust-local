package com.broject.eutrustlocal.Command.ConcreteCommand.Search;
import com.broject.eutrustlocal.Command.ConcreteCommand.Command;
import com.broject.eutrustlocal.Query.Query;

public class SearchStatusCommand extends VirtualSearch implements Command {

    public SearchStatusCommand(Query mainQuery) {
        super(mainQuery);
    }

    @Override
    public void execute() {

    }
}

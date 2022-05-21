package com.broject.eutrustlocal.Command.ConcreteCommand;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import java.util.Vector;

public class ViewCountriesCommand implements Command {

    private DataArchive archive;

    public ViewCountriesCommand() {}

    @Override
    public void execute() {

        try {
            archive = DataArchive.newDataArchive();
            Vector<Country> response = archive.getCountries();
        } catch (BadResponseException e) {
            //open error page
        }

    }
}

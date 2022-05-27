package com.broject.eutrustlocal.Command;

import com.broject.eutrustlocal.Creation.BadResponseException;

public interface Command {

    public void execute() throws BadResponseException;
}

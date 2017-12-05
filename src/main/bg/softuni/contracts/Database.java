package main.bg.softuni.contracts;

import java.io.IOException;

public interface Database extends FilteredTaker, OrderedTaker, Requester{

    void loadData(String fileName) throws IOException;
    public void unloadData();
}

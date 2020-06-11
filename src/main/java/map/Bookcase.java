package map;

import engine.Parser;

import java.util.ArrayList;

public class Bookcase extends RoomContainer {

    {
        //GameProgress.
    }

    @Override
    public void close(ArrayList item) {
        //Can't close a bookcase.
        return;
    }
}

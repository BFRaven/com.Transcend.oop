package com.Transcend.bo.interfaces;



// NOTES: ANYTHING THAT IS A LOCATION CAN HAVE THESE QUESTIONS ANSWERED.

// THIS IS AN EXAMPLE OF ABSTRACTION

public interface ILocation {

//    one can put constants, but do not leave without storing a value.

    public int numberOfWorkspaces();

    public boolean canHaveMeetings();

    public String getLocationName();

    public boolean hasCoffee();
}

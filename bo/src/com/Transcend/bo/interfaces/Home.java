package com.Transcend.bo.interfaces;

import com.Transcend.bo.Employee;

public class Home implements ILocation {


    private String Address;

    private Employee Owner;




    // region GETTERS / SETTERS

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public Employee getOwner() {
        return this.Owner;
    }

    public void setOwner(Employee owner) {
        this.Owner = owner;
    }


    //endregion

// ctrl + i and it gives you the framework for the interface
    @Override
    public int numberOfWorkspaces() {
        return 1;
    }

    @Override
    public boolean canHaveMeetings() {
        return false;
    }

    @Override
    public String getLocationName() {
        return this.Owner.getFirstName() + " 's Home";
    }

    @Override
    public boolean hasCoffee() {
        return true;
    }
}

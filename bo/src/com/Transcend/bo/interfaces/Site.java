package com.Transcend.bo.interfaces;

public class Site implements ILocation{

    private int ConferenceRooms;

    private int Offices;

    private int Cubicles;

    private int TrainingDesks;

    private int CoffeeMachines;

    private String SiteName;




    // region GETTERS/ SETTERS

    public String getSitename() {
        return this.SiteName;
    }

    public void setSitename(String sitename) {
        this.SiteName = sitename;
    }

    public int getConferenceRooms() {
        return this.ConferenceRooms;
    }

    public void setConferenceRooms(int conferenceRooms) {
        this.ConferenceRooms = conferenceRooms;
    }

    public int getOffices() {
        return this.Offices;
    }

    public void setOffices(int offices) {
        this.Offices = offices;
    }

    public int getCubicles() {
        return this.Cubicles;
    }

    public void setCubicles(int cubicles) {
        this.Cubicles = cubicles;
    }

    public int getTrainingDesks() {
        return this.TrainingDesks;
    }

    public void setTrainingDesks(int trainingDesks) {
        this.TrainingDesks = trainingDesks;
    }

    public int getCoffeeMachines() {
        return this.CoffeeMachines;
    }

    public void setCoffeeMachines(int coffeeMachines) {
        this.CoffeeMachines = coffeeMachines;
    }


    // endregion

    @Override
    public int numberOfWorkspaces(){
        return (this.Cubicles + this.Offices + this.TrainingDesks);
    }

    @Override
    public boolean canHaveMeetings() {
        if(this.ConferenceRooms > 0)
            return true;
        else
            return false;
    }


    @Override
    public String getLocationName() {
        return this.SiteName;
    }

    @Override
    public boolean hasCoffee() {
        if(CoffeeMachines > 0)
            return true;
        else
            return false;
    }



}

package com.transcend.dao;

import com.Transcend.bo.Person;

import java.util.List;

// NOTES: CREATING THE METHOD SIGNATURES FOR ALL THE CRUD OPERATIONS

public interface PersonDAO {

    // NOTES: GET METHODS
//    These methods are analogous to our getPerson stored procedures in
//    mySQL workbench

     Person getPersonById(int personId);
     List<Person> getPersonList();



    //NOTES: EXECUTE METHODS

//    Looks very familiar to the SQL stored procedure labeled ExecPerson
//    accept a person object and return an ID of newly created person
    int insertPerson(Person person);
//    updates a person object with all values, return true or false if successful
     boolean updatePerson(Person person);
//    deletes a person in the person object by their unique id.
    boolean deletePerson(int personId);

}

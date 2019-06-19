package com.Transcend.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static common.helpers.StringHelper.isNullOfEmpty;

//the extends method concept is called inheritance
//every class we create is going to inherit BaseBO
//Base BO is the super class and Person is the subclass
public class Person extends BaseBO implements Serializable {

    // region PROPERTIES

//    PersonID
    private int PersonID;
//    Title
    private String Title;
//    First Name
    private String FirstName;

//    Middle Name
    private String MiddleName;

//    Last Name
    private String LastName;
//    DisplayFirstName
    private String DisplayName;
//    Gender
    private String Gender;

//    Birthdate
    private Date BirthDate;

//    SSN; by marking a property transient, it will not be serialized.
    private transient String SSN;

    // List email objects
    private List<Email> Emails;


// to deserialize an object stored in a text file, you will need to include this unique UID
    private static final long serialVersionUID = 54622233600l;


    // endregion



    // region CONSTRUCTORS

    public Person() {
        this.setEmails(new ArrayList<>());
    }




    // endregion



    // region GETTERS / SETTERS

    public void setPersonID(int personID) {
        this.PersonID = personID;
    }

    public int getPersonID() {
        return this.PersonID;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getFirstName(){
        return this.FirstName;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getLastName() {
       return this.LastName;
    }

    public void setDisplayName(String displayFirstName) {
        this.DisplayName = displayFirstName;
    }

    public String getDisplayName(){
        return this.DisplayName;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public String getGender() {
        return this.Gender;
    }

    public List<Email> getEmails() {
        return this.Emails;
    }

    public void setEmails(List<Email> emails) {
        this.Emails = emails;
    }

    public String getMiddleName() {
        return this.MiddleName;
    }

    public void setMiddleName(String middleName) {
        this.MiddleName = middleName;
    }

    public Date getBirthDate() {
        return this.BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.BirthDate = birthDate;
    }

    public String getSSN() {
        return this.SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }




    // endregion


//   region CUSTOM METHODS this will become an extension method to other subclasses


//    instance method, we can call other methods to help us as well!
    public String GetFullName() {
      if(isNullOfEmpty(this.FirstName) && isNullOfEmpty(this.LastName)) {
          return "No name is set";
      } else {

          if(isNullOfEmpty(this.FirstName))
              return this.LastName;
          else if (isNullOfEmpty(this.LastName))
              return this.FirstName;
          else if (isNullOfEmpty(this.MiddleName))
              return  this.FirstName + " " + this.LastName;
          else
              return this.FirstName + " " + this.MiddleName + " " + this.LastName;

      }

    }


    public String ToString() {
        return "PersonId=" + this.PersonID + " Full Name=" + this.GetFullName() + " Date of Birth=" + this.BirthDate + " SSN=" + this.SSN;
    }


    // endregion




}

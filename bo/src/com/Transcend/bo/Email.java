package com.Transcend.bo;

public class Email extends BaseBO {


    //    region PROPERTIES

    private int EmailId;
    private String EmailAddress;
    // Created custom properties
    private EntityType EmailType;



//    endregion





//    region CONSTRUCTORS

    public Email() {
        this.EmailType = new EntityType();
    }

    public Email(String emailAddress) {
        this.EmailType = new EntityType();
        this.EmailAddress = emailAddress;
    }






//    endregion







    //    region GETTERS / SETTERS

    public void setEmailId(int emailId) {
        this.EmailId = emailId;
    }

    public int getEmailId() {
        return this.EmailId;
    }


    public void setEmailAddress(String emailAddress) {
        this.EmailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return this.EmailAddress;
    }

    public void setEmailType(EntityType emailType){
        EmailType = emailType;
    }

    public EntityType getEmailType() {
        return EmailType;
    }

//    endregion
















}

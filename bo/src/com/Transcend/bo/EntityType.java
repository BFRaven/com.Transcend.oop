package com.Transcend.bo;

public class EntityType extends BaseBO {

//    region PROPERTIES

    private int EntityTypeId;
    private String EntityTypeName;




//    endregion

 //    region CONSTRUCTORS

    public EntityType() {}

    public EntityType(String entityTypeName) {
        this.EntityTypeName = entityTypeName;
    }






//    endregion


//    region GETTERS / SETTERS

    public void setEntityTypeId(int entityTypeId) {
        this.EntityTypeId = entityTypeId;
    }

    public int getEntityTypeId() {
        return this.EntityTypeId;
    }


    public void setEntityTypeName(String entityTypeName) {
        this.EntityTypeName = entityTypeName;
    }

    public String getEntityTypeName() {
        return this.EntityTypeName;
    }


//    endregion

}
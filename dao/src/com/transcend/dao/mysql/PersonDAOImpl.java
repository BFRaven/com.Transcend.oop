package com.transcend.dao.mysql;

import com.Transcend.bo.Person;
import com.transcend.dao.PersonDAO;
import common.helpers.DateHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl extends MySQL implements PersonDAO {

    @Override
    public Person getPersonById(int personId) {
        Connect();
        Person person = null; // no instantiation; if no records are returned, we don't want to use memory.
        try {
            String sp = "{call GetPerson(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, personId);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {

                person = HydrateObj(rs);
            }

        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return person;
    }

    @Override
//    to get a list of people from the DB
    public List<Person> getPersonList() {

        // connecting to the DB
        Connect();
//        create that list or 'container' that is going to hold the individual person objects
        List<Person> personList = new ArrayList<Person>();
        try {
//            setting up the stored procedure call
            String sp = "{call GetPerson(?, ?)}";
            CallableStatement cStmt = connection.prepareCall(sp);


//            changing query ID to 20, to expect back the info that the procedure is programmed to do.
            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2, 1);
            ResultSet rs = cStmt.executeQuery();


//            as long as there is a record in the result set....
            while(rs.next()) {


//                we are going to add this person object to the list, and we will continue
//                to run this loop until we run out of records. The person obj, is actually represented
//                by the HydrateObj method.
                personList.add(HydrateObj(rs));
            }

        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return personList;
    }

    @Override
    public int insertPerson(Person person) {
        Connect();
        int id = 0;

        try {
//        call ExecPerson(10, personId, firstName, middleName, lastName, birthDate, SSN);
            String sp = "{call ExecPerson(?,?,?,?,?,?,?)}";

//            this line represents the store procedure that is in mySQL workbench.
            CallableStatement cStmt = connection.prepareCall(sp);

//            the callable statements and parameters needed to insert a new person. Hydrating procedure.
            cStmt.setInt(1, INSERT);
            cStmt.setInt(2,0);
            cStmt.setString(3, person.getFirstName());
            cStmt.setString(4, person.getMiddleName());
            cStmt.setString(5, person.getLastName());
            cStmt.setDate(6, DateHelper.utilDateToSqlDate(person.getBirthDate()));
            cStmt.setString(7, person.getSSN());

           ResultSet rs = cStmt.executeQuery();

           if(rs.next()) {
               id = rs.getInt(1);
           }



        } catch (SQLException sqlEx) {
            logger.error(sqlEx);

        }

        return id;
    }

    @Override
    public boolean updatePerson(Person person) {
        Connect();
        int id = 0;

        try {
//        call ExecPerson(10, personId, firstName, middleName, lastName, birthDate, SSN);
            String sp = "{call ExecPerson(?,?,?,?,?,?,?)}";

//            this line represents the store procedure that is in mySQL workbench.
            CallableStatement cStmt = connection.prepareCall(sp);

//            the callable statements and parameters needed to insert a new person. Hydrating procedure.
            cStmt.setInt(1, UPDATE);  // from INSERT to UPDATE
            cStmt.setInt(2,person.getPersonID());  // from INSERT to UPDATE
            cStmt.setString(3, person.getFirstName());
            cStmt.setString(4, person.getMiddleName());
            cStmt.setString(5, person.getLastName());
            cStmt.setDate(6, DateHelper.utilDateToSqlDate(person.getBirthDate()));
            cStmt.setString(7, person.getSSN());

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                id = rs.getInt(1);
            }



        } catch (SQLException sqlEx) {
            logger.error(sqlEx);

        }

        return id > 0;
    }

    @Override
    public boolean deletePerson(int personId) {
        Connect();
        int id = 0;

        try {
//        call ExecPerson(10, personId, firstName, middleName, lastName, birthDate, SSN);
            String sp = "{call ExecPerson(?,?,?,?,?,?,?)}";

//            this line represents the store procedure that is in mySQL workbench.
            CallableStatement cStmt = connection.prepareCall(sp);

//            the callable statements and parameters needed to insert a new person. Hydrating procedure.
            cStmt.setInt(1, DELETE);  // from INSERT to UPDATE
            cStmt.setInt(2,personId);  // from INSERT to UPDATE
            cStmt.setString(3, "");
            cStmt.setString(4, "");
            cStmt.setString(5, "");
            cStmt.setDate(6, new java.sql.Date(0));
            cStmt.setString(7, "");

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                id = rs.getInt(1);
            }



        } catch (SQLException sqlEx) {
            logger.error(sqlEx);

        }

        return id > 0;    }

    private static Person HydrateObj(ResultSet rs) throws SQLException {

          /*
                	a.PersonId,                 index 1      INT
					a.FirstName,                index 2      VARCHAR
					a.MiddleName,               index 3      VARCHAR
					a.LastName,                 index 4      VARCHAR
					a.BirthDate,                index 5      DATETIME
					a.SocialSecurityNumber      index 6      VARCHAR
                 */

        // we create a person object by hydrating the data...
        // notes (BUZZWORD): HYDRATING DATA; we are pouring the information we get from DB into one of our JAVA objects.

        Person person = new Person();

        person.setPersonID(rs.getInt(1));
        person.setFirstName(rs.getString(2));
        person.setMiddleName(rs.getString(3));
        person.setLastName(rs.getString(4));
        person.setBirthDate(rs.getDate(5));
        person.setSSN(rs.getString(6));

        return person;

    }

}

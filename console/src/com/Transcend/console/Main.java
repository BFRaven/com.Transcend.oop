package com.Transcend.console;

//import to custom class that we created in another module.
import com.Transcend.bo.*;
import com.Transcend.bo.interfaces.Home;
import com.Transcend.bo.interfaces.ILocation;
import com.Transcend.bo.interfaces.Site;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.transcend.dao.PersonDAO;
import com.transcend.dao.mysql.MySQL;
import com.transcend.dao.mysql.PersonDAOImpl;
import common.helpers.DateHelper;
import javafx.beans.binding.ObjectExpression;
import org.apache.log4j.Logger;

import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.Date;

// classes are blueprints for whatever you want to do.
public class Main {

    final static Logger logger = Logger.getLogger(MySQL.class);

    public static void main(String[] args) {

//        NOTES: PRIVATE STATIC METHOD FOR THE LESSONS

//        this would loop through the current directory marked by the "."
//        LessonRecursionComplex(new File("."));
        LessonRecursionComplex(new File("C:\\Users\\belka\\DevCurriculum"));

    }

    private static void LessonRecursionComplex(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {

                if (file.isDirectory()) {
                    // notes: recursion happening below
                    logger.info("directory: " + file.getCanonicalPath());
                    LessonRecursionComplex(file);
                } else {
                    logger.info("     file: " + file.getCanonicalPath());
                }
            }

        } catch (IOException ioEx) {
            logger.info(ioEx);
        }
    }

    private static void LessonRecursion(int recursionCount) {
//        NOTES: A function or a method that calls itself, over and over again until certain criteria is met

        logger.info("Recursive count = " + recursionCount);
//        you always want to wrap any type of recursion into tight logic, like in an in if else statement.
        if(recursionCount > 0)
            LessonRecursion(recursionCount - 1);
    }

//      BUZZWORD
    private static void Deserialization() {
//        need a place to put the person
        Person person = null;

        try {
            FileInputStream fileIn = new FileInputStream("./ser_person.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
//        Cast as the data type, or object that we are expecting.
            person = (Person) in.readObject();
//        closing the streams
            fileIn.close();

        } catch (FileNotFoundException fileEx) {
            logger.error(fileEx);
        } catch (IOException ioEx) {
            logger.info(ioEx);
        } catch (ClassNotFoundException clzEx) {
            logger.info(clzEx);
        }

        logger.info("De-serialized Object " + person.ToString());
    }


//       BUZZWORD
    private static void LessonSerialization() {
//        convert an obj to a series of bytes storing in into a text file, read the file back in and deserialize it back to an object

        // notes: get an object from db

        PersonDAO personDAO = new PersonDAOImpl();
        Person person = personDAO.getPersonById(1);

        //notes: serialize to a text file, wrap in a try catch

        try {
//            creating object and...
            FileOutputStream fileOut = new FileOutputStream("./ser_person.txt");
//            chaining the object.
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            passing the person we are collecting from the database.
            out.writeObject(person);
            out.close();
            fileOut.close();
            logger.info("Object serialized and written to file: ./ser_person.txt");
            logger.info("Serialized Object: " + person.ToString());


        } catch (IOException ioEx) {
            logger.info(ioEx);
        }
    }


    private static void LessonBoxUnboxCast() {

//        notes: BUZZWORDS good to know for interview questions.
//        notes: BOXING
        int x = 10;
        Object o = x;
        LessonReflectionandGenerics(o.getClass());

//        notes: UNBOXING and putting the parenthesis with the type e.g (int) is casting, 'explicit'; we told it what to cast to
        int y = (int) o;
        logger.info(y);

//        another example of explicit casting.
        double db = 1.92;
        int in = (int) db;
        logger.info(in);

//        notes: implicit casting; you can't implicitly go from a double to an int,
//        but you can go from an int to a double, that's implicit casting.

        int i = 100;
        double d = i;

    }

    private static <T> void LessonReflectionandGenerics(Class<T> genericClass) {


        logger.info("Full Name: " + genericClass.getName());
        logger.info("Simple Name: " + genericClass.getSimpleName());
        for(Field field : genericClass.getDeclaredFields()) {
            logger.info("Field: " + field.getName() + " -Type: " + field.getType());

        }
        for(Method method : genericClass.getDeclaredMethods()) {
            logger.info("Method: " + method.getName());
        }
    }



    private static void LessonDAODelete() {
        PersonDAO personDAO = new PersonDAOImpl();

        if (personDAO.deletePerson(9))
            logger.info("Person Deleted Successfully.");
        else
            logger.info("Person Delete failed!");
    }

    private static void LessonDAOUpdate() {

        PersonDAO personDAO = new PersonDAOImpl();

        Person person = personDAO.getPersonById(9);
        person.setMiddleName("UPDATED!!!");

        if(personDAO.updatePerson(person))
            logger.info("Person updated successfully");

         else
             logger.info("Person Update Failed!");
    }




    private static void LessonDAOInsert() {

        Person person = new Person();

        person.setFirstName("Tony");
        person.setMiddleName("junior");
        person.setLastName("Stark");
        person.setBirthDate(new Date());
        person.setSSN("xxx-xx-xxxx");

        PersonDAO personDAO = new PersonDAOImpl();
        int id = personDAO.insertPerson(person);

        logger.info("New Person Record Inserted. ID = " + id);
    }


    private static void LessonDAO() {
        //region CREATE MENU
        // dynamic info fetched from DB, nothing hard coded.

        PersonDAO personDAO = new PersonDAOImpl();
        List<Person> personList = personDAO.getPersonList();

        System.out.println("===================================");

        for(Person person : personList) {
            System.out.println(person.getPersonID() + ") " + person.getLastName() + " " + person.getFirstName());
        }

        System.out.println("===================================");


        //endregion

        //region PROMPT USER
        Scanner reader = new Scanner(System.in);
        System.out.println("Please select a Person from list: ");
        String personID = reader.nextLine();


        //endregion

        //region GET PERSON DETAILS
        Person personDetail = personDAO.getPersonById(Integer.parseInt(personID));

        System.out.println("------ PERSON DETAILS -------");
        System.out.println("FULL NAME: " + personDetail.GetFullName());
        System.out.println("DOB: " + personDetail.getBirthDate());
        System.out.println("SSN: " + personDetail.getSSN());


        //endregion

    }


    private static void LessonStoreProcedure() {
//        calling a stored procedure from mySQL, using the jdbc java library.
        Connection conn = LessonDBConnection();

        try {
//            the ? marks are placeholders for the parameters required from the stored
//            procedures e.g if the procedure needs 5 params, you would use 5 ? marks
            String sp = "{call GetPerson(?,?)}";
            CallableStatement cStat = conn.prepareCall(sp);

//            this is the rep of mySQL 'call GetPerson (10, 5)' where 10 if the first param and
//            5 is the second param.
            cStat.setInt(1, 20);
            cStat.setInt(2, 0);
            ResultSet rs = cStat.executeQuery();

            while(rs.next()) {

                /*
                	a.PersonId,    index 1      INT
					a.FirstName,   index 2      VARCHAR
					a.MiddleName,  index 3      VARCHAR
					a.LastName,    index 4      VARCHAR
					a.BirthDate,   index 5      DATETIME
					a.SocialSecurityNumber    index 6      VARCHAR
                 */
                System.out.println(rs.getInt(1)+ ": " + rs.getString(2)+ " | | " + " Last Name: " + rs.getString(4));
            }


        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

    }


    private static void LessonExecuteQuery() {
        Connection conn = LessonDBConnection();

        try {
            Statement statement = conn.createStatement();

            String sql = "select PersonId, FirstName, LastName from person";

            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int personId = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);

                System.out.println("ID: " + personId  + " - First Name: " + firstName + " - Last Name: " + lastName);
            }
                conn.close();

        } catch (SQLException sqlEx) {
            System.out.println("sqlEx");
        }
    }



    private static Connection LessonDBConnection() {
        String dbHost = "localhost";
        String dbName = "hr";
        String dbUser = "root";
        String dbPass = "root";
        String useSSL = "false";
        String procBod = "true";


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("My SQL Driver not found!" + ex);
            return null;
        }

        System.out.println("My SQL Driver Registered");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" + dbName + "?useSSL=" + useSSL + "&noAccessToProcedureBodies=" + procBod, dbUser, dbPass);
        } catch (SQLException ex) {
            System.out.println("Connection failed!" + ex);
            return null;
        }

        if(connection != null) {
            System.out.println("Successfully connected to MySQL database");
            return connection;
        } else {
        System.out.println("Connection failed!");
        return null;
        }
    }


    private static void LessonLogging() {


    }



    private static void LessonLists() {
        // collections/lists
        // this requires a java util import
        List<String> theStringColl = new ArrayList<String>();

        // add is a method of array lists to add strings into the lists
        theStringColl.add("1st string");
        theStringColl.add("2nd string");
        theStringColl.add("3 string");
        theStringColl.add("4 string");
        theStringColl.add("5 string");

        // This way gives you back the strings inside an array format
        System.out.println(theStringColl);

         for(String singleString : theStringColl) {
//         this way prints it back as individual strings
             System.out.println(singleString);
         }

        // same thing methods tried with integers
        List<Integer> theIntColl = new ArrayList<Integer>();

        theIntColl.add(1);
        theIntColl.add(2);
        theIntColl.add(3);
        theIntColl.add(4);
        theIntColl.add(5);

        System.out.println(theIntColl);

        for(Integer sInteger : theIntColl) {
            System.out.println(sInteger);
        }


        // arrays, not the most efficient way to portray data

        // need to tell the compiler how many spaces you need
        String[] theStringArr = new String[5];

        theStringArr[0] = "1st";
        theStringArr[1] = "2nd";
        theStringArr[2] = "3rd";
        theStringArr[3] = "4rd";
        theStringArr[4] = "5rd";

        System.out.println(theStringArr);

        for(String singleString: theStringArr) {
            System.out.println(singleString);
        }



    }


    private static void LessonInterfaceTest() {
        Site MN010 = new Site();
        MN010.setSitename("MN010");
        MN010.setCoffeeMachines(2);
        MN010.setConferenceRooms(1);
        MN010.setCubicles(8);
        MN010.setOffices(6);
        MN010.setTrainingDesks(36);

        Home KarenHouse = new Home();
        KarenHouse.setAddress("2535 Clinton Ave S");
        KarenHouse.setOwner(new Employee("Karen", "Beltran"));

        LessonInterfaces(MN010);
        LessonInterfaces(KarenHouse);
    }

    private static void LessonInterfaces(ILocation ilocation) {
        System.out.println("--------------------------------------");
        System.out.println("Location Name: " + ilocation.getLocationName());
        System.out.println("Can Have Meetings: " + ilocation.canHaveMeetings());
        System.out.println("Number of Workspaces: " + ilocation.numberOfWorkspaces());
        System.out.println("Has Coffee: " + ilocation.hasCoffee());


    }







    private static void LessonValRef() {

//        only primitive types are value types, everything is a reference to the primitives.

        // NOTES: REFERENCE TYPE
        Employee firstEmp = new Employee();

        firstEmp.setFirstName("Karen");

//        this is a reference in memory to the first instance of Employee, meaning they are the same objects in memory; you are not
//        actually replacing or making a whole new employee object.
        Employee secondEmp = firstEmp;

        firstEmp.setFirstName("Brenda");
        secondEmp.setFirstName("Adrian");

        System.out.println(firstEmp.getFirstName());
        System.out.println(secondEmp.getFirstName());


        //NOTES: VALUE TYPES
        int firstInt = 10;

//        actually crating a new variable and setting it equal to the memory in the firstInt value and that stays in memory
//        is not affected by another reassigning of the name of the previous value.
        int secondInt = firstInt;

         firstInt = 20;

        System.out.println(firstInt);
        System.out.println(secondInt);


    }





    private static void LessonHash(){
//        COMMON INTERVIEW QUESTIONS

//        NOTES: key-value pairs / value list
//        TODO: HASHTABLE
        /*

        1) does NOT allow null for either key or value
         2) synchronized, thread safe, but performance is decreased
         */

        System.out.println("---------HASH TABLE---------");

        Hashtable<Integer, String> firstHashTable = new Hashtable<>();

        firstHashTable.put(1, "Inheritance");
        firstHashTable.put(2, "Encapsulation");
        firstHashTable.put(3, "Polymorphism");
        firstHashTable.put(4, "Abstraction");

        System.out.println("Value from given key: " + firstHashTable.get(3));

//        To print out all of the values in the hash table use the for method
//        it is going to print out from the most recently added to the least.
//        does not allow value for 'null', it will throw an nullPointerException (NFE).

        for(Integer key: firstHashTable.keySet()) {
            System.out.println("Key: " + key + " - value: " + firstHashTable.get(key));
        }

        System.out.println("----------------------------");
        System.out.println("----------------------------");


//        TODO: HASHMAP

        /* 1) DOES allow null for either key or value
           2) un-synchronized, not thread safe, better performance
         */

        System.out.println("---------HASH MAP---------");

        HashMap<Integer, String> firstHashMap = new HashMap<>();
//        using the same put() method as the HashTable
        firstHashMap.put(1, "Inheritance");
        firstHashMap.put(2, "Encapsulation");
        firstHashMap.put(3, "Polymorphism");
        firstHashMap.put(4, "Abstraction");
        firstHashMap.put(5, null );

//        the way to access the Hash Map is the same as the Hash Table

        System.out.println("Value from given key: " + firstHashMap.get(1));

        // To print out all of the values in the hash table use the for method
//        it is going to print out from the first added to the last added.

        for(Integer key: firstHashMap.keySet()) {
            System.out.println("Key: " + key + " - value: " + firstHashMap.get(key));
        }


        System.out.println("----------------------------");

        System.out.println("----------------------------");


//        TODO: HASHSET

        /* 1) built in mechanism for duplicates
           2) used for where you want to maintain a unique list
           DOES NOT HAVE A KEY AND WILL NOT LET YOU ADD MULTIPLE VALUES.
         */

        System.out.println("---------HASH SET---------");

        HashSet<String> oopPrincip = new HashSet<>();

//        use add method, similar to array list, to add a value

        oopPrincip.add("inheritance");
        oopPrincip.add("encapsulation");
        oopPrincip.add("abstraction");
        oopPrincip.add("abstraction");
        oopPrincip.add("polymorphism");
//        does allow you to add null as a value
        oopPrincip.add(null);

//        use the contains() method to see if a value already exists in the HashSet.

       if(oopPrincip.contains("abstraction"))
           System.out.println("Value Exists");
       else
           System.out.println("Value does not exist");

        for(String s : oopPrincip) {
            System.out.println(s);
        }
        System.out.println("----------------------------");
        System.out.println("----------------------------");



    }





//    Lesson on Polymorphism = many forms one name

    private static void LessonPolymorphism() {

        // NOTES: compile time polymorphism.
        // overloaded - create methods of the same name but the method parameters or signature are different.

        // NOTES: run-time polymorphism.
        // override - override a method from a base-class or superclass in the sub or child class.
//      // in previous methods you had to specify with @override, but latest versions of java, it is implied.
//        BOTH TYPES OF POLYMORPHISM ARE COMMON IN INTERVIEW QUESTIONS.

    }







    private static void LessonComplexClassProp() {


//        NOTES: when to use inheritance (ANSWER ? : "IS A?")
//               when to use complex(nested) objects (ANSWER ? : "HAS A?")

        EntityType emailWorkType = new EntityType("Work");
        Email myEmail = new Email("karen@karen.com");
        myEmail.setEmailType(emailWorkType);

        System.out.println(myEmail.getEmailAddress() + " Type: " + myEmail.getEmailType().getEntityTypeName());


        Employee myEmployee = new Employee();

        myEmployee.getEmails().add(new Email("test@test.com"));
        myEmployee.getEmails().add(new Email("karen@test.com"));
        myEmployee.getEmails().add(new Email("brenda@test.com"));

        for(Email email : myEmployee.getEmails()) {
            System.out.println((email.getEmailAddress()));

        }






    }




    //   Lesson on Collections
    private static void LessonCollections() {
//        NOTES: List<t>  = generic type "T";

//        new list of employees
        List<Employee> employeeList = new ArrayList<Employee>();

        Employee emp1 = new Employee("Karen", "Beltran");
        Employee emp2 = new Employee("Brenda", "Beltran");
        Employee emp3 = new Employee("Gregg", "Ball");
        Employee emp4 = new Employee("Cody", "Rienstra");

//        adding our employees to the list
        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);
        employeeList.add(emp4);

        employeeList.add(new Employee("Adrian","R"));

        System.out.println(employeeList.get(0).GetFullName());
        System.out.println(employeeList.get(1).GetFullName());
        System.out.println(employeeList.get(2).GetFullName());
        System.out.println(employeeList.get(3).GetFullName());
        System.out.println(employeeList.get(4).GetFullName());

//        Doing the same thing, except using a for method, iterating over the object

        for(Employee e : employeeList) {
            System.out.println(e.GetFullName());
        }





    }





//    An in depth lesson on methods
//    this top line is a method signature / declaration
    private static void LessonConstructors() {

        /* <access modifier> <instance/static> <return data type> <method name> (<data type> <param name>....)


        {  body }
        */

        Employee constructorEmployee = new Employee("Karen", "Beltran");
        System.out.println(constructorEmployee.getFirstName() + " " +  constructorEmployee.getLastName());

        Employee const2Employee = new Employee("Lopez");
        System.out.println(const2Employee.getLastName());


        Employee employeeBrenda = new Employee("Brenda", "Beltran");
        System.out.println(employeeBrenda.GetFullName());



    }

    private static void LessonInheritance() {
//      Creating an instance of our employee class.

        Employee employeeKar = new Employee();

        employeeKar.setFirstName("Karen");
        employeeKar.setLastName("Beltran");
        employeeKar.setId(1);
        System.out.print(employeeKar.getFirstName());
        System.out.print("  ");
        System.out.print(employeeKar.getLastName());
        System.out.print("  ");
        System.out.print(employeeKar.getId());


    }

    private static void LessonClassObjects() {
//        NOTES: instantiating a new object
//        the new key word creates a constructor, allocating memory for this class
//        giving it the name myFirstPerson.
        Person myFirstPerson = new Person();

//        After creating a new instance of a class, we can have access to its attributes
        myFirstPerson.setFirstName("Karen");
        myFirstPerson.setLastName("Beltran");
        myFirstPerson.setTitle("Developer");
        myFirstPerson.setGender("Female");

        System.out.print(myFirstPerson.getFirstName());
        System.out.print(" ");
        System.out.print(myFirstPerson.getLastName());
        System.out.print(" ");
        System.out.print(myFirstPerson.getTitle());
        System.out.print(" ");
        System.out.print(myFirstPerson.getGender());
        System.out.print(" ");

//        NOTES: Setting value for super/inherited class
        myFirstPerson.setId(1);

        System.out.print(myFirstPerson.getId());

    }
}

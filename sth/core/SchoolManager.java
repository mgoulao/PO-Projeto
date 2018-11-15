package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchProjectIdException;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import sth.core.Teacher;
import sth.core.Student;
import sth.core.Employee;
import sth.core.exception.NoSuchPersonIdException;

/**
 * The façade class.
 */
public class SchoolManager implements java.io.Serializable {

  private static final long serialVersionUID = 201811061121L;

  private School _school;
  private String _ficheiroAssociado = "";
  private Person _user;

  /**
   * @param datafile
   * @throws ImportFileException
   * @throws IOException
   * @throws BadEntryException
   */
  public void importFile(String datafile) throws ImportFileException, IOException, BadEntryException {
    _school = new School();
    _school.importFile(datafile);
  }

  /**
   * @param filename
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void doSave(String filename) throws IOException, FileNotFoundException {
    if (_ficheiroAssociado.isEmpty())
      _ficheiroAssociado = filename;
    ObjectOutputStream save = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(_ficheiroAssociado)));
    save.writeObject(_school);
    save.close();
  }

  /**
   * @param filename
   * @throws IOException
   * @throws ClassNotFoundException
   * @throws FileNotFoundException
   */
  public void doLoad(String filename) throws IOException, ClassNotFoundException, FileNotFoundException {
    ObjectInputStream load = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
    _school = (School) load.readObject();
    load.close();
    _ficheiroAssociado = filename;
  }

  /**
   * @return associated object file name
   */
  public String getFile() {
    return _ficheiroAssociado;
  }

  /**
   * @return School
   */

  public School getSchool() {
    return _school;
  }

  /**
   * Do the login of the user with the given identifier.
   * 
   * @param id identifier of the user to login
   * @throws NoSuchPersonIdException if there is no uers with the given identifier
   */
  public void login(int id) throws NoSuchPersonIdException {
    Person temp = _school.getPerson(id);
    if (temp == null)
      throw new NoSuchPersonIdException(id);
    else
      _user = temp;

  }

  /**
   * @return true when the currently logged in person is an administrative
   */
  public boolean isLoggedUserAdministrative() {
    return _user instanceof Employee;
  }

  /**
   * @return true when the currently logged in person is a professor
   */
  public boolean isLoggedUserProfessor() {
    return _user instanceof Teacher;
  }

  /**
   * @return true when the currently logged in person is a student
   */
  public boolean isLoggedUserStudent() {
    return _user instanceof Student;
  }

  /**
   * @return true when the currently logged in person is a representative
   */
  public boolean isLoggedUserRepresentative() {
    if (_user instanceof Student) {
      Student student = (Student) _user;
      return student.isRepresentative();
    }
    return false;
  }

  /**
   * Tries to find the user id in the object file.
   * 
   * @param id identifier of the user to login
   * @throws NoSuchPersonIdException if there is no uers with the given identifier
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void findPersonIdInFile(String filename)
      throws NoSuchPersonIdException, FileNotFoundException, IOException, ClassNotFoundException {
    ObjectInputStream load = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
    School school = (School) load.readObject();
    Person person = school.getPerson(_user.getID());
    load.close();
    if (person == null)
      throw new NoSuchPersonIdException(_user.getID());
  }

  /**
   * @return Person string
   */
  public String showPerson() {
    return _user.toString();
  }

  /**
   * @return String with all School users
   */
  public String showAllPersons() {
    List<Person> users = _school.getAllUsers();
    Collections.sort(users, new Comparator<Person>() {
      @Override
      public int compare(Person a, Person b) {
        if (a.getID() < b.getID())
          return -1;
        else if (a.getID() == b.getID())
          return 0;
        return 1;
      }
    });
    String ret = "";

    for (Person person : users) {
      ret += person.toString();
    }
    return ret;
  }

  /**
   * @return Person String with the new phone number
   */
  public String changePhoneNumber(int phoneNumber) {
    _user.setPhoneNumber(phoneNumber);
    return _user.toString();
  }

  /**
   * @param name
   * @return String with all users that have the name
   */
  public String searchPerson(String name) {
    List<Person> users = _school.searchPerson(name);
    Collections.sort(users, new Comparator<Person>() {
      @Override
      public int compare(Person a, Person b) {
        return a.getName().compareTo(b.getName());
      }
    });
    String ret = "";
    for (Person person : users) {
      ret += person.toString();
    }
    return ret;
  }

  /**
   * @param disciplineName
   * @param projectName
   * @return true if the project was created
   * @throws NoSuchDisciplineIdException
   */
  public boolean createProject(String disciplineName, String projectName) throws NoSuchDisciplineIdException {
    Discipline discipline = null;
    Teacher teacher = (Teacher) _user;
    Project project = null;

    discipline = teacher.getDisciplines().get(disciplineName);

    if (discipline == null)
      throw new NoSuchDisciplineIdException(disciplineName);

    if(discipline.getProjects().get(projectName) != null)
      return false;

    discipline.addProject(new Project(projectName));
    return true;
  }

  /**
   * @param disciplineName
   * @param projectName
   */
  public void closeProject(String disciplineName, String projectName)
      throws NoSuchDisciplineIdException, NoSuchProjectIdException {
    Teacher teacher = (Teacher) _user;
    Discipline discipline = null;
    Project project = null;

    discipline = teacher.getDisciplines().get(disciplineName);

    if (discipline == null)
      throw new NoSuchDisciplineIdException(disciplineName);

    project = discipline.getProjects().get(projectName);

    if (project == null)
      throw new NoSuchProjectIdException(projectName);

    project.close();
  }

  /**
   * @param disciplineName
   * @return string with all enrolled students
   */
  public String showDisciplineStudents(String disciplineName) throws NoSuchDisciplineIdException {
    Teacher teacher = (Teacher) _user;
    Discipline discipline = teacher.getDiscipline(disciplineName);
    String res = "";
    if (discipline == null) {
      throw new NoSuchDisciplineIdException(disciplineName);
    }

    for (Student student : discipline.getStudents()) {
      res += student.toString();
    }
    return res;
  }
}

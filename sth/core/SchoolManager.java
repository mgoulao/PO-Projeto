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

import sth.core.Teacher;
import sth.core.Student;
import sth.core.Employee;
import sth.core.exception.NoSuchPersonIdException;

//FIXME import other classes if needed

/**
 * The façade class.
 */
public class SchoolManager implements java.io.Serializable {

  private static final long serialVersionUID = 201811061121L;

  private School _school;
  private String _ficheiroAssociado = "";
  private Person _user;

  // FIXME add object attributes if needed

  // FIXME implement constructors if needed

  /**
   * @param datafile
   * @throws ImportFileException
   * @throws InvalidCourseSelectionException
   */
  public void importFile(String datafile) throws IOException, BadEntryException, ImportFileException {
    try {
      _school = new School();
      _school.importFile(datafile);
    } catch (IOException | BadEntryException e) {
      throw new ImportFileException(e);
    }
  }

  public void doSave(String filename) throws IOException {
    if (_ficheiroAssociado.equals(""))
      _ficheiroAssociado = filename; // associa ficheiro se nao existir
    try {
      ObjectOutputStream save = new ObjectOutputStream(
          new BufferedOutputStream(new FileOutputStream(_ficheiroAssociado)));
      save.writeObject(_school);
      save.close();
    }
    // catch (ImportFileException e ) {throw new
    // ImportFileException(_ficheiroAssociado);}
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void doLoad(String filename) throws IOException, ClassNotFoundException {
    try {
      ObjectInputStream load = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
      _school = (School) load.readObject();
      load.close();
      _ficheiroAssociado = filename;
    }
    // catch (ImportFileException e) { throw new ImportFileException(filename);}
    catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public String getFile() {
    return _ficheiroAssociado;
  }

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
    _user = _school.getPerson(id);
    if (_user == null) {
      throw new NoSuchPersonIdException(id);
    }

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

  // FIXME implement other methods (in general, one for each command in sth-app)

  public String showPerson(int id) throws NoSuchPersonIdException {
    Person person = _school.getPerson(id);
    if (person == null) {
      throw new NoSuchPersonIdException(id);
    }
    return _school.getPerson(id).printPerson();
  }

  public String showAllPersons() {
    TreeSet<Person> users = _school.getAllUsers();
    String ret = "";

    for (Person person : users) {
      ret += person.printPerson();
    }
    return ret;
  }

  public String changePhoneNumber(int phoneNumber) {
    _user.setPhoneNumber(phoneNumber);
    return _user.printPerson();
  }

  public String searchPerson(String name) {
    TreeSet<Person> users = _school.searchPerson(name);
    String ret = "";
    for (Person person : users) {
      ret += person.printPerson();
    }
    return ret;
  }

  public boolean createProject(String disciplineName, String projectName) throws NoSuchDisciplineIdException {
    Discipline discipline = null;
    Project project = null;

    for (Course course : _school.getCourses()) {
      for (Discipline disc : course.getDisciplines()) {
        if (disc.getName().equals(disciplineName)) {
          discipline = disc;
        }
      }
    }

    if (discipline == null)
      throw new NoSuchDisciplineIdException(disciplineName);

    for (Project proj : discipline.getProjects()) {
      if (proj.getName().equals(projectName))
        return false;
    }

    discipline.addProject(new Project(projectName));
    return true;
  }

  public void closeProject(String disciplineName, String projectName)
      throws NoSuchDisciplineIdException, NoSuchProjectIdException {
    Discipline discipline = null;
    Project project = null;

    for (Course course : _school.getCourses()) {
      for (Discipline disc : course.getDisciplines()) {
        if (disc.getName().equals(disciplineName)) {
          discipline = disc;
        }
      }
    }

    if (discipline == null)
      throw new NoSuchDisciplineIdException(disciplineName);

    for (Project proj : discipline.getProjects()) {
      if (proj.getName().equals(projectName)) {
        project = proj;
      }
    }

    if (project == null)
      throw new NoSuchProjectIdException(projectName);

    project.close();
  }

  public String showDisciplineStudents(String disciplineName) throws NoSuchDisciplineIdException {
    Teacher teacher = (Teacher) _user;
    Discipline discipline = teacher.getDiscipline(disciplineName);
    String res = "";
    if(discipline == null) {
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    for(Student student : discipline.getStudents()) {
      res += student.printPerson();
    }
    return res;
  }
}

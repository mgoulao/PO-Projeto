package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;
import java.io.IOException;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import sth.core.Teacher;
import sth.core.Student;
import sth.core.Employee;


//FIXME import other classes if needed

/**
 * The façade class.
 */
public class SchoolManager implements java.io.Serializable{

  private static final long serialVersionUID = 201811061121L; 

	private School _school;

  //FIXME add object attributes if needed

  //FIXME implement constructors if needed
  
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

  public void doSave(String filename) throws IOException{
    _school.save(filename);
  }

  public void doLoad(String filename) throws IOException, ClassNotFoundException{
    _school.load(filename);
  }

  public String getFile(){
    return _school.getFicheiroAssociado();
  }

  public School getSchool(){
    return _school;
  }
  /**
   * Do the login of the user with the given identifier.

   * @param id identifier of the user to login
   * @throws NoSuchPersonIdException if there is no uers with the given identifier
   */
  public void login(int id) throws NoSuchPersonIdException {
  }

  /**
   * @return true when the currently logged in person is an administrative
   */
  public boolean isLoggedUserAdministrative() {
    return false;
  }

  /**
   * @return true when the currently logged in person is a professor
   */
  public boolean isLoggedUserProfessor() {
    return false;
  }

  /**
   * @return true when the currently logged in person is a student
   */
  public boolean isLoggedUserStudent() {
    return false;
  }

  /**
   * @return true when the currently logged in person is a representative
   */
  public boolean isLoggedUserRepresentative() {
    return false;
  }

  //FIXME implement other methods (in general, one for each command in sth-app)
}

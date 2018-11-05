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
public class SchoolManager {

	private School _school;

  //FIXME add object attributes if needed

  private TreeMap<Integer,Student>  _students = new TreeMap<Integer,Student>();
  private TreeMap<Integer,Teacher>  _teachers = new TreeMap<Integer,Teacher>();
  private TreeMap<Integer,Employee>  _employees = new TreeMap<Integer,Employee>();
  private Student _logInStudent;
  private Teacher _logInTeacher;
  private Employee _logInEmplyee;
  private String _type;

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

  /**
   * Do the login of the user with the given identifier.

   * @param id identifier of the user to login
   * @throws NoSuchPersonIdException if there is no uers with the given identifier
   */
  public void login(int id) throws NoSuchPersonIdException {
    if(id<=100000) throw new NoSuchPersonIdException(id);
    if(_students.containsKey(id)){
      _logInStudent = _students.get(id);
      _type = "Student";
    }
    if(_teachers.containsKey(id)){
      _logInTeacher = _teachers.get(id);
      _type = "Teacher";
    }
    if(_employees.containsKey(id)){
      _logInEmplyee = _employees.get(id);
      _type = "Employee";
    }
  }

  /**
   * @return true when the currently logged in person is an administrative
   */
  public boolean isLoggedUserAdministrative() {
    //FIXME implement predicate
    return _type == "Employee";
  }

  /**
   * @return true when the currently logged in person is a professor
   */
  public boolean isLoggedUserProfessor() {
    //FIXME implement predicate
    return _type == "Teacher";
  }

  /**
   * @return true when the currently logged in person is a student
   */
  public boolean isLoggedUserStudent() {
    //FIXME implement predicate
    return _type == "Student";
  }

  /**
   * @return true when the currently logged in person is a representative
   */
  public boolean isLoggedUserRepresentative() {
    if(_type == "Student"){
      return _logInStudent.getRepresentative();
    }
    return false;
  }

  //FIXME implement other methods (in general, one for each command in sth-app)
  
  public void addStudent(boolean representative, int id, int phoneNumber, String name, Course course, ArrayList<Discipline> disciplines){
    Student s = new Student(course, representative, name,  id, phoneNumber, disciplines);
    _students.put(id, s);
    course.addStudent(s);

    for(Discipline d : disciplines){
      if(course.getDisciplines().contains(d) == false){
        course.addDiscipline(d);
      }
      d.addStudent(s);

    }
  }

  public void addTeacher(int id, int phoneNumber, String name, TreeMap<Course,ArrayList<Discipline>> courseAndDisciplines){
    Teacher t = new Teacher( name, id, phoneNumber, courseAndDisciplines);

    _teachers.put(id, t);

    for(Map.Entry<Course,ArrayList<Discipline>> c : courseAndDisciplines.entrySet()){
      c.getKey().addDiscipline(c.getValue());
      ArrayList<Discipline> withoutDuplicates = new ArrayList<>(new HashSet<>(c.getKey().getDisciplines()));
      c.getKey().setDisciplines(withoutDuplicates);
    }
  }

  public void addEmployee(int id, int phoneNumber, String name){
    _employees.put(id, new Employee(id, phoneNumber, name));
  }
}

package sth.core;

//FIXME import other classes if needed

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sth.core.exception.ImportFileException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  // FIXME define object fields (attributes and, possibly, associations)
  private ArrayList<Course> _courses = new ArrayList<Course>();
  private HashMap<Integer, Person> _users = new HashMap<>();

  // FIXME implement constructors if needed

  /**
   * @param filename
   * @throws BadEntryException
   * @throws IOException
   */
  void importFile(String filename) throws IOException, BadEntryException, ImportFileException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

  public Person getPerson(int personId) {
    return _users.get(personId);
  }

  public TreeSet<Person> getAllUsers() {
    TreeSet<Person> users = new TreeSet<>();
    for (Map.Entry<Integer, Person> entry : _users.entrySet()) {
      users.add(entry.getValue());
    }

    return users;
  }

  String printAllUsers() {
    TreeSet<Person> users = getAllUsers();
    String ret = "";

    for(Person person : users) {
      ret += person.printPerson();
    }
     return ret;
  }

  void addPerson(Person p) {
    _users.put(p.getID(), p);
  }

  void addCourse(Course course) {
    _courses.add(course);
  }

  Course parseCourse(String name) {
    Course course = null;

    for (Course c : _courses) {
      if (name.equals(c.getName())) {
        course = c;
        break;
      }
    }

    if (course == null) {
      course = new Course(name);
      addCourse(course);
    }

    return course;
  }
}

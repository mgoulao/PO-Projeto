package sth.core;

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
import java.util.List;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private String _name;
  private Map<String, Course> _courses;
  private Map<Integer, Person> _users;

  School() {
    _name = "UBI";
    _courses = new HashMap<>();
    _users = new HashMap<>();
  }

  /**
   * @param filename
   * @throws BadEntryException
   * @throws IOException
   */
  void importFile(String filename) throws IOException, BadEntryException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

  /**
   * @param personId
   * @return Person
   */
  Person getPerson(int personId) {
    return _users.get(personId);
  }

  /**
   * @return List with all users
   */
  List<Person> getAllUsers() {
    List<Person> users = new ArrayList<>();
    for (Map.Entry<Integer, Person> entry : _users.entrySet()) {
      users.add(entry.getValue());
    }

    return users;
  }

  /**
   * @param name
   * @return List with all users with the name
   */
  List<Person> searchPerson(String name) {
    List<Person> users = new ArrayList<>();
    for (Map.Entry<Integer, Person> entry : _users.entrySet()) {
      Person person = entry.getValue();
      if (person.getName().contains(name))
        users.add(person);
    }
    return users;
  }

  /**
   * @param p
   */
  void addPerson(Person p) {
    _users.put(p.getID(), p);
  }

  /**
   * @param course
   */
  void addCourse(Course course) {
    _courses.put(course.getName(), course);
  }

  /**
   * @return Map with all courses
   */
  Map<String, Course> getCourses() {
    return _courses;
  }

  /**
   * @param name
   * @return Course with the unique name
   */
  Course parseCourse(String name) {
    Course course = _courses.get(name);

    if (course == null) {
      course = new Course(name);
      addCourse(course);
    }

    return course;
  }
}

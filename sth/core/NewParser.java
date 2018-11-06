package sth.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import sth.core.exception.ImportFileException;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import java.lang.String;

import sth.core.Discipline;
import sth.core.Course;

public class NewParser{
	
	private SchoolManager _schoolManager = new SchoolManager();

	public SchoolManager parseFile(String fileName) throws ImportFileException {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      ArrayList<String> text = new ArrayList<String>();
      String person;
      while((line = reader.readLine()) != null){
        text.add(line);
      }


      for(int i = 0; i < text.size(); i++){
        if(text.get(i).startsWith("FUNCIONÁRIO")){
          person = text.get(i);
          parseLine(person);
          person = null;
        }
        if(text.get(i).startsWith("DOCENTE")){
          person = text.get(i);
          i++;
          while(text.get(i).startsWith("#")){
            person += text.get(i);
            i++;
          }
          parseLine(person);
          i--;
          person = null;
          continue;
        }
        if(text.get(i).startsWith("DELEGADO")){
          person = text.get(i);
          i++;
          while(text.get(i).startsWith("#")){
            person += text.get(i);
            i++;
          }
          parseLine(person);
          i--;
          person = null;
          continue;
        }
        if(text.get(i).startsWith("ALUNO")){
          person = text.get(i);
          i++;
          while(text.get(i).startsWith("#")){

            person += text.get(i);
            i++;
          }
          parseLine(person);
          i--;
          person = null;
          continue;
        }
      }

    } catch (IOException ioe) {
     throw new ImportFileException(ioe);
   }

   return _schoolManager;
 }

 private void parseLine(String line) throws ImportFileException {
   String[] components = line.split("[\\|#]");
   switch (components[0]) {
    case "ALUNO":
    parseStudent(components);
    break;

    case "DOCENTE":
    parseTeacher(components);
    break;

    case "FUNCIONÁRIO":
    parseEmployee(components);
    break;

    case "DELEGADO":
    parseStudent(components);
    break;

    default:
    throw new ImportFileException("dei erro no parserLine " + components[0]);
  }
}

private void parseEmployee(String[] components) throws ImportFileException{

  int id = Integer.parseInt(components[1]);
  int phoneNumber = Integer.parseInt(components[2]);
  String name = components[3];

  if(components.length != 4)
   throw new ImportFileException("invalid number of arguments in employee line: " + components.length);
 _schoolManager.addEmployee(id, phoneNumber, name);
}

private void parseStudent(String[] components){
  ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
  boolean representative = false;

  int id = Integer.parseInt(components[1]);
  int phoneNumber = Integer.parseInt(components[2]);
  String name = components[3];

  if(components[0] == "DELEGADO"){
   representative = true;
 }

 Course course = new Course(components[4]);

 for (int i =5; i < components.length; i+= 2){
   Discipline d = new Discipline(components[i]);
   disciplines.add(d);
  }

_schoolManager.addStudent(representative, id, phoneNumber, name, course, disciplines);
}


private void parseTeacher(String[] components){
 TreeMap<Course,ArrayList<Discipline>>  courseAndDisciplines = new TreeMap<Course,ArrayList<Discipline>>();

 int id = Integer.parseInt(components[1]);
 int phoneNumber = Integer.parseInt(components[2]);
 String name = components[3];
 for (int i = 5; i < components.length; i+=2){
   Course c = new Course(components[i-1]);

   if(!courseAndDisciplines.containsKey(c)){
    courseAndDisciplines.put(c, new ArrayList<Discipline>());
    }
    Discipline d = new Discipline(components[i]);
    courseAndDisciplines.get(c).add(d);
  }

_schoolManager.addTeacher(id,phoneNumber, name, courseAndDisciplines);
}

}
package sth.core;

import java.util.Collections;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import sth.core.Discipline;
import sth.core.Student;

public class Course implements Comparable<Course>, java.io.Serializable{
	
	private String _name;
	private ArrayList<Discipline> _disciplines = new ArrayList<Discipline>();
	private ArrayList<Integer> _representatives = new ArrayList<Integer>();
	private ArrayList<Student> _students = new ArrayList<Student>();

	public Course(String name){
		_name = name;
	}

	public String getCourseName(){
		return _name;
	}

	public ArrayList<Discipline> getDisciplines(){
		return _disciplines;
	}

	public void setDisciplines(ArrayList<Discipline> disciplines){
		_disciplines = disciplines;
	}

	public void parseDiscipline(String name){
		Discipline d = new Discipline(name);
		if(!_disciplines.contains(d)){
			_disciplines.add(d);
		}
	}

	public void addRepresentative(int id){
		if(_representatives.size() < 6 && !_representatives.contains(id)){
			_representatives.add((_representatives.size()+1), id);
		}
	}

	public void removeRepresentative(Integer id){
		if(_representatives.contains(id)){
			_representatives.remove(id);
		}
	}

	public void addStudent(Student a){
		_students.add(a);
	}

	@Override
	public int compareTo(Course other) {
		return this._name.compareTo(other.getCourseName());	
	}
}
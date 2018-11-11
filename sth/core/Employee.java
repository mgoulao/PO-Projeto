package sth.core;

public class Employee extends Person implements Comparable<Employee> , java.io.Serializable{
	
	public Employee(int iD, int phoneNumber, String name){
		super(iD, phoneNumber, name);
	}

	@Override
	String printPerson(){
		return("FUNCIONÁRIO | " + super.getID() + " " + super.getPhoneNumber() + " " + super.getName()); 
	}

	@Override
	public int compareTo(Employee other) {
		int a=this.getID();
		int b=other.getID();
		int cmp = a > b ? +1 : a < b ? -1 : 0;
		return cmp;
	}
}

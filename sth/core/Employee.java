package sth.core;

public class Employee extends Person implements java.io.Serializable{
	
	public Employee(int iD, int phoneNumber, String name){
		super(iD, phoneNumber, name);
	}

	@Override
	String printPerson(){
		return("FUNCIONÁRIO|" + super.getID() + "|" + super.getPhoneNumber() + "|" + super.getName() + "\n"); 
	}

	
}

package sth.core;

public abstract class Person{
	
	private String _name;
	private int _iD;
	private int _phoneNumber;
	private String _type;

	public Person(int iD, int phoneNumber, String name){
		this._name = name;
		this._iD= iD;
		this._phoneNumber = phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber){
		_phoneNumber = phoneNumber;
	}

	public String getName(){
		return _name;
	}

	public int getID(){
		return _iD;
	}

	public int getPhoneNumber(){
		return _phoneNumber;
	}

	public void setID(int id){
		_iD = id;
	}

	public void setName(String name){
		_name = name;
	}
}
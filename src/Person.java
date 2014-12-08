import java.util.Iterator;
import java.util.LinkedList;

public class Person {

	String name;
	String school; // null --> no school
	LinkedList<Person> friends;
	
	public Person(String name){
		this.name = name;
		this.school = null;
		friends = new LinkedList<Person>();
	}
	
	public Person(String name, String school){
		this.name = name;
		this.school = school;
		friends = new LinkedList<Person>();
	}
	
	public void addFriend(Person p){
		friends.add(p);
	}
	
	public String toString(){
		String s = "";
		s += name;
		if(school == null)
			s += "|n";
		else
			s += "|y|" + school;
		return s;
	}
	
	public String friendships(){
		String s = "";
		Iterator<Person> it = friends.iterator();
		while(it.hasNext()){
			s += name + "|" + it.next().name;
			if(it.hasNext())
				s += "\n";
		}
		return s;
	}
}

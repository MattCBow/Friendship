import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	static HashMap<String, Integer> indices; // maps names to indices
	static ArrayList<Person> graph; // all people (people store a list of friends)
	
	public Graph(){
		indices = new HashMap<String, Integer>();
		graph = new ArrayList<Person>();
	}
	
	// Add and get methods (overloaded af)
	public void addPerson(Person p){
		indices.put(p.name, graph.size());
		graph.add(p);
	}
	public void addFriendship(Person a, Person b){
		graph.get(indices.get(a.name)).addFriend(b);
	}
	public void addFriendship(String a, String b){
		graph.get(indices.get(getPerson(a))).addFriend(getPerson(b));
	}
	public void addFriendship(int a, int b){
		graph.get(indices.get(getPerson(a))).addFriend(getPerson(b));
	}
	public Person getPerson(int i){
		return graph.get(i);
	}
	public Person getPerson(String name){
		return graph.get(indices.get(name));
	}
	public int getIndex(String name){
		return indices.get(name);
	}
	public int getIndex(Person p){
		return indices.get(p.name);
	}
	public String getName(int index){
		return graph.get(index).name;
	}
	public String getName(Person p){
		return p.name;
	}
	
	public String toString(){
		String s = graph.size() + "\n";
		for(int i = 0; i < graph.size(); i++){
			s += graph.get(i);
			s += "\n";
		}
		for(int i = 0; i < graph.size(); i++){
			String f = graph.get(i).friendships();
			if(f == "")
				continue;
			s += f;
			s += "\n";
		}
		return s;
	}
}
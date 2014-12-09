import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
	HashMap<String, Integer> indices; // maps names to indices
	ArrayList<Person> graph; // all people (people store a list of friends)
	
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
	
	// finds the shortest (or one of the shortest) introduction chains between
		// two people
	public void chain(String arg1, String arg2) {
		Queue<String> names = new LinkedList<String>();
		HashMap<String, Integer> distance = new HashMap<String, Integer>();
		HashMap<String, String> previous = new HashMap<String, String>();
		names.add(arg1);
		distance.put(arg1, 0);
		boolean found = false;
		while (!names.isEmpty() && !found) {
			String current = names.remove();
			if (current.equals(arg2)) {
				found = true;
				break;
			}
			Iterator<Person> it = graph.get(indices.get(current)).friends
					.iterator();
			while (it.hasNext()) {
				String next = it.next().name;
				// if we haven't visited the vertex do this stuff
				if (!distance.containsKey(next)) {
					distance.put(next, distance.get(current) + 1);
					previous.put(next, current);
					names.add(next);
				}
			}
		}
		if (!found) {
			System.out.println("Failed to find a chain between these people");
			return;
		}
		String result = "";
		String temp = arg2;
		do {
			result = temp + "--" + result;
			temp = previous.get(temp);
		} while (distance.get(temp) != 0);
		System.out.println("The chain is: " + arg1 + "--"
				+ result.substring(0, result.length() - 2));
	}
		// finds all cliques of the given school and prints them in the format of
		// this program's input
		public void cliques(String school) {
			// TODO Auto-generated method stub
			System.out.println("cliques(\"" + school + "\");");
		}

		// finds all people who are connectors and prints their names
		public void connectors() {
			for(Person name:graph)System.out.println(name.name);
			System.out.println("\n\n\n\n");
			HashMap<Person, Integer[]> dfsnums = new HashMap<Person, Integer[]>();
			ArrayList<String> connectors = new ArrayList<String>();
			for(Person p: graph) if(!dfsnums.containsKey(p)) reCon(p, dfsnums, connectors);
			System.out.println(connectors);
		}
		
		private void reCon(Person p, HashMap<Person, Integer[]> dfsnums, ArrayList<String> connectors){
			System.out.println(dfsnums.keySet());
			System.out.println("1."+p.name+" ---- "+p.friends +" -----"+ graph.indexOf(p.friends.get(0)) );
			int count = 1;
			Integer[] cur = {count,count};
			dfsnums.put(p, cur);
			for(Person f : p.friends){
				System.out.println("2."+f.name+" ---- "+f.friends);
				if(dfsnums.get(f)==null){
					reCon(count, f, dfsnums, connectors);
					if(p.friends.indexOf(f)!=0)connectors.add(p.name);
				}
			}
		}
		
		private Integer[] reCon(int count, Person p, HashMap<Person, Integer[]> dfsnums, ArrayList<String> connectors){
			System.out.println(dfsnums.keySet());
			//System.out.println("2."+p.name+" ---- "+p.friends);
			++count;
			Integer[] cur = {count,count};
			dfsnums.put(p, cur);
			for(Person f : p.friends){
				Integer[] dfs = dfsnums.get(f);
				if(dfs==null){
					dfs= reCon(count, f, dfsnums, connectors);
					if(cur[0]<=dfs[1]) connectors.add(p.name);
				}
				if(dfs[1]<cur[1]) cur[1] = dfs[1];
			}
			return cur;
		}
		
		
		
		
		
		
		
	
}
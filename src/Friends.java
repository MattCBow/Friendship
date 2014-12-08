import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Friends {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Travis and Matt's friendship graph analysis program.");
		System.out.print("Please enter the name of your friendship graph file: ");
		String file = scan.nextLine();
		Graph g = buildGraph(file);
		System.out.println("What would you like to do?");
		while (true) {
			System.out
					.println("\"chain [person] [person]\": 	find the shortest intro chain between two people");
			System.out
					.println("\"cliques [school]\": 		display graphs for all cliques from a certain school");
			System.out
					.println("\"connectors\": 			list all people who are connectors");
			System.out.println("\"quit\": 			exit the application");
			String choice = scan.nextLine();
			if (choice.equals("quit"))
				break;
			else
				performOperation(g, choice);
			System.out.println("Is there something else you would like to do?");
		}
		scan.close();
		System.out.println("Have a nice day.");
	}

	private static void performOperation(Graph g, String choice) {
		String command = "";
		String arg1 = "";
		String arg2 = "";
		int index = -1;
		while (index < choice.length() - 1) {
			index++;
			if (choice.charAt(index) == ' ')
				break;
			command += choice.charAt(index);
		}
		while (index < choice.length() - 1) {
			index++;
			if (choice.charAt(index) == ' ')
				break;
			arg1 += choice.charAt(index);
		}
		while (index < choice.length() - 1) {
			index++;
			if (choice.charAt(index) == ' ')
				break;
			arg2 += choice.charAt(index);
		}
		switch (command) {
		case "chain":
			System.out.println();
			System.out.println("Finding the shortest chain between " + arg1
					+ " and " + arg2 + "...");
			chain(arg1, arg2);
			System.out.println();
			break;
		case "cliques":
			String arg = arg1 + " " + arg2;
			if (arg.charAt(arg.length() - 1) == ' ')
				arg = arg.substring(0, arg.length() - 1);
			System.out.println();
			System.out.println("Finding all cliques in " + arg + "...");
			cliques(arg);
			System.out.println();
			break;
		case "connectors":
			System.out.println();
			System.out.println("Finding all connectors...");
			connectors();
			System.out.println();
			break;
		}
	}

	private static Graph buildGraph(String graphFile)
			throws FileNotFoundException {
		Scanner sc = new Scanner(new File(graphFile));
		Graph g = new Graph();
		int n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; i++) {
			g.addPerson(buildPerson(sc.nextLine()));
		}
		while (sc.hasNextLine()) {
			Person[] p = buildFriendship(sc.nextLine());
			g.addFriendship(p[0], p[1]);
		}
		sc.close();
		return g;
	}

	private static Person buildPerson(String s) {
		String name = "";
		String school = "";
		int index = 0;
		while (index < s.length()) {
			if (s.charAt(index) != '|')
				name += s.charAt(index);
			else
				break;
			index++;
		}
		index++;
		if (s.charAt(index) == 'n') {
			return new Person(name);
		}
		index += 2;
		while (index < s.length()) {
			school += s.charAt(index);
			index++;
		}
		return new Person(name, school);
	}

	private static Person[] buildFriendship(String s) {
		Person[] p = new Person[2];
		p[0] = new Person("");
		p[1] = new Person("");
		int index = 0;
		while (index < s.length()) {
			if (s.charAt(index) != '|')
				p[0].name += s.charAt(index);
			else
				break;
			index++;
		}
		index++;
		while (index < s.length()) {
			p[1].name += s.charAt(index);
			index++;
		}
		return p;
	}

	// finds the shortest (or one of the shortest) introduction chains between
	// two people
	private static void chain(String arg1, String arg2) {
		// TODO Auto-generated method stub
		System.out.println("chain(\"" + arg1 + "\", \"" + arg2 + "\");");
	}

	// finds all cliques of the given school and prints them in the format of
	// this program's input
	private static void cliques(String school) {
		// TODO Auto-generated method stub
		System.out.println("cliques(\"" + school + "\");");
	}

	// finds all people who are connectors and prints their names
	private static void connectors() {
		// TODO Auto-generated method stub
		System.out.println("connectors();");
	}
}
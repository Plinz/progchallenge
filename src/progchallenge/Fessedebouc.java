package progchallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Person {
	int index;
	int nbFriends;
	List<Person> friends;

	public Person(int index, int nbFriends) {
		this.index = index;
		this.nbFriends = nbFriends;
		this.friends = new ArrayList<Person>();
	}
}

class PersonComparator implements Comparator<Person> {
	@Override
	public int compare(Person x, Person y) {
		if ((y.nbFriends-y.friends.size()) - (x.nbFriends-x.friends.size()) == 0) {
			return x.index-y.index;
		} else {
			return (y.nbFriends-y.friends.size()) - (x.nbFriends-x.friends.size());
		}
	}
}

class SimplePersonComparator implements Comparator<Person> {
	@Override
	public int compare(Person x, Person y) {
		return x.index-y.index;
	}
}

public class Fessedebouc {

	public static void main(String[] args) {
		Comparator<Person> comparator = new PersonComparator();
		Comparator<Person> simpleComparator = new SimplePersonComparator();

		ArrayList<Person> queue = new ArrayList<Person>();
		ArrayList<Person> list = new ArrayList<Person>();
		Scanner in = new Scanner(System.in);
		int nbPersons = 0;
		boolean error = false;
		if (in.hasNext()) {
			nbPersons = Integer.valueOf(in.nextInt());
		}
		for (int i=0; i<nbPersons; i++){
			if (in.hasNext()) {
				int nbFriends = Integer.valueOf(in.nextInt());
				Person p = new Person(i, nbFriends);
				if (nbFriends!=0)
					queue.add(p);
				list.add(p);
			}
		}

		Collections.sort(queue, comparator);
		while (!queue.isEmpty() && queue.size() != 1){
			Person p = queue.remove(0);
			ArrayList<Person> alreadyFriends = new ArrayList<Person>();
			while(p.friends.size()!=p.nbFriends && !queue.isEmpty()){
				Person tmp = queue.remove(0);
				if (tmp.friends.size() < tmp.nbFriends){
					p.friends.add(tmp);
					tmp.friends.add(p);
					if(tmp.friends.size() < tmp.nbFriends)
						alreadyFriends.add(tmp);
				}
			}
			if (p.friends.size()!=p.nbFriends && queue.isEmpty()){
				error = true;
				break;
			}
			queue.addAll(alreadyFriends);
			Collections.sort(queue, comparator);
		}
		if (!queue.isEmpty() || error){
			System.out.println("non");
		} else {
			System.out.println("oui");
			for (Person p : list){
				Collections.sort(p.friends, simpleComparator);
				for (Person p2 : p.friends){
					System.out.println(p.index+" "+p2.index);
					p2.friends.remove(p);
				}
			}
		}
	}

}

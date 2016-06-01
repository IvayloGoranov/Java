import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class CollectionOfPersons implements PersonCollection {

	private LinkedHashMap<String, Person> personsByEmail;
    private LinkedHashMap<String, TreeSet<Person>> personsByEmailDomain;
    private LinkedHashMap<String, TreeSet<Person>> personsByNameAndTown;
    private TreeMap<Integer, TreeSet<Person>> personsByAge;
    private LinkedHashMap<String, TreeMap<Integer, TreeSet<Person>>> personsByTownAndAge;
	
	public CollectionOfPersons() {

		this.personsByEmail = new LinkedHashMap<String, Person>();
		this.personsByEmailDomain = new LinkedHashMap<String, TreeSet<Person>>();
		this.personsByNameAndTown = new LinkedHashMap<String, TreeSet<Person>>();
		this.personsByAge = new TreeMap<Integer, TreeSet<Person>>();
		this.personsByTownAndAge = new LinkedHashMap<String, TreeMap<Integer, TreeSet<Person>>>();
	}

	@Override
	public boolean addPerson(String email, String name, int age, String town) {

		if (this.findPerson(email) != null) {
			
            return false;
        }

        Person person = new Person(email, name, age, town);
        this.personsByEmail.put(email, person);

        String emailDomain = this.etractEmailDomain(email);
        if (!this.personsByEmailDomain.containsKey(emailDomain)) {
			
        	this.personsByEmailDomain.put(emailDomain, new TreeSet<Person>());
		}
        
        this.personsByEmailDomain.get(emailDomain).add(person);

        String nameAndTown = this.combineNameAndTown(name, town);
        if (!this.personsByNameAndTown.containsKey(nameAndTown)) {
			
        	this.personsByNameAndTown.put(nameAndTown, new TreeSet<Person>());
		}
        
        this.personsByNameAndTown.get(nameAndTown).add(person);

        if (!this.personsByAge.containsKey(age)) {
			
        	this.personsByAge.put(age, new TreeSet<Person>());
		}
        
        this.personsByAge.get(age).add(person);

        if (!this.personsByTownAndAge.containsKey(town)) {
			
        	this.personsByTownAndAge.put(town, new TreeMap<Integer, TreeSet<Person>>());
        	
		}
        
        if (!this.personsByTownAndAge.get(town).containsKey(age)) {
			
        	this.personsByTownAndAge.get(town).put(age, new TreeSet<Person>());
		}
        
        this.personsByTownAndAge.get(town).get(age).add(person);

        return true;
	}

	@Override
	public int size() {

		return this.personsByEmail.size();
	}

	@Override
	public Person findPerson(String email) {

		Person person = this.personsByEmail.getOrDefault(email, null);

        return person;
	}

	@Override
	public boolean deletePerson(String email) {

		Person person = this.findPerson(email);
        if (person == null) {
        	
            return false;
        }

        this.personsByEmail.remove(email);
        
        String emailDomain = this.etractEmailDomain(email);
        this.personsByEmailDomain.get(emailDomain).remove(person);

        String nameAndTown = this.combineNameAndTown(person.getName(), person.getTown());
        this.personsByNameAndTown.get(nameAndTown).remove(person);

        int personAge = person.getAge();
        this.personsByAge.get(personAge).remove(person);

        String town = person.getTown();
        this.personsByTownAndAge.get(town).get(personAge).remove(person);

        return true;
	}

	@Override
	public Iterable<Person> findPersons(String emailDomain) {
		
		TreeSet<Person> personsByEmailDomain = this.personsByEmailDomain.get(emailDomain);

		if (personsByEmailDomain  != null) {
			
			return personsByEmailDomain ;
		} else {
			
			return new TreeSet<Person>();
		}
	}

	@Override
	public Iterable<Person> findPersons(String name, String town) {

		String nameAndTown = this.combineNameAndTown(name, town);
		TreeSet<Person> personsByNameAndTown = this.personsByNameAndTown.get(nameAndTown);
		if (personsByNameAndTown != null) {
			
			return personsByNameAndTown;
		} else {
			
			return new TreeSet<Person>();
		}
	}

	@Override
	public Iterable<Person> findPersons(int startAge, int endAge) {

		NavigableMap<Integer, TreeSet<Person>> personsByAgeRange = 
				this.personsByAge.subMap(Integer.valueOf(startAge), true, Integer.valueOf(endAge), true);
        ArrayList<Person> results = new ArrayList<Person>();
		for (Map.Entry<Integer, TreeSet<Person>> personsByAge : personsByAgeRange.entrySet())
        {
            for (Person person : personsByAge.getValue())
            {
                results.add(person);
            }
        }
		
		return results;
	}

	@Override
	public Iterable<Person> findPersons(int startAge, int endAge, String town) {

		ArrayList<Person> results = new ArrayList<Person>();
		if (!this.personsByTownAndAge.containsKey(town))
        {
            //Returns an empty sequence of persons.
            return results;
        }

		NavigableMap<Integer, TreeSet<Person>> personsByTownInAgeRange = 
				this.personsByTownAndAge.get(town).subMap(Integer.valueOf(startAge), true, Integer.valueOf(endAge), true);
        for (Map.Entry<Integer, TreeSet<Person>> personsByAge : personsByTownInAgeRange.entrySet())
        {
            for (Person person : personsByAge.getValue())
            {
                results.add(person);
            }
        }
        
        return results;
	}

	private String etractEmailDomain(String email) {
		
        int indexOfSeparator = email.indexOf("@");
        int startIndex = indexOfSeparator + 1;
        String emailDomain = email.substring(startIndex);

        return emailDomain;
    }
	
	private String combineNameAndTown(String name, String town) {
		
        final String Separator = "|!|";

        return name + Separator + town;
    }
	
}

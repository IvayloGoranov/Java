import org.apache.commons.lang3.StringUtils;

public class PlayWithPersons {

	public static void main(String[] args) {
		
		CollectionOfPersons persons = new CollectionOfPersons();
        persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
        System.out.println("Added a person. Count = " + persons.size());

        persons.addPerson("pesho@gmail.com", "Pesho2", 222, "Plovdiv222");
        System.out.println("Duplicated person. Count = " + persons.size());

        persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Plovdiv");
        System.out.println("Added a person. Count = " + persons.size());

        persons.addPerson("asen@gmail.com", "Asen", 22, "Sofia");
        System.out.println("Added a person. Count = " + persons.size());

        Person existingPerson = persons.findPerson("pesho@gmail.com");
        System.out.println("Find existing person: " + existingPerson.getEmail());

        Person nonExistingPerson = persons.findPerson("non-existing person");
        System.out.println("Find non-existing person: " + 
            (nonExistingPerson == null ? "null" : "not null"));

        Iterable<Person> personsGmail = persons.findPersons("gmail.com");
        System.out.println("Persons @ GMail:");
        for (Person person : personsGmail) {
			
        	System.out.print(person.getEmail() + ", ");
		}
        
        System.out.println();

        Iterable<Person> personsPeshoPlovdiv = persons.findPersons("Pesho", "Plovdiv");
        System.out.println("Persons 'Pesho' from 'Plovdiv:");
        for (Person person : personsPeshoPlovdiv) {
			
        	System.out.print(person.getEmail() + ", ");
		}
        
        System.out.println();

        Iterable<Person> personsPeshoSofia = persons.findPersons("Pesho", "Sofia");
        System.out.println("Persons 'Pesho' from 'Sofia':");
        for (Person person : personsPeshoSofia) {
			
        	System.out.print(person.getEmail() + ", ");
		}
        
        System.out.println();

        Iterable<Person> personsAge22To28 = persons.findPersons(22, 28);
        System.out.println("Persons of age 22 ... 28:");
        for (Person person : personsAge22To28) {
			
        	System.out.print(person.getEmail() + ", ");
		}
        
        System.out.println();

        Iterable<Person> personsAge22To28Plovdiv = persons.findPersons(22, 28, "Plovdiv");
        System.out.println("Persons of age 22 ... 28 from 'Plovdiv':");
        for (Person person : personsAge22To28Plovdiv) {
			
        	System.out.print(person.getEmail() + ", ");
		}
        
        System.out.println();

        boolean isDeleted = persons.deletePerson("pesho@gmail.com");
        System.out.println("Person 'Pesho' deleted: " + isDeleted);

        Person pesho = persons.findPerson("pesho@gmail.com");
        System.out.println("Find deleted person: " + (pesho == null ? "null" : "not null"));
	}
}

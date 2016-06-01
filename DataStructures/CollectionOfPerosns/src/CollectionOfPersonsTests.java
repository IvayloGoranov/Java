import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class CollectionOfPersonsTests {

	private PersonCollection persons;
	
	@Before
	public void testInitialize() {
	      
		this.persons = new CollectionOfPersons();
	}

	@Test
	public void addPerson_ShouldWorkCorrectly() {

		// Arrange

        // Act
        boolean isAdded =
            this.persons.addPerson("pesho@gmail.com", "Peter", 18, "Sofia");

        // Assert
        assertTrue(isAdded);
        assertEquals(1, this.persons.size());
	}
	
	@Test
	public void addPerson_DuplicatedEmail_ShouldWorkCorrectly() {

		// Arrange

        // Act
        boolean isAddedFirst = this.persons.addPerson("pesho@gmail.com", "Peter", 18, "Sofia");
        boolean isAddedSecond = this.persons.addPerson("pesho@gmail.com", "Maria", 24, "Plovdiv");

        // Assert
        assertTrue(isAddedFirst);
        assertFalse(isAddedSecond);
        assertEquals(1, this.persons.size());
	}
	
	@Test
	public void findPerson_ExistingPerson_ShouldReturnPerson() {

		// Arrange
        this.persons.addPerson("pesho@gmail.com", "Peter", 28, "Plovdiv");

        // Act
        Person person = this.persons.findPerson("pesho@gmail.com");

        // Assert
        assertNotNull(person);
	}
	
	@Test
	public void findPerson_NonExistingPerson_ShouldReturnNothing() {

		// Arrange

        // Act
        Person person = this.persons.findPerson("pesho@gmail.com");

        // Assert
        assertNull(person);
	}
	
	@Test
	public void deletePerson_ShouldWorkCorrectly() {

		// Arrange
        this.persons.addPerson("pesho@gmail.com", "Peter", 28, "Plovdiv");

        // Act
        boolean isDeletedExisting = this.persons.deletePerson("pesho@gmail.com");
        boolean isDeletedNonExisting = this.persons.deletePerson("pesho@gmail.com");

        // Assert
        assertTrue(isDeletedExisting);
        assertFalse(isDeletedNonExisting);
        assertEquals(0, persons.size());
	}
	
	@Test
	public void findPersonsByEmailDomain_ShouldReturnMatchingPersons() {

		// Arrange
        this.persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
        this.persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Sofia");
        this.persons.addPerson("mary@gmail.com", "Maria", 21, "Plovdiv");
        this.persons.addPerson("ani@gmail.com", "Anna", 19, "Bourgas");

        // Act
        Iterable<Person> personsGmail = this.persons.findPersons("gmail.com");
        Iterable<Person> personsYahoo = this.persons.findPersons("yahoo.co.uk");
        Iterable<Person> personsHoo = this.persons.findPersons("hoo.co.uk");

        // Assert
        assertArrayEquals(
            new String[] { "ani@gmail.com", "mary@gmail.com", "pesho@gmail.com" },
            makeCollection(personsGmail).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { "kiro@yahoo.co.uk" },
                makeCollection(personsYahoo).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { },
                makeCollection(personsHoo).stream().map(p -> p.getEmail()).toArray(String[]::new));
	}
	
	@Test
	public void findPersonsByNameAndTown_ShouldReturnMatchingPersons() {

		// Arrange
		this.persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
        this.persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Sofia");
        this.persons.addPerson("pepi@gmail.com", "Pesho", 21, "Plovdiv");
        this.persons.addPerson("ani@gmail.com", "Anna", 19, "Bourgas");
        this.persons.addPerson("pepi2@yahoo.fr", "Pesho", 21, "Plovdiv");

        // Act
        Iterable<Person> personsPeshoPlovdiv = this.persons.findPersons("Pesho", "Plovdiv");
        Iterable<Person> personsLowercase = this.persons.findPersons("pesho", "plovdiv");
        Iterable<Person> personsPeshoNoTown = this.persons.findPersons("Pesho", null);
        Iterable<Person> personsAnnaBourgas = this.persons.findPersons("Anna", "Bourgas");

        // Assert
        assertArrayEquals(
            new String[] { "pepi@gmail.com", "pepi2@yahoo.fr", "pesho@gmail.com" },
            makeCollection(personsPeshoPlovdiv).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { },
                makeCollection(personsLowercase).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { },
                makeCollection(personsPeshoNoTown).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { "ani@gmail.com" },
                makeCollection(personsAnnaBourgas).stream().map(p -> p.getEmail()).toArray(String[]::new));
	}
	
	@Test
	public void findPersonsByAgeRange_ShouldReturnMatchingPersons() {

		// Arrange
		this.persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
        this.persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Sofia");
        this.persons.addPerson("pepi@gmail.com", "Pesho", 21, "Plovdiv");
        this.persons.addPerson("ani@gmail.com", "Anna", 19, "Bourgas");
        this.persons.addPerson("pepi2@yahoo.fr", "Pesho", 21, "Plovdiv");
        this.persons.addPerson("asen@gmail.com", "Asen", 21, "Rousse");

        // Act
        Iterable<Person> personsAgedFrom21to22 = this.persons.findPersons(21, 22);
        Iterable<Person> personsAgedFrom10to11 = this.persons.findPersons(10, 11);
        Iterable<Person> personsAged21 = this.persons.findPersons(21, 21);
        Iterable<Person> personsAged19 = this.persons.findPersons(19, 19);
        Iterable<Person> personsAgedFrom0to1000 = this.persons.findPersons(0, 1000);

        // Assert
        assertArrayEquals(
            new String[] { "pepi@gmail.com", "pepi2@yahoo.fr", "pesho@gmail.com" },
            makeCollection(personsAgedFrom21to22).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { },
                makeCollection(personsAgedFrom10to11).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { "asen@gmail.com", "pepi@gmail.com", "pepi2@yahoo.fr" },
                makeCollection(personsAged21).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { "ani@gmail.com" },
                makeCollection(personsAged19).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { "ani@gmail.com", "asen@gmail.com", "pepi@gmail.com", "pepi2@yahoo.fr", "kiro@yahoo.co.uk", "pesho@gmail.com" },
                makeCollection(personsAgedFrom0to1000).stream().map(p -> p.getEmail()).toArray(String[]::new));
	}
	
	@Test
	public void findPersonsByAgeRangeAndTown_ShouldReturnMatchingPersons() {

		// Arrange
		 	this.persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
	        this.persons.addPerson("kirosofia@yahoo.co.uk", "Kiril", 22, "Sofia");
	        this.persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Plovdiv");
	        this.persons.addPerson("pepi@gmail.com", "Pesho", 21, "Plovdiv");
	        this.persons.addPerson("ani@gmail.com", "Anna", 19, "Bourgas");
	        this.persons.addPerson("ani17@gmail.com", "Anna", 17, "Bourgas");
	        this.persons.addPerson("pepi2@yahoo.fr", "Pesho", 21, "Plovdiv");
	        this.persons.addPerson("asen.rousse@gmail.com", "Asen", 21, "Rousse");
	        this.persons.addPerson("asen@gmail.com", "Asen", 21, "Plovdiv");

        // Act
	        Iterable<Person> personsAgedFrom21to22Plovdiv = this.persons.findPersons(21, 22, "Plovdiv");
	        Iterable<Person> personsAgedFrom10to11Sofia = this.persons.findPersons(10, 11, "Sofia");
	        Iterable<Person> personsAged21Plovdiv = this.persons.findPersons(21, 21, "Plovdiv");
	        Iterable<Person> personsAged19Bourgas = this.persons.findPersons(19, 19, "Bourgas");
	        Iterable<Person> personsAgedFrom0to1000Plovdiv = this.persons.findPersons(0, 1000, "Plovdiv");
	        Iterable<Person> personsAgedFrom0to1000NewYork = this.persons.findPersons(0, 1000, "New York");

        // Assert
        assertArrayEquals(
            new String[] { "asen@gmail.com", "kiro@yahoo.co.uk", "pepi@gmail.com", "pepi2@yahoo.fr" },
            makeCollection(personsAgedFrom21to22Plovdiv).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { },
                makeCollection(personsAgedFrom10to11Sofia).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { "asen@gmail.com", "pepi@gmail.com", "pepi2@yahoo.fr" },
                makeCollection(personsAged21Plovdiv).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { "ani@gmail.com" },
                makeCollection(personsAged19Bourgas).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { "asen@gmail.com", "pepi@gmail.com", "pepi2@yahoo.fr", "kiro@yahoo.co.uk", "pesho@gmail.com" },
                makeCollection(personsAgedFrom0to1000Plovdiv).stream().map(p -> p.getEmail()).toArray(String[]::new));
        assertArrayEquals(
                new String[] { },
                makeCollection(personsAgedFrom0to1000NewYork).stream().map(p -> p.getEmail()).toArray(String[]::new));
	}
	
	private <E> Collection<E> makeCollection(Iterable<E> iter) {
	    
		Collection<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        
	    	list.add(item);
	    }
	    
	    return list;
	}
}

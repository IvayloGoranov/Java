import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CollectionOfPersonsPerformanceTests {

	private PersonCollection persons;
	
	@Before
	public void testInitialize() {
	      
		this.persons = new CollectionOfPersons();
	}
	
	@Test(timeout = 200)
	public void testPerformance_FindPerson() {

		// Arrange
        this.addPersons(5000);

        // Act
        for (int i = 0; i < 100000; i++) {
        	
            Person existingPerson = this.persons.findPerson("pesho1@gmail1.com");
            assertNotNull(existingPerson);
            Person nonExistingPerson = this.persons.findPerson("non-existing email");
            assertNull(nonExistingPerson);
        }
	}

	@Test(timeout = 250)
	public void testPerformance_AddPerson() {

		// Act
        this.addPersons(5000);
        assertEquals(5000, this.persons.size());
	}
	
	@Test(timeout = 300)
	public void testPerformance_FindPersonsByEmailDomain() {

		// Arrange
        this.addPersons(5000);
        List<Person> target = new ArrayList<Person>();
        
        // Act
        for (int i = 0; i < 10000; i++) {
        	
            Iterable<Person> existingPersons = this.persons.findPersons("gmail1.com");
            Collection<Person> existingPersonsCollection = this.makeCollection(existingPersons);
            assertEquals(50, existingPersonsCollection.size());
            Iterable<Person> notExistingPersons = this.persons.findPersons("non-existing email");
            Collection<Person> nonExistingPersonsCollection = this.makeCollection(notExistingPersons);
            assertEquals(0, nonExistingPersonsCollection.size());
        }
	}
	
	@Test(timeout = 300)
	public void testPerformance_FindPersonsByNameAndTown() {

		// Arrange
        this.addPersons(5000);

        // Act
        for (int i = 0; i < 10000; i++) {
        	
            Iterable<Person> existingPersons = this.persons.findPersons("Pesho1", "Sofia1");
            Collection<Person> existingPersonsCollection = this.makeCollection(existingPersons);
            assertEquals(50, existingPersonsCollection.size());
            Iterable<Person> notExistingPersons = this.persons.findPersons("Pesho1", "Sofia5");
            Collection<Person> notExistingPersonsCollection = this.makeCollection(notExistingPersons);
            assertEquals(0, notExistingPersonsCollection.size());
        }
	}
	
	@Test(timeout = 300)
	public void testPerformance_FindPersonsByAgeRange() {

		// Arrange
        this.addPersons(5000);

        // Act
        for (int i = 0; i < 2000; i++) {
        	
            Iterable<Person> existingPersons = this.persons.findPersons(20, 21);
            Collection<Person> existingPersonsCollection = this.makeCollection(existingPersons);
            assertEquals(100, existingPersonsCollection.size());
            Iterable<Person> notExistingPersons = this.persons.findPersons(500, 600);
            Collection<Person> notExistingPersonsCollection = this.makeCollection(notExistingPersons);
            assertEquals(0, notExistingPersonsCollection.size());
        }
	}
	
	@Test(timeout = 300)
	public void testPerformance_FindPersonsByTownAndAgeRange() {

		// Arrange
        this.addPersons(5000);

        // Act
        for (int i = 0; i < 5000; i++) {
        	
            Iterable<Person> existingPersons = this.persons.findPersons(18, 22, "Sofia20");
            Collection<Person> existingPersonsCollection = this.makeCollection(existingPersons);
            assertEquals(50, existingPersonsCollection.size());
            Iterable<Person> notExistingTownPersons = this.persons.findPersons(20, 30, "Missing town");
            Collection<Person> notExistingTownPersonsCollection = this.makeCollection(notExistingTownPersons);
            assertEquals(0, notExistingTownPersonsCollection.size());
            Iterable<Person> notExistingAgePersons = this.persons.findPersons(200, 300, "Sofia1");
            Collection<Person> notExistingAgePersonsCollection = this.makeCollection(notExistingAgePersons);
            assertEquals(0, notExistingAgePersonsCollection.size());
        }
	}
	
	private void addPersons(int count)
    {
        for (int i = 0; i < count; i++) {
        	
            this.persons.addPerson(
                "pesho" + i + "@gmail" + (i % 100) + ".com",
                "Pesho" + (i % 100),
                i % 100,
                "Sofia" + (i % 100));
        }
    }
	
	private <E> Collection<E> makeCollection(Iterable<E> iter) {
	    
		Collection<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        
	    	list.add(item);
	    }
	    
	    return list;
	}
}

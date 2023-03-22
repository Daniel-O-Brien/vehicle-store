package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScooterTest {
    Scooter scooterValidAtBottomEdge, scooterInValidBelow, scooterValidAtTopEdge, scooterValidOverTopEdge;

    @BeforeEach
    void setup() {
        scooterValidAtBottomEdge = new Scooter("notTesting", "NotTesting", 0, null, 0, 250, 5, 100);
        scooterInValidBelow = new Scooter("notTesting", "NotTesting", 0, null, 0, 249, 4, 99);
        scooterValidAtTopEdge = new Scooter("notTesting", "NotTesting", 0, null, 0, 1000, 100, 120);
        scooterValidOverTopEdge = new Scooter("notTesting", "NotTesting", 0, null, 0, 1001, 101, 121);
    }

    @Nested
    class constructorTests {
        @Test
        void validatingPower() {
            assertEquals(250, scooterValidAtBottomEdge.getPower());
            assertEquals(250, scooterInValidBelow.getPower());
            assertEquals(1000, scooterValidAtTopEdge.getPower());
            assertEquals(250, scooterValidOverTopEdge.getPower());
        }

        @Test
        void validatingTopRiderWeight() {
            assertEquals(100, scooterValidAtBottomEdge.getTopRiderWeight());
            assertEquals(100, scooterInValidBelow.getTopRiderWeight());
            assertEquals(120, scooterValidAtTopEdge.getTopRiderWeight());
            assertEquals(100, scooterValidOverTopEdge.getTopRiderWeight());
        }

        @Test
        void getWeight() {
            assertEquals(5, scooterValidAtBottomEdge.getWeight());
            assertEquals(5, scooterInValidBelow.getWeight());
            assertEquals(100, scooterValidAtTopEdge.getWeight());
            assertEquals(5, scooterValidOverTopEdge.getWeight());
        }

    }


    @Nested
    class setterAndGettersTests {
        @Test
        void setGetPower() {
            assertEquals(250, scooterValidAtBottomEdge.getPower());
            scooterValidAtBottomEdge.setPower(249);   //just below 250-> 1000 range - should not be changed.
            assertEquals(250, scooterValidAtBottomEdge.getPower());
            scooterValidAtBottomEdge.setPower(251);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(251, scooterValidAtBottomEdge.getPower());
            scooterValidAtBottomEdge.setPower(999);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(999, scooterValidAtBottomEdge.getPower());
            scooterValidAtBottomEdge.setPower(1000);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(1000, scooterValidAtBottomEdge.getPower());
            scooterValidAtBottomEdge.setPower(1001);   //just outside 250-> 1000 range - should not  be updated.
            assertEquals(1000, scooterValidAtBottomEdge.getPower());

        }

        @Test
        void setGetWeight() {
            //5 -> 100
            assertEquals(5, scooterValidAtBottomEdge.getWeight());
            scooterValidAtBottomEdge.setWeight(4);   //just below 5 -> 100 range - should not be changed.
            assertEquals(5, scooterValidAtBottomEdge.getWeight());
            scooterValidAtBottomEdge.setWeight(6);   //just inside 5 -> 100  range - should  be updated.
            assertEquals(6, scooterValidAtBottomEdge.getWeight());
            scooterValidAtBottomEdge.setWeight(99);   //just inside 5 -> 100  range - should  be updated.
            assertEquals(99, scooterValidAtBottomEdge.getWeight());
            scooterValidAtBottomEdge.setWeight(100);   //just inside 5 -> 100  range - should  be updated.
            assertEquals(100, scooterValidAtBottomEdge.getWeight());
            scooterValidAtBottomEdge.setWeight(101);   //just outside 5 -> 100 range - should  not be updated.
            assertEquals(100, scooterValidAtBottomEdge.getWeight());

        }

        @Test
        void setGetTopRiderWeight() {
            //100->120
            assertEquals(100, scooterValidAtBottomEdge.getTopRiderWeight());
            scooterValidAtBottomEdge.setTopRiderWeight(99);   //just below 100->120 range - should not be changed.
            assertEquals(100, scooterValidAtBottomEdge.getTopRiderWeight());
            scooterValidAtBottomEdge.setTopRiderWeight(101);   //just inside 100->120  range - should  be updated.
            assertEquals(101, scooterValidAtBottomEdge.getTopRiderWeight());
            scooterValidAtBottomEdge.setTopRiderWeight(119);   //just inside 100->120  range - should  be updated.
            assertEquals(119, scooterValidAtBottomEdge.getTopRiderWeight());
            scooterValidAtBottomEdge.setTopRiderWeight(120);   //just inside 100->120  range - should  be updated.
            assertEquals(120, scooterValidAtBottomEdge.getTopRiderWeight());
            scooterValidAtBottomEdge.setTopRiderWeight(121);   //just outside 100->120  range - should  not be updated.
            assertEquals(120, scooterValidAtBottomEdge.getTopRiderWeight());

        }
    }


    @Test
    void getCarbonFootPrint() {
        assertEquals(1.916, scooterValidAtBottomEdge.getCarbonFootPrint(), 0.01);
        assertEquals(1.916, scooterInValidBelow.getCarbonFootPrint(), 0.01);
        assertEquals(153.333, scooterValidAtTopEdge.getCarbonFootPrint(), 0.01);
        assertEquals(1.916, scooterValidOverTopEdge.getCarbonFootPrint(), 0.01);
    }

    @Test
    void testEquals() {
        Scooter scooter1 = new Scooter("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 250, 10, 110);
        Scooter scooter2 = new Scooter("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota",1000), 2023, 250, 10, 110);

        assertEquals(scooter1, scooter2);

        // Test the subclasses' fields
        scooter2.setPower(500);  // change value of power
        assertNotEquals(scooter1, scooter2);  // check that the equals picks up the difference
        scooter2.setPower(250);  // reset
        assertEquals(scooter1, scooter2);

        scooter2.setWeight(15);  // change value of weight
        assertNotEquals(scooter1, scooter2);  // check that the equals picks up the difference
        scooter2.setWeight(10);  // reset
        assertEquals(scooter1, scooter2);

        scooter2.setTopRiderWeight(120);  // change value of weight
        assertNotEquals(scooter1, scooter2);  // check that the equals picks up the difference
        scooter2.setTopRiderWeight(110);  // reset
        assertEquals(scooter1, scooter2);

    }

    @Test
    void testToString() {
        Scooter scooter = new Scooter("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 250, 10, 110);
        // test that the inherited fields appear in the toString
        String strToTest = scooter.toString();
        assertTrue(strToTest.contains("ABCD5678"));  // reg number
        assertTrue(strToTest.contains("toyota789012345")); // make
        assertTrue(strToTest.contains("Toyota"));  //  manufacturer (name)
        assertTrue(strToTest.contains("1000")); //cost
        // now check the fields of Scooter subclass
        assertTrue(strToTest.contains("250")); //power
        assertTrue(strToTest.contains("Weight : 10")); //weight
        assertTrue(strToTest.contains("110")); //topRiderWeight


    }
}
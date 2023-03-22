package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    // Using a Scooter object to test the methods of the (abstract) Vehicle. We ignore the fields of the Scooter in these tests.
    Scooter vehicleValid, vehicleValidBelow, vehicleInvalidStringAbove, vehicleInvalidStringWayAbove;

    //   Scooter
    @BeforeEach
    void setup() {
        vehicleValid = new Scooter("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 0, 0, 0);
        vehicleValidBelow = new Scooter("ABCD567", "toyota78901234", 999, new Manufacturer("Toyota", 1000), 2024, 0, 0, 0);
        vehicleInvalidStringAbove = new Scooter("ABCD56789", "toyota7890123456", 500, new Manufacturer("Toyota", 1000), 1999, 0, 0, 0);
        vehicleInvalidStringWayAbove = new Scooter("ABCD56789XXXXX", "toyota7890123456XXXXXX", 30000, new Manufacturer("Toyota", 2000), 2000, 0, 0, 0);
    }

    @Nested
    class constructorTests {
        @Test
        void validatingRegNumber() {
            // create a vehicle object with some reg number
            // assert that the reg number is equal to what we expect
            assertEquals("ABCD5678", vehicleValid.getRegNumber());  // reg number length is at limit (8)
            assertEquals("ABCD567", vehicleValidBelow.getRegNumber());  // reg number length is below limit - so should be accepted
            assertEquals("ABCD5678", vehicleInvalidStringAbove.getRegNumber()); // reg number length is one over limit (9) - so should be truncated to length 8
            assertEquals("ABCD5678", vehicleInvalidStringWayAbove.getRegNumber()); // reg number length is several(14) over limit  - so should be truncated to length
        }

        @Test
        void validatingMake() {
            assertEquals("toyota789012345", vehicleValid.getModel());  //make length is at max length (25) so accepted
            assertEquals("toyota78901234", vehicleValidBelow.getModel()); //make length is one below max length (24) so accepted
            assertEquals("toyota789012345", vehicleInvalidStringAbove.getModel());//make length is one above max length (26) so truncated to 25
            assertEquals("toyota789012345", vehicleInvalidStringWayAbove.getModel());  //make length is several above max length  so truncated to 25

        }

        @Test
        void getCost() {
            assertEquals(1000, vehicleValid.getCost(), .01);   //valid no change
            assertEquals(1000, vehicleValidBelow.getCost(), .01);  // invalid - moves to default
            assertEquals(1000, vehicleInvalidStringAbove.getCost(), .01);// invalid - moves to default
            assertEquals(30000, vehicleInvalidStringWayAbove.getCost(), .01); // valid value

        }

        @Test
        void getManufacturer() {
            Manufacturer man = new Manufacturer("Toyota", 1000);
            //checking that the manufacturer is instatianted correctly
            assertEquals(man, vehicleValid.getManufacturer());
        }


        @Test
        void getYear() {
            assertEquals(2023, vehicleValid.getYear());  // year at edge tested (2023)
            assertEquals(2000, vehicleValidBelow.getYear());  //year over limit (2024) input - should be defaulted to 2000
            assertEquals(2000, vehicleInvalidStringWayAbove.getYear());  //year on lower limit (2000) input - should be accepted/
            assertEquals(2000, vehicleInvalidStringAbove.getYear());  //year below limit (1999) input - should be defaulted to 2000

        }

        @Test
        void getAge() {
            //testing calculated field of getAge which is current year (2023) - year
            assertEquals(0, vehicleValid.getAge());   // year is 2023 so age is 0
            vehicleValid.setYear(2000);   // getAge should now be 23
            assertEquals(23, vehicleValid.getAge());  //checking that working within limit.
            vehicleValid.setYear(2015);  //now getAge should return 8
            assertEquals(8, vehicleValid.getAge());

        }


    }

    @Nested
    class setterAndGettersTests {

        @Test
        void setGetRegNumber() {
            assertEquals("ABCD5678", vehicleValid.getRegNumber());
            vehicleValid.setRegNumber("BELOW");  // This is a valid field length - this should be changed
            assertEquals("BELOW", vehicleValid.getRegNumber());
            vehicleValid.setRegNumber("TOOLONG89");   //This is too long - the field should remain unchanged
            assertEquals("BELOW", vehicleValid.getRegNumber());
        }


        @Test
        void setGetMake() {
            assertEquals("toyota789012345", vehicleValid.getModel());  // valid - just ensuring fresh value is there
            vehicleValid.setModel("toyota78901234");  // this is a valid length (14)- should be changed
            assertEquals("toyota78901234", vehicleValid.getModel());
            vehicleValid.setModel("toyota789012345");// this is a valid length (15)- should be changed
            assertEquals("toyota789012345", vehicleValid.getModel());
            vehicleValid.setModel("toyota789");// this is a valid length (9)- should be changed
            assertEquals("toyota789", vehicleValid.getModel());
            vehicleValid.setModel("toyota789012345XXXX");  //this is longer than the valid 15 so should not be changed
            assertEquals("toyota789", vehicleValid.getModel());
        }

        @Test
        void setGetCost() {
            assertEquals(1000, vehicleValid.getCost(), .01);
            vehicleValid.setCost(1001);  //valid
            assertEquals(1001, vehicleValid.getCost(), .01);
            vehicleValid.setCost(999);  //invalid - so stay the same
            assertEquals(1001, vehicleValid.getCost(), .01);
            vehicleValid.setCost(-100);  //invalid - so stay the same
            assertEquals(1001, vehicleValid.getCost(), .01);
        }

        @Test
        void setGetManufacturer() {
            Manufacturer man = new Manufacturer("Toyota", 1000);
            assertEquals(man, vehicleValid.getManufacturer());
            Manufacturer updatedMan = new Manufacturer("VW", 10000);
            vehicleValid.setManufacturer(updatedMan);
            assertEquals(updatedMan, vehicleValid.getManufacturer());
        }

        @Test
        void setGetYear() {
            assertEquals(2023, vehicleValid.getYear());
            vehicleValid.setYear(2022);   //valid
            assertEquals(2022, vehicleValid.getYear());
            vehicleValid.setYear(2024);   //invalid
            assertEquals(2022, vehicleValid.getYear());
            vehicleValid.setYear(1999);   //invalid
            assertEquals(2022, vehicleValid.getYear());

            vehicleValid.setYear(2000);   //invalid
            assertEquals(2000, vehicleValid.getYear());

        }

    }

    @Test
    void validatingTheEqualsMethod() {
        // change each of the fields to ensure that each of the fields are taken into account

        Scooter newVehicle = new Scooter("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 0, 0, 0);
        assertEquals(newVehicle, vehicleValid);   // working when values in objects are equal
        vehicleValid.setRegNumber("ASD");  // now reg Numbers unequal
        assertNotEquals(newVehicle, vehicleValid);
        vehicleValid.setRegNumber("ABCD5678");  //reset to make them equal again
        assertEquals(newVehicle, vehicleValid);  //ensure they are equal again

        vehicleValid.setModel("toyota"); //now makes unequal
        assertNotEquals(newVehicle, vehicleValid);
        vehicleValid.setModel("toyota789012345");//reset to make them equal again
        assertEquals(newVehicle, vehicleValid);  //ensure they are equal again

        vehicleValid.setCost(1005); //now makes unequal
        assertNotEquals(newVehicle, vehicleValid);
        vehicleValid.setCost(1000);//reset to make them equal again
        assertEquals(newVehicle, vehicleValid);  //ensure they are equal again

        vehicleValid.setManufacturer(new Manufacturer("NotToyota", 999));
        assertNotEquals(newVehicle, vehicleValid);
        vehicleValid.setManufacturer(new Manufacturer("Toyota", 1000));//reset to make them equal again
        assertEquals(newVehicle, vehicleValid);  //ensure they are equal again

        vehicleValid.setYear(2015);   //change year to make the two objects not equal
        assertNotEquals(newVehicle, vehicleValid);
        vehicleValid.setYear(2023);   //reset to make them equal again
        assertEquals(newVehicle, vehicleValid);//ensure they are equal again

    }

    @Nested
    class ToStringTests {

    @Test
    void toStringContainsAllFieldsInObject() {
        //checking that the toString contains the following field
        String strToTest = vehicleValid.toString();
        assertTrue(strToTest.contains("ABCD5678"));  // reg number
        assertTrue(strToTest.contains("toyota789012345")); // make
        assertTrue(strToTest.contains("Toyota"));  //  manufacturer (name)
        assertTrue(strToTest.contains("1000")); //cost
    }

    @Test
    void toStringManagesAgeStrings() {
        String strToTest = vehicleValid.toString();
        assertTrue(strToTest.contains("Brand New!"));  // when age of car is zero
        assertTrue(vehicleInvalidStringWayAbove.toString().contains("23 years old"));   //when car is plural number of years old
        vehicleValid.setYear(2022);
        assertTrue(vehicleValid.toString().contains("1 year old"));   // when year is singular
    }
}
}
import org.example.Man;
import org.example.Woman;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestMan {
    SoftAssert softAssert = new SoftAssert();
    DataProviderValues people = new DataProviderValues();
    Man[] men = people.menlist();
    Woman[] women = people.womenList();

    @Test(dataProvider = "manList", dataProviderClass = DataProviderValues.class)
    public void testGetFullName(Man man){
        Assert.assertEquals(man.getFullName(), man.getFirstName()+" "+man.getLastName(), "names mismatch");
    }

    @Test
    public void testFavoriteThings(){
        Assert.assertEquals(men[0].favoriteThings(), "As a Man I like beer, squash and bikes", "Not my favorite things printed");
    }

    @Test(dataProvider = "menAndWomen", dataProviderClass = DataProviderValues.class)
    public void testMarriage(Man man, Woman woman){
        man.registerPartnership(woman);
        softAssert.assertTrue(man.married && woman.married);
        softAssert.assertEquals(man.getLastName(), woman.getLastName(), "Last name mismatch");
        softAssert.assertEquals(man.getSpouseName(), woman.getFullName(), "Spouse name mismatch");
        softAssert.assertAll();
    }

    @Test(groups = "smoke", dataProvider = "manList", dataProviderClass = DataProviderValues.class)
    public void checkManIsNotRetired(Man man) {
            Assert.assertFalse(man.isRetired(), "At least one man is retired according to the company policy");
    }

    @Test(groups = "smoke")
    public void testSetRetirementAge() {
        men[0].setAge(65);
        Assert.assertTrue(men[0].isRetired(), String.format("Something went wrong, please check the %s's age or the retirement requirements", men[0].getFullName()));
    }

    @Test(dataProvider = "manList", dataProviderClass = DataProviderValues.class)
    public void testIfCanGetMarried(Man man) {
            Assert.assertTrue(man.canGetMarried(women[0]),
                    String.format("%s can not get married, please check if him or suggested spouse is not married already", man.getFullName()));
    }

    @AfterMethod
    public void resetToDefault(){
        for (Man man : men){
            man.resetToOriginalData();
        }
    }
}

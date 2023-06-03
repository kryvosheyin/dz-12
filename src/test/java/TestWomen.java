import org.example.Man;
import org.example.Woman;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.PeopleList;

public class TestWomen {

    DataProviderValues people = new DataProviderValues();
    SoftAssert softAssert = new SoftAssert();

    Man[] men = people.menlist();
    Man[] marriedMen = people.marriedMen();
    Woman[] women = people.womenList();


    @Test(dataProvider = "womenList", dataProviderClass = DataProviderValues.class)
    public void testWomenNotRetired(Woman woman) {
        Assert.assertFalse(woman.isRetired(), "At least one of the woman's age exceeds the retirement policy");
    }

    @Test(dataProvider = "womenList", dataProviderClass = DataProviderValues.class)
    public void testCanNotGetMarried(Woman woman) {
        Assert.assertFalse(woman.canGetMarried(marriedMen[0]), "At least one woman can get married to a married man");
    }

    @Test(dataProvider = "womenList", dataProviderClass = DataProviderValues.class)
    public void testCanGetMarried(Woman woman) {
        Assert.assertTrue(woman.canGetMarried(men[0]));
    }

    @Test(groups = "smoke", dataProvider = "womenList", dataProviderClass = DataProviderValues.class)
    public void testWomenGetFullName(Woman woman) {
        Assert.assertEquals(woman.getFullName(),
                woman.getFirstName() + " " + woman.getLastName(),
                "One or more names mismatch");
    }

    @Test
    public void testFavoriteThings() {
        Assert.assertEquals(women[0].favoriteThings(), "As a Woman I like to dance and draw", "Not what women like to do");
    }

    @Test(groups = "smoke")
    public void testRetirementAge() {
        women[0].setAge(63);
        Assert.assertTrue(women[0].isRetired(), "At least one woman is not at the retirement age");
    }

    @Test(dataProvider = "menAndWomen", dataProviderClass = DataProviderValues.class)
    public void testRegisterPartnership(Man man, Woman woman) {
        woman.registerPartnership(man);
        softAssert.assertTrue(woman.married && man.married);
        softAssert.assertEquals(woman.getLastName(), man.getLastName());
        softAssert.assertEquals(woman.getSpouseName(), man.getFullName());
        softAssert.assertAll();
    }

    @AfterMethod
    public void resetToOriginalData() {
        for (Woman woman : women) woman.resetToOriginalData();
    }
}
import org.example.Man;
import org.example.Person;
import org.example.Woman;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.PeopleList;

public class TestMan {
    SoftAssert softAssert = new SoftAssert();
    PeopleList peoplesList = new PeopleList();
    Man[] menList;
    Woman[] womenList;

    private void registerPartnership(Person man, Person woman) {
        man.registerPartnership(woman);
        softAssert.assertTrue(man.married && woman.married);
        softAssert.assertEquals(man.getLastName(), woman.getLastName());
        softAssert.assertEquals(man.getSpouseName(), woman.getFullName());
        softAssert.assertAll();
    }

    @BeforeClass(groups = "smoke")
    public void setUp(){
        menList = PeopleList.getListOfMen(peoplesList.menRecords, new Man[peoplesList.menRecords.size()]);
        womenList = PeopleList.getListOfWomen(peoplesList.womenRecords, new Woman[peoplesList.womenRecords.size()]);
    }

    @Test(groups = "smoke")
    public void testGetAge() {
        for (Man man : menList) {
            Assert.assertFalse(man.isRetired(), "At least one man is retired according to the company policy");
        }
    }

    @Test(groups = "smoke")
    public void testSetRetirementAge() {
        menList[0].setAge(65);
        Assert.assertTrue(menList[0].isRetired(), String.format("Something went wrong, please check the %s's age or the retirement requirements", menList[0].getFullName()));
    }

    @Test(groups = "smoke")
    public void testMenGetFullName() {
        for (int i = 0; i < menList.length; i++) {
            Assert.assertEquals(menList[i].getFullName(),
                    peoplesList.menRecords.get(i).getFirstName() + " " + peoplesList.menRecords.get(i).getLastName(),
                    "One or more names mismatch");
        }
    }

    @Test
    public void testIfCanGetMarried() {
        for (Man man : menList) {
            softAssert.assertTrue(man.canGetMarried(womenList[0]),
                    String.format("%s can not get married, please check if him or suggested spouse is not married already", man.getFullName()));
        }
        softAssert.assertAll();
    }

    @Test
    public void testGetMarried() {
        this.registerPartnership(menList[0], womenList[0]);
    }

    @Test
    public void testGetAnotherMarriage() {
        this.registerPartnership(menList[0], womenList[1]);
    }

    @Test
    public void testGetThirdMarriage() {
        this.registerPartnership(menList[1], womenList[0]);
    }

    @Test
    public void testDeregisterPartnership() {
        this.registerPartnership(menList[0], womenList[1]);
        menList[0].deregisterPartnership();
        Assert.assertTrue(!menList[0].married && !womenList[1].married,
                "One of the people is still married, please ask if they had filed for the divorce");
    }

    @Test
    public void testRegisterPartnerAgain() {
        menList[0].registerPartnership(womenList[1]);
        softAssert.assertTrue(menList[0].married && womenList[1].married);
        softAssert.assertEquals(menList[0].getFullName(), womenList[1].getSpouseName(), "Spouses names mismatch");
        softAssert.assertAll();
    }

    @Test
    public void testFailToRegisterPartnership() {
        menList[3].registerPartner(womenList[1]);
        Assert.assertEquals(menList[3].getSpouseName(), womenList[1].getFullName(),
                "Looks like you can get married, but you shouldn't ne able to. Please contact authorities before doing so :)");
    }

    @AfterMethod
    public void resetToOriginalData() {
        for (Man man : menList) man.resetToOriginalData();
        for (Woman woman : womenList) woman.resetToOriginalData();
    }
}

import org.example.Man;
import org.example.Woman;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.PeopleList;

public class TestWomen {

    PeopleList peopleList = new PeopleList();
    SoftAssert softAssert = new SoftAssert();

    Man[] men, marriedMen;
    Woman[] women;

    @BeforeClass(groups = "smoke")
    public void setUp() {
        men = PeopleList.getListOfMen(peopleList.menRecords, new Man[peopleList.menRecords.size()]);
        marriedMen = PeopleList.getListOfMen(peopleList.marriedManRecords, new Man[peopleList.marriedManRecords.size()]);
        women = PeopleList.getListOfWomen(peopleList.womenRecords, new Woman[peopleList.womenRecords.size()]);
    }

    @Test
    public void testWomenNotRetired(){
        for(Woman woman : women){
        Assert.assertFalse(woman.isRetired(), "At least one of the woman's age exceeds the retirement policy");
        }
    }

    @Test
    public void testCanNotGetMarried(){
        for (Woman woman : women){
            Assert.assertFalse(woman.canGetMarried(marriedMen[0]), "At least one woman can get married to a married man");
        }
    }

    @Test(groups = "smoke")
    public void testWomenGetFullName() {
        for (int i = 0; i < women.length; i++) {
            Assert.assertEquals(women[i].getFullName(),
                    peopleList.womenRecords.get(i).getFirstName() + " " + peopleList.womenRecords.get(i).getLastName(),
                    "One or more names mismatch");
        }
    }

    @Test(groups = "smoke")
    public void testRetirementAge(){
        for(Woman woman : women){
            woman.setAge(63);
            Assert.assertTrue(woman.isRetired(), "At least one woman is not at the retirement age");
        }
    }

    @Test
    public void testRegisterPartnership(){
        for(int i=0; i<women.length;i++){
            women[i].registerPartnership(men[i]);
            softAssert.assertTrue(women[i].married && men[i].married);
            softAssert.assertEquals(women[i].getLastName(), men[i].getLastName());
            softAssert.assertEquals(women[i].getSpouseName(), men[i].getFullName());
            softAssert.assertAll();
        }
    }

    @AfterMethod
    public void resetToOriginalData(){
        for (Man man: men) man.resetToOriginalData();
        for (Woman woman : women) woman.resetToOriginalData();
        for (Man man :marriedMen) man.resetToOriginalData();
    }
}

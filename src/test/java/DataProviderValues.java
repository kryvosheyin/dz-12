import org.example.Man;
import org.example.Woman;
import org.testng.annotations.DataProvider;
import utils.DataRecord;
import utils.PeopleList;

import java.util.List;


public class DataProviderValues {

    protected Man[] menlist(){
        return PeopleList.getListOfPeople(PeopleList.menRecords,
                new Man[PeopleList.menRecords.size()],
                r-> new Man(r.getFirstName(), r.getLastName(), r.getAge(), r.isMarried()));
    }

    protected Woman[] womenList(){
        return PeopleList.getListOfPeople(PeopleList.womenRecords,
                new Woman[PeopleList.womenRecords.size()],
                r-> new Woman(r.getFirstName(), r.getLastName(), r.getAge(), r.isMarried()));
    }

    protected Man[] marriedMen(){
        return PeopleList.getListOfPeople(PeopleList.marriedManRecords,
                new Man[PeopleList.marriedManRecords.size()],
                r-> new Man(r.getFirstName(), r.getLastName(), r.getAge(), r.isMarried()));
    }
    @DataProvider(name="manList")
    public Object[] men(){
        return menlist();
    }

    @DataProvider(name="womenList")
    public Object[] women(){
        return womenList();
    }

    @DataProvider(name="menAndWomen")
    public Object[][] menAndWomen(){
        Man[] men  = menlist();
        Woman[] women = womenList();

        Object[][] pairs = new Object[men.length][2];
        for (int i = 0; i < men.length; i++) {
            pairs[i][0] = men[i];
            pairs[i][1] = women[i];
        }
        return pairs;
    }

    @DataProvider(name="marriedMen")
    public Object[] marriedMenList(){
        return marriedMen();
    }
}

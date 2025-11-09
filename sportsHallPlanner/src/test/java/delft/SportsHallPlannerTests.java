package delft;

/*import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;*/

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.*;

import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;
import static delft.Field.*;
import static delft.Property.*;
import static delft.SportsHallPlanner.planHalls;
import static org.junit.jupiter.api.Assertions.*;

class SportsHallPlannerTests {

    private List<Request> requests = new ArrayList<>();
    private List<SportsHall> halls = new ArrayList<>();

    Set<Property> properties1 = new HashSet<>();
    Set<Property> properties2 = new HashSet<>();
    Set<Property> properties3 = new HashSet<>();

    Map<Field, Integer> fields1 = new HashMap<>();
    Map<Field, Integer> fields2 = new HashMap<>();
    Map<Field, Integer> fields3 = new HashMap<>();

    private SportsHall sportsHall1 = new SportsHall(properties1, fields1);
    private SportsHall sportsHall2 = new SportsHall(properties2, fields2);
    private SportsHall sportsHall3 = new SportsHall(properties3, fields3);


    //——————————————————————————————————————————————————————————————————————————————
    //————————————————————— specification-based testing ————————————————————————————
    //——————————————————————————————————————————————————————————————————————————————
    @Test
    void OneCorrectInputForPlanHallTest() {
        sportsHall1.fields.put(TENNIS, 3);
        sportsHall1.properties.add(CLOSE_PUBLIC_TRANSPORT);

        halls.add(sportsHall1);

        Request request1 = new Request(new HashSet<>(), TENNIS, 2);
        requests.add(request1);

        Map<SportsHall, Request> result = new  HashMap<>();
        result.put(sportsHall1, request1);

        assertEquals(1, (planHalls(requests, halls)).size());
        assertEquals(result, planHalls(requests, halls));
    }

    @Test
    void CorrectInputsMixedForPlanHallTest() {
        sportsHall1.fields.put(TENNIS, 3);
        sportsHall1.properties.add(CLOSE_PUBLIC_TRANSPORT);

        sportsHall2.fields.put(VOLLEYBALL, 2);
        sportsHall2.properties.add(HAS_RESTAURANT);
        sportsHall2.properties.add(NEAR_CITY_CENTRE);

        halls.add(sportsHall2);
        halls.add(sportsHall1);

        Request request1 = new Request(new HashSet<>(), TENNIS, 2);
        Request request2 = new Request(new HashSet<>(), VOLLEYBALL, 2);
        requests.add(request1);
        requests.add(request2);

        Map<SportsHall, Request> result = new  HashMap<>();
        result.put(sportsHall2, request2);
        result.put(sportsHall1, request1);

        assertEquals(2, (planHalls(requests, halls)).size());
        assertEquals(result, planHalls(requests, halls));
    }

    @Test
    void emptyInputForPlanHallTest() {
        SportsHallPlanner  SportsHallPlanner = null;
        assertTrue(planHalls(requests, halls).isEmpty());
        assertEquals(0, (planHalls(requests, halls)).size());
        assertNotNull(SportsHallPlanner.planHalls(requests, halls));
    }

    //——————————————————————————————————————————————————————————————————————————————
    //—————————————————————————— structural testing ————————————————————————————————
    //——————————————————————————————————————————————————————————————————————————————





}

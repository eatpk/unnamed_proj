package src;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TestSpaceStationSecurity {
    private SpaceStationSecurity securityValidation;

    @Before
    public void setUp() {
        SpaceStationSecurity securityValidation = new SpaceStationSecurity();
    }

    @After
    public void tearDown() {
        securityValidation.map =  new HashMap<>();
        securityValidation.blacklist =  new HashSet<UUID>();
        securityValidation.violationCounterForTest =0;
    }

    @Test
    public void testCondition1() throws Exception {
        String[] args= {"data/condition1.gz"};
        try {
            securityValidation.main(args);
            assertEquals(1,securityValidation.violationCounterForTest);
        } catch (Exception e) {
            throw e;
        }
    }
    @Test
    public void testCondition2() throws Exception {
        String[] args= {"data/condition2.gz"};
        try {
            securityValidation.main(args);
            assertEquals(1,securityValidation.violationCounterForTest);
        } catch (Exception e) {
            throw e;
        }
    }
    @Test
    public void testCondition3_1() throws Exception {
        String[] args= {"data/condition3-1.gz"};
        try {
            securityValidation.main(args);
            assertEquals(1,securityValidation.violationCounterForTest);
        } catch (Exception e) {
            throw e;
        }
    }
    @Test
    public void testCondition3_2() throws Exception {
        String[] args= {"data/condition3-2.gz"};
        try {
            securityValidation.main(args);
            assertEquals(0,securityValidation.violationCounterForTest);
        } catch (Exception e) {
            throw e;
        }
    }
    @Test
    public void testCondition3_3() throws Exception {
        String[] args= {"data/condition3-3.gz"};
        try {
            securityValidation.main(args);
            assertEquals(0,securityValidation.violationCounterForTest);
        } catch (Exception e) {
            throw e;
        }
    }
}
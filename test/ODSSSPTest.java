package test;

import org.junit.Test;

import alg.OptimizedDijkstraSSSP;

public class ODSSSPTest extends SSSPTest {

    protected void reset() {
        ssspAlg = new OptimizedDijkstraSSSP();
    } 

    @Test(timeout=60000)
    public void tooBigForBF() {
        runSSSP("tooBigForBF",0, 46918.84);
    }

}

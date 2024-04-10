package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerPlateTest {

    @Test
    void validatePlate() {
        CheckerPlate cp = new CheckerPlate();
        int response = cp.validatePlate("IT €URO 123");
        boolean cadrega;
        if (response == 1){
            cadrega = true;
        }else{
            cadrega = false;
        }
        assertFalse(cadrega, "test1");
    }
    @Test
    void validatePlate2() {
        CheckerPlate cp = new CheckerPlate();
        int response = cp.validatePlate("*FR 12345");
        boolean cadrega;
        if (response == 1){
            cadrega = true;
        }else{
            cadrega = false;
        }
        assertFalse(cadrega, "test2");
    }
    @Test
    void validatePlate3() {
        CheckerPlate cp = new CheckerPlate();
        int response = cp.validatePlate("DE 987-CD");
        boolean cadrega;
        if (response == 1){
            cadrega = true;
        }else{
            cadrega = false;
        }
        assertFalse(cadrega, "test3");
    }
    @Test
    void validatePlate4() {
        CheckerPlate cp = new CheckerPlate();
        int response = cp.validatePlate("IT ÄBC 123");
        boolean cadrega;
        if (response == 1){
            cadrega = true;
        }else{
            cadrega = false;
        }
        assertTrue(cadrega, "test4");
    }
    @Test
    void validatePlate5() {
        CheckerPlate cp = new CheckerPlate();
        int response = cp.validatePlate("it abc 123");
        boolean cadrega;
        if (response == 1){
            cadrega = true;
        }else{
            cadrega = false;
        }
        assertTrue(cadrega, "test5");
    }

    @Test
    void validatePlate6() {
        CheckerPlate cp = new CheckerPlate();
        int response = cp.validatePlate("itabc123");
        boolean cadrega;
        if (response == 1){
            cadrega = true;
        }else{
            cadrega = false;
        }
        assertTrue(cadrega, "test6");
    }

    @Test
    void validatePlate7() {
        CheckerPlate cp = new CheckerPlate();
        int response = cp.validatePlate("I    T €  U   R    O 1  2    3");
        boolean cadrega;
        if (response == 1){
            cadrega = true;
        }else{
            cadrega = false;
        }
        assertFalse(cadrega, "test7");
    }

    @Test
    void validatePlate8() {
        CheckerPlate cp = new CheckerPlate();
        int response = cp.validatePlate("it     ab     c12    3");
        boolean cadrega;
        if (response == 1){
            cadrega = true;
        }else{
            cadrega = false;
        }
        assertTrue(cadrega, "test8");
    }
}
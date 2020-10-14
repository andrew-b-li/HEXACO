package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAssessment {
    Assessment testAssessment1 = new Assessment(2,2,2,2,2,2);
    int expectedDefaultValue = 2;
    int expectedChangedValue = 5;

    @Test
    void testSetHonestyHumility() {
        testAssessment1.setHonestyHumility(5);
        int actualChangedValue = testAssessment1.getHonestyHumility();
        assertEquals(expectedChangedValue, actualChangedValue);
    }

    @Test
    void testSetEmotionality(){
        testAssessment1.setEmotionality(5);
        int actualChangedValue = testAssessment1.getEmotionality();
        assertEquals(expectedChangedValue, actualChangedValue);
    }

    @Test
    void testSetExtraversion(){
        testAssessment1.setExtraversion(5);
        int actualChangedValue = testAssessment1.getExtraversion();
        assertEquals(expectedChangedValue, actualChangedValue);
    }

    @Test
    void testSetAgreeableness(){
        testAssessment1.setAgreeableness(5);
        int actualChangedValue = testAssessment1.getAgreeableness();
        assertEquals(expectedChangedValue, actualChangedValue);
    }

    @Test
    void testSetConscientiousness() {
        testAssessment1.setConscientiousness(5);
        int actualChangedValue = testAssessment1.getConscientiousness();
        assertEquals(expectedChangedValue, actualChangedValue);
    }

    @Test
    void testSetOpennessToExperience() {
        testAssessment1.setOpennessToExperience(5);
        int actualValue = testAssessment1.getOpennessToExperience();
        assertEquals(expectedChangedValue, actualValue);
    }

    @Test
    void testGetHonestyHumility() {
        int actualValue = testAssessment1.getHonestyHumility();
        assertEquals(expectedDefaultValue, actualValue);
    }

    @Test
    void testGetEmotionality() {
        int actualValue = testAssessment1.getEmotionality();
        assertEquals(expectedDefaultValue, actualValue);
    }

    @Test
    void testGetExtraversion() {
        int actualValue = testAssessment1.getExtraversion();
        assertEquals(expectedDefaultValue, actualValue);
    }

    @Test
    void testGetAgreeableness() {
        int actualValue = testAssessment1.getAgreeableness();
        assertEquals(expectedDefaultValue, actualValue);
    }

    @Test
    void testGetConscientiousness() {
        int actualValue = testAssessment1.getConscientiousness();
        assertEquals(expectedDefaultValue, actualValue);
    }

    @Test
    void testGetOpennessToExperience() {
        int actualValue = testAssessment1.getOpennessToExperience();
        assertEquals(expectedDefaultValue, actualValue);
    }

    @Test
    void testToString(){
        String actualString = testAssessment1.toString();
        String expectedString = "H:2 E:2 X:2 A:2 C:2 O:2";
        assertEquals(expectedString, actualString);

    }
}

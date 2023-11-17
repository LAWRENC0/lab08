package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {
    private DeathNoteImpl myDeathNote;

    @BeforeEach
    public void setUp() {
        this.myDeathNote = new DeathNoteImpl();
    }

    @Test
    public void testInitialization() {}

    @Test
    public void testGetRule() {
        try {
            this.myDeathNote.getRule(0);
            this.myDeathNote.getRule(-5);
            fail();
        } catch (IllegalArgumentException e) {
            Assertions.assertNotNull(e.getMessage());
            Assertions.assertNotEquals("", e.getMessage().trim());
        }
    }

    @Test
    public void testRules() {
        for(String rule : this.myDeathNote.RULES){
            Assertions.assertNotNull(rule);
            Assertions.assertNotEquals("", rule.trim());
        }
    }

    @Test
    public void testIsNameWritten() {
        final String humanName1 = "Paolo Verdi";
        final String humanName2 = "Marco Verdi";
        Assertions.assertFalse(this.myDeathNote.isNameWritten(humanName1));
        this.myDeathNote.writeName(humanName1);
        Assertions.assertTrue(this.myDeathNote.isNameWritten(humanName1));
        Assertions.assertFalse(this.myDeathNote.isNameWritten(humanName2));
        Assertions.assertFalse(this.myDeathNote.isNameWritten(""));
    }

    @Test
    public void testWriteDeathCause() throws InterruptedException {
        Thread.sleep(40);
        try {
            this.myDeathNote.writeDeathCause("incidente");
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertNotNull(e.getMessage());
            Assertions.assertNotEquals("", e.getMessage().trim());
        }
        final String humanName1 = "Paolo Bonolis";
        final String humanName2 = "Carlo Conti";
        this.myDeathNote.writeName(humanName1);
        Assertions.assertEquals("heart attack", this.myDeathNote.getDeathCause(humanName1));
        this.myDeathNote.writeName(humanName2);
        final String deathCause = "karting accident";
        boolean retValue = this.myDeathNote.writeDeathCause(deathCause);
        Assertions.assertTrue(retValue);
        Assertions.assertEquals(deathCause, this.myDeathNote.getDeathCause(humanName2));
        Thread.sleep(100);
        retValue = this.myDeathNote.writeDeathCause(deathCause);
        Assertions.assertFalse(retValue);
    }

    @Test
    public void testWriteDetails() throws InterruptedException {
        try {
            this.myDeathNote.writeDetails("sbatte il mignolo contro lo spigolo");
            fail();
        } catch (IllegalStateException e) {
            
        }
        final String humanName1 = "Gerry Scotty";
        this.myDeathNote.writeName(humanName1);
        Assertions.assertEquals("", this.myDeathNote.getDeathDetails(humanName1));
        final boolean b1 = this.myDeathNote.writeDetails("ran for too long");
        Assertions.assertTrue(b1);
        Assertions.assertEquals(this.myDeathNote.getDeathDetails(humanName1), "ran for too long");
        final String humanName2 = "Rudy Zerby";
        this.myDeathNote.writeName(humanName2);
        final String details = this.myDeathNote.getDeathDetails(humanName2);
        Thread.sleep(6100);
        final boolean b2 = this.myDeathNote.writeDetails(humanName2);
        Assertions.assertFalse(b2);
        Assertions.assertEquals(this.myDeathNote.getDeathDetails(humanName2), details);
    }

}
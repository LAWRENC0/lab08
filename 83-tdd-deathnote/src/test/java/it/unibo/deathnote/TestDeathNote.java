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
        String humanName1 = "Paolo Verdi";
        String humanName2 = "Marco Verdi";
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
        String humanName1 = "Paolo Bonolis";
        String humanName2 = "Carlo Conti";
        this.myDeathNote.writeName(humanName1);
        Assertions.assertEquals("heart attack", this.myDeathNote.getDeathCause(humanName1));
        this.myDeathNote.writeName(humanName1);

    }


}
package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    @Override
    public String getRule(int ruleNumber) {
        throw new IllegalArgumentException("Unimplemented method 'getRule'");
    }

    @Override
    public void writeName(String name) {
        throw new NullPointerException("Unimplemented method 'writeName'");
    }

    @Override
    public boolean writeDeathCause(String cause) {
        throw new IllegalStateException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        throw new IllegalStateException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        throw new IllegalArgumentException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        throw new IllegalArgumentException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }
    
}

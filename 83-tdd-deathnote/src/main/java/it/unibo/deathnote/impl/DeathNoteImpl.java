package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;
import java.util.Map;
import java.util.HashMap;

public class DeathNoteImpl implements DeathNote {
    private final Map<String, Death> killNote;
    private long lastInsertionTime;
    private String lastInsertedHuman;

    public DeathNoteImpl(){
        this.killNote = new HashMap<>();
        this.lastInsertedHuman = null;
    }

    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber <= 0 || ruleNumber > DeathNote.RULES.size()){
            throw new IllegalArgumentException("Unimplemented method 'getRule'");
        }else{
            return DeathNote.RULES.get(ruleNumber - 1);
        }
    }

    @Override
    public void writeName(String name) {
        if(name != null){
            killNote.put(name, new Death());
            this.lastInsertionTime = System.currentTimeMillis();
            this.lastInsertedHuman = name;
        }else{
            throw new NullPointerException("provided name is null");
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(cause == null || this.killNote.keySet().size() == 0){
            throw new IllegalStateException("Unimplemented method 'writeDeathCause'");
        }
        final long now = System.currentTimeMillis();
        if(now - this.lastInsertionTime < 40){
            Death temp = new Death();
            temp.setDeathCause(cause);
            this.killNote.put(this.lastInsertedHuman, temp);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean writeDetails(String details) {
        if(details == null || this.killNote.keySet().size() == 0){
            throw new IllegalStateException("Unimplemented method 'writeDetails'");
        }
        final long now = System.currentTimeMillis();
        if(now - this.lastInsertionTime < 6040){
            this.killNote.get(lastInsertedHuman).deathDetails = details;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getDeathCause(String name) {
        if(!this.killNote.containsKey(name)){
            throw new IllegalArgumentException("Unimplemented method 'getDeathCause'");
        }
        return this.killNote.get(name).deathCause;
    }

    @Override
    public String getDeathDetails(String name) {
        if(!this.killNote.containsKey(name)){
            throw new IllegalArgumentException("Unimplemented method 'getDeathCause'");
        }
        return this.killNote.get(name).deathDetails;
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.killNote.containsKey(name) ? true : false;
    }

    private class Death{
        private static String DEFAULT_DEATH_CAUSE = "heart attack";

        private String deathCause;
        private String deathDetails;

        public Death(){
            this.deathCause = DEFAULT_DEATH_CAUSE;
            this.deathDetails = "";
        }

        public String getDeathCause(){
            return this.deathCause;
        }

        public String getDeathDetails(){
            return this.deathCause;
        }

        public void setDeathCause(final String deathCause){
            this.deathCause = deathCause;
        }

        public void setDeathDetails(final String deathDetails){
            this.deathDetails = deathDetails;
        }
    }
    
}

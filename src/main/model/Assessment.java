package model;

/*
 * Represents a personality assessment with scores for each HEXACO personality trait
 */
public class Assessment {
    private int honestyHumility;
    private int emotionality;
    private int extraversion;
    private int agreeableness;
    private int conscientiousness;
    private int opennessToExperience;

    public static final String QUESTION2C = "I plan ahead and organize things, to avoid scrambling at the last minute.";
    public static final String QUESTION3A = "I rarely hold a grudge, even against people who have badly wronged me.";
    public static final String QUESTION4X = "I feel reasonably satisfied with myself overall.";
    public static final String QUESTION5E = "I would feel afraid if I had to travel in bad weather conditions.";
    public static final String QUESTION6H = "I wouldn't use flattery to get a raise or promotion at work, "
            + "even if I thought it would succeed.";
    public static final String QUESTION7O = "I'm interested in learning about the history and "
            + "politics of other countries.";

    //Construct an Assessment
    //Effects: Assigns scores to the six personality traits for the test
    public Assessment(int honest, int emotional, int extraverted, int agreeable, int conscientious, int open) {
        honestyHumility = honest;
        emotionality = emotional;
        extraversion = extraverted;
        agreeableness = agreeable;
        conscientiousness = conscientious;
        opennessToExperience = open;

    }

    //Requires: honestyHumility must be an integer (1,2,3,4,5)
    //Modifies: this
    //Effects: sets the honestyHumility score
    public void setHonestyHumility(int honestyHumility) {
        this.honestyHumility = honestyHumility;
    }

    //Requires: honestyHumility must be an integer (1,2,3,4,5)
    //Modifies: this
    //Effects: sets the honestyHumility score
    public void setEmotionality(int emotionality) {
        this.emotionality = emotionality;
    }

    //Requires: honestyHumility must be an integer (1,2,3,4,5)
    //Modifies: this
    //Effects: sets the honestyHumility score
    public void setExtraversion(int extraversion) {
        this.extraversion = extraversion;
    }

    //Requires: honestyHumility must be an integer (1,2,3,4,5)
    //Modifies: this
    //Effects: sets the honestyHumility score
    public void setAgreeableness(int agreeableness) {
        this.agreeableness = agreeableness;
    }

    //Requires: honestyHumility must be an integer (1,2,3,4,5)
    //Modifies: this
    //Effects: sets the honestyHumility score
    public void setConscientiousness(int conscientiousness) {
        this.conscientiousness = conscientiousness;
    }

    //Requires: honestyHumility must be an integer (1,2,3,4,5)
    //Modifies: this
    //Effects: sets the honestyHumility score
    public void setOpennessToExperience(int opennessToExperience) {
        this.opennessToExperience = opennessToExperience;
    }

    public int getHonestyHumility() {
        return honestyHumility;
    }

    //Effects: returns the emotionality score
    public int getEmotionality() {
        return emotionality;
    }

    //Effects: returns the extraversion score
    public int getExtraversion() {
        return extraversion;
    }

    //Effects: returns the agreeableness score
    public int getAgreeableness() {
        return agreeableness;
    }

    //Effects: returns the conscientiousness score
    public int getConscientiousness() {
        return conscientiousness;
    }

    //Effects: returns the opennessToExperience score
    public int getOpennessToExperience() {
        return opennessToExperience;
    }

    //Effects: Returns a string containing the scores for each personality trait
    @Override
    public String toString() {
        return "H:" + honestyHumility + " E:" + emotionality + " X:" + extraversion + " A:" + agreeableness + " C:"
                + conscientiousness + " O:" + opennessToExperience;
    }
}

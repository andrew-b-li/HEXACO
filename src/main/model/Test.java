package model;

public class Test {
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
    public static final String QUESTION6H = "I wouldn't use flattery to get a raise or promotion at work, even if I thought it would succeed.";
    public static final String QUESTION7O = "I'm interested in learning about the history and politics of other countries.";


    public void setHonestyHumility(int honestyHumility) {
        this.honestyHumility = honestyHumility;
    }

    public void setEmotionality(int emotionality) {
        this.emotionality = emotionality;
    }

    public void setExtraversion(int extraversion) {
        this.extraversion = extraversion;
    }

    public void setAgreeableness(int agreeableness) {
        this.agreeableness = agreeableness;
    }

    public void setConscientiousness(int conscientiousness) {
        this.conscientiousness = conscientiousness;
    }

    public void setOpennessToExperience(int opennessToExperience) {
        this.opennessToExperience = opennessToExperience;
    }

    @Override
    public String toString() {
        return "H:" + honestyHumility + "E:" + emotionality + "X:" + extraversion + "A:" + agreeableness + "C:"
                + conscientiousness + "O" + opennessToExperience;
    }
}

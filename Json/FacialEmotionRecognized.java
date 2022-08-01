package Json;

public class FacialEmotionRecognized
{
    private Mouth mouth;
    private Eyebrows eyebrows;


    private  Emotion emotion;

    public FacialEmotionRecognized(Mouth mouth, Eyebrows eyebrows)
    {
        this.mouth = mouth;
        this.eyebrows = eyebrows;
        detectEmotion();
    }
    private void detectEmotion()
    {
        if(eyebrows == Eyebrows.UPWARD && mouth == Mouth.SMILE)
            emotion = Emotion.SATISFIED;
        else if(eyebrows == Eyebrows.UPWARD && mouth == Mouth.LAUGH)
            emotion = Emotion.HAPPY;
        else if(eyebrows == Eyebrows.UPWARD && mouth == Mouth.NEUTRAL)
            emotion = Emotion.COOL;
        else if(eyebrows == Eyebrows.UPWARD && mouth == Mouth.FROWN)
            emotion = Emotion.SAD;

        else if(eyebrows == Eyebrows.DOWNWARD && (mouth == Mouth.SMILE || mouth == Mouth.LAUGH))
            emotion = Emotion.EVIL_LAUGH;
        else if(eyebrows == Eyebrows.DOWNWARD && (mouth == Mouth.NEUTRAL || mouth == Mouth.FROWN))
            emotion = Emotion.ANGRY;

        else if(eyebrows == Eyebrows.RAISED && mouth == Mouth.SMILE)
            emotion = Emotion.SATISFIED;
        else if(eyebrows == Eyebrows.RAISED && mouth == Mouth.LAUGH)
            emotion = Emotion.HAPPY;
        else if(eyebrows == Eyebrows.RAISED && (mouth == Mouth.NEUTRAL || mouth == Mouth.FROWN))
            emotion = Emotion.COOL;

        else if(eyebrows == Eyebrows.NEUTRAL && mouth == Mouth.SMILE)
            emotion = Emotion.SATISFIED;
        else if(eyebrows == Eyebrows.NEUTRAL && mouth == Mouth.LAUGH)
            emotion = Emotion.HAPPY;
        else if(eyebrows == Eyebrows.NEUTRAL && mouth == Mouth.NEUTRAL)
            emotion = Emotion.NEUTRAL;
        else if(eyebrows == Eyebrows.NEUTRAL && mouth == Mouth.FROWN)
            emotion = Emotion.SAD;

    }
    public enum Emotion
    {
        NEUTRAL(":-|"),HAPPY(":-)"),ANGRY(">:O"),SAD(":-("),SATISFIED("8-)"),EVIL_LAUGH(">:D"),COOL("( ▀ ͜ ▀) ");

        private String emo;
        private Emotion(String emo)
        {
            this.emo = emo;
        }
        @Override
        public String toString() {
            return super.toString() + " " +  emo;
        }

        public String getEmo() {
            return emo;
        }
    }
    public Emotion getEmotion() {
        return emotion;
    }

}

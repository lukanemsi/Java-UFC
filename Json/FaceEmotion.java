package Json;

public class FaceEmotion
{
    private Mouth mouth;
    private Eyebrows eyebrows;
    public FaceEmotion(Mouth mouth,Eyebrows eyebrows)
    {
        this.mouth = mouth;
        this.eyebrows = eyebrows;
    }

    public Mouth getMouth() {
        return mouth;
    }

    public Eyebrows getEyebrows() {
        return eyebrows;
    }
}

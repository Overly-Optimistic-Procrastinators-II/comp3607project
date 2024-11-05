package comp3607project;

public class LightOffCommand implements CommandInterface
{
    Light light;

    public LightOffCommand(Light light)
    {
        this.light = light;
    }

    public void execute()
    {
        light.switchOff();
    }
}

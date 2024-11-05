package comp3607project;

public class LightOnCommand implements CommandInterface
{
    Light light;

    public LightOnCommand(Light light)
    {
        this.light = light;
    }

    public void execute()
    {
        light.switchOn();
    }
}

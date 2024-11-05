package comp3607project;

public class Light
{

    private boolean on;
    Light light;
    
    public Light()
    {
        light = new Light();
    }

    public boolean switchOn()
    {
        on = true;
        return on;
    }

    public boolean switchOff()
    {
        on = false;
        return on;
    }
}

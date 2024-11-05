package comp3607project;

import java.util.Enumeration;
import java.util.Vector;

public class GUI implements GUI_interface
{
    @SuppressWarnings("unused")
    private boolean subjectState;
    @SuppressWarnings("rawtypes")
    private Vector listeners = new Vector();

    public void alert()
    {
        for(Enumeration e =  listeners.elements(); e.hasMoreElements(); )
        {
            ((Observer) e.nextElement()).alert();
        }

    }

    @SuppressWarnings("unchecked")
    public void register(Observer o)
    {
        listeners.addElement(o);
    }
}

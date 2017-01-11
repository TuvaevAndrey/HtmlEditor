package listeners;

import mvc.View;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Андрей on 20.10.2016.
 */
public class FrameListener extends WindowAdapter
{
    private View view;

    public FrameListener(View view)
    {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        view.exit();
    }
}

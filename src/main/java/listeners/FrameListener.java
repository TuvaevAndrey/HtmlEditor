package listeners;

import mvc.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class FrameListener extends WindowAdapter {
    private final View view;

    public FrameListener(View view) {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        view.exit();
    }
}

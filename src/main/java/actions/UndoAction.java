package actions;

import mvc.View;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class UndoAction extends AbstractAction
{
    private final View view;
    public UndoAction(View view)
    {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        view.undo();
    }
}

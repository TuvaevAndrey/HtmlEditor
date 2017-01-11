package actions;

import mvc.View;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Андрей on 25.10.2016.
 */
public class RedoAction extends AbstractAction
{
    private View view;
    public RedoAction(View view)
    {
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        view.redo();
    }
}

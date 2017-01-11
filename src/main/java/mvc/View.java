package mvc;

import listeners.UndoListener;
import listeners.TabbedPaneChangeListener;
import listeners.FrameListener;
import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Андрей on 20.10.2016.
 */
public class View extends JFrame implements ActionListener
{
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public UndoListener getUndoListener()
    {
        return undoListener;
    }

    public View()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e)
        {
            ExceptionHandler.log(e);
        }

    }

    public void undo()
    {
        try
        {
            undoManager.undo();
        }
        catch (CannotUndoException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public void redo()
    {

        try
        {
            undoManager.redo();
        }
        catch (CannotRedoException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo()
    {
        undoManager.discardAllEdits();
    }

    public boolean canUndo()
    {
        return undoManager.canUndo();
    }

    public boolean canRedo()
    {
        return undoManager.canRedo();
    }

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if(s.equals("New")) controller.createNewDocument();
        else if(s.equals("Open")) controller.openDocument();
        else if(s.equals("Save")) controller.saveDocument();
        else if(s.equals("Save as...")) controller.saveDocumentAs();
        else if(s.equals("Exit"))controller.exit();
        else if(s.equals("About")) showAbout();

    }

    public void init()
    {
        initGui();
        FrameListener listener = new FrameListener(this);
        this.addWindowListener(listener);

        this.setVisible(true);
    }

    public void exit()
    {
        controller.exit();
    }

    public void initMenuBar()
    {
        JMenuBar bar = new JMenuBar();
        MenuHelper.initFileMenu(this, bar);
        MenuHelper.initEditMenu(this, bar);
        MenuHelper.initStyleMenu(this, bar);
        MenuHelper.initAlignMenu(this, bar);
        MenuHelper.initColorMenu(this, bar);
        MenuHelper.initFontMenu(this, bar);
        MenuHelper.initHelpMenu(this, bar);
        getContentPane().add(bar, BorderLayout.NORTH);
    }

    public void initEditor()
    {

        htmlTextPane.setContentType("text/html");
        JScrollPane pane1 = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", pane1);
        JScrollPane pane2 = new JScrollPane(plainTextPane);
        tabbedPane.add("Text", pane2);
        tabbedPane.setPreferredSize(new Dimension(800, 600));
        TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(listener);
        Container container = getContentPane();
        container.add(tabbedPane, BorderLayout.CENTER);

    }

    public void initGui()
    {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged()
    {
        switch (tabbedPane.getSelectedIndex()){
            case 0:controller.setPlainText(plainTextPane.getText());break;
            case 1:plainTextPane.setText(controller.getPlainText());break;
        }
        resetUndo();
    }
    public boolean isHtmlTabSelected()
    {
        return tabbedPane.getSelectedIndex()==0;
    }
    public void selectHtmlTab()
    {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }
    public void update()
    {
        htmlTextPane.setDocument(controller.getDocument());
    }
    public void showAbout()
    {
        JOptionPane.showMessageDialog(getContentPane(),"This is a simple editor","About",JOptionPane.INFORMATION_MESSAGE);
    }
}

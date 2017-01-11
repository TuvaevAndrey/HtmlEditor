package mvc;

import javax.swing.filechooser.FileFilter;
import java.io.File;


public class HTMLFileFilter extends FileFilter
{
    @Override
    public boolean accept(File f)
    {
        String fileName = f.getName().toLowerCase();
        return (fileName.endsWith(".htm")|| fileName.endsWith(".html") || f.isDirectory());
    }

    @Override
    public String getDescription()
    {
        return "HTML и HTM files";
    }
}

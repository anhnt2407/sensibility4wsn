package br.cin.ufpe.sensibility.gui.data;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author avld
 */
public abstract class DataJPanel<T> extends JPanel implements Cloneable
{
    public abstract T getData();
    public abstract void addRemoveActionListener( ActionListener action );
    
    @Override
    public DataJPanel clone() throws CloneNotSupportedException
    {
        return (DataJPanel) super.clone();
    }
}

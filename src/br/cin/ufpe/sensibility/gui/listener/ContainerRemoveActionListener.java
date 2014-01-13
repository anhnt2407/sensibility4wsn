package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.ContainerJPanel;
import br.cin.ufpe.sensibility.gui.data.DataJPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author avld
 */
public class ContainerRemoveActionListener implements ActionListener
{
    private ContainerJPanel container;
    private DataJPanel panel;
    
    public ContainerRemoveActionListener( ContainerJPanel container , DataJPanel panel )
    {
        this.container = container;
        this.panel = panel;
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        this.container.removePanel( this.panel );
    }
    
}

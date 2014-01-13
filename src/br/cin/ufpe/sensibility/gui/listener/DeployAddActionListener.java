package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.ContainerJPanel;
import br.cin.ufpe.sensibility.gui.data.DataJPanel;
import br.cin.ufpe.sensibility.gui.data.DeployJPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author avld
 */
public class DeployAddActionListener implements ActionListener
{
    private ContainerJPanel container;
    
    public DeployAddActionListener( ContainerJPanel container )
    {
        this.container = container;
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        try
        {
            DataJPanel obj = new DeployJPanel();
            container.addPanel( obj );
        }
        catch ( Exception ex )
        {
            JOptionPane.showMessageDialog( null , ex );
        }
    }
    
}

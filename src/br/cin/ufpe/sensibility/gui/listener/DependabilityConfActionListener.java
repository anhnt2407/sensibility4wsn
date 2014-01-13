package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.DependabilityConfJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 *
 * @author avld
 */
public class DependabilityConfActionListener implements ActionListener
{
    private DependabilityConfJDialog dialog;
    
    public DependabilityConfActionListener()
    {
        dialog = new DependabilityConfJDialog( null );
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        dialog.setVisible( true );
        dialog.dispose();
    }
    
    public void setMap( Map<String,String> confMap )
    {
        dialog.setMap( confMap );
    }
    
    public Map<String,String> getMap()
    {
        return dialog.getMap();
    }
}

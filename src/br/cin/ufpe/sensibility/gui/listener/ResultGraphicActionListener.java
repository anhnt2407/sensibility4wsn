package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.result.ResultJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author avld
 */
public class ResultGraphicActionListener implements ActionListener
{
    
    public ResultGraphicActionListener()
    {
        
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        ResultJDialog dialog = new ResultJDialog( null );
        dialog.setVisible( true );
        dialog.dispose();
    }
    
}

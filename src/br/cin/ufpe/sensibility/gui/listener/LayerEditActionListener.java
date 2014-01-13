package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.LayerJDialog;
import br.cin.ufpe.sensibility.gui.PropertyJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author avld
 */
public class LayerEditActionListener extends MouseAdapter implements ActionListener
{
    private boolean isNode;
    private LayerJDialog layer;
    
    public LayerEditActionListener( LayerJDialog layer , boolean isNode )
    {
        this.layer = layer;
        this.isNode = isNode;
    }
    
    @Override
    public void mouseClicked( MouseEvent e )
    {
        if( e.getClickCount() >= 2 )
        {
            actionPerformed( null );
        }
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        try
        {
            process();
        }
        catch(Exception err)
        {
            JOptionPane.showMessageDialog( null , err );
        }
    }
    
    private void process() throws Exception
    {
        Map.Entry<String,String> entry = layer.getEntrySelected( isNode );
        
        PropertyJDialog dialog = new PropertyJDialog( layer );
        dialog.setKeyAndValue( entry.getKey() , entry.getValue() );
        dialog.setVisible( true );
        
        if( dialog.isSalvou() )
        {
            entry = dialog.getKeyAndValue();
            layer.setEntrySelected( isNode , entry );
        }
        
        dialog.dispose();
        dialog = null;
    }
    
}
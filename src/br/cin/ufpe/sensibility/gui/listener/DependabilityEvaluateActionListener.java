package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.DependabilityJDialog;
import br.cin.ufpe.wsn2cpn.Topology;
import br.cin.ufpe.wsn2rbd.Wsn2Rbd;
import br.cin.ufpe.wsn2rbd.xml.RbdFile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.modcs.tools.rbd.blocks.RBDModel;

/**
 *
 * @author avld
 */
public class DependabilityEvaluateActionListener implements ActionListener
{
    private DependabilityJDialog dialog;
    
    public DependabilityEvaluateActionListener( DependabilityJDialog dialog )
    {
        this.dialog = dialog;
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        Topology top = dialog.getTopology();
        int sender = dialog.getSenderId();
        int receiver = dialog.getReceiverId();
        
        dialog.getListModel().clear();
        
        for( int i = 0 ; i < dialog.getTime() ; i++ )
        {
            double value = evaluate( top , sender , receiver );
            dialog.getListModel().add( value );
        }
        
        dialog.calculateMean();
    }
    
    private double evaluate( Topology top , int sender , int receiver )
    {
        Wsn2Rbd rbd = new Wsn2Rbd( top );
        RBDModel model = null;
        
        int counter = 0;
        do
        {
            model = rbd.convert( sender , receiver );
            counter++;
        }
        while( model.getModel() != null && counter < 10 );
        
        try
        {
            RbdFile.save( "./rbd.xml" , model );
        }
        catch( Exception err )
        {
            err.printStackTrace();
        }
        
        return rbd.evaluate( model );
    }
    
}

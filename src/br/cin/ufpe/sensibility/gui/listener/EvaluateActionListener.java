package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.SensibilityEvaluate;
import br.cin.ufpe.sensibility.file.SensibilityFile;
import br.cin.ufpe.sensibility.gui.ProgressJDialog;
import br.cin.ufpe.sensibility.gui.SensibilityJFrame;
import br.cin.ufpe.sensibility.model.Sensibility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author avld
 */
public class EvaluateActionListener implements ActionListener
{
    private SensibilityJFrame frame;
    
    public EvaluateActionListener( SensibilityJFrame frame )
    {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        try
        {
            // -----------------------------
            Sensibility sens = frame.getSensibility();
            SensibilityFile.save( "./sensibility.xml" , sens );
            
            ProgressJDialog dialog = new ProgressJDialog( frame );
            
            // -----------------------------
            SensibilityEvaluate evaluator = new SensibilityEvaluate( sens , dialog );
            Thread thread = new Thread( evaluator );
            thread.start();
            
            dialog.setVisible( true );
        }
        catch( Exception err )
        {
            err.printStackTrace();
            JOptionPane.showMessageDialog( frame , err );
        }
    }
    
}

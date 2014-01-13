package br.cin.ufpe.sensibility.gui;

import br.cin.ufpe.sensibility.gui.data.DataJPanel;
import br.cin.ufpe.sensibility.gui.listener.ContainerRemoveActionListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author avld
 */
public class ContainerJPanel<T> extends javax.swing.JPanel
{
    private JPanel containerJPanel;
    private List<DataJPanel> panelList;
    
    /**
     * Creates new form ContainerJPanel
     */
    public ContainerJPanel()
    {
        initComponents();
        init();
    }

    private void init()
    {
        panelList = new ArrayList<>();
        
        // ---------------
        
        containerJPanel = new JPanel();
        containerJPanel.setLayout( new BoxLayout( containerJPanel , BoxLayout.Y_AXIS ) );
        jScrollPane1.setViewportView( containerJPanel );
    }
    
    public void addButtonActionListener( ActionListener action )
    {
        addJButton.addActionListener( action );
    }
    
    public void addPanel( DataJPanel<T> panel )
    {
        panel.addRemoveActionListener( new ContainerRemoveActionListener( this , panel ) );
        
        panelList.add( panel );
        containerJPanel.add( panel );
        
        updateUI();
    }
    
    public void removePanel( DataJPanel<T> panel )
    {
        panelList.remove( panel );
        containerJPanel.remove( panel );
        
        updateUI();
    }
    
    public void refresh()
    {
        containerJPanel.removeAll();
        
        for( JPanel panel : panelList )
        {
            containerJPanel.add( panel );
        }
        
        updateUI();
    }
    
    public List<T> getList()
    {
        List<T> list = new ArrayList<>();
        
        for( DataJPanel<T> panel : panelList )
        {
            list.add( panel.getData() );
        }
        
        return list;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        addJButton.setText("add");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 348, Short.MAX_VALUE)
                        .addComponent(addJButton))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addJButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

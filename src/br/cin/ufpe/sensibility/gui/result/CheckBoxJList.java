package br.cin.ufpe.sensibility.gui.result;

import br.cin.ufpe.sensibility.model.Scenario;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author avld
 */
public class CheckBoxJList extends JList
{
    private ScenarioListModel model;
    
    public CheckBoxJList()
    {
        init();
    }
    
    private void init()
    {
        model = new ScenarioListModel();
        setModel( model );
        
        setCellRenderer( new CheckBoxCellRenderer() );
        
        // Define a seleção única para a lista
        setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        // Aqui nós permitimos que as checkboxes sejam marcadas
        // ou desmarcadas com a barra de espaço  
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if( e.getKeyCode() == KeyEvent.VK_SPACE )
                {
                  int index = getSelectedIndex();
                  
                  if(index != -1)
                  {
                    JCheckBox checkbox = (JCheckBox) getModel().getElementAt( index );
                    checkbox.setSelected( !checkbox.isSelected() );
                    repaint();
                  }
                }
            }
        });

        // Aqui nós permitimos que as checkboxes sejam marcadas
        // ou desmarcadas com o mouse   
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e)
            {
                int index = locationToIndex(e.getPoint());
                if(index != -1)
                {
                    JCheckBox checkbox = (JCheckBox) getModel().getElementAt( index );
                    checkbox.setSelected( !checkbox.isSelected() );
                    repaint();
                }
            }
        });
    }
    
    public void setMap( Map<String, Scenario> map )
    {
        model.setMap( map );
    }
    
    public void addMap( Map<String, Scenario> map )
    {
        model.addMap( map );
    }
    
    public List<Scenario> getScenarioSelected()
    {
        return model.getScenarioSelected();
    }
    
    /**
     * Classe personalizada que permite que os itens
     * da lista sejam exibidos como 
     * JCheckBoxesclass 
     */
    class CheckBoxCellRenderer implements ListCellRenderer
    {
        Border noFocusBorder = new EmptyBorder( 1 , 1 , 1 , 1 );
        
        @Override
        public Component getListCellRendererComponent( JList list , Object value , int index , boolean isSelected , boolean cellHasFocus )
        {
            JCheckBox checkbox = (JCheckBox) value;
            checkbox.setBackground( isSelected ? list.getSelectionBackground() : list.getBackground() );
            //checkbox.setForeground( isSelected ? list.getSelectionForeground() : list.getForeground() );
            checkbox.setEnabled( list.isEnabled() );
            checkbox.setFont( list.getFont() );
            checkbox.setFocusPainted( false );
            checkbox.setBorderPainted( true );
            checkbox.setBorder(isSelected ? UIManager.getBorder( "List.focusCellHighlightBorder" ) : noFocusBorder );
            
            return checkbox;
        }
    }
}

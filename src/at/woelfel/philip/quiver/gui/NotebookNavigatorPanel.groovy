package at.woelfel.philip.quiver.gui

import java.awt.BorderLayout

import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTree
import javax.swing.border.TitledBorder

import at.woelfel.philip.quiver.objects.Notebook

class NotebookNavigatorPanel extends JPanel {
	
	Notebook notebook
	
	JLabel nameLabel
	JTree notebookTree
	NotebookTreeModel notebookTreeModel
	
	public NotebookNavigatorPanel(){
		this(null);
	}
	
	public NotebookNavigatorPanel(Notebook nb){
		notebook = nb;
		
		setLayout(new BorderLayout())
		
		nameLabel = new JLabel(nb.name)
		nameLabel.setBorder(new TitledBorder("Notebook"))
		add(nameLabel, BorderLayout.NORTH)
		
		notebookTreeModel = new NotebookTreeModel(notebook)
		notebookTree = new JTree(notebookTreeModel)
		notebookTree.setCellRenderer(new NotebookTreeCellRenderer())
		
		add(notebookTree, BorderLayout.CENTER)
		
	}
}

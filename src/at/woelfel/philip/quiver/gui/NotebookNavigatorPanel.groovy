package at.woelfel.philip.quiver.gui

import java.awt.BorderLayout

import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTree
import javax.swing.border.TitledBorder
import javax.swing.event.TreeSelectionEvent
import javax.swing.event.TreeSelectionListener

import at.woelfel.philip.quiver.objects.Note
import at.woelfel.philip.quiver.objects.Notebook

class NotebookNavigatorPanel extends JPanel implements TreeSelectionListener {
	
	Notebook notebook
	
	JTree notebookTree
	NotebookTreeModel notebookTreeModel
	
	List<NotebookNavListener> navListener = []
	
	public NotebookNavigatorPanel(){
		this(null);
	}
	
	public NotebookNavigatorPanel(Notebook nb){
		notebook = nb;
		
		setLayout(new BorderLayout())
		
		notebookTreeModel = new NotebookTreeModel(notebook)
		notebookTree = new JTree(notebookTreeModel)
		notebookTree.setCellRenderer(new NotebookTreeCellRenderer())
		
		notebookTree.addTreeSelectionListener(this)
		
		add(new JScrollPane(notebookTree), BorderLayout.CENTER)
		
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		def node = e.getPath().getLastPathComponent()
		if(node instanceof Note){
			fireNoteSelected(node)
		}
		if(node instanceof Notebook){
			fireNotebookSelected(node)
		}
	}
	
	private fireNoteSelected(Note n){
		navListener.each{ l ->
			l.onNoteSelected(n)
		}
	}
	
	private fireNotebookSelected(Notebook n){
		navListener.each{ l ->
			l.onNotebookSelected(n)
		}
	}
	
	public addNotebookNavListener(NotebookNavListener listener){
		navListener.add(listener)
	}
	
	public removeNotebookNavListener(NotebookNavListener listener){
		navListener.remove(listener)
	}
}

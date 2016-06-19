package at.woelfel.philip.quiver.gui

import java.awt.Component

import javax.swing.JTree
import javax.swing.tree.DefaultTreeCellRenderer

import at.woelfel.philip.quiver.objects.Cell
import at.woelfel.philip.quiver.objects.Note
import at.woelfel.philip.quiver.objects.Notebook

class NotebookTreeCellRenderer extends DefaultTreeCellRenderer {

	public NotebookTreeCellRenderer(){
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		
        if(value){
			if(value instanceof Notebook){
				setText(((Notebook)value).name)
			}
			else if(value instanceof Note){
				setText(((Note)value).title)
			}
			else if(value instanceof Cell){
				setText("Cell (${((Cell)value).type})")
			}
		} 

		return this
	}
}

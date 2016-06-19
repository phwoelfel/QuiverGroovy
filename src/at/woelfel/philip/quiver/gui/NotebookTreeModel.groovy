package at.woelfel.philip.quiver.gui

import javax.swing.event.TreeModelListener
import javax.swing.tree.TreeModel
import javax.swing.tree.TreePath

import at.woelfel.philip.quiver.objects.Cell
import at.woelfel.philip.quiver.objects.Note
import at.woelfel.philip.quiver.objects.Notebook

class NotebookTreeModel implements TreeModel{

	Notebook notebook
	
	public NotebookTreeModel(Notebook nb){
		notebook = nb
	}
	
	@Override
	public Object getRoot() {
		return notebook;
	}

	@Override
	public Object getChild(Object parent, int index) {
		if(parent instanceof Notebook){
			return ((Notebook)parent)?.notes?.getAt(index)
		}
		if(parent instanceof Note){
			return ((Note)parent)?.cells?.getAt(index)
		}
		
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if(parent instanceof Notebook){
			return ((Notebook)parent)?.notes?.size()
		}
		if(parent instanceof Note){
			return ((Note)parent)?.cells?.size()
		}
		return 0;
	}

	@Override
	public boolean isLeaf(Object node) {
		if(node instanceof Cell){
			return true;
		}
		return false;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if(parent instanceof Notebook){
			return ((Notebook)parent)?.notes?.indexOf(child)
		}
		if(parent instanceof Note){
			return ((Note)parent)?.cells?.indexOf(child)
		}
		return 0;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
	}

}

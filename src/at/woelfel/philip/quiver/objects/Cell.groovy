package at.woelfel.philip.quiver.objects

import groovy.transform.Sortable

@Sortable
class Cell {
	public enum Type{
		TEXT,
		CODE,
		LATEX,
		DIAGRAM,
		MARKDOWN
	}

	Type type
	
	String data

	public String toString() {
		StringBuilder sb = new StringBuilder()

		sb.append("Cell ($type)\n")
		sb.append("\tData: $data\n")

		return sb.toString()
	}
}

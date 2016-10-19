package at.woelfel.philip.quiver.objects

import at.woelfel.philip.quiver.objects.Cell.Type

/**
 * Created by philip on 19.06.16.
 */
class DiagramCell extends Cell {
	
	public enum DiagramType {
		SEQUENCE,
		FLOWCHART
	}
	
	DiagramType diagramType
	
	public DiagramCell(){
		type = Type.DIAGRAM
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder()

		sb.append("Diagram Cell\n")
		sb.append("\tDiagram Type: $diagramType\n")
		sb.append("\tData: $data\n")

		return sb.toString()
	}
}

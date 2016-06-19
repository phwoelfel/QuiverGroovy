package at.woelfel.philip.quiver.objects

class Cell {
	public enum Type{
		TEXT,
		CODE,
		LATEX,
		DIAGRAM,
		MARKDOWN
	}

	Type type
	
	def data

	public String toString() {
		StringBuilder sb = new StringBuilder()

		sb.append("Cell ($type)\n")
		sb.append("\tData: $data\n")

		return sb.toString()
	}
}

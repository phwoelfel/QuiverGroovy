package at.woelfel.philip.quiver.objects

import at.woelfel.philip.quiver.objects.Cell.Type

class CodeCell extends Cell {
	def language

	public CodeCell(){
		type = Type.CODE
	}

	public String toString() {
		StringBuilder sb = new StringBuilder()

		sb.append("Code Cell\n")
		sb.append("\tLanguage: $language\n")
		sb.append("\tData: $data\n")

		return sb.toString()
	}
}

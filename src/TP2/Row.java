package TP2;
public class Row implements Comparable<Row> {
		
	private List<TableCell> cells = new List<TableCell>();
	
	public Row (List<TableCell> cells) {
		this.cells = cells;
	}

	@Override
	public int compareTo(Row r) {
		int diff = cells.getLength() - r.getCells().getLength();
		if (diff > 0) return 1;
		if (diff < 0) return -1;
		return diff;
	}
	
	@Override
	public String toString() {
		String r = "[Row: ";
		for (int i = 0; i < cells.getLength(); i++)
			try {
				r += cells.elementAt(i).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return r + "]";
	}
	
	@Override
	public Row clone() {
		return new Row(cells.clone());
	}
	
	public List<TableCell> getCells() {
		return cells;
	}
	
	public void addCell(TableCell c) {
		cells.push(c);
	}
	
	public TableCell removeCell() throws Exception {
		return cells.pop();
	}
	
	public TableCell substituteCellAt(TableCell c, int columnIdx) throws Exception {
		TableCell rc = cells.removeAt(columnIdx);
		cells.insertAt(c, columnIdx);
		return rc;
	}
	
	public int getNumberOfCells() {
		return cells.getLength();
	}
	
	public String printToString(int[] columnWidths, String[] alignments) throws Exception {
		if (columnWidths.length != getNumberOfCells()) throw new Exception("O numero de regras de comprimento deve ser igual ao numero de celulas (" + getNumberOfCells() + ").");
		
		String r = "";
		for (int j = 0; j < getMaxHeight(); j++) {
			for (int i = 0; i < cells.getLength(); i++) {
				String content = cells.elementAt(i).getContent().split("\n")[j];
				if (alignments[i] == "left")
					content += Table.repeat(" ", columnWidths[i] - content.length());
				else if (alignments[i] == "center")
					content = Table.repeat(" ", columnWidths[i] - content.length()/2) + content + Table.repeat(" ", columnWidths[i] - content.length()/2);
				else
					content = Table.repeat(" ", columnWidths[i] - content.length()) + content;
				r += "|" + content;
			}
			r += "|\n";
		}
		
		return r;
	}
	
	public int getMaxHeight() throws Exception {
		int maxHeight = 0;
		for (int i = 0; i < cells.getLength(); i++)
			maxHeight = Math.max(maxHeight, cells.elementAt(i).getHeight());
		return maxHeight;
	}
	
}
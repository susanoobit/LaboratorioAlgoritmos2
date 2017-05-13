package TP1;
public class Table {
	
	private int numberOfColumns = 1;
	private Row header;
	private List<Row> rows = new List<Row>();
	
	public Table (int numberOfColumns, Row header) throws Exception {
		this.numberOfColumns = numberOfColumns;
		validateRow(header);
		this.header = header;
	}
	
	public Table (int numberOfColumns, Row header, List<Row> rows) throws Exception {
		this.numberOfColumns = numberOfColumns;
		validateRow(header);
		this.header = header;
		for (int i = 0; i < rows.getLength(); i++)
			validateRow(rows.elementAt(i));
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		String r = "[Table: ";
		for (int i = 0; i < rows.getLength(); i++)
			try {
				r += rows.elementAt(i).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return r + "]";
	}
	
	@Override
	public Table clone() {
		try {
			return new Table(numberOfColumns, header, rows.clone());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getNumberOfColumns() {
		return this.numberOfColumns;
	}
	
	public void setHeader(Row header) throws Exception {
		validateRow(header);
		this.header = header;
	}
	
	public void addRow(Row r) throws Exception {
		if (validateRow(r));
			rows.push(r);
	}
	
	public void addRowAt(Row r, int rowIdx) throws Exception {
		if (validateRow(r));
			rows.insertAt(r, rowIdx);
	}
	
	public Row removeRow() throws Exception {
		return rows.pop();
	}
	
	public Row removeRowAt(int rowIdx) throws Exception {
		return rows.removeAt(rowIdx);
	}
	
	public String printToString() throws Exception {
		int[] columnWidths = new int[numberOfColumns];
		String[] columnAlignments = new String[numberOfColumns];
		for (int i = 0; i < columnWidths.length; columnWidths[i] = 0, i++);
		for (int i = 0; i < columnWidths.length; i++) {
			for (int j = 0; j < rows.getLength(); j++) {
				columnWidths[i] = Math.max(columnWidths[i], rows.elementAt(j).getCells().elementAt(i).getWidth());
				columnWidths[i] = Math.max(columnWidths[i], header.getCells().elementAt(i).getWidth());
				try {
					Integer.parseInt(rows.elementAt(j).getCells().elementAt(i).getContent());
					Float.parseFloat(rows.elementAt(j).getCells().elementAt(i).getContent());
					columnAlignments[i] = "right";
				}
				catch (Exception e) {
					columnAlignments[i] = "left";
				}
			}
		}
		
		String r = this.header.printToString(columnWidths, columnAlignments);
		String rowSeparator = "+" + repeat("-", r.length() - numberOfColumns) + "+";
		r = rowSeparator + "\n" + r + rowSeparator;
		for (int i = 0; i < rows.getLength(); i++)
			r += "\n" + rows.elementAt(i).printToString(columnWidths, columnAlignments) + rowSeparator;
		return r;
	}
	
	private boolean validateRow(Row r) throws Exception {
		if (r.getNumberOfCells() != numberOfColumns) throw new Exception("Esta tabela aceita linhas com ate " + numberOfColumns + " celulas.");
		return true;
	}
	
	public static String repeat(String s, int times) {
		String r = "";
		for (int i = 0; i < times; r += s, i++);
		return r;
	}
}

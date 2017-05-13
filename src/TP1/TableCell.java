package TP1;
import java.util.Arrays;

public class TableCell implements Comparable<TableCell> {
			
	private int height = 0;
	private int width = 0;
	private String content = "";
	
	public TableCell(String content) {
		this.content = content;
		height = content.split("\n").length;
		width =
			Arrays.stream(content.split("\n"))
				.mapToInt(s -> s.length())
				.max()
				.getAsInt();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
		height = content.split("\n").length;
		width = content.length();
	}

	@Override
	public int compareTo(TableCell c) {
		return content.compareTo(c.content);
	}
	
	@Override
	public String toString() {
		return "[Cell: " + content + "]";
	}
	
	@Override
	public TableCell clone() {
		return new TableCell(content);
	}
}
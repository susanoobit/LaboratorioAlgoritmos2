package TP2;

public interface Tree {
	
	public int getHeight ();
	public boolean isEmpty ();
	public void insert (int item);
	public int remove (int item);
	public void show ();
	public void showPrevious ();
	public void showPosterior ();
}

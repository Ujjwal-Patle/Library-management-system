package LibraryManagementSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Library implements Serializable {
	private List<Book> books;
	private List<Patron> patrons;

	public Library() {
		this.books = new ArrayList<>();
		this.patrons = new ArrayList<>();
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void addPatron(Patron patron) {
		patrons.add(patron);
	}

	public void displayBooks() {
		for (Book book : books) {
			System.out.println(book);
		}
	}

	public void displayPatrons() {
		for (Patron patron : patrons) {
			System.out.println("Patron: " + patron.getName());
			System.out.println("Borrowed Books:");
			for (Book book : patron.getBorrowedBooks()) {
				System.out.println(" - " + book.getTitle());
			}
			System.out.println();
		}
	}

	public void saveLibraryData() {

		try (FileOutputStream fos = new FileOutputStream("D:\\aa.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(this);
			System.out.println("Library data saved successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Library loadLibraryData() {
		Library library = null;
		try (FileInputStream fis = new FileInputStream("D:\\aa.txt");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			library = (Library) ois.readObject();
			System.out.println("Library data loaded successfully.");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return library;
	}

	public static void main(String[] args) {
		Library library = null;

		library = loadLibraryData();

		if (library == null) {
			library = new Library();
			library.addBook(new Book("Java Programming", "Ramesh Jha"));
			library.addBook(new Book("Python Basics", "Ram "));
			library.addPatron(new Patron("Anil"));
			library.addPatron(new Patron("suresh"));
		}

		System.out.println("Books in the Library:");
		library.displayBooks();

		System.out.println("\nPatrons in the Library:");
		library.displayPatrons();

		library.saveLibraryData();
	}
}

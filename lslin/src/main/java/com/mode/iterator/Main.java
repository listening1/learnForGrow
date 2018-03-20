package com.mode.iterator;

public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(3);
        bookShelf.appendBook(new Book("1"));
        bookShelf.appendBook(new Book("2"));
        bookShelf.appendBook(new Book("3"));
//        bookShelf.appendBook(new Book("4"));

        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()){
            Book next = (Book) iterator.next();
            System.out.println(next.getName());
        }
        BookShelfIterator iterator1 = new BookShelfIterator(bookShelf);
        System.out.println(iterator1.hasNext());
        System.out.println(bookShelf.getLength());
    }
}

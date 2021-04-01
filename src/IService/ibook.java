/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entity.Book;

/**
 *
 * @author Aoe Neko 98
 */
public interface ibook {
    public void addBook(Book b);
    public void editBook(Book b);
    public void deleteBook(Book b);
    public Book showBook(Book b);

}
//
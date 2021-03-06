import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom';
import LibraryService from "../../repository/libraryService";
import Header from "../Header/header";
import Authors from "../Authors/AuthorList/authors";
import Categories from "../Categories/categories";
import Countries from "../Countries/countries";
import Books from "../Books/BookList/books";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import AuthorAdd from "../Authors/AuthorAdd/authorAdd";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            authors: [],
            books: [],
            categories: [],
            countries: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Route path={"/books/add"} exact
                               render={() => <BookAdd categories={this.state.categories}
                                                      authors={this.state.authors}
                                                      onAddBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} exact render={() =>
                            <BookEdit categories={this.state.categories}
                                      authors={this.state.authors}
                                      onEditBook={this.editBook}
                                      book={this.state.selectedBook}/>}/>
                        <Route path={"/books"} exact render={() =>
                            <Books books={this.state.books}
                                   onDelete={this.deleteBook}
                                   onMarkAsTaken={this.markAsTaken}
                                   onEdit={this.getBook}
                            />}/>
                        <Route path={"/authors/add"} exact render={() =>
                            <AuthorAdd countries={this.state.countries}
                                       onAddAuthor={this.addAuthor}/>}/>
                        <Route path={"/authors"} exact
                               render={() => <Authors authors={this.state.authors}/>}/>
                        <Route path={"/categories"} exact
                               render={() => <Categories categories={this.state.categories}/>}/>
                        <Route path={"/countries"} exact
                               render={() => <Countries countries={this.state.countries}/>}/>
                        <Redirect to={"/books"}/>
                    </div>
                </main>
            </Router>
        )
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            })
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            })
    }

    loadCountries = () => {
        LibraryService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            })
    }
    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            })
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            })
    }

    markAsTaken = (id) => {
        LibraryService.markAsTaken(id)
            .then(() => {
                this.loadBooks();
            })
    }

    addBook = (name, category, author, availableCopies) => {
        LibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }

    addAuthor = (name, surname, country) => {
        LibraryService.addAuthor(name, surname, country)
            .then(() => {
                this.loadAuthors();
            })
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, author, availableCopies) => {
        LibraryService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }

    componentDidMount() {
        this.loadAuthors();
        this.loadCategories();
        this.loadCountries();
        this.loadBooks();
    }
}

export default App;

import axios from '../custom-axios/axios'; //za site povici da gi zema od baseUrl

const LibraryService = {

    //pravi get baranje
    fetchBooks: () => {
        return axios.get("/books");
    },
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    },
    fetchCountries: () => {
        return axios.get("/countries");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`)
    },
    markAsTaken: (id) => {
        return axios.put(`/books/taken/${id}`)
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post("/books/add", { //so data se zadava @RequestBody -> ona sto ke go ima vo dto
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        })
    },
    editBook: (id, name, category, author, availableCopies) => {
        // edit-iranje so put request
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    addAuthor: (name, surname, country) => {
        return axios.post("/authors/add", {
            "name": name,
            "surname": surname,
            "country": country
        });
    }

}

export default LibraryService;
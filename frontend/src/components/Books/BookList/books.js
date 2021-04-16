import React from "react";
import BookTerm from "../BookTerm/bookTerm";
import ReactPaginate from 'react-paginate';
import {Link} from 'react-router-dom';

class Books extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            size: 5 // kolku produkti na edna strana
        }

    }
    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size);
        const books = this.getBooksPage(offset, nextPageOffset);

        return(
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Category</th>
                            <th scope={"col"}>Author's name</th>
                            <th scope={"col"}>Author's surname</th>
                            <th scope={"col"}>Available Copies</th>
                            <th scope={"col"}>Delete Book</th>
                            <th scope={"col"}>Mark As Taken</th>
                            <th scope={"col"}>Edit</th>

                        </tr>
                        </thead>
                        <tbody>
                        {books}
                        </tbody>
                    </table>
                </div>
                <div className="col mb-3">
                    <div className="row">
                        <div className="col-sm-12 col-md-12">
                            <Link className={"btn btn-block btn-dark"} to={"/books/add"}>ADD NEW BOOK</Link>
                        </div>
                    </div>
                </div>
            </div>
            <ReactPaginate previousLabel={"Back"}
                           nextLabel={" Next"}
                           breakLabel={<a href="/#">...</a>}
                           breakClassName={"break-me"}
                           pageClassName={"ml-1"}
                           pageCount={pageCount}
                           marginPagesDisplayed={5}
                           pageRangeDisplayed={5}
                           onPageChange={this.handlePageClick}
                           containerClassName={"pagination m-4 justify-content-center"}
                           activeClassName={"active"}/>
        </div>
        )
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getBooksPage = (offset, nextPageOffset) => {
        return this.props.books.map((term, index) => {
            return (
                <BookTerm term={term}
                          onDelete={this.props.onDelete}
                          onMarkAsTaken={this.props.onMarkAsTaken}
                          onEdit={this.props.onEdit}/>
            );
        }).filter((book, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }

}

// const books = (props) => {
//     return (
//         <div className="container mm-4 mt mt-5">
//             <div className="row">
//                 <div className="row">
//                     <table className={"table table-stripped"}>
//                         <thead>
//                         <tr>
//                             <th scope={"col"}>Name</th>
//                             <th scope={"col"}>Category</th>
//                             <th scope={"col"}>Author's Name</th>
//                             <th scope={"col"}>Author's Surname</th>
//                             <th scope={"col"}>Available Copies</th>
//                         </tr>
//                         </thead>
//                         <tbody>
//                         {props.books.map((term) => {
//                             return (
//                                 <BookTerm term={term} />
//                             );
//                         })}
//                         </tbody>
//                     </table>
//                 </div>
//             </div>
//         </div>
//     )
// }

export default Books;
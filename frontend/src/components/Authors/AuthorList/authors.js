import React from "react";
import {Link} from "react-router-dom";


const authors = (props) => {
    return (
        <div>
            <div className={"container mm-4 mt mt-5"}>
                <div className={"row"}>
                    <div className={"row"}>
                        <table className={"table table-stripped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Surname</th>
                                <th scope={"col"}>Country</th>
                            </tr>
                            </thead>
                            <tbody>
                            {props.authors.map((term) => {
                                return (
                                    <tr>
                                        <td>{term.name}</td>
                                        <td>{term.surname}</td>
                                        <td>{term.country.name}</td>
                                    </tr>
                                )
                            })}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div className="col mb-3">
                <div className="row">
                    <div className="col-sm-12 col-md-12">
                        <Link className={"btn btn-block btn-dark"} to={"/authors/add"}>ADD NEW AUTHOR</Link>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default authors;
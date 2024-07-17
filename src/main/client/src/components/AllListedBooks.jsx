import { useEffect, useState } from "react";
import BookCard from "./BookCard";

function AllListedBooks() {

    const [allBooks, setAllBooks] = useState([])
    useEffect(() => {
        const fetchBooks = async () => {
            const response = await fetch('/api/books');
            const data = await response.json();
            //console.log(data);
            setAllBooks(data);
        }
        fetchBooks();
    }, [])

    return (
        <>
            <main>
            {allBooks.map((book) => {
                return (
                    <BookCard book={book}></BookCard>
                );
            })}
            </main>
        </>
    )
}

export default AllListedBooks;
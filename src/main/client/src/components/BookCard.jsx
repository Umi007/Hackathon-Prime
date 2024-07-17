import Card from "@mui/material/Card";
import CardContent from '@mui/material/CardContent';
import { useEffect, useState } from "react";

function BookCard({book}) {
    console.log(book.isLent, "<<")
    const [buttonText, setButtonText] = !book.isLent ? useState("Borrow Book") : useState("On Loan")
        // TODO: extract user id from logged in user, instead of hard coding in user_id 1 for post loan
    const borrowBookClick = () =>{
        setButtonText("On Loan");

            const postLoan = async () => {
                const response = await fetch(`api/books/${book.id}/loans`, {
                    method: "POST",
                    body: JSON.stringify({
                        "user_id": 1,
                    }),
                    headers: { 
                        "Content-type": "application/json; charset=UTF-8"
                    } 
                });
                console.log("hello")
                console.log(response, "<< response")
                const data = await response.json();
                console.log(data, "<< loan data");
            }
            postLoan();
 
    }

    return (
        <>
            <Card
            variant="outlined"
            >
                <div className="book-card-content">
                    <p>{book.title}</p>
                    <p>{book.author}</p>
                    <p>{book.genre}</p>
                    <p>{book.owner.username}</p>

                    <button onClick={borrowBookClick}>{buttonText}</button>
                </div>
            </Card>
        </>
    )
}

export default BookCard;
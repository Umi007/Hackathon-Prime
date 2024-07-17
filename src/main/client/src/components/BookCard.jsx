import Card from "@mui/material/Card";
import CardContent from '@mui/material/CardContent';

function BookCard({book}) {
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

                    <button onClick={() => console.log('Button clicked!')}>Borrow Book</button>
                </div>
            </Card>
        </>
    )
}

export default BookCard;
# LibraryManagement


## 📚 Library Management System Functionalities

---

### 🔐 User Management
- **Sign Up**: ✍️ New users (students or librarians) can register with an email, password, and role.
- **Login**: 🔑 Authenticates users and generates a JWT token upon success.
- **Role-based Access**: 🎚️ Access to specific features based on user roles (e.g., librarians, students).
- **JWT Token**: 🔒 Ensures secure access with tokens generated upon login.

---

### 📘 Book Management (For Librarians)
- **Add New Book**: ➕ Librarians can add books by specifying title, author, genre, and availability.
- **Update Book Details**: 📝 Modify book details, such as title, genre, author, and status.
- **Delete Book**: 🗑️ Remove books from the catalog.
- **View Book Details**: 📖 View or search the book list using filters.

---

### ✍️ Author Management (For Librarians)
- **Add New Author**: ➕ Add new authors with details like name and biography.
- **Update Author Details**: ✏️ Modify author information.
- **Delete Author**: 🗑️ Remove authors from the list.
- **View Author Details**: 👀 See details of all authors or search for specific ones.

---

### 📚 Genre Management (For Librarians)
- **Add New Genre**: ➕ Introduce a new genre to the catalog (e.g., Fiction, Science).
- **Update Genre**: ✏️ Modify genre information as needed.
- **Delete Genre**: 🗑️ Remove genres from the catalog.
- **View Genres**: 📋 List all genres available in the library.

---

### 📕 Borrowing and Returning Books (For Students)
- **Borrow Book**: 📥 Allows students to borrow available books, storing borrowing details with due dates.
- **Return Book**: 📤 Return borrowed books, with penalty applied for late returns.
- **Penalty Calculation**: 💰 Calculates penalties based on overdue days.
- **Borrowing History**: 📜 View a history of borrowed books with details.

---

### 🔎 Search and Filter Books
- **Search by Title, Author, or Genre**: 🔍 Locate books based on title, author, or genre.
- **Filter by Availability**: 🎛️ Show only available or borrowed books.

---

### 📬 Overdue Notifications
- **Overdue Notifications**: 📧 Sends email reminders for overdue books, notifying users about penalties.

---

### 🔒 Security and Authentication
- **JWT Authentication**: 🛡️ Ensures secure access by requiring a valid JWT token for each request.
- **Spring Security**: 🚨 Manages authentication, authorization, and role-based access.
- **Password Encryption**: 🔑 Uses BCrypt to hash passwords for secure storage.

---

### 🛠️ Additional Features
- **Exception Handling**: ⚠️ Friendly error messages and validation feedback.
- **API Documentation**: 📜 Integrates Swagger for API documentation and testing.
- **Docker Support**: 🐳 Containerizes the application for easy deployment.
- **Testing**: 🧪 Includes unit and integration tests for reliability.

---

This breakdown gives a clear and attractive overview of all functionalities in the Library Management System, ensuring efficient, secure, and user-friendly management of library resources!





## Database Schema

### Entities and Relationships

1. **User**
   - **Attributes**:
     - `userId` (Primary Key)
     - `email` (Unique, Not Null)
     - `password` (Not Null)
     - `role` (Enum - e.g., LIBRARIAN, STUDENT)
     - `isActive` (Boolean, defaults to true)
   - **Relationships**:
     - One-to-Many with `BorrowingRecord` (User can have multiple borrowing records)

2. **Book**
   - **Attributes**:
     - `bookId` (Primary Key)
     - `title` (Not Null)
     - `authorId` (Foreign Key referencing `Author`)
     - `genreId` (Foreign Key referencing `Genre`)
     - `isbn` (Unique identifier)
     - `availabilityStatus` (Boolean indicating if the book is available)
   - **Relationships**:
     - Many-to-One with `Author`
     - Many-to-One with `Genre`
     - One-to-Many with `BorrowingRecord` (Book can have multiple borrowing records)

3. **Author**
   - **Attributes**:
     - `authorId` (Primary Key)
     - `name` (Not Null)
     - `biography` (Optional text)
   - **Relationships**:
     - One-to-Many with `Book` (Author can write multiple books)

4. **Genre**
   - **Attributes**:
     - `genreId` (Primary Key)
     - `name` (Not Null, Unique)
   - **Relationships**:
     - One-to-Many with `Book` (A genre can contain multiple books)

5. **BorrowingRecord**
   - **Attributes**:
     - `borrowId` (Primary Key)
     - `bookId` (Foreign Key referencing `Book`)
     - `userId` (Foreign Key referencing `User`)
     - `borrowDate` (Date when the book was borrowed)
     - `dueDate` (Expected return date)
     - `returnDate` (Date the book was actually returned)
     - `penalty` (Penalty amount if the book is returned late)
   - **Relationships**:
     - Many-to-One with `User`
     - Many-to-One with `Book`

### Relationships Summary
- **User** ↔ **BorrowingRecord**: One-to-Many
- **Book** ↔ **BorrowingRecord**: One-to-Many
- **Book** ↔ **Author**: Many-to-One
- **Book** ↔ **Genre**: Many-to-One

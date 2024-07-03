# Questions 
### Part A: Data Structures

1. **Merge two sorted arrays into one sorted array.**
   - Draw flow chart / algorithm for the marked question and write down its C program.
   - Flow chart / Algorithm - 10 marks, Program - 10 marks, and Output - 10 marks (Total - 30 marks)

2. **Read a sparse matrix and display its triplet representation using an array.**
   - Draw flow chart / algorithm for the marked question and write down its C program.
   - Flow chart / Algorithm - 10 marks, Program - 10 marks, and Output - 10 marks (Total - 30 marks)

3. **Create a singly linked list of `n` nodes and display it.**
   - Draw flow chart / algorithm for the marked question and write down its C program.
   - Flow chart / Algorithm - 10 marks, Program - 10 marks, and Output - 10 marks (Total - 30 marks)

4. **Implement Stack using array.**
   - Draw flow chart / algorithm for the marked question and write down its C program.
   - Flow chart / Algorithm - 10 marks, Program - 10 marks, and Output - 10 marks (Total - 30 marks)

5. **Implement Stack using linked list.**
   - Draw flow chart / algorithm for the marked question and write down its C program.
   - Flow chart / Algorithm - 10 marks, Program - 10 marks, and Output - 10 marks (Total - 30 marks)

### Part B: RDBMS

1. **Create a table `Employee` with fields (EmpID, EName, Salary, Department, and Age).**
   - Draw the structure of the table and write down the SQL statements / program code for the marked question.
   - Table structure - 5 marks, Program code / SQL Statements - 15 marks, Output - 10 marks (Total - 30 marks)

2. **Insert some records. Write SQL queries using aggregate functions and `GROUP BY` clause.**
   - Display the total number of employees.
   - Display the name and age of the oldest employee of each department.
   - Display the average age of employees of each department.
   - Display departments and the average salaries.
   - Display the lowest salary in the employee table.
   - Display the highest salary in the sales department.

3. **Create a table `Product` with the fields (Product_code PRIMARY KEY, Product_Name, Category, Quantity, Price).**
   - Draw the structure of the table and write down the SQL statements / program code for the marked question.
   - Table structure - 5 marks, Program code / SQL Statements - 15 marks, Output - 10 marks (Total - 30 marks)

4. **Insert some records. Write the queries to perform the following:**
   - Display the records in the descending order of `Product_Name`.
   - Display `Product_Code`, `Product_Name` with price between 20 and 50.
   - Display the details of products which belong to the categories of `bathsoap`, `paste`, or `washingpowder`.
   - Display the products whose `Quantity` is less than 100 or greater than 500.
   - Display the products whose names start with 's'.
   - Display the products which do not belong to the category 'paste'.

5. **Write a program code to calculate the area of a circle for a value of radius varying from 3 to 7.**
   - Store the radius and the corresponding value of the calculated area in an empty table named `Areas` with fields `radius` and `area`.

6. **Write a program block to calculate the electricity bill by accepting `cust_no` and `units_consumed`.**

7. **Create a procedure to print Fibonacci numbers up to a limit.**
   - Limit is passed as an argument.
  
---

# Answers 

### Merge Two Sorted Arrays into One Sorted Array

#### Algorithm:
1. Initialize two pointers, `i` and `j`, to 0.
2. Initialize an empty array `result`.
3. Compare elements of both arrays.
   - If element in the first array is smaller, append it to `result` and move the pointer `i`.
   - Otherwise, append the element of the second array and move the pointer `j`.
4. Append the remaining elements from both arrays to `result`.

#### C Program:
```c
#include <stdio.h>

void mergeArrays(int arr1[], int n1, int arr2[], int n2, int result[]) {
    int i = 0, j = 0, k = 0;

    while (i < n1 && j < n2) {
        if (arr1[i] <= arr2[j]) {
            result[k++] = arr1[i++];
        } else {
            result[k++] = arr2[j++];
        }
    }

    while (i < n1) {
        result[k++] = arr1[i++];
    }

    while (j < n2) {
        result[k++] = arr2[j++];
    }
}

int main() {
    int arr1[] = {1, 3, 5, 7};
    int arr2[] = {2, 4, 6, 8};
    int n1 = sizeof(arr1) / sizeof(arr1[0]);
    int n2 = sizeof(arr2) / sizeof(arr2[0]);
    int result[n1 + n2];
    
    mergeArrays(arr1, n1, arr2, n2, result);

    printf("Merged array: ");
    for (int i = 0; i < n1 + n2; i++) {
        printf("%d ", result[i]);
    }

    return 0;
}
```

---

### Read a Sparse Matrix and Display its Triplet Representation using Array

#### Algorithm:
1. Read the dimensions of the matrix and the number of non-zero elements.
2. Store each non-zero element's row index, column index, and value.
3. Print the triplet representation.

#### C Program:
```c
#include <stdio.h>

#define MAX 100

typedef struct {
    int row;
    int col;
    int value;
} Triplet;

int main() {
    int sparseMatrix[MAX][MAX], triplet[MAX], rows, cols, nonZero = 0;
    
    printf("Enter the number of rows and columns: ");
    scanf("%d %d", &rows, &cols);

    printf("Enter the elements of the matrix:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            scanf("%d", &sparseMatrix[i][j]);
            if (sparseMatrix[i][j] != 0) {
                triplet[nonZero].row = i;
                triplet[nonZero].col = j;
                triplet[nonZero].value = sparseMatrix[i][j];
                nonZero++;
            }
        }
    }

    printf("Triplet representation:\n");
    for (int i = 0; i < nonZero; i++) {
        printf("(%d, %d, %d)\n", triplet[i].row, triplet[i].col, triplet[i].value);
    }

    return 0;
}
```

---

### Create a Singly Linked List of n Nodes and Display It

#### Algorithm:
1. Initialize the head to NULL.
2. Create nodes one by one and link them.
3. Traverse the list to display it.

#### C Program:
```c
#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node* next;
} Node;

Node* createNode(int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

void displayList(Node* head) {
    Node* temp = head;
    while (temp != NULL) {
        printf("%d -> ", temp->data);
        temp = temp->next;
    }
    printf("NULL\n");
}

int main() {
    Node* head = NULL;
    Node* temp = NULL;
    int n, data;

    printf("Enter the number of nodes: ");
    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        printf("Enter data for node %d: ", i + 1);
        scanf("%d", &data);
        Node* newNode = createNode(data);
        if (head == NULL) {
            head = newNode;
        } else {
            temp->next = newNode;
        }
        temp = newNode;
    }

    printf("Singly Linked List: ");
    displayList(head);

    return 0;
}
```

---

### Implement Stack using Array

#### Algorithm:
1. Initialize top to -1.
2. Push: Increment top and add the element.
3. Pop: Return the element at top and decrement top.
4. Display: Print all elements from the bottom to the top.

#### C Program:
```c
#include <stdio.h>
#include <stdlib.h>

#define MAX 100

typedef struct {
    int data[MAX];
    int top;
} Stack;

void initStack(Stack* stack) {
    stack->top = -1;
}

int isFull(Stack* stack) {
    return stack->top == MAX - 1;
}

int isEmpty(Stack* stack) {
    return stack->top == -1;
}

void push(Stack* stack, int value) {
    if (isFull(stack)) {
        printf("Stack overflow\n");
        return;
    }
    stack->data[++stack->top] = value;
}

int pop(Stack* stack) {
    if (isEmpty(stack)) {
        printf("Stack underflow\n");
        return -1;
    }
    return stack->data[stack->top--];
}

void display(Stack* stack) {
    if (isEmpty(stack)) {
        printf("Stack is empty\n");
        return;
    }
    for (int i = 0; i <= stack->top; i++) {
        printf("%d ", stack->data[i]);
    }
    printf("\n");
}

int main() {
    Stack stack;
    initStack(&stack);

    push(&stack, 10);
    push(&stack, 20);
    push(&stack, 30);

    printf("Stack elements: ");
    display(&stack);

    printf("Popped element: %d\n", pop(&stack));
    printf("Stack elements after pop: ");
    display(&stack);

    return 0;
}
```

---

### Implement Stack using Linked List

#### Algorithm:
1. Initialize the head to NULL.
2. Push: Create a new node and make it the new head.
3. Pop: Remove the head and return its value.
4. Display: Traverse the list from head to the end.

#### C Program:
```c
#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node* next;
} Node;

Node* push(Node* top, int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = top;
    return newNode;
}

Node* pop(Node* top, int* poppedData) {
    if (top == NULL) {
        printf("Stack underflow\n");
        return NULL;
    }
    Node* temp = top;
    *poppedData = top->data;
    top = top->next;
    free(temp);
    return top;
}

void display(Node* top) {
    if (top == NULL) {
        printf("Stack is empty\n");
        return;
    }
    Node* temp = top;
    while (temp != NULL) {
        printf("%d -> ", temp->data);
        temp = temp->next;
    }
    printf("NULL\n");
}

int main() {
    Node* top = NULL;
    int poppedData;

    top = push(top, 10);
    top = push(top, 20);
    top = push(top, 30);

    printf("Stack elements: ");
    display(top);

    top = pop(top, &poppedData);
    printf("Popped element: %d\n", poppedData);
    printf("Stack elements after pop: ");
    display(top);

    return 0;
}
```

---

### Create a Table "Employee"

#### Table Structure:
| Field     | Data Type |
|-----------|------------|
| EmpID     | INT        |
| EName     | VARCHAR(50)|
| Salary    | FLOAT      |
| Department| VARCHAR(50)|
| Age       | INT        |

#### SQL Statements:

```sql
-- Create the Employee table
CREATE TABLE Employee (
    EmpID INT PRIMARY KEY,
    EName VARCHAR(50),
    Salary FLOAT,
    Department VARCHAR(50),
    Age INT
);

-- Insert some records
INSERT INTO Employee (EmpID, EName, Salary, Department, Age) VALUES
(1, 'John Doe', 50000, 'Sales', 30),
(2, 'Jane Smith', 60000, 'HR', 45),
(3, 'Alice Johnson', 55000, 'IT', 28),
(4, 'Bob Brown', 70000, 'Sales', 50),
(5, 'Charlie Davis', 45000, 'HR', 38);

-- Display the total number of employees
SELECT COUNT(*) AS TotalEmployees FROM Employee;

-- Display the name and age of the oldest employee of each department
SELECT EName, Age, Department
FROM Employee
WHERE (Department, Age) IN (
    SELECT Department, MAX(Age)
    FROM Employee
    GROUP BY Department
);

-- Display the average age of employees of each department
SELECT Department, AVG(Age) AS AverageAge
FROM Employee
GROUP BY Department;

-- Display departments and the average salaries
SELECT Department, AVG(Salary) AS AverageSalary
FROM Employee
GROUP BY Department;

-- Display the lowest salary in the employee table
SELECT MIN(Salary) AS LowestSalary FROM Employee;

-- Display the highest salary in the Sales department
SELECT MAX(Salary) AS HighestSalesSalary
FROM Employee
WHERE Department = 'Sales';
```

---

### Create a Table "Product"

#### Table Structure:
| Field       | Data Type   |
|-------------|-------------|
| Product_code| INT         |
| Product_Name| VARCHAR(50) |
| Category    | VARCHAR(50) |
| Quantity    | INT         |
| Price       | FLOAT       |

#### SQL Statements:

```sql
-- Create the Product table
CREATE TABLE Product (
    Product_code INT PRIMARY KEY,
    Product_Name VARCHAR(50),
    Category VARCHAR(50),
    Quantity INT,
    Price FLOAT
);

-- Insert some records
INSERT INTO Product (Product_code, Product_Name, Category, Quantity, Price) VALUES
(1, 'Shampoo', 'bathsoap', 120, 25),
(2, 'Toothpaste', 'paste', 200, 15),
(3, 'Washing Powder', 'washingpowder', 90, 30),
(4, 'Soap', 'bathsoap', 300, 20),
(5, 'Handwash', 'bathsoap', 150, 50);

-- Display the records in the descending order of Product_Name
SELECT * FROM Product
ORDER BY Product_Name DESC;

-- Display Product_Code, Product_Name with price between 20 and 50
SELECT Product_code, Product_Name
FROM Product
WHERE Price BETWEEN 20 AND 50;

-- Display the details of products which belong to the categories of 'bathsoap', 'paste', or 'washingpowder'
SELECT * FROM Product
WHERE Category IN ('bathsoap', 'paste', 'washingpowder');

-- Display the products whose Quantity is less than 100 or greater than 500
SELECT * FROM Product
WHERE Quantity < 100 OR Quantity > 500;

-- Display the products whose names start with 's'
SELECT * FROM Product
WHERE Product_Name LIKE 's%';

-- Display the products which do not belong to the category 'paste'
SELECT * FROM Product
WHERE Category <> 'paste';
```

---

### Program to Calculate the Area of a Circle and Store in a Table

#### SQL and Program Code:

```sql
-- Create the Areas table
CREATE TABLE Areas (
    Radius FLOAT,
    Area FLOAT
);

-- Program code in a procedural language (e.g., PL/SQL, T-SQL) to calculate area
DECLARE
    radius FLOAT;
    area FLOAT;
BEGIN
    FOR radius IN 3..7 LOOP
        area := 3.14 * radius * radius;
        INSERT INTO Areas (Radius, Area) VALUES (radius, area);
    END LOOP;
END;
```

---

### Program Block to Calculate Electricity Bill

#### SQL and Program Code:

```sql
-- Assuming a table named Electricity_Bill to store customer details and bill amount
CREATE TABLE Electricity_Bill (
    Cust_no INT,
    Units_Consumed INT,
    Bill_Amount FLOAT
);

-- Program block to calculate the electricity bill
DECLARE
    cust_no INT;
    units_consumed INT;
    bill_amount FLOAT;
BEGIN
    -- Accepting customer number and units consumed
    cust_no := :cust_no;
    units_consumed := :units_consumed;
    
    -- Calculating bill amount based on some rate, e.g., 5 per unit
    bill_amount := units_consumed * 5;
    
    -- Inserting the bill details into the table
    INSERT INTO Electricity_Bill (Cust_no, Units_Consumed, Bill_Amount) VALUES (cust_no, units_consumed, bill_amount);
END;
```

---

### Procedure to Print Fibonacci Numbers up to a Limit

#### SQL and Program Code:

```sql
-- Create a procedure to print Fibonacci numbers up to a limit
CREATE PROCEDURE PrintFibonacci (limit INT)
BEGIN
    DECLARE a INT DEFAULT 0;
    DECLARE b INT DEFAULT 1;
    DECLARE c INT;
    
    WHILE a <= limit DO
        -- Printing the Fibonacci number
        SELECT a;
        
        -- Generating the next Fibonacci number
        c := a + b;
        a := b;
        b := c;
    END WHILE;
END;
```

# Questions 

### Part A: Data Structures

1. **Implement Pattern Matching Algorithm:**
   - Draw a flow chart or write an algorithm for the pattern matching problem.
   - Write a C program to implement the pattern matching algorithm.
   - Display the output of the program.

2. **Append Two Arrays:**
   - Draw a flow chart or write an algorithm to append two arrays.
   - Write a C program to append two arrays.
   - Display the output of the program.

3. **Merge Two Sorted Arrays into One Sorted Array:**
   - Draw a flow chart or write an algorithm to merge two sorted arrays.
   - Write a C program to merge two sorted arrays into one sorted array.
   - Display the output of the program.

4. **Create a Singly Linked List of N Nodes and Display It:**
   - Draw a flow chart or write an algorithm to create a singly linked list.
   - Write a C program to create a singly linked list of N nodes.
   - Display the linked list.

5. **Implement Stack Using Linked List:**
   - Draw a flow chart or write an algorithm to implement a stack using a linked list.
   - Write a C program to implement a stack using a linked list.
   - Display the output of the program.

---

### Part B: RDBMS

1. **Create a Table `Hospital`:**
   - Draw the structure of the `Hospital` table with the fields (doctorid, doctorname, department, qualification, experience).
   - Write the SQL statements to:
     - Insert 5 records into the `Hospital` table.
     - Display the details of all doctors.
     - Display the details of doctors who have the qualification `MD`.
     - Display all doctors who have more than 5 years' experience but do not have the qualification `MD`.
     - Display the doctors in the `Skin` department.
     - Update the experience of the doctor with `doctorid = 'D003'` to 5.
     - Delete the doctor with `doctorid = 'D005'`.

2. **Create a Table `Product`:**
   - Draw the structure of the `Product` table with the fields (Product_code PRIMARY KEY, Product_Name, Category, Quantity, Price).
   - Write the SQL statements to:
     - Insert some records into the `Product` table.
     - Display the records in descending order of `Product_Name`.
     - Display `Product_Code` and `Product_Name` with price between 20 and 50.
     - Display the details of products that belong to the categories `bathsoap`, `paste`, or `washingpowder`.
     - Display the products whose quantity is less than 100 or greater than 500.
     - Display the products whose names start with 's'.
     - Display the products which do not belong to the category `paste`.

3. **Additional Tasks:**
   - Write a program block to calculate the electricity bill by accepting `cust_no` and `units_consumed`.
   - Create a function to check whether a given number is prime or not.
   - Create a procedure to print factorials of the numbers from 1 to 10.
  
   ---

# Answers
   Sure, Here are the details for the Data Structures tasks.

## Part A: Data Structures

### 1. Implement Pattern Matching Algorithm

#### Flow Chart / Algorithm

**Algorithm:**
1. Input the main string and the pattern string.
2. Loop through the main string.
3. For each position in the main string, check if the pattern matches.
4. If a match is found, return the position.
5. If no match is found, return -1.

#### C Program

```c
#include <stdio.h>
#include <string.h>

int patternMatching(char *text, char *pattern) {
    int textLen = strlen(text);
    int patternLen = strlen(pattern);

    for (int i = 0; i <= textLen - patternLen; i++) {
        int j;
        for (j = 0; j < patternLen; j++) {
            if (text[i + j] != pattern[j])
                break;
        }
        if (j == patternLen)
            return i; // Match found
    }
    return -1; // No match found
}

int main() {
    char text[] = "hello world";
    char pattern[] = "world";
    int result = patternMatching(text, pattern);

    if (result != -1)
        printf("Pattern found at index %d\n", result);
    else
        printf("Pattern not found\n");

    return 0;
}
```

### 2. Append Two Arrays

#### Flow Chart / Algorithm

**Algorithm:**
1. Input two arrays and their sizes.
2. Create a new array with size equal to the sum of the two input arrays.
3. Copy elements from the first array to the new array.
4. Copy elements from the second array to the new array.
5. Return the new array.

#### C Program

```c
#include <stdio.h>

void appendArrays(int *arr1, int size1, int *arr2, int size2, int *result) {
    for (int i = 0; i < size1; i++) {
        result[i] = arr1[i];
    }
    for (int i = 0; i < size2; i++) {
        result[size1 + i] = arr2[i];
    }
}

int main() {
    int arr1[] = {1, 2, 3};
    int arr2[] = {4, 5, 6};
    int size1 = sizeof(arr1) / sizeof(arr1[0]);
    int size2 = sizeof(arr2) / sizeof(arr2[0]);
    int result[size1 + size2];

    appendArrays(arr1, size1, arr2, size2, result);

    for (int i = 0; i < size1 + size2; i++) {
        printf("%d ", result[i]);
    }

    return 0;
}
```

### 3. Merge Two Sorted Arrays into One Sorted Array

#### Flow Chart / Algorithm

**Algorithm:**
1. Input two sorted arrays and their sizes.
2. Create a new array with size equal to the sum of the two input arrays.
3. Use two pointers to traverse both arrays.
4. Compare elements from both arrays and copy the smaller element to the new array.
5. If one array is exhausted, copy remaining elements from the other array.
6. Return the new array.

#### C Program

```c
#include <stdio.h>

void mergeSortedArrays(int *arr1, int size1, int *arr2, int size2, int *result) {
    int i = 0, j = 0, k = 0;
    while (i < size1 && j < size2) {
        if (arr1[i] <= arr2[j]) {
            result[k++] = arr1[i++];
        } else {
            result[k++] = arr2[j++];
        }
    }
    while (i < size1) {
        result[k++] = arr1[i++];
    }
    while (j < size2) {
        result[k++] = arr2[j++];
    }
}

int main() {
    int arr1[] = {1, 3, 5};
    int arr2[] = {2, 4, 6};
    int size1 = sizeof(arr1) / sizeof(arr1[0]);
    int size2 = sizeof(arr2) / sizeof(arr2[0]);
    int result[size1 + size2];

    mergeSortedArrays(arr1, size1, arr2, size2, result);

    for (int i = 0; i < size1 + size2; i++) {
        printf("%d ", result[i]);
    }

    return 0;
}
```

### 4. Create a Singly Linked List of N Nodes and Display It

#### Flow Chart / Algorithm

**Algorithm:**
1. Define a structure for the linked list node.
2. Create a function to add a node to the linked list.
3. Create a function to display the linked list.
4. In the main function, create the linked list and display it.

#### C Program

```c
#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *next;
};

void append(struct Node **head, int newData) {
    struct Node *newNode = (struct Node*)malloc(sizeof(struct Node));
    struct Node *last = *head;
    newNode->data = newData;
    newNode->next = NULL;

    if (*head == NULL) {
        *head = newNode;
        return;
    }

    while (last->next != NULL) {
        last = last->next;
    }
    last->next = newNode;
}

void displayList(struct Node *node) {
    while (node != NULL) {
        printf("%d -> ", node->data);
        node = node->next;
    }
    printf("NULL\n");
}

int main() {
    struct Node *head = NULL;
    int n, data;

    printf("Enter the number of nodes: ");
    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        printf("Enter data for node %d: ", i + 1);
        scanf("%d", &data);
        append(&head, data);
    }

    printf("Singly linked list: ");
    displayList(head);

    return 0;
}
```

### 5. Implement Stack Using Linked List

#### Flow Chart / Algorithm

**Algorithm:**
1. Define a structure for the stack node.
2. Create push and pop functions.
3. Create a function to display the stack.
4. In the main function, perform stack operations and display the stack.

#### C Program

```c
#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *next;
};

void push(struct Node **top, int newData) {
    struct Node *newNode = (struct Node*)malloc(sizeof(struct Node));
    if (!newNode) {
        printf("Stack overflow\n");
        return;
    }
    newNode->data = newData;
    newNode->next = *top;
    *top = newNode;
}

int pop(struct Node **top) {
    if (*top == NULL) {
        printf("Stack underflow\n");
        return -1;
    }
    struct Node *temp = *top;
    *top = (*top)->next;
    int popped = temp->data;
    free(temp);
    return popped;
}

void displayStack(struct Node *top) {
    while (top != NULL) {
        printf("%d -> ", top->data);
        top = top->next;
    }
    printf("NULL\n");
}

int main() {
    struct Node *stack = NULL;
    push(&stack, 10);
    push(&stack, 20);
    push(&stack, 30);

    printf("Stack: ");
    displayStack(stack);

    printf("Popped: %d\n", pop(&stack));
    printf("Stack after pop: ");
    displayStack(stack);

    return 0;
}
```

---

Sure, Here are the details for the DBMS tasks.

## Part B: RDBMS

### Hospital Table

#### Table Structure

```sql
CREATE TABLE Hospital (
    doctorid VARCHAR(5) PRIMARY KEY,
    doctorname VARCHAR(100),
    department VARCHAR(50),
    qualification VARCHAR(50),
    experience INT
);
```

#### SQL Statements

1. **Insert 5 Records:**

```sql
INSERT INTO Hospital (doctorid, doctorname, department, qualification, experience) VALUES
('D001', 'Dr. Smith', 'Cardiology', 'MD', 10),
('D002', 'Dr. John', 'Neurology', 'MD', 8),
('D003', 'Dr. Alice', 'Dermatology', 'MBBS', 5),
('D004', 'Dr. Rose', 'Skin', 'MD', 6),
('D005', 'Dr. James', 'Orthopedics', 'MBBS', 7);
```

2. **Display the Details of Doctors:**

```sql
SELECT * FROM Hospital;
```

3. **Display the Details of Doctors Who Have the Qualification `MD`:**

```sql
SELECT * FROM Hospital WHERE qualification = 'MD';
```

4. **Display All Doctors Who Have More Than 5 Years' Experience But Do Not Have the Qualification `MD`:**

```sql
SELECT * FROM Hospital WHERE experience > 5 AND qualification != 'MD';
```

5. **Display the Doctors in `Skin` Department:**

```sql
SELECT * FROM Hospital WHERE department = 'Skin';
```

6. **Update the Experience of Doctor with doctorid = 'D003' to 5:**

```sql
UPDATE Hospital SET experience = 5 WHERE doctorid = 'D003';
```

7. **Delete the Doctor with doctorid = 'D005':**

```sql
DELETE FROM Hospital WHERE doctorid = 'D005';
```

### Product Table

#### Table Structure

```sql
CREATE TABLE Product (
    Product_code VARCHAR(10) PRIMARY KEY,
    Product_Name VARCHAR(100),
    Category VARCHAR(50),
    Quantity INT,
    Price DECIMAL(10, 2)
);
```

#### SQL Statements

1. **Insert Some Records:**

```sql
INSERT INTO Product (Product_code, Product_Name, Category, Quantity, Price) VALUES
('P001', 'Bath Soap', 'bathsoap', 150, 25.00),
('P002', 'Toothpaste', 'paste', 100, 40.00),
('P003', 'Washing Powder', 'washingpowder', 200, 30.00),
('P004', 'Shampoo', 'haircare', 90, 50.00),
('P005', 'Conditioner', 'haircare', 60, 55.00);
```

2. **Display the Records in Descending Order of Product_Name:**

```sql
SELECT * FROM Product ORDER BY Product_Name DESC;
```

3. **Display Product_Code, Product_Name with Price Between 20 and 50:**

```sql
SELECT Product_code, Product_Name FROM Product WHERE Price BETWEEN 20 AND 50;
```

4. **Display the Details of Products Which Belong to the Categories of `bathsoap`, `paste`, or `washingpowder`:**

```sql
SELECT * FROM Product WHERE Category IN ('bathsoap', 'paste', 'washingpowder');
```

5. **Display the Products Whose Quantity is Less Than 100 or Greater Than 500:**

```sql
SELECT * FROM Product WHERE Quantity < 100 OR Quantity > 500;
```

6. **Display the Products Whose Names Start with 's':**

```sql
SELECT * FROM Product WHERE Product_Name LIKE 's%';
```

7. **Display the Products Which Do Not Belong to the Category 'paste':**

```sql
SELECT * FROM Product WHERE Category != 'paste';
```

### Additional Tasks

#### 1. Write a Program Block to Calculate the Electricity Bill by Accepting cust_no and units_consumed

#### SQL Function

```sql
CREATE FUNCTION calculateElectricityBill(cust_no INT, units_consumed INT)
RETURNS DECIMAL(10, 2)
BEGIN
    DECLARE bill DECIMAL(10, 2);
    
    IF units_consumed <= 100 THEN
        SET bill = units_consumed * 1.5;
    ELSEIF units_consumed <= 200 THEN
        SET bill = 100 * 1.5 + (units_consumed - 100) * 2.5;
    ELSE
        SET bill = 100 * 1.5 + 100 * 2.5 + (units_consumed - 200) * 3.5;
    END IF;
    
    RETURN bill;
END;
```

#### 2. Create a Function to Check Whether a Given Number is Prime or Not

#### SQL Function

```sql
CREATE FUNCTION isPrime(num INT)
RETURNS BOOLEAN
BEGIN
    DECLARE i INT;
    DECLARE flag BOOLEAN;
    SET i = 2;
    SET flag = TRUE;

    IF num < 2 THEN
        RETURN FALSE;
    END IF;

    WHILE i <= SQRT(num) DO
        IF num % i = 0 THEN
            SET flag = FALSE;
            LEAVE;
        END IF;
        SET i = i + 1;
    END WHILE;

    RETURN flag;
END;
```

#### 3. Create a Procedure to Print Factorials of the Numbers from 1 to 10

#### SQL Procedure

```sql
CREATE PROCEDURE printFactorials()
BEGIN
    DECLARE i INT;
    DECLARE fact INT;
    SET i = 1;

    WHILE i <= 10 DO
        SET fact = 1;
        DECLARE j INT;
        SET j = 1;

        WHILE j <= i DO
            SET fact = fact * j;
            SET j = j + 1;
        END WHILE;

        SELECT CONCAT('Factorial of ', i, ' is ', fact) AS Result;
        SET i = i + 1;
    END WHILE;
END;
```

# Discussion

**Document all error conditions you determined and why they are error
 conditions. Do this by including the inputs that you used to test your
  program and what error conditions they exposed:**

  1. Bad Token - there is an error if the user inputs something which is not an integer nor a valid operator or command
     Ex: input like a double (2.0), string ("cat"), or characters which are not commands or operators ("&")
  2. Empty Stack + User Enters '.' - the LinkedStack cannot return data if the top of the stack is null
     Ex: no data has been input yet the user commands "." to get the top data
  3. No Operators + Operator
  4. One Operator + Operator
  5. Integer Division by Zero
  6. Remainder Division by Zero
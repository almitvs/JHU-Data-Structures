# Discussion

**Document all error conditions you determined and why they are error
 conditions. Do this by including the inputs that you used to test your
  program and what error conditions they exposed:**

  1. Bad Token - the user inputs something which is not an integer nor a valid operator or command
     Ex: input like 2.0, "cat", or '&' cannot operate or be operated on as integers or invoke commands
  2. Empty Stack + User Fetches Top - the LinkedStack cannot return data via top() if the top of the stack is null
     Ex: no data has been input yet the user commands "." to get the top data
  3. No Operands + Operator - an operation cannot be performed on no numbers
     Ex: no data has been input yet the users commands "+", "-", "*", "/", or "%"
  4. One Operand + Operator - an operation cannot be performed on only one number
     Ex: the user has input only one integer (Ex: 1) yet the users commands "+", "-", "*", "/", or "%"
  5. Integer Division by Zero - an integer cannot be integer divided by zero, this is an arithmetic error
     Ex: the user commands "/" when the top of the stack is "0"
  6. Remainder Division by Zero - an integer cannot be remained divided by zero, this is an arithmetic error
     Ex: the user commands "%" when the top of the stack is "0"
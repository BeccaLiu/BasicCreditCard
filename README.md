# BasicCreditCard
Extract the core part of credit card provider , I can directly see two objects: User and Credit card

[User] has userName

[Credit card] has information about card number, user, balance, credit line, is credit card number pass Luhn 10 or not (getter and setter function for each variable)
and also credit card can provide two functions: credit and charge

Furthermore, I see there is a need for a controller to manage all the process, I call it CreditCardCenter
[Credit Card Center]it can accept a String array and intelligently called different function to fulfill the process like create new Card, charge, and credit and get the summary info of credit card in this center
as we only have one Credit card center, I will apply singleton pattern to Credit card center

Handle Multithreaded: I using synchronized keyword to make sure getInstance of a Credit card center and credit and charge the credit card is thread safe

System Design: when requirement explode, and when more users are using this platform, will extract add, credit, charge to different server
CreditCardCenter serve as Master server to receive the request, and after diagnosing the request, will distribute the process to different process server(eg. Add process Server, credit process Server)


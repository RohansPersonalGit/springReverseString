# springReverseString
Thought Process
So I started out with a short refresh of Spring, luckily some of it was still familiar to me and I was able to get started quickly as my past experiences came to mind while installing maven.  https://spring.io/guides/gs/rest-service/ this link had basically everything I needed to get started. After I had got started and was able to make some basic request through postman so that I knew my server was running I got started on tests. I found this tutorial that helped a lot https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/. As I was writing these tests I started making some of the below considerations like URL request character limit and response format. I have some further discussions of these choices below, but basically I went with the most intuitive solution that fit for most users. 

Considerations made 

What Java version to use 

•	I used Java 11 main reasons for this is, because it used in the documentation I was looking and it is not the newest nor the oldest version of java. Meaning that I can years of documentation and stability updates while not worrying about deprecation anytime soon. 

Jar vs War file

•	I chose Jar file, because it has more flexibility and can be run from terminal rather then additional server

URL request character limit

•I said it at 2000 which although is low compared to the address bar browser limit length of 32000 on chrome, it is just below IE 16’s limit of 2047 and I felt it was best to cater this to all users. Past that there aren’t many reasons why a url request path should be that long could be difficult for users to interact with. In this case I opted to return a 400 code with a helpful message. 

Response format

•I just did it in text/plain for any actual service or backend I would use application/json, but for the purposes of this I felt text/plain was easier to read as a user during postman testing. 

Seperate RestControllerClass

*Although I know it is unlikely that I will ever return to this application I like to make a seperate class for different purposes. If in the future I wanted to add security considerations or anything to the application it would be easier to do so in a seperate file I think then to have the reverse string and security logic in the same file. 